package com.project.web.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.appinterface.domain.GiftGoods;
import com.project.appinterface.domain.GiftLocation;
import com.project.appinterface.domain.SysAreas;
import com.project.appinterface.mapper.CommonInterfaceMapper;
import com.project.appinterface.mapper.GiftGoodsMapper;
import com.project.appinterface.mapper.GiftLocationMapper;
import com.project.appinterface.service.GiftMachineService;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.common.support.Convert;
import com.project.framework.util.ShiroUtils;
import com.project.util.LatticeUtil;
import com.project.util.UUIDUtil;
import com.project.web.domain.TGift;
import com.project.web.domain.TGiftApply;
import com.project.web.domain.TGiftModel;
import com.project.web.domain.TPayOrder;
import com.project.web.domain.TUserInfo;
import com.project.web.domain.TWallet;
import com.project.web.domain.vo.TGiftParam;
import com.project.web.domain.vo.TGiftVo;
import com.project.web.mapper.TGiftApplyMapper;
import com.project.web.mapper.TGiftMapper;
import com.project.web.mapper.TGiftModelMapper;
import com.project.web.mapper.TPayOrderMapper;
import com.project.web.mapper.TUserInfoMapper;
import com.project.web.mapper.TWalletMapper;

/**
 * 礼物机 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TGiftServiceImpl implements ITGiftService 
{
	@Autowired
	private TGiftMapper tGiftMapper;

	@Autowired
	private TGiftApplyMapper tGiftApplyMapper;

	@Autowired
	private TUserInfoMapper userInfoMapper;

	@Autowired
	private TPayOrderMapper tPayOrderMapper;

	@Autowired
	private TWalletMapper twalletMapper;

	@Autowired
	private CommonInterfaceMapper commonInterfaceMapper;
	
	@Autowired
	private GiftGoodsMapper giftGoodsMapper;
	@Autowired
	private TGiftModelMapper tGiftModelMapper;
	@Autowired
	private GiftLocationMapper giftLocationMapper;
	@Autowired
	GiftMachineService giftMachineService;
	
	/**
     * 查询礼物机信息
     * 
     * @param id 礼物机ID
     * @return 礼物机信息
     */
    @Override
	public TGift selectTGiftById(String id)
	{
		TGift tGift = tGiftMapper.selectTGiftById(id);

		TGiftApply tGiftApply = new TGiftApply();
		tGiftApply.setGiftId(tGift.getId());
		tGiftApply.setState("1");
		List<TGiftApply> tGiftApplies = tGiftApplyMapper.selectTGiftApplyBYList(tGiftApply);

		// 计算天数
		Integer number = null;
		if(tGiftApplies.size() != 0) {
			//  天数
			if (tGiftApplies.get(0).getTimeType().equals("0")) {
				number = tGiftApplies.get(0).getNumber();
			} else {
				number = tGiftApplies.get(0).getNumber() * 30;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = tGift.getCreateDate();
			Date date1 = new Date();
			String format = simpleDateFormat.format(date);
			Date parse = null;
			String format1 = simpleDateFormat.format(date1);
			Date parse1 = null;
			try {
				parse = simpleDateFormat.parse(format);
				parse1 = simpleDateFormat.parse(format1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// 当前时间 减去 申请天数
			long l = (parse1.getTime() - parse.getTime()) / (24 * 60 * 60 * 1000);
			long l1 = number - l;
			if (l1 < 0) {
				tGift.setTimeType(0 + "天");
			} else {
				tGift.setTimeType(l1 + "天");
			}
			//  礼物金额
			for(int i=0; i< tGiftApplies.size(); i++) {
				if(tGiftApplies.get(i).getOrderno() != null) {
					TPayOrder tPayOrder = tPayOrderMapper.selectTPayOrderByOrderNo(tGiftApplies.get(i).getOrderno());
					Long latticePrice = tPayOrder.getMoney() / 100;
					//  格子单价
					tGift.setPrice("￥" + latticePrice);
				}
			}
			//  租赁类型
			if("0".equals(tGiftApplies.get(0).getTimeType())){
				tGift.setLng("日租");
			}else {
				tGift.setLng("月租");
			}
			//  格子数量
			tGift.setPictureUrl(tGiftApplies.get(0).getNumber() + "/" + tGift.getModel());
		}

		// 占用人
		TUserInfo tUserInfo = userInfoMapper.selectTUserInfoById(tGift.getUserId());
		//  押金
		TWallet tWallet = twalletMapper.selectTWalletByUserId(tGift.getUserId());
		float deposit = tWallet.getDeposit() / 100;
		tGift.setSource("￥" + deposit);
		tGift.setUserId(tUserInfo.getNickname());
	    return tGift;
	}

	/**
	 * 查询礼物机类型
	 *
	 * @return 礼物机所有类型
	 */
	@Override
	public List<TGift> selectTGiftListModel() {
		return tGiftMapper.selectTGiftListModel();
	}


	/**
     * 查询礼物机列表
     * 
     * @param tGift 礼物机信息
     * @return 礼物机集合
     */
	@Override
	public List<TGiftVo> selectTGiftList(TGift tGift)
	{
		List<TGiftVo> tGifts = tGiftMapper.selectTGiftList(tGift);
		if(CollectionUtils.isNotEmpty(tGifts)){
			for(TGiftVo tGiftVo:tGifts){
				Integer dayUsed = tGiftVo.getDayUsed();
				Integer dayTotalNumber = tGiftVo.getDayTotalNumber();
				if(dayUsed != null && dayTotalNumber != null){
					if(dayTotalNumber.intValue() >= dayUsed.intValue()){
						tGiftVo.setDayNumber(dayTotalNumber.intValue() - dayUsed.intValue());
					}else{
						tGiftVo.setDayNumber(0);
					}
				}
			}
		}
		return tGifts;
	}
	
    /**
     * 新增礼物机
     * 
     * @param tGift 礼物机信息
     * @return 结果
     */
	@Override
	public int insertTGift(TGift tGift)
	{
		try {
			tGift.setId(UUIDUtil.getUUID());
			tGift.setCreateDate(new Date());
			tGift.setCreateUser(ShiroUtils.getUserId().toString());
			tGift.setState("0");
			if(StringUtils.isNotEmpty(tGift.getLocationName())){
				SysAreas sysAreas = commonInterfaceMapper.getAddressById(tGift.getLocationName());
				tGift.setLng(sysAreas.getLng());
				tGift.setLat(sysAreas.getLat());
				TGift gift = tGiftMapper.selectTGiftByLngLat(tGift.getLng(),tGift.getLat());
				if(gift != null) {
					//重复申请
					return 2;
				}
			}
//			if(file != null && !"".equals(file.getOriginalFilename())){
//				String fileName = FileUploadUtils.upload(file);
//				tGift.setPictureUrl(fileName);
//			}
			return tGiftMapper.insertTGift(tGift);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	@Transactional
	public int updateTGiftState(TGift tGift) {
		try {
			String giftId = tGift.getId();
			TGiftApply tGiftApply = tGiftApplyMapper.selectGiftApplyByGiftIdAndState(giftId);
			DataResult result = giftMachineService.refundDeposit(giftId, tGiftApply.getUserId());
			if(Result.SUCCESS.equals(result.getStatus())) {
				return tGiftMapper.updateTGiftState(tGift);
			}else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
     * 修改礼物机
     * 
     * @param tGift 礼物机信息
     * @return 结果
     */
	@Override
	public int updateTGift(TGift tGift)
	{
	    return tGiftMapper.updateTGift(tGift);
	}

	/**
     * 删除礼物机对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTGiftByIds(String ids)
	{
		return tGiftMapper.deleteTGiftByIds(Convert.toStrArray(ids));
	}

	@Override
	@Transactional
	public DataResult updateTGiftGoodsInfo(TGiftParam tGiftParam) {
		DataResult result=new DataResult();
		try {
			String giftId = tGiftParam.getGiftId();
			List<TGiftVo> tGiftVos = tGiftMapper.selectTGiftsByGiftId(giftId);
			if(CollectionUtils.isNotEmpty(tGiftVos)) {
				result.setMessage("已经有人申请该礼物机");
				result.setStatus(Result.FAILED);
				return result;
			}
			String sysUserId = ShiroUtils.getUserId().toString();
			Date now = new Date();
			TGift tGift = new TGift();
			tGift.setId(giftId);
			tGift.setModel(tGiftParam.getModel());
			tGift.setModelName(tGiftParam.getModelName());
			tGift.setLocationName(tGiftParam.getLocationName());
			if (StringUtils.isNotEmpty(tGift.getLocationName())) {
				SysAreas sysAreas = commonInterfaceMapper.getAddressById(tGift.getLocationName());
				tGift.setLng(sysAreas.getLng());
				tGift.setLat(sysAreas.getLat());
			}
			tGift.setState("1");
			tGift.setUpdateDate(now);
			tGift.setSource("0");//平台
			tGift.setUserId(sysUserId);
			tGiftMapper.updateTGift(tGift);
			TGiftApply tGiftApply = new TGiftApply();
			String giftApplyId = UUIDUtil.getUUID();
			tGiftApply.setId(giftApplyId);
			tGiftApply.setGiftId(giftId);
			tGiftApply.setTimeType(tGiftParam.getTimeType());
			tGiftApply.setNumber(tGiftParam.getNumber());
			tGiftApply.setUserId(sysUserId);
			tGiftApply.setCreateDate(now);
			tGiftApply.setState("1");
			tGiftApply.setIntroduce(tGiftParam.getModelName());
			tGiftApply.setLatticePrice(tGiftParam.getLatticePrice());
			tGiftApplyMapper.insertTGiftApply(tGiftApply);
			String[] goodsId = tGiftParam.getGoodsId();
			int[] goodsNumber = tGiftParam.getGoodsNumber();
			Long[] goodsPrice = tGiftParam.getGoodsPrice();
			if (goodsId != null && goodsId.length > 0 && goodsNumber != null && goodsNumber.length > 0
					&& goodsNumber.length == goodsId.length) {
				List<GiftGoods> list = new ArrayList<GiftGoods>();
				for (int i = 0; i < goodsId.length; i++) {
					String goods_id = goodsId[i];
					if (StringUtils.isNotEmpty(goods_id)) {
						for (int j = 0; j < goodsNumber[i]; j++) {
							GiftGoods giftGoods = new GiftGoods();
							giftGoods.setId(UUIDUtil.getUUID());
							giftGoods.setGiftId(giftId);
							giftGoods.setGoodsId(goods_id);
							giftGoods.setState("0");
							giftGoods.setGiftApplyId(giftApplyId);
							giftGoods.setPrice(goodsPrice[i]);
							list.add(giftGoods);
						}
					}
				}
				if (CollectionUtils.isNotEmpty(list)) {
					giftGoodsMapper.insertGiftGoodsBatch(list);
					TGiftModel tGiftModel = tGiftModelMapper.selectTGiftModelById(tGift.getModel());
					GiftLocation giftLocation = new GiftLocation();
					giftLocation.setId(UUIDUtil.getUUID());
					int[] arr = LatticeUtil.getlLatticePosition(tGiftModel.getLatticeNum(), list.size());
					String content = Arrays.toString(arr);
					content = content.replace(" ", "");
					content = content.substring(1, content.length() - 1);
					giftLocation.setContent(content);
					giftLocation.setCreateDate(new Date());
					giftLocation.setGiftId(tGiftApply.getGiftId());
					giftLocation.setCreateUser(tGiftApply.getUserId());
					giftLocation.setState("0");
					giftLocation.setSurplusPosition(tGiftModel.getLatticeNum());
					giftLocation.setTotalPosition(tGiftModel.getLatticeNum());
					giftLocation.setLatticePrice(tGiftApply.getLatticePrice());
					giftLocationMapper.insertGiftLocation(giftLocation);
					result.setMessage("补充礼物机成功");
					result.setStatus(Result.SUCCESS);
					return result;
				}
			}
			result.setMessage("补充礼物机失败");
			result.setStatus(Result.FAILED);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public TGiftVo selectTGiftVoById(String id) {
		TGiftVo tGiftVo = tGiftMapper.selectTGiftVoById(id);
		Integer dayUsed = tGiftVo.getDayUsed();
		Integer dayTotalNumber = tGiftVo.getDayTotalNumber();
		if(dayUsed != null && dayTotalNumber != null){
			if(dayTotalNumber.intValue() >= dayUsed.intValue()){
				tGiftVo.setDayNumber(dayTotalNumber.intValue() - dayUsed.intValue());
			}else{
				tGiftVo.setDayNumber(0);
			}
		}
		return tGiftVo;
	}

	@Override
	public TGift selectTGiftByGiftId(String giftId) {
		return tGiftMapper.selectTGiftById(giftId);
	}
	
}

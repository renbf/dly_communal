package com.project.web.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.appinterface.domain.GiftGoods;
import com.project.appinterface.domain.GiftLocation;
import com.project.appinterface.mapper.GiftGoodsMapper;
import com.project.appinterface.mapper.GiftLocationMapper;
import com.project.common.support.Convert;
import com.project.util.LatticeUtil;
import com.project.util.UUIDUtil;
import com.project.web.domain.GiftApplyVo;
import com.project.web.domain.TGift;
import com.project.web.domain.TGiftApply;
import com.project.web.domain.TGiftModel;
import com.project.web.domain.TPayOrder;
import com.project.web.domain.TWallet;
import com.project.web.mapper.TGiftApplyMapper;
import com.project.web.mapper.TGiftMapper;
import com.project.web.mapper.TGiftModelMapper;
import com.project.web.mapper.TPayOrderMapper;
import com.project.web.mapper.TWalletMapper;

/**
 * 礼物机申请 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TGiftApplyServiceImpl implements ITGiftApplyService 
{
	@Autowired
	private TGiftApplyMapper tGiftApplyMapper;

	@Autowired
	private TPayOrderMapper tPayOrderMapper;
	@Autowired
	private GiftLocationMapper giftLocationMapper;
	
	@Autowired
	private TGiftMapper tGiftMapper;
	
	@Autowired
	private TGiftModelMapper tGiftModelMapper;
	
	@Autowired
	private GiftGoodsMapper giftGoodsMapper;
	
	@Autowired
	private TWalletMapper tWalletMapper;
	/**
     * 查询礼物机申请信息
     * 
     * @param id 礼物机申请ID
     * @return 礼物机申请信息
     */
    @Override
	public TGiftApply selectTGiftApplyById(String id)
	{
	    return tGiftApplyMapper.selectTGiftApplyById(id);
	}

	@Override
	public TGiftApply selectTGiftApplyByTGiftId(String giftId)
	{
		return tGiftApplyMapper.selectTGiftApplyByTGiftId(giftId);
	}

	/**
     * 查询礼物机申请列表
     * 
     * @param giftApplyVo 礼物机申请信息
     * @return 礼物机申请集合
     */
	@Override
	public List<GiftApplyVo> selectTGiftApplyList(GiftApplyVo giftApplyVo)
	{
		List<GiftApplyVo> gv=tGiftApplyMapper.selectTGiftApplyList(giftApplyVo);
	    return gv;
	}

	@Override
	public List<TGiftApply> selectTGiftApplyBYList(TGiftApply tGiftApply) {
		List<TGiftApply> tGiftApplies = tGiftApplyMapper.selectTGiftApplyBYList(tGiftApply);
		// 计算天数
		Integer number = null;
		if(tGiftApplies.size() != 0) {
			//  礼物金额
			for(int i=0; i< tGiftApplies.size(); i++) {
				if(tGiftApplies.get(i).getOrderno() != null) {
					TPayOrder tPayOrder = tPayOrderMapper.selectTPayOrderByOrderNo(tGiftApplies.get(i).getOrderno());

					Long latticePrice = tPayOrder.getMoney() / 100;
					//  礼物价格
					Integer number1 = tGiftApplies.get(i).getNumber();
					long l2 = latticePrice * number1;
					tGiftApplies.get(i).setUserId("￥" + l2);
				}
			}
		}
		return tGiftApplies;
	}

	@Override
	public List<TGiftApply> selectTGiftApplyIntroduce(String giftId) {
		List<TGiftApply> tGiftApplies = tGiftApplyMapper.selectTGiftApplyIntroduce(giftId);

		for (int i = 0; i < tGiftApplies.size(); i++){

			for(int j = 0; j < tGiftApplies.size(); j++){
				if(i != j) {
					if (tGiftApplies.get(i).getIntroduce().equals(tGiftApplies.get(j).getIntroduce())) {
						tGiftApplies.remove(j);
					}
				}
			}

		}
		for(int j = 0; j < tGiftApplies.size(); j++){
			tGiftApplies.get(j).setId(j+1 +"");
			Long latticePrice = tGiftApplies.get(j).getLatticePrice();
			latticePrice /= 100;
			tGiftApplies.get(j).setOrderno("￥" + latticePrice + ".00");
		}

		return tGiftApplies;
	}

	/**
     * 新增礼物机申请
     * 
     * @param tGiftApply 礼物机申请信息
     * @return 结果
     */
	@Override
	public int insertTGiftApply(TGiftApply tGiftApply)
	{
		tGiftApply.setState("1");
		String introduce = tGiftApply.getIntroduce();
		String[] split = introduce.split(",");
		int i1 = 0;
		for(int i =0; i < split.length; i++){
			tGiftApply.setIntroduce(split[i]);
			i1 = tGiftApplyMapper.insertTGiftApply(tGiftApply);
		}

		return i1;
	}
	
	/**
     * 修改礼物机申请
     * 
     * @param tGiftApply 礼物机申请信息
     * @return 结果
     */
	@Override
	public int updateTGiftApply(TGiftApply tGiftApply)
	{
	    return tGiftApplyMapper.updateTGiftApply(tGiftApply);
	}

	/**
     * 删除礼物机申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTGiftApplyByIds(String ids)
	{
		return tGiftApplyMapper.deleteTGiftApplyByIds(Convert.toStrArray(ids));
	}

	@Override
	@Transactional
	public int examine(String id,String state) {
		try {
			TGiftApply tGiftApply = tGiftApplyMapper.selectTGiftApplyById(id);
			tGiftApply.setState(state);
			int l = tGiftApplyMapper.updateTGiftApply(tGiftApply);
			if(l > 0){
				//审核通过
				if("1".equals(state)){
					String userId = tGiftApply.getUserId();
					TGift tGift = tGiftMapper.selectTGiftById(tGiftApply.getGiftId());
					tGift.setState("1");
					tGift.setUserId(userId);
					//后台审核通过的来源我认为都是用户
					tGift.setSource("1");
					tGift.setUpdateDate(new Date());
					tGiftMapper.updateTGift(tGift);
					GiftGoods giftGoods = new GiftGoods();
					giftGoods.setGiftId(tGiftApply.getGiftId());
					List<GiftGoods> GiftGoods = giftGoodsMapper.selectGiftGoodsList(giftGoods);
					TGiftModel tGiftModel = tGiftModelMapper.selectTGiftModelById(tGift.getModel());
					GiftLocation giftLocation = new GiftLocation();
					giftLocation.setId(UUIDUtil.getUUID());
					int[] arr = LatticeUtil.getlLatticePosition(tGiftModel.getLatticeNum(), GiftGoods.size());
					String content = Arrays.toString(arr);
					content = content.replace(" ", "");
					content = content.substring(1, content.length()-1);
					giftLocation.setContent(content);
					giftLocation.setCreateDate(new Date());
					giftLocation.setGiftId(tGiftApply.getGiftId());
					giftLocation.setCreateUser(userId);
					giftLocation.setState("0");
					giftLocation.setSurplusPosition(tGiftModel.getLatticeNum());
					giftLocation.setTotalPosition(tGiftModel.getLatticeNum());
					giftLocation.setLatticePrice(tGiftApply.getLatticePrice());
					giftLocationMapper.insertGiftLocation(giftLocation);
					//其他申请当前礼物机的人拒绝
					List<TGiftApply> tGiftApplys = tGiftApplyMapper.selectTGiftApplyIntroduce(tGiftApply.getGiftId());
					if(CollectionUtils.isNotEmpty(tGiftApplys)){
						for(TGiftApply giftApply:tGiftApplys){
							if(!userId.equals(giftApply.getUserId())){
								refundToWallet(giftApply);
							}
						}
					}
				}else{
					//拒绝
					refundToWallet(tGiftApply);
				}
				return 1;
			}else{
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	private void refundToWallet(TGiftApply tGiftApply) throws Exception{
		//拒绝
		String userId = tGiftApply.getUserId();
		TPayOrder tPayOrder = tPayOrderMapper.selectTPayOrderByOrderNo(tGiftApply.getOrderno());
		Long money = tPayOrder.getMoney();
		TWallet wallet = tWalletMapper.selectTWalletByUserId(userId);
		TWallet tWallet = new TWallet();
		tWallet.setUserId(userId);
		if(wallet != null){
			Long banlance = wallet.getBalance();
			banlance = banlance + money;
			tWallet.setBalance(banlance);
			tWalletMapper.updateTWallet(tWallet);
		}else{
			tWallet.setId(UUIDUtil.getUUID());
			tWallet.setBalance(money);
			tWalletMapper.insertTWallet(tWallet);
		}
	}
}

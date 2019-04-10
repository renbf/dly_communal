package com.project.web.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appinterface.util.kuaidi.Kuaidi100Util;
import com.project.appinterface.util.kuaidi.pojo.pojo.KuaidiResult;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.common.support.Convert;
import com.project.web.domain.OrderVo;
import com.project.web.domain.TOrder;
import com.project.web.mapper.TOrderMapper;

/**
 * 订单 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TOrderServiceImpl implements ITOrderService 
{
	@Autowired
	private TOrderMapper tOrderMapper;

	/**
     * 查询订单信息
     * 
     * @param id 订单ID
     * @return 订单信息
     */
    @Override
	public TOrder selectTOrderById(String id)
	{
	    return tOrderMapper.selectTOrderById(id);
	}

	/**
     * 查询订单列表
     * 
     * @param tOrder 订单信息
     * @return 订单集合
     */
	@Override
	public List<OrderVo> selectTOrderList(TOrder tOrder)
	{
	    return tOrderMapper.selectTOrderAllList(tOrder);
	}
	
    /**
     * 新增订单
     * 
     * @param tOrder 订单信息
     * @return 结果
     */
	@Override
	public int insertTOrder(TOrder tOrder)
	{
	    return tOrderMapper.insertTOrder(tOrder);
	}
	
	/**
     * 修改订单
     * 
     * @param tOrder 订单信息
     * @return 结果
     */
	@Override
	public DataResult updateTOrder(TOrder tOrder)
	{
		DataResult result = new DataResult();
		try {
			String companyCode = tOrder.getCompanyCode();
			String expressNo = tOrder.getExpressNo();
			if (StringUtils.isEmpty(companyCode) || StringUtils.isEmpty(expressNo)) {
				result.setStatus(Result.FAILED);
				result.setMessage("参数不能为空");
				return result;
			}
			KuaidiResult kuaidiResult = Kuaidi100Util.getString(companyCode, expressNo);
			if (kuaidiResult == null) {
				result.setStatus(Result.FAILED);
				result.setMessage("请核实");
				return result;
			}
			tOrder.setState("1");
			//发货时间
			tOrder.setDeliveryDate(new Date());
			tOrderMapper.updateTOrder(tOrder);
			result.setStatus(Result.SUCCESS);
			result.setMessage("发货成功");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(Result.FAILED);
			result.setMessage("发货失败");
			return result;
		}
	}

	/**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTOrderByIds(String ids)
	{
		return tOrderMapper.deleteTOrderByIds(Convert.toStrArray(ids));
	}

	@Override
	public OrderVo selectTOrderVoDetail(String id) {
		return tOrderMapper.selectTOrderVoDetail(id);
	}
	
}

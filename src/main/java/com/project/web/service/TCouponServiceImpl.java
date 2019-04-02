package com.project.web.service;

import java.util.List;

import com.project.web.domain.TCoupon;
import com.project.web.mapper.TCouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.support.Convert;

/**
 * 优惠券 服务层实现
 * 
 * @author lws
 * @date 2019-03-13
 */
@Service
public class TCouponServiceImpl implements ITCouponService 
{
	@Autowired
	private TCouponMapper tCouponMapper;

	/**
     * 查询优惠券信息
     * 
     * @param id 优惠券ID
     * @return 优惠券信息
     */
    @Override
	public TCoupon selectTCouponById(String id)
	{
	    return tCouponMapper.selectTCouponById(id);
	}
	
	/**
     * 查询优惠券列表
     * 
     * @param tCoupon 优惠券信息
     * @return 优惠券集合
     */
	@Override
	public List<TCoupon> selectTCouponList(TCoupon tCoupon)
	{
	    return tCouponMapper.selectTCouponList(tCoupon);
	}
	
    /**
     * 新增优惠券
     * 
     * @param tCoupon 优惠券信息
     * @return 结果
     */
	@Override
	public int insertTCoupon(TCoupon tCoupon)
	{
	    return tCouponMapper.insertTCoupon(tCoupon);
	}
	
	/**
     * 修改优惠券
     * 
     * @param tCoupon 优惠券信息
     * @return 结果
     */
	@Override
	public int updateTCoupon(TCoupon tCoupon)
	{
	    return tCouponMapper.updateTCoupon(tCoupon);
	}

	/**
     * 删除优惠券对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTCouponByIds(String ids)
	{
		return tCouponMapper.deleteTCouponByIds(Convert.toStrArray(ids));
	}
	
}

package com.project.web.service;

import com.project.web.domain.TCoupon;

import java.util.List;

/**
 * 优惠券 服务层
 * 
 * @author lws
 * @date 2019-03-13
 */
public interface ITCouponService 
{
	/**
     * 查询优惠券信息
     * 
     * @param id 优惠券ID
     * @return 优惠券信息
     */
	public TCoupon selectTCouponById(String id);
	
	/**
     * 查询优惠券列表
     * 
     * @param tCoupon 优惠券信息
     * @return 优惠券集合
     */
	public List<TCoupon> selectTCouponList(TCoupon tCoupon);
	
	/**
     * 新增优惠券
     * 
     * @param tCoupon 优惠券信息
     * @return 结果
     */
	public int insertTCoupon(TCoupon tCoupon);
	
	/**
     * 修改优惠券
     * 
     * @param tCoupon 优惠券信息
     * @return 结果
     */
	public int updateTCoupon(TCoupon tCoupon);
		
	/**
     * 删除优惠券信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTCouponByIds(String ids);
	
}

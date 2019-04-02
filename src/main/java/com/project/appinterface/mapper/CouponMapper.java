package com.project.appinterface.mapper;

import com.project.appinterface.domain.Coupon;

import java.util.List;

/**
 * 优惠券 数据层
 * 
 * @author lws
 * @date 2019-03-07
 */
public interface CouponMapper 
{
	/**
     * 查询优惠券信息
     * 
     * @param id 优惠券ID
     * @return 优惠券信息
     */
	public Coupon selectCouponById();
	
	/**
     * 查询优惠券列表
     * 
     * @param coupon 优惠券信息
     * @return 优惠券集合
     */
	public List<Coupon> selectCouponList(Coupon coupon);
	
	/**
     * 新增优惠券
     * 
     * @param coupon 优惠券信息
     * @return 结果
     */
	public int insertCoupon(Coupon coupon);
	
	/**
     * 修改优惠券
     * 
     * @param coupon 优惠券信息
     * @return 结果
     */
	public int updateCoupon(Coupon coupon);
	
	/**
     * 删除优惠券
     * 
     * @param id 优惠券ID
     * @return 结果
     */
	public int deleteCouponById(String id);
	
	/**
     * 批量删除优惠券
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCouponByIds(String[] ids);
	
}
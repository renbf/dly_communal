package com.project.appinterface.mapper;

import com.project.appinterface.domain.CouponReceive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券领取记录 数据层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface CouponReceiveMapper 
{
	/**
     * 查询优惠券领取记录信息
     * 
     * @param id 优惠券领取记录ID
     * @return 优惠券领取记录信息
     */
	public CouponReceive selectCouponReceiveById(@Param("id")String id);
	
	/**
     * 查询优惠券领取记录列表
     * 
     * @param couponReceive 优惠券领取记录信息
     * @return 优惠券领取记录集合
     */
	public List<CouponReceive> selectCouponReceiveList(CouponReceive couponReceive);
	
	/**
     * 新增优惠券领取记录
     * 
     * @param couponReceive 优惠券领取记录信息
     * @return 结果
     */
	public int insertCouponReceive(CouponReceive couponReceive);
	
	/**
     * 修改优惠券领取记录
     * 
     * @param couponReceive 优惠券领取记录信息
     * @return 结果
     */
	public int updateCouponReceive(CouponReceive couponReceive);
	
	/**
     * 删除优惠券领取记录
     * 
     * @param id 优惠券领取记录ID
     * @return 结果
     */
	public int deleteCouponReceiveById(String id);
	
	/**
     * 批量删除优惠券领取记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCouponReceiveByIds(String[] ids);
	
}
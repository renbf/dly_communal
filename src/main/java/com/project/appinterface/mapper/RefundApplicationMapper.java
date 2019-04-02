package com.project.appinterface.mapper;

import com.project.appinterface.domain.RefundApplication;

import java.util.List;

/**
 * 退款申请 数据层
 * 
 * @author lws
 * @date 2019-03-07
 */
public interface RefundApplicationMapper 
{
	/**
     * 查询退款申请信息
     * 
     * @param id 退款申请ID
     * @return 退款申请信息
     */
	public RefundApplication selectRefundApplicationById(String id);
	
	/**
     * 查询退款申请列表
     * 
     * @param refundApplication 退款申请信息
     * @return 退款申请集合
     */
	public List<RefundApplication> selectRefundApplicationList(RefundApplication refundApplication);
	
	/**
     * 新增退款申请
     * 
     * @param refundApplication 退款申请信息
     * @return 结果
     */
	public int insertRefundApplication(RefundApplication refundApplication);
	
	/**
     * 修改退款申请
     * 
     * @param refundApplication 退款申请信息
     * @return 结果
     */
	public int updateRefundApplication(RefundApplication refundApplication);
	
	/**
     * 删除退款申请
     * 
     * @param id 退款申请ID
     * @return 结果
     */
	public int deleteRefundApplicationById(String id);
	
	/**
     * 批量删除退款申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRefundApplicationByIds(String[] ids);
	
}
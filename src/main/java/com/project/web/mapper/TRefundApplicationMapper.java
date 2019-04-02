package com.project.web.mapper;

import com.project.web.domain.TRefundApplication;
import java.util.List;

/**
 * 退款申请 数据层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface TRefundApplicationMapper 
{
	/**
     * 查询退款申请信息
     * 
     * @param id 退款申请ID
     * @return 退款申请信息
     */
	public TRefundApplication selectTRefundApplicationById(String id);
	
	/**
     * 查询退款申请列表
     * 
     * @param tRefundApplication 退款申请信息
     * @return 退款申请集合
     */
	public List<TRefundApplication> selectTRefundApplicationList(TRefundApplication tRefundApplication);
	
	/**
     * 新增退款申请
     * 
     * @param tRefundApplication 退款申请信息
     * @return 结果
     */
	public int insertTRefundApplication(TRefundApplication tRefundApplication);
	
	/**
     * 修改退款申请
     * 
     * @param tRefundApplication 退款申请信息
     * @return 结果
     */
	public int updateTRefundApplication(TRefundApplication tRefundApplication);
	
	/**
     * 删除退款申请
     * 
     * @param id 退款申请ID
     * @return 结果
     */
	public int deleteTRefundApplicationById(String id);
	
	/**
     * 批量删除退款申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTRefundApplicationByIds(String[] ids);
	
}
package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.domain.RefundApplication;
import com.project.appinterface.mapper.RefundApplicationMapper;
import com.project.appinterface.service.IRefundApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 退款申请 服务层实现
 * 
 * @author lws
 * @date 2019-03-07
 */
@Service
public class RefundApplicationServiceImpl implements IRefundApplicationService
{
	@Autowired
	private RefundApplicationMapper refundApplicationMapper;

	/**
     * 查询退款申请信息
     * 
     * @param id 退款申请ID
     * @return 退款申请信息
     */
    @Override
	public RefundApplication selectRefundApplicationById(String id)
	{
	    return refundApplicationMapper.selectRefundApplicationById(id);
	}
	
	/**
     * 查询退款申请列表
     * 
     * @param refundApplication 退款申请信息
     * @return 退款申请集合
     */
	@Override
	public List<RefundApplication> selectRefundApplicationList(RefundApplication refundApplication)
	{
	    return refundApplicationMapper.selectRefundApplicationList(refundApplication);
	}
	
    /**
     * 新增退款申请
     * 
     * @param refundApplication 退款申请信息
     * @return 结果
     */
	@Override
	public int insertRefundApplication(RefundApplication refundApplication)
	{
	    return refundApplicationMapper.insertRefundApplication(refundApplication);
	}
	
	/**
     * 修改退款申请
     * 
     * @param refundApplication 退款申请信息
     * @return 结果
     */
	@Override
	public int updateRefundApplication(RefundApplication refundApplication)
	{
	    return refundApplicationMapper.updateRefundApplication(refundApplication);
	}

	/**
     * 删除退款申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRefundApplicationByIds(String ids)
	{
		return refundApplicationMapper.deleteRefundApplicationByIds(Convert.toStrArray(ids));
	}
	
}

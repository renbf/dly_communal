package com.project.web.service;

import java.util.List;

import com.project.web.domain.TRefundApplication;
import com.project.web.mapper.TRefundApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.support.Convert;

/**
 * 退款申请 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TRefundApplicationServiceImpl implements ITRefundApplicationService 
{
	@Autowired
	private TRefundApplicationMapper tRefundApplicationMapper;

	/**
     * 查询退款申请信息
     * 
     * @param id 退款申请ID
     * @return 退款申请信息
     */
    @Override
	public TRefundApplication selectTRefundApplicationById(String id)
	{
	    return tRefundApplicationMapper.selectTRefundApplicationById(id);
	}
	
	/**
     * 查询退款申请列表
     * 
     * @param tRefundApplication 退款申请信息
     * @return 退款申请集合
     */
	@Override
	public List<TRefundApplication> selectTRefundApplicationList(TRefundApplication tRefundApplication)
	{
	    return tRefundApplicationMapper.selectTRefundApplicationList(tRefundApplication);
	}
	
    /**
     * 新增退款申请
     * 
     * @param tRefundApplication 退款申请信息
     * @return 结果
     */
	@Override
	public int insertTRefundApplication(TRefundApplication tRefundApplication)
	{
	    return tRefundApplicationMapper.insertTRefundApplication(tRefundApplication);
	}
	
	/**
     * 修改退款申请
     * 
     * @param tRefundApplication 退款申请信息
     * @return 结果
     */
	@Override
	public int updateTRefundApplication(TRefundApplication tRefundApplication)
	{
	    return tRefundApplicationMapper.updateTRefundApplication(tRefundApplication);
	}

	/**
     * 删除退款申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTRefundApplicationByIds(String ids)
	{
		return tRefundApplicationMapper.deleteTRefundApplicationByIds(Convert.toStrArray(ids));
	}
	
}

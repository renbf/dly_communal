package com.project.web.service;

import java.util.List;

import com.project.web.domain.TParameter;
import com.project.web.mapper.TParameterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 参数 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TParameterServiceImpl implements ITParameterService 
{
	@Autowired
	private TParameterMapper tParameterMapper;

	/**
     * 查询参数信息
     * 
     * @param id 参数ID
     * @return 参数信息
     */
    @Override
	public TParameter selectTParameterById(String id)
	{
	    return tParameterMapper.selectTParameterById(id);
	}
	
	/**
     * 查询参数列表
     * 
     * @param tParameter 参数信息
     * @return 参数集合
     */
	@Override
	public List<TParameter> selectTParameterList(TParameter tParameter)
	{
	    return tParameterMapper.selectTParameterList(tParameter);
	}
	
    /**
     * 新增参数
     * 
     * @param tParameter 参数信息
     * @return 结果
     */
	@Override
	public int insertTParameter(TParameter tParameter)
	{
	    return tParameterMapper.insertTParameter(tParameter);
	}
	
	/**
     * 修改参数
     * 
     * @param tParameter 参数信息
     * @return 结果
     */
	@Override
	public int updateTParameter(TParameter tParameter)
	{
	    return tParameterMapper.updateTParameter(tParameter);
	}

	/**
     * 删除参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTParameterByIds(String ids)
	{
		return tParameterMapper.deleteTParameterByIds(Convert.toStrArray(ids));
	}
	
}

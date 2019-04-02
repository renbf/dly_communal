package com.project.appinterface.service.impl;

import java.util.List;

import com.project.appinterface.domain.Parameter;
import com.project.appinterface.mapper.ParameterMapper;
import com.project.appinterface.service.IParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 参数 服务层实现
 * 
 * @author lws
 * @date 2019-03-05
 */
@Service
public class ParameterServiceImpl implements IParameterService
{
	@Autowired
	private ParameterMapper parameterMapper;

	/**
     * 查询参数信息
     * 
     * @param id 参数ID
     * @return 参数信息
     */
    @Override
	public Parameter selectParameterById(String id)
	{
	    return parameterMapper.selectParameterById(id);
	}
	
	/**
     * 查询参数列表
     * 
     * @param parameter 参数信息
     * @return 参数集合
     */
	@Override
	public List<Parameter> selectParameterList(Parameter parameter)
	{
	    return parameterMapper.selectParameterList(parameter);
	}
	
    /**
     * 新增参数
     * 
     * @param parameter 参数信息
     * @return 结果
     */
	@Override
	public int insertParameter(Parameter parameter)
	{
	    return parameterMapper.insertParameter(parameter);
	}
	
	/**
     * 修改参数
     * 
     * @param parameter 参数信息
     * @return 结果
     */
	@Override
	public int updateParameter(Parameter parameter)
	{
	    return parameterMapper.updateParameter(parameter);
	}

	/**
     * 删除参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteParameterByIds(String ids)
	{
		return parameterMapper.deleteParameterByIds(Convert.toStrArray(ids));
	}
	
}

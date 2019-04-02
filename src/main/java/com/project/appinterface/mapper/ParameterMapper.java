package com.project.appinterface.mapper;

import com.project.appinterface.domain.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数 数据层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface ParameterMapper 
{
	/**
     * 查询参数信息
     * 
     * @param id 参数ID
     * @return 参数信息
     */
	public Parameter selectParameterById(@Param("id")String id);
	
	/**
     * 查询参数列表
     * 
     * @param parameter 参数信息
     * @return 参数集合
     */
	public List<Parameter> selectParameterList(Parameter parameter);
	
	/**
     * 新增参数
     * 
     * @param parameter 参数信息
     * @return 结果
     */
	public int insertParameter(Parameter parameter);
	
	/**
     * 修改参数
     * 
     * @param parameter 参数信息
     * @return 结果
     */
	public int updateParameter(Parameter parameter);
	
	/**
     * 删除参数
     * 
     * @param id 参数ID
     * @return 结果
     */
	public int deleteParameterById(String id);
	
	/**
     * 批量删除参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteParameterByIds(String[] ids);
	
}
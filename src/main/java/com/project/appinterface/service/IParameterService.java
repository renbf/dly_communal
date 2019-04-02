package com.project.appinterface.service;

import com.project.appinterface.domain.Parameter;

import java.util.List;

/**
 * 参数 服务层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface IParameterService 
{
	/**
     * 查询参数信息
     * 
     * @param id 参数ID
     * @return 参数信息
     */
	public Parameter selectParameterById(String id);
	
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
     * 删除参数信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteParameterByIds(String ids);
	
}

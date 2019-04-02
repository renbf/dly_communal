package com.project.web.mapper;

import com.project.web.domain.TParameter;

import java.util.List;

/**
 * 参数 数据层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface TParameterMapper 
{
	/**
     * 查询参数信息
     * 
     * @param id 参数ID
     * @return 参数信息
     */
	public TParameter selectTParameterById(String id);
	
	/**
     * 查询参数列表
     * 
     * @param tParameter 参数信息
     * @return 参数集合
     */
	public List<TParameter> selectTParameterList(TParameter tParameter);
	
	/**
     * 新增参数
     * 
     * @param tParameter 参数信息
     * @return 结果
     */
	public int insertTParameter(TParameter tParameter);
	
	/**
     * 修改参数
     * 
     * @param tParameter 参数信息
     * @return 结果
     */
	public int updateTParameter(TParameter tParameter);
	
	/**
     * 删除参数
     * 
     * @param id 参数ID
     * @return 结果
     */
	public int deleteTParameterById(String id);
	
	/**
     * 批量删除参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTParameterByIds(String[] ids);
	
}
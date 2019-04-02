package com.project.web.mapper;

import java.util.List;

import com.project.web.domain.TExpressCompany;	

/**
 * 快递公司 数据层
 * 
 * @author lws
 * @date 2019-04-02
 */
public interface TExpressCompanyMapper 
{
	/**
     * 查询快递公司信息
     * 
     * @param id 快递公司ID
     * @return 快递公司信息
     */
	public TExpressCompany selectTExpressCompanyById(String id);
	
	/**
     * 查询快递公司列表
     * 
     * @param tExpressCompany 快递公司信息
     * @return 快递公司集合
     */
	public List<TExpressCompany> selectTExpressCompanyList(TExpressCompany tExpressCompany);
	
	/**
     * 新增快递公司
     * 
     * @param tExpressCompany 快递公司信息
     * @return 结果
     */
	public int insertTExpressCompany(TExpressCompany tExpressCompany);
	
	/**
     * 修改快递公司
     * 
     * @param tExpressCompany 快递公司信息
     * @return 结果
     */
	public int updateTExpressCompany(TExpressCompany tExpressCompany);
	
	/**
     * 删除快递公司
     * 
     * @param id 快递公司ID
     * @return 结果
     */
	public int deleteTExpressCompanyById(String id);
	
	/**
     * 批量删除快递公司
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTExpressCompanyByIds(String[] ids);
	
	/**
     * 查询快递公司信息
     * 
     * @param id 快递公司编码
     * @return 快递公司信息
     */
	public TExpressCompany selectTExpressCompanyByCode(String companyCode);
	
}
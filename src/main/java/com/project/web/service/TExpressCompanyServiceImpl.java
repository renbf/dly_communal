package com.project.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.common.support.Convert;
import com.project.web.domain.TExpressCompany;
import com.project.web.mapper.TExpressCompanyMapper;

/**
 * 快递公司 服务层实现
 * 
 * @author lws
 * @date 2019-04-02
 */
@Service
public class TExpressCompanyServiceImpl implements ITExpressCompanyService 
{
	private static final Logger log = LoggerFactory.getLogger(TExpressCompanyServiceImpl.class);
	@Autowired
	private TExpressCompanyMapper tExpressCompanyMapper;

	/**
     * 查询快递公司信息
     * 
     * @param id 快递公司ID
     * @return 快递公司信息
     */
    @Override
	public TExpressCompany selectTExpressCompanyById(String id)
	{
	    return tExpressCompanyMapper.selectTExpressCompanyById(id);
	}
	
	/**
     * 查询快递公司列表
     * 
     * @param tExpressCompany 快递公司信息
     * @return 快递公司集合
     */
	@Override
	public List<TExpressCompany> selectTExpressCompanyList(TExpressCompany tExpressCompany)
	{
	    return tExpressCompanyMapper.selectTExpressCompanyList(tExpressCompany);
	}
	
    /**
     * 新增快递公司
     * 
     * @param tExpressCompany 快递公司信息
     * @return 结果
     */
	@Override
	public int insertTExpressCompany(TExpressCompany tExpressCompany)
	{
	    return tExpressCompanyMapper.insertTExpressCompany(tExpressCompany);
	}
	
	/**
     * 修改快递公司
     * 
     * @param tExpressCompany 快递公司信息
     * @return 结果
     */
	@Override
	public int updateTExpressCompany(TExpressCompany tExpressCompany)
	{
	    return tExpressCompanyMapper.updateTExpressCompany(tExpressCompany);
	}

	/**
     * 删除快递公司对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTExpressCompanyByIds(String ids)
	{
		return tExpressCompanyMapper.deleteTExpressCompanyByIds(Convert.toStrArray(ids));
	}

	@Override
	public DataResult getTExpressCompanys() {
		DataResult result=new DataResult();
		try {
			List<TExpressCompany> tExpressCompanys = tExpressCompanyMapper.selectTExpressCompanyList(null);
			result.setResult(tExpressCompanys);
			result.setMessage("查询成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setMessage("查询失败");
			result.setStatus(Result.FAILED);
			log.error("查询失败", e);
		}
		return result;
	}
	
}

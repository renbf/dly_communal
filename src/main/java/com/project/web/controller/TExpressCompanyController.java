package com.project.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.framework.web.page.TableDataInfo;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TExpressCompany;
import com.project.web.service.ITExpressCompanyService;
import com.wordnik.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiImplicitParams;

/**
 * 快递公司 信息操作处理
 * 
 * @author lws
 * @date 2019-04-02
 */
@Controller
@RequestMapping("/web/tExpressCompany")
public class TExpressCompanyController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(TExpressCompanyController.class);
    private String prefix = "web/tExpressCompany";
	
	@Autowired
	private ITExpressCompanyService tExpressCompanyService;
	
	@RequiresPermissions("web:tExpressCompany:view")
	@GetMapping()
	public String tExpressCompany()
	{
	    return prefix + "/tExpressCompany";
	}
	
	/**
	 * 查询快递公司列表
	 */
	@RequiresPermissions("web:tExpressCompany:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TExpressCompany tExpressCompany)
	{
		startPage();
        List<TExpressCompany> list = tExpressCompanyService.selectTExpressCompanyList(tExpressCompany);
		return getDataTable(list);
	}
	
	/**
	 * 新增快递公司
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存快递公司
	 */
	@RequiresPermissions("web:tExpressCompany:add")
	@Log(title = "快递公司", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TExpressCompany tExpressCompany)
	{		
		return toAjax(tExpressCompanyService.insertTExpressCompany(tExpressCompany));
	}

	/**
	 * 修改快递公司
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TExpressCompany tExpressCompany = tExpressCompanyService.selectTExpressCompanyById(id);
		mmap.put("tExpressCompany", tExpressCompany);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存快递公司
	 */
	@RequiresPermissions("web:tExpressCompany:edit")
	@Log(title = "快递公司", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TExpressCompany tExpressCompany)
	{		
		return toAjax(tExpressCompanyService.updateTExpressCompany(tExpressCompany));
	}
	
	/**
	 * 删除快递公司
	 */
	@RequiresPermissions("web:tExpressCompany:remove")
	@Log(title = "快递公司", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tExpressCompanyService.deleteTExpressCompanyByIds(ids));
	}
	
	/**
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	@ApiOperation(value = "查询快递公司字典", httpMethod="POST",response=Result.class )
	@ApiImplicitParams({
	})
	@RequestMapping(value="/getTExpressCompanys",method=RequestMethod.POST)
	public @ResponseBody DataResult getTExpressCompanys(){
		DataResult result =new DataResult();
		try {
			result = tExpressCompanyService.getTExpressCompanys();
			return result;
		} catch (Exception e) {
			result.setMessage("查询失败");
			result.setStatus(Result.FAILED);
			log.error("查询失败", e);
			return result;
		}
	}
}

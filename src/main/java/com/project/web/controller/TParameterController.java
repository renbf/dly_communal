package com.project.web.controller;

import java.util.List;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.system.core.base.BaseController;
import com.project.util.UUIDUtil;
import com.project.web.domain.TParameter;
import com.project.web.service.ITParameterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.framework.web.page.TableDataInfo;

/**
 * 参数 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tParameter")
public class TParameterController extends BaseController
{
    private String prefix = "web/tParameter";
	
	@Autowired
	private ITParameterService tParameterService;
	
	@RequiresPermissions("web:tParameter:view")
	@GetMapping()
	public String tParameter()
	{
	    return prefix + "/tParameter";
	}
	
	/**
	 * 查询参数列表
	 */
	@RequiresPermissions("web:tParameter:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TParameter tParameter)
	{
		startPage();
        List<TParameter> list = tParameterService.selectTParameterList(tParameter);
		return getDataTable(list);
	}
	
	/**
	 * 新增参数
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存参数
	 */
	@RequiresPermissions("web:tParameter:add")
	@Log(title = "参数", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TParameter tParameter)
	{
		tParameter.setId(UUIDUtil.getUUID());
		return toAjax(tParameterService.insertTParameter(tParameter));
	}

	/**
	 * 修改参数
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TParameter tParameter = tParameterService.selectTParameterById(id);
		mmap.put("tParameter", tParameter);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存参数
	 */
	@RequiresPermissions("web:tParameter:edit")
	@Log(title = "参数", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TParameter tParameter)
	{		
		return toAjax(tParameterService.updateTParameter(tParameter));
	}
	
	/**
	 * 删除参数
	 */
	@RequiresPermissions("web:tParameter:remove")
	@Log(title = "参数", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tParameterService.deleteTParameterByIds(ids));
	}
	
}

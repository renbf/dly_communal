package com.project.web.controller;

import java.util.List;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TRefundApplication;
import com.project.web.service.ITRefundApplicationService;
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
 * 退款申请 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tRefundApplication")
public class TRefundApplicationController extends BaseController
{
    private String prefix = "web/tRefundApplication";
	
	@Autowired
	private ITRefundApplicationService tRefundApplicationService;
	
	@RequiresPermissions("web:tRefundApplication:view")
	@GetMapping()
	public String tRefundApplication()
	{
	    return prefix + "/tRefundApplication";
	}
	
	/**
	 * 查询退款申请列表
	 */
	@RequiresPermissions("web:tRefundApplication:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TRefundApplication tRefundApplication)
	{
		startPage();
        List<TRefundApplication> list = tRefundApplicationService.selectTRefundApplicationList(tRefundApplication);
		return getDataTable(list);
	}
	
	/**
	 * 新增退款申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存退款申请
	 */
	@RequiresPermissions("web:tRefundApplication:add")
	@Log(title = "退款申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TRefundApplication tRefundApplication)
	{		
		return toAjax(tRefundApplicationService.insertTRefundApplication(tRefundApplication));
	}

	/**
	 * 修改退款申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TRefundApplication tRefundApplication = tRefundApplicationService.selectTRefundApplicationById(id);
		mmap.put("tRefundApplication", tRefundApplication);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存退款申请
	 */
	@RequiresPermissions("web:tRefundApplication:edit")
	@Log(title = "退款申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TRefundApplication tRefundApplication)
	{		
		return toAjax(tRefundApplicationService.updateTRefundApplication(tRefundApplication));
	}
	
	/**
	 * 删除退款申请
	 */
	@RequiresPermissions("web:tRefundApplication:remove")
	@Log(title = "退款申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tRefundApplicationService.deleteTRefundApplicationByIds(ids));
	}
	
}

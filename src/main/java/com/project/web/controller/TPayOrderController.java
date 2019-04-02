package com.project.web.controller;

import java.util.List;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TPayOrder;
import com.project.web.service.ITPayOrderService;
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
 * 支付 信息操作处理
 * 
 * @author lws
 * @date 2019-03-15
 */
@Controller
@RequestMapping("/web/tPayOrder")
public class TPayOrderController extends BaseController
{
    private String prefix = "web/tPayOrder";
	
	@Autowired
	private ITPayOrderService tPayOrderService;
	
	@RequiresPermissions("web:tPayOrder:view")
	@GetMapping()
	public String tPayOrder()
	{
	    return prefix + "/tPayOrder";
	}
	
	/**
	 * 查询支付列表
	 */
	@RequiresPermissions("web:tPayOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TPayOrder tPayOrder)
	{
		startPage();
        List<TPayOrder> list = tPayOrderService.selectTPayOrderList(tPayOrder);
		return getDataTable(list);
	}
	
	/**
	 * 新增支付
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存支付
	 */
	@RequiresPermissions("web:tPayOrder:add")
	@Log(title = "支付", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TPayOrder tPayOrder)
	{		
		return toAjax(tPayOrderService.insertTPayOrder(tPayOrder));
	}

	/**
	 * 修改支付
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TPayOrder tPayOrder = tPayOrderService.selectTPayOrderById(id);
		mmap.put("tPayOrder", tPayOrder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存支付
	 */
	@RequiresPermissions("web:tPayOrder:edit")
	@Log(title = "支付", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TPayOrder tPayOrder)
	{		
		return toAjax(tPayOrderService.updateTPayOrder(tPayOrder));
	}
	
	/**
	 * 删除支付
	 */
	@RequiresPermissions("web:tPayOrder:remove")
	@Log(title = "支付", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tPayOrderService.deleteTPayOrderByIds(ids));
	}
	
}

package com.project.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.common.result.DataResult;
import com.project.framework.web.page.TableDataInfo;
import com.project.system.core.base.BaseController;
import com.project.web.domain.OrderVo;
import com.project.web.domain.TOrder;
import com.project.web.service.ITOrderService;

/**
 * 订单 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tOrder")
public class TOrderController extends BaseController
{
    private String prefix = "web/tOrder";
	
	@Autowired
	private ITOrderService tOrderService;
	
	@RequiresPermissions("web:tOrder:view")
	@GetMapping()
	public String tOrder()
	{
	    return prefix + "/tOrder";
	}
	
	/**
	 * 查询订单列表
	 */
	@RequiresPermissions("web:tOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TOrder tOrder)
	{
		startPage();
        List<OrderVo> list = tOrderService.selectTOrderList(tOrder);
		return getDataTable(list);
	}
	
	/**
	 * 新增订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单
	 */
	@RequiresPermissions("web:tOrder:add")
	@Log(title = "订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TOrder tOrder)
	{		
		return toAjax(tOrderService.insertTOrder(tOrder));
	}

	/**
	 * 修改订单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TOrder tOrder = tOrderService.selectTOrderById(id);
		mmap.put("tOrder", tOrder);
	    return prefix + "/edit";
	}
	/**
	 * 修改订单
	 */
	@GetMapping("/orderDetail/{id}")
	public String orderDetail(@PathVariable("id") String id, ModelMap mmap)
	{
		TOrder tOrder = tOrderService.selectTOrderById(id);
		mmap.put("tOrder", tOrder);
		return prefix + "/orderDetail";
	}
	/**
	 * 修改保存订单
	 */
	@RequiresPermissions("web:tOrder:edit")
	@Log(title = "订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TOrder tOrder)
	{		
		DataResult result = tOrderService.updateTOrder(tOrder);
		if(result.SUCCESS.equals(result.getStatus())) {
			return success();
		}else {
			return error(result.getMessage());
		}
	}
	
	/**
	 * 删除订单
	 */
	@RequiresPermissions("web:tOrder:remove")
	@Log(title = "订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tOrderService.deleteTOrderByIds(ids));
	}
	
	/**
	 * 查看订单
	 */
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") String id, ModelMap mmap)
	{
		OrderVo orderVo = tOrderService.selectTOrderVoDetail(id);
		mmap.put("orderVo", orderVo);
	    return prefix + "/detail";
	}
	
}

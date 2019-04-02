package com.project.web.controller;

import java.util.Date;
import java.util.List;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.framework.util.ShiroUtils;
import com.project.system.core.base.BaseController;
import com.project.util.UUIDUtil;
import com.project.web.domain.TCoupon;
import com.project.web.service.ITCouponService;
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
 * 优惠券 信息操作处理
 * 
 * @author lws
 * @date 2019-03-13
 */
@Controller
@RequestMapping("/web/tCoupon")
public class TCouponController extends BaseController
{
    private String prefix = "web/tCoupon";
	
	@Autowired
	private ITCouponService tCouponService;
	
	@RequiresPermissions("web:tCoupon:view")
	@GetMapping()
	public String tCoupon()
	{
	    return prefix + "/tCoupon";
	}
	
	/**
	 * 查询优惠券列表
	 */
	@RequiresPermissions("web:tCoupon:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TCoupon tCoupon)
	{
		startPage();
        List<TCoupon> list = tCouponService.selectTCouponList(tCoupon);
		return getDataTable(list);
	}
	
	/**
	 * 新增优惠券
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存优惠券
	 */
	@RequiresPermissions("web:tCoupon:add")
	@Log(title = "优惠券", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TCoupon tCoupon)
	{
		tCoupon.setId(UUIDUtil.getUUID());
		tCoupon.setCreateDate(new Date());
		tCoupon.setCreateUser(ShiroUtils.getUserId());
		return toAjax(tCouponService.insertTCoupon(tCoupon));
	}

	/**
	 * 修改优惠券
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TCoupon tCoupon = tCouponService.selectTCouponById(id);
		mmap.put("tCoupon", tCoupon);
	    return prefix + "/edit";
	}

	
	/**
	 * 修改保存优惠券
	 */
	@RequiresPermissions("web:tCoupon:edit")
	@Log(title = "优惠券", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TCoupon tCoupon)
	{		
		return toAjax(tCouponService.updateTCoupon(tCoupon));
	}
	/**
	 * 修改保存优惠券状态
	 */
	@RequiresPermissions("web:tCoupon:edit")
	@Log(title = "优惠券", businessType = BusinessType.UPDATE)
	@PostMapping("/updateState")
	@ResponseBody
	public AjaxResult updateState(String id)
	{
		TCoupon tCoupon=  tCouponService.selectTCouponById(id);
		if("0".equals(tCoupon.getState())) {
			//当状态为正常时，执行冻结
			tCoupon.setState("1");
		}else{
			//当状态为冻结时，执行解冻
			tCoupon.setState("0");
		}
		return toAjax(tCouponService.updateTCoupon(tCoupon));
	}

	/**
	 * 删除优惠券
	 */
	@RequiresPermissions("web:tCoupon:remove")
	@Log(title = "优惠券", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tCouponService.deleteTCouponByIds(ids));
	}
	
}

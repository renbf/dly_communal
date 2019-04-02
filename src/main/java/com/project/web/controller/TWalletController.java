package com.project.web.controller;

import java.util.List;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TWallet;
import com.project.web.service.ITWalletService;
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
 * 用户钱包 信息操作处理
 * 
 * @author lws
 * @date 2019-03-15
 */
@Controller
@RequestMapping("/web/tWallet")
public class TWalletController extends BaseController
{
    private String prefix = "web/tWallet";
	
	@Autowired
	private ITWalletService tWalletService;
	
	@RequiresPermissions("web:tWallet:view")
	@GetMapping()
	public String tWallet()
	{
	    return prefix + "/tWallet";
	}
	
	/**
	 * 查询用户钱包列表
	 */
	@RequiresPermissions("web:tWallet:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TWallet tWallet)
	{
		startPage();
        List<TWallet> list = tWalletService.selectTWalletList(tWallet);
		return getDataTable(list);
	}
	
	/**
	 * 新增用户钱包
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户钱包
	 */
	@RequiresPermissions("web:tWallet:add")
	@Log(title = "用户钱包", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TWallet tWallet)
	{		
		return toAjax(tWalletService.insertTWallet(tWallet));
	}

	/**
	 * 修改用户钱包
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TWallet tWallet = tWalletService.selectTWalletById(id);
		mmap.put("tWallet", tWallet);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户钱包
	 */
	@RequiresPermissions("web:tWallet:edit")
	@Log(title = "用户钱包", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TWallet tWallet)
	{		
		return toAjax(tWalletService.updateTWallet(tWallet));
	}
	
	/**
	 * 删除用户钱包
	 */
	@RequiresPermissions("web:tWallet:remove")
	@Log(title = "用户钱包", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tWalletService.deleteTWalletByIds(ids));
	}
	
}

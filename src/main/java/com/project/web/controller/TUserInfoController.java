package com.project.web.controller;

import java.util.List;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TUserInfo;
import com.project.web.service.ITUserInfoService;
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
 * 用户 信息操作处理
 * 
 * @author lws
 * @date 2019-03-08
 */
@Controller
@RequestMapping("web/tUserInfo")
public class TUserInfoController extends BaseController
{
    private String prefix = "web/tUserInfo";
	
	@Autowired
	private ITUserInfoService tUserInfoService;
	
	@RequiresPermissions("web:tUserInfo:view")
	@GetMapping()
	public String tUserInfo()
	{
	    return prefix + "/tUserInfo";
	}
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("web:tUserInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TUserInfo tUserInfo)
	{
		startPage();
        List<TUserInfo> list = tUserInfoService.selectTUserInfoList(tUserInfo);
		return getDataTable(list);
	}
	
	/**
	 * 新增用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("web:tUserInfo:add")
	@Log(title = "用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TUserInfo tUserInfo)
	{		
		return toAjax(tUserInfoService.insertTUserInfo(tUserInfo));
	}

	/**
	 * 查看用户
	 */
	@GetMapping("/detail/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TUserInfo tUserInfo = tUserInfoService.selectTUserInfoById(id);
		mmap.put("tUserInfo", tUserInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户
	 */
	@RequiresPermissions("web:tUserInfo:edit")
	@Log(title = "用户", businessType = BusinessType.UPDATE)
	@PostMapping("/updateState")
	@ResponseBody
	public AjaxResult editSave(String id)
	{		TUserInfo user =  tUserInfoService.selectTUserInfoById(id);
		if("0".equals(user.getState())) {
			//当状态为正常时，执行冻结
			user.setState("1");
		}else{
			//当状态为冻结时，执行解冻
			user.setState("0");
		}
		return toAjax(tUserInfoService.updateTUserInfo(user));
	}
	
	/**
	 * 删除用户
	 */
	@RequiresPermissions("web:tUserInfo:remove")
	@Log(title = "用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tUserInfoService.deleteTUserInfoByIds(ids));
	}
	
}

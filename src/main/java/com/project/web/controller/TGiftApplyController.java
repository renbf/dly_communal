package com.project.web.controller;

import java.util.List;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.system.core.base.BaseController;
import com.project.web.domain.GiftApplyVo;
import com.project.web.domain.TGiftApply;
import com.project.web.service.ITGiftApplyService;
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
 * 礼物机申请 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tGiftApply")
public class TGiftApplyController extends BaseController
{
    private String prefix = "web/tGiftApply";
	
	@Autowired
	private ITGiftApplyService tGiftApplyService;
	
	@RequiresPermissions("web:tGiftApply:view")
	@GetMapping()
	public String tGiftApply()
	{
	    return prefix + "/tGiftApply";
	}
	
	/**
	 * 查询礼物机申请列表
	 */
	@RequiresPermissions("web:tGiftApply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GiftApplyVo giftApplyVo)
	{
		startPage();
        List<GiftApplyVo> list = tGiftApplyService.selectTGiftApplyList(giftApplyVo);
		return getDataTable(list);
	}
	
	/**
	 * 新增礼物机申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存礼物机申请
	 */
	@RequiresPermissions("web:tGiftApply:add")
	@Log(title = "礼物机申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TGiftApply tGiftApply)
	{		
		return toAjax(tGiftApplyService.insertTGiftApply(tGiftApply));
	}

	/**
	 * 修改礼物机申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TGiftApply tGiftApply = tGiftApplyService.selectTGiftApplyById(id);
		mmap.put("tGiftApply", tGiftApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存礼物机申请
	 */
	@RequiresPermissions("web:tGiftApply:edit")
	@Log(title = "礼物机申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TGiftApply tGiftApply)
	{		
		return toAjax(tGiftApplyService.updateTGiftApply(tGiftApply));
	}
	
	/**
	 * 删除礼物机申请
	 */
	@RequiresPermissions("web:tGiftApply:remove")
	@Log(title = "礼物机申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tGiftApplyService.deleteTGiftApplyByIds(ids));
	}
	
	/**
	 * 审核通过
	 */
	@RequiresPermissions("web:tGiftApply:examineAdopt")
	@Log(title = "礼物机申请", businessType = BusinessType.UPDATE)
	@PostMapping( "/examineAdopt")
	@ResponseBody
	public AjaxResult examineAdopt(String id,String state)
	{		
		return toAjax(tGiftApplyService.examine(id,state));
	}
}

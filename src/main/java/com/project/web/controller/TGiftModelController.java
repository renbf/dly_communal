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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.framework.web.page.TableDataInfo;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TGiftModel;
import com.project.web.service.ITGiftModelService;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 *  礼物机类型 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tGiftModel")
public class TGiftModelController extends BaseController
{
    private String prefix = "web/tGiftModel";
	
	@Autowired
	private ITGiftModelService tGiftModelService;
	
	@RequiresPermissions("web:tGiftModel:view")
	@GetMapping()
	public String tGiftModel()
	{
	    return prefix + "/tGiftModel";
	}
	
	/**
	 * 查询 礼物机类型列表
	 */
	@RequiresPermissions("web:tGiftModel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TGiftModel tGiftModel)
	{
		startPage();
        List<TGiftModel> list = tGiftModelService.selectTGiftModelList(tGiftModel);
		return getDataTable(list);
	}
	
	/**
	 * 新增 礼物机类型
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存 礼物机类型
	 */
	@RequiresPermissions("web:tGiftModel:add")
	@Log(title = " 礼物机类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TGiftModel tGiftModel)
	{		
		return toAjax(tGiftModelService.insertTGiftModel(tGiftModel));
	}

	/**
	 * 修改 礼物机类型
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TGiftModel tGiftModel = tGiftModelService.selectTGiftModelById(id);
		mmap.put("tGiftModel", tGiftModel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存 礼物机类型
	 */
	@RequiresPermissions("web:tGiftModel:edit")
	@Log(title = " 礼物机类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TGiftModel tGiftModel)
	{		
		return toAjax(tGiftModelService.updateTGiftModel(tGiftModel));
	}
	
	/**
	 * 删除 礼物机类型
	 */
	@RequiresPermissions("web:tGiftModel:remove")
	@Log(title = " 礼物机类型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tGiftModelService.deleteTGiftModelByIds(ids));
	}
	
	/**
	 * 礼物机类型字典
	 * @param tGiftModel
	 * @return
	 */
	@ApiOperation(value = "礼物机类型", httpMethod="POST",response=Result.class)
	@RequestMapping(value="/getTGiftModel",method=RequestMethod.POST)
	public @ResponseBody DataResult getTGiftModel(TGiftModel tGiftModel){
		return tGiftModelService.getTGiftModel(tGiftModel);
	}
}

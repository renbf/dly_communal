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
import com.project.web.domain.TGoodsType;
import com.project.web.service.ITGoodsTypeService;
import com.wordnik.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiImplicitParams;

/**
 * 商品类型 信息操作处理
 * 
 * @author lws
 * @date 2019-04-01
 */
@Controller
@RequestMapping("/web/tGoodsType")
public class TGoodsTypeController extends BaseController
{
	private static final Logger log = LoggerFactory.getLogger(TGoodsTypeController.class);
	
    private String prefix = "web/tGoodsType";
	
	@Autowired
	private ITGoodsTypeService tGoodsTypeService;
	
	@RequiresPermissions("web:tGoodsType:view")
	@GetMapping()
	public String tGoodsType()
	{
	    return prefix + "/tGoodsType";
	}
	
	/**
	 * 查询商品类型列表
	 */
	@RequiresPermissions("web:tGoodsType:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TGoodsType tGoodsType)
	{
		startPage();
        List<TGoodsType> list = tGoodsTypeService.selectTGoodsTypeList(tGoodsType);
		return getDataTable(list);
	}
	
	/**
	 * 新增商品类型
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品类型
	 */
	@RequiresPermissions("web:tGoodsType:add")
	@Log(title = "商品类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TGoodsType tGoodsType)
	{		
		return toAjax(tGoodsTypeService.insertTGoodsType(tGoodsType));
	}

	/**
	 * 修改商品类型
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TGoodsType tGoodsType = tGoodsTypeService.selectTGoodsTypeById(id);
		mmap.put("tGoodsType", tGoodsType);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品类型
	 */
	@RequiresPermissions("web:tGoodsType:edit")
	@Log(title = "商品类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TGoodsType tGoodsType)
	{		
		return toAjax(tGoodsTypeService.updateTGoodsType(tGoodsType));
	}
	
	/**
	 * 删除商品类型
	 */
	@RequiresPermissions("web:tGoodsType:remove")
	@Log(title = "商品类型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tGoodsTypeService.deleteTGoodsTypeByIds(ids));
	}
	
	/**
	 * 
	 * @param request
	 * @param code
	 * @return
	 */
	@ApiOperation(value = "查询商品类型", httpMethod="POST",response=Result.class )
	@ApiImplicitParams({
	})
	@RequestMapping(value="/getTGoodsTypes",method=RequestMethod.POST)
	public @ResponseBody DataResult getAddress(){
		DataResult result =new DataResult();
		try {
			result = tGoodsTypeService.getTGoodsTypes();
			return result;
		} catch (Exception e) {
			result.setMessage("查询失败");
			result.setStatus(Result.FAILED);
			log.error("查询失败", e);
			return result;
		}
	}
}

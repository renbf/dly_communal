package com.project.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.config.Global;
import com.project.common.enums.BusinessType;
import com.project.framework.util.FileUploadUtils;
import com.project.framework.util.ShiroUtils;
import com.project.framework.web.page.TableDataInfo;
import com.project.system.core.base.BaseController;
import com.project.util.UUIDUtil;
import com.project.web.domain.TCommodityInformation;
import com.project.web.service.ITCommodityInformationService;

/**
 * 商品 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tCommodityInformation")
public class TCommodityInformationController extends BaseController
{
    private String prefix = "web/tCommodityInformation";
	
	@Autowired
	private ITCommodityInformationService tCommodityInformationService;
	
	@RequiresPermissions("web:tCommodityInformation:view")
	@GetMapping()
	public String tCommodityInformation()
	{
	    return prefix + "/tCommodityInformation";
	}
	
	/**
	 * 查询商品列表
	 */
	@RequiresPermissions("web:tCommodityInformation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TCommodityInformation tCommodityInformation)
	{
		startPage();
        List<TCommodityInformation> list = tCommodityInformationService.selectTCommodityInformationList(tCommodityInformation);
		return getDataTable(list);
	}
	
	/**
	 * 新增商品
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品
	 */
	@RequiresPermissions("web:tCommodityInformation:add")
	@Log(title = "商品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TCommodityInformation tCommodityInformation, @RequestParam("imagePath") MultipartFile file)
	{
		try {
			String fileName = FileUploadUtils.upload(file);
			fileName = Global.getPictureUrl()+fileName;
			tCommodityInformation.setPicture(fileName);
			tCommodityInformation.setId(UUIDUtil.getUUID());
			tCommodityInformation.setCreateDate(new Date());
			tCommodityInformation.setCreateUser(ShiroUtils.getUserId().toString());
			return toAjax(tCommodityInformationService.insertTCommodityInformation(tCommodityInformation));
		} catch (Exception e) {
			e.printStackTrace();
			return toAjax(0);
		}
	}

	/**
	 * 修改商品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TCommodityInformation tCommodityInformation = tCommodityInformationService.selectTCommodityInformationById(id);
		mmap.put("tCommodityInformation", tCommodityInformation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品
	 */
	@RequiresPermissions("web:tCommodityInformation:edit")
	@Log(title = "商品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TCommodityInformation tCommodityInformation, @RequestParam("imagePath") MultipartFile file)
	{		
		try {
			if(file != null && StringUtils.isNotEmpty(file.getOriginalFilename())){
				String fileName = FileUploadUtils.upload(file);
				fileName = Global.getPictureUrl()+fileName;
				tCommodityInformation.setPicture(fileName);
			}
			return toAjax(tCommodityInformationService.updateTCommodityInformation(tCommodityInformation));
		} catch (Exception e) {
			e.printStackTrace();
			return toAjax(0);
		}
	}
	
	/**
	 * 删除商品
	 */
	@RequiresPermissions("web:tCommodityInformation:remove")
	@Log(title = "商品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tCommodityInformationService.deleteTCommodityInformationByIds(ids));
	}
	
}

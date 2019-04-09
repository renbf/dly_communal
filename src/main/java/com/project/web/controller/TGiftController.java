package com.project.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
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
import com.project.framework.web.page.TableDataInfo;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TCommodityInformation;
import com.project.web.domain.TGift;
import com.project.web.domain.TGiftApply;
import com.project.web.domain.vo.TCommodityInformationVo;
import com.project.web.domain.vo.TGiftParam;
import com.project.web.domain.vo.TGiftVo;
import com.project.web.service.ITCommodityInformationService;
import com.project.web.service.ITGiftApplyService;
import com.project.web.service.ITGiftService;

/**
 * 礼物机 信息操作处理
 *
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tGift")
public class TGiftController extends BaseController
{
	private String prefix = "web/tGift";

	@Autowired
	private ITGiftService tGiftService;

	@Autowired
	private ITCommodityInformationService tCommodityInformationService;
	
	
	@RequiresPermissions("web:tGift:view")
	@GetMapping()
	public String tGift()
	{
		return prefix + "/tGift";
	}

	/**
	 * 查询礼物机列表
	 */
	@RequiresPermissions("web:tGift:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TGift tGift)
	{
		startPage();
		List<TGiftVo> tGifts = tGiftService.selectTGiftList(tGift);

		return getDataTable(tGifts);
	}

	/**
	 * 新增礼物机
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存礼物机
	 */
	@RequiresPermissions("web:tGift:add")
	@Log(title = "礼物机", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TGift tGift)
	{
		return toAjax(tGiftService.insertTGift(tGift));
	}

	/**
	 * 补充礼物机
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TGift tGift = new TGift();
		tGift.setId(id);
		List<TGiftVo> tGiftVos = tGiftService.selectTGiftList(tGift);
		TCommodityInformation tCommodityInformation = new TCommodityInformation();
		List<TCommodityInformation> goods = tCommodityInformationService.selectTCommodityInformationList(tCommodityInformation);
		if(CollectionUtils.isNotEmpty(tGiftVos) && tGiftVos.size() == 1){
			mmap.put("tGiftVo", tGiftVos.get(0));
		}
		mmap.put("goods", goods);
		return prefix + "/edit";
	}

	/**
	 *  查看礼物机
	 */
	@GetMapping("/sele")
	public String seleTGift(String id, ModelMap mmap)
	{
		TGiftVo tGiftVo = tGiftService.selectTGiftVoById(id);
		List<TCommodityInformationVo> tCommodityInformationVos = tCommodityInformationService.getTCommodityInformationVosByGiftId(id);
		mmap.put("tGiftVo", tGiftVo);
		mmap.put("tCommodityInformationVos", tCommodityInformationVos);
		return prefix + "/seleTGift";
	}

	/**
	 * 修改保存礼物机
	 */
	@RequiresPermissions("web:tGift:edit")
	@Log(title = "礼物机", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TGiftParam tGiftParam)
	{
		return toAjax(tGiftService.updateTGiftGoodsInfo(tGiftParam));
	}

	/**
	 * 修改礼物机  下架
	 */
	@RequiresPermissions("web:tGift:edit")
	@Log(title = "礼物机", businessType = BusinessType.UPDATE)
	@PostMapping("/editState")
	@ResponseBody
	public AjaxResult editState(String id)
	{
		try {
			TGift tGift = new TGift();
			tGift.setId(id);
			tGift.setState("2");
			return toAjax(tGiftService.updateTGiftState(tGift));
		} catch (Exception e) {
			return error("礼物机下架失败");
		}
	}

	/**
	 * 删除礼物机
	 */
	@RequiresPermissions("web:tGift:remove")
	@Log(title = "礼物机", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(tGiftService.deleteTGiftByIds(ids));
	}

	/**
	 * 修改礼物机
	 */
	@GetMapping("/editGift/{id}")
	public String editGift(@PathVariable("id") String id, ModelMap mmap)
	{
		TGift tGift = tGiftService.selectTGiftByGiftId(id);
		mmap.put("tGift", tGift);
		return prefix + "/editGift";
	}
	
	/**
	 * 修改保存礼物机
	 */
	@RequiresPermissions("web:tGift:edit")
	@Log(title = "礼物机修改", businessType = BusinessType.UPDATE)
	@PostMapping("/editGift")
	@ResponseBody
	public AjaxResult editGift(TGift tGift)
	{		
		return toAjax(tGiftService.updateTGift(tGift));
	}
}

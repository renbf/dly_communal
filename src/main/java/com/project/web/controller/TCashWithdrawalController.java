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
import com.project.common.result.Result;
import com.project.framework.web.page.TableDataInfo;
import com.project.system.core.base.BaseController;
import com.project.util.UUIDUtil;
import com.project.web.domain.TCashWithdrawal;
import com.project.web.domain.vo.TCashWithdrawalVo;
import com.project.web.service.ITCashWithdrawalService;

/**
 * 提现申请 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/tCashWithdrawal")
public class TCashWithdrawalController extends BaseController
{
    private String prefix = "web/tCashWithdrawal";
	
	@Autowired
	private ITCashWithdrawalService tCashWithdrawalService;
	
	@RequiresPermissions("web:tCashWithdrawal:view")
	@GetMapping()
	public String tCashWithdrawal()
	{
	    return prefix + "/tCashWithdrawal";
	}
	
	/**
	 * 查询提现申请列表
	 */
	@RequiresPermissions("web:tCashWithdrawal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TCashWithdrawal tCashWithdrawal)
	{
		startPage();
        List<TCashWithdrawalVo> list = tCashWithdrawalService.selectTCashWithdrawalList(tCashWithdrawal);
		return getDataTable(list);
	}
	
	/**
	 * 新增提现申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存提现申请
	 */
	@RequiresPermissions("web:tCashWithdrawal:add")
	@Log(title = "提现申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TCashWithdrawal tCashWithdrawal)
	{		
		tCashWithdrawal.setId(UUIDUtil.getUUID());
		return toAjax(tCashWithdrawalService.insertTCashWithdrawal(tCashWithdrawal));
	}

	/**
	 * 修改提现申请
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		TCashWithdrawal tCashWithdrawal = tCashWithdrawalService.selectTCashWithdrawalById(id);
		mmap.put("tCashWithdrawal", tCashWithdrawal);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存提现申请
	 */
	@RequiresPermissions("web:tCashWithdrawal:edit")
	@Log(title = "提现申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TCashWithdrawal tCashWithdrawal)
	{		
		try {
//			AliPayUtil.getUserInfoAuth();
			DataResult result = tCashWithdrawalService.updateTCashWithdrawal(tCashWithdrawal);
			if(Result.SUCCESS.contentEquals(result.getStatus())) {
				return success(result.getMessage());
			}else {
				return error("提现申请异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error("提现申请异常");
		}
	}
	
	/**
	 * 删除提现申请
	 */
	@RequiresPermissions("web:tCashWithdrawal:remove")
	@Log(title = "提现申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tCashWithdrawalService.deleteTCashWithdrawalByIds(ids));
	}
	
}

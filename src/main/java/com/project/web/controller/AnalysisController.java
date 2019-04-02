package com.project.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.project.system.core.base.BaseController;
import com.project.web.domain.TOrder;
import com.project.web.service.ITUserInfoService;

/**
 * 订单 信息操作处理
 * 
 * @author lws
 * @date 2019-03-12
 */
@Controller
@RequestMapping("/web/analysis")
public class AnalysisController extends BaseController
{
    private String prefix = "web/analysis";
	
	@Autowired
	private ITUserInfoService tUserInfoService;
	
	@RequiresPermissions("web:analysis:view")
	@GetMapping()
	public String tOrder()
	{
	    return prefix + "/userAnalysis";
	}
	
	/**
	 * 查询订单列表
	 */
	@RequiresPermissions("web:analysis:list")
	@PostMapping("/list")
	@ResponseBody
	public Result list(TOrder tOrder)
	{
		DataResult result = new DataResult();
		Map<String,List> map = tUserInfoService.reportRegisterCount();
		result.setMessage("查询成功");
		result.setStatus(Result.SUCCESS);
		result.setResult(map);
		return result;
	}
	
public static void main(String[] args) {
	double d = Math.random();
	int n=(int)(d*2);
	System.out.println(d);
	System.out.println(n);
}
	
}

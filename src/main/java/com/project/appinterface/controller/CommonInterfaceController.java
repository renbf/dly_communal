package com.project.appinterface.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.appinterface.service.CommonInterfaceService;
import com.project.appinterface.service.IWinningRecordService;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;


/**
 *
 * @author 张鹏浩
 * @ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”；produces=“media类型”；)
 * @ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”)
 */

@Controller
@RequestMapping("gift/commonInterface")
@Api(value="公共",description="公共")
public class CommonInterfaceController {
	@Autowired
	IWinningRecordService winningRecordService;
	@Autowired
	CommonInterfaceService commonInterfaceService;
	/********************************      领礼物      **********************************/
	/**
	 * 查询中奖纪录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryPrizeRecord",method=RequestMethod.GET)
	@ApiOperation(value="查询中奖公告信息",httpMethod="GET",response=Result.class)
	public @ResponseBody DataResult queryPrizeRecord(HttpServletRequest request
	){
		DataResult result=new DataResult();
		result=winningRecordService.selectWinningRecordList();
		return result;
	}
	/**
	 * 查询省市县数据
	 * @param request
	 * @return
			 */
	@ApiOperation(value = "查询省市县数据", httpMethod="GET",response=Result.class )
	@RequestMapping(value="/getAddress",method=RequestMethod.GET)
	public @ResponseBody DataResult getAddress(HttpServletRequest request,
											   @ApiParam(name="code",value="代码（默认100000查询省）",required=true)@RequestParam("code")String code){
		DataResult result =new DataResult();
		result = commonInterfaceService.getAddress(code);
		return result;
	}

}


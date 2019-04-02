package com.project.system.controller.system;

import com.github.pagehelper.PageInfo;
import com.project.common.base.AjaxResult;
import com.project.system.core.base.BaseController;
import com.project.system.domain.SysOperLog;
import com.project.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/system/log")
@Controller
public class SysOperLogController extends BaseController {
    @Autowired
    private ISysOperLogService sysOperLogService;

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(SysOperLog sysOperLog) {
        startPage();
        List<SysOperLog> list = sysOperLogService.selectOperLogList(sysOperLog);
        AjaxResult ajaxResult= AjaxResult.success();
        ajaxResult.put("data",new PageInfo(list));
        return ajaxResult;
    }


}

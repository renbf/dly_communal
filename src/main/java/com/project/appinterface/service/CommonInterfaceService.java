package com.project.appinterface.service;

import com.project.appinterface.domain.Order;
import com.project.common.result.DataResult;

import java.util.List;

/**
 * 公共 服务层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface CommonInterfaceService
{
	/**
	 * 查询省市县数据
	 * @param code
	 * @return
	 */
	public DataResult getAddress(String code);
	
}

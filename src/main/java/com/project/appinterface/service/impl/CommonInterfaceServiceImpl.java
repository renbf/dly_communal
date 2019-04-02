package com.project.appinterface.service.impl;

import com.project.appinterface.domain.SysAreas;
import com.project.appinterface.mapper.CommodityInformationMapper;
import com.project.appinterface.mapper.CommonInterfaceMapper;
import com.project.appinterface.mapper.OrderMapper;
import com.project.appinterface.service.CommonInterfaceService;
import com.project.common.result.DataResult;
import com.project.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单 服务层实现
 * 
 * @author lws
 * @date 2019-03-05
 */
@Service
public class CommonInterfaceServiceImpl implements CommonInterfaceService
{
	@Autowired
	private CommonInterfaceMapper commonInterfaceMapper;

	@Override
	public DataResult getAddress(String code) {
		DataResult result=new DataResult();
		try {
			List<Map<String,String>> returnlist=new ArrayList<Map<String,String>>();
			Map<String,String> map=new HashMap<String,String>();
			map.put("parent_id", code);
			List<SysAreas> salist=commonInterfaceMapper.getAddress(map);
			for(SysAreas as :salist) {
				Map<String,String> asmap=new HashMap<String,String>();
				asmap.put("code", as.getId());
				asmap.put("lng", as.getLng());
				asmap.put("lat", as.getLat());
				asmap.put("name", as.getName());
				asmap.put("shortName", as.getShort_name());
				returnlist.add(asmap);
			}
			result.setResult(returnlist);
			result.setMessage("查询成功");
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			result.setMessage("查询失败");
			result.setStatus(Result.SUCCESS);
			e.printStackTrace();
		}
		return result;
	}
}

package com.project.appinterface.mapper;

import com.project.appinterface.domain.CommodityInformation;
import com.project.appinterface.domain.SysAreas;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 公共 数据层
 * 
 * @author lws
 * @date 2019-03-04
 */
public interface CommonInterfaceMapper
{
	/**
	 * 查询省市县数据
	 * @param map
	 * @return
	 */
	List<SysAreas> getAddress(Map<String,String> map);
	
	SysAreas getAddressById(String id);
}
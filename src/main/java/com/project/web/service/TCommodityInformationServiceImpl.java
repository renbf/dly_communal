package com.project.web.service;

import java.util.List;

import com.project.web.domain.TCommodityInformation;
import com.project.web.domain.vo.TCommodityInformationVo;
import com.project.web.mapper.TCommodityInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.support.Convert;

/**
 * 商品 服务层实现
 * 
 * @author lws
 * @date 2019-03-12
 */
@Service
public class TCommodityInformationServiceImpl implements ITCommodityInformationService 
{
	@Autowired
	private TCommodityInformationMapper tCommodityInformationMapper;

	/**
     * 查询商品信息
     * 
     * @param id 商品ID
     * @return 商品信息
     */
    @Override
	public TCommodityInformation selectTCommodityInformationById(String id)
	{
	    return tCommodityInformationMapper.selectTCommodityInformationById(id);
	}

	/**
     * 查询商品列表
     * 
     * @param tCommodityInformation 商品信息
     * @return 商品集合
     */
	@Override
	public List<TCommodityInformation> selectTCommodityInformationList(TCommodityInformation tCommodityInformation)
	{
	    return tCommodityInformationMapper.selectTCommodityInformationList(tCommodityInformation);
	}
	
    /**
     * 新增商品
     * 
     * @param tCommodityInformation 商品信息
     * @return 结果
     */
	@Override
	public int insertTCommodityInformation(TCommodityInformation tCommodityInformation)
	{
	    return tCommodityInformationMapper.insertTCommodityInformation(tCommodityInformation);
	}
	
	/**
     * 修改商品
     * 
     * @param tCommodityInformation 商品信息
     * @return 结果
     */
	@Override
	public int updateTCommodityInformation(TCommodityInformation tCommodityInformation)
	{
	    return tCommodityInformationMapper.updateTCommodityInformation(tCommodityInformation);
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTCommodityInformationByIds(String ids)
	{
		return tCommodityInformationMapper.deleteTCommodityInformationByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<TCommodityInformationVo> getTCommodityInformationVosByGiftId(String giftId) {
		return tCommodityInformationMapper.getTCommodityInformationVosByGiftId(giftId);
	}
	
}

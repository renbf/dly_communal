package com.project.appinterface.service;

import com.project.appinterface.domain.GiftApply;
import java.util.List;

/**
 * 礼物机申请 服务层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface IGiftApplyService 
{
	/**
     * 查询礼物机申请信息
     * 
     * @param id 礼物机申请ID
     * @return 礼物机申请信息
     */
	public GiftApply selectGiftApplyById(String id);
	
	/**
     * 查询礼物机申请列表
     * 
     * @param giftApply 礼物机申请信息
     * @return 礼物机申请集合
     */
	public List<GiftApply> selectGiftApplyList(GiftApply giftApply);
	
	/**
     * 新增礼物机申请
     * 
     * @param giftApply 礼物机申请信息
     * @return 结果
     */
	public int insertGiftApply(GiftApply giftApply);
	
	/**
     * 修改礼物机申请
     * 
     * @param giftApply 礼物机申请信息
     * @return 结果
     */
	public int updateGiftApply(GiftApply giftApply);
		
	/**
     * 删除礼物机申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGiftApplyByIds(String ids);
	
}

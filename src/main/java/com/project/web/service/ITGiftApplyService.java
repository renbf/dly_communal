package com.project.web.service;

import com.project.web.domain.GiftApplyVo;
import com.project.web.domain.TGiftApply;

import java.util.List;

/**
 * 礼物机申请 服务层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface ITGiftApplyService 
{
	/**
     * 查询礼物机申请信息
     * 
     * @param id 礼物机申请ID
     * @return 礼物机申请信息
     */
	public TGiftApply selectTGiftApplyById(String id);

	/**
	 * 查询礼物机申请信息
	 *
	 * @param giftId 礼物机申请ID
	 * @return 礼物机申请信息
	 */
	public TGiftApply selectTGiftApplyByTGiftId(String giftId);

	/**
     * 查询礼物机申请列表
     * 
     * @param giftApplyVo 礼物机申请信息
     * @return 礼物机申请集合
     */
	public List<GiftApplyVo> selectTGiftApplyList(GiftApplyVo giftApplyVo);

	/**
	 * 查询礼物机申请列表
	 *
	 * @param tGiftApply 礼物机申请信息
	 * @return 礼物机申请集合
	 */
	public List<TGiftApply> selectTGiftApplyBYList(TGiftApply tGiftApply);

	/**
	 * 查询礼物机里的礼物
	 *
	 * @param giftId 礼物机ID
	 * @return 礼物机所拥有的礼物
	 */
	public List<TGiftApply> selectTGiftApplyIntroduce(String giftId);

	/**
     * 新增礼物机申请
     * 
     * @param tGiftApply 礼物机申请信息
     * @return 结果
     */
	public int insertTGiftApply(TGiftApply tGiftApply);
	
	/**
     * 修改礼物机申请
     * 
     * @param tGiftApply 礼物机申请信息
     * @return 结果
     */
	public int updateTGiftApply(TGiftApply tGiftApply);
		
	/**
     * 删除礼物机申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTGiftApplyByIds(String ids);
	/**
	 * 审核
	 * @param id
	 * @param state
	 * @return
	 */
	public int examine(String id,String state);
	
}

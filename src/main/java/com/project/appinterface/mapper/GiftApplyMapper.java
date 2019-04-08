package com.project.appinterface.mapper;

import com.project.appinterface.domain.GiftApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 礼物机申请 数据层
 * 
 * @author lws
 * @date 2019-03-05
 */
public interface GiftApplyMapper 
{
	/**
     * 查询礼物机申请信息
     * 
     * @param id 礼物机申请ID
     * @return 礼物机申请信息
     */
	public GiftApply selectGiftApplyById(@Param("id")String id);
	/**
	 * 查询礼物机申请信息
	 *查询状态为0和1的
	 * @param giftId 礼物机申请ID
	 * @return 礼物机申请信息
	 */
	public GiftApply selectGiftApplyByGiftId(@Param("giftId")String giftId,@Param("userId") String userId);

	
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
     * 删除礼物机申请
     * 
     * @param id 礼物机申请ID
     * @return 结果
     */
	public int deleteGiftApplyById(@Param("giftId")String giftId);
	
	/**
     * 批量删除礼物机申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGiftApplyByIds(String[] ids);
	/**
	 * 同一个人申请同一个礼物机
	 * @param giftId
	 * @param userId
	 * @return
	 */
	public Integer checkIsApply(@Param("giftId")String giftId,@Param("userId")String userId);
	/**
	 * 礼物机审核通过的
	 * @param giftId
	 * @return
	 */
	public Integer checkIsApplyByGiftId(@Param("giftId")String giftId);
	/**
	 * 查询过期的礼物机
	 * @return
	 */
	public List<GiftApply> selectGiftIdOverdue();
	
}
package com.project.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.web.domain.TGift;
import com.project.web.domain.vo.TGiftVo;

/**
 * 礼物机 数据层
 * 
 * @author lws
 * @date 2019-03-12
 */
public interface TGiftMapper 
{
	/**
     * 查询礼物机信息
     * 
     * @param id 礼物机ID
     * @return 礼物机信息
     */
	public TGift selectTGiftById(String id);

		/**
     * 查询礼物机类型
     *
     * @return 礼物机所有类型
     */
	public List<TGift> selectTGiftListModel();

	/**
     * 查询礼物机列表
     * 
     * @param tGift 礼物机信息
     * @return 礼物机集合
     */
	public List<TGiftVo> selectTGiftList(TGift tGift);
	
	/**
     * 新增礼物机
     * 
     * @param tGift 礼物机信息
     * @return 结果
     */
	public int insertTGift(TGift tGift);
	
	/**
     * 修改礼物机
     * 
     * @param tGift 礼物机信息
     * @return 结果
     */
	public int updateTGift(TGift tGift);

	/**
	 * 修改礼物机  下架
	 *
	 * @param tGift 礼物机信息
	 * @return 结果
	 */
	public int updateTGiftState(TGift tGift);

	/**
     * 删除礼物机
     * 
     * @param id 礼物机ID
     * @return 结果
     */
	public int deleteTGiftById(String id);
	
	/**
     * 批量删除礼物机
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTGiftByIds(String[] ids);
	/**
	 * 礼物机详情信息
	 * @param id
	 * @return
	 */
	public TGiftVo selectTGiftVoById(String id);
	/**
	 * 根据经纬度查询
	 * @param lng
	 * @param lat
	 * @return
	 */
	public TGift selectTGiftByLngLat(@Param("lng") String lng,@Param("lat")String lat);
	/**
	 * 查询申请中的礼物机
	 * @param giftId
	 * @return
	 */
	public List<TGiftVo> selectTGiftsByGiftId(String giftId);
	
}
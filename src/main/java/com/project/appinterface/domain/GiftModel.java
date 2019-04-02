package com.project.appinterface.domain;

import com.project.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

 public class GiftModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    private String id;
    /** 格子数 */
    private Integer lattice_num;
    /** 礼物机图片 */
    private String gift_picture;
    /** 创建时间 */
    private Date createDate;
    /** 状态0使用中1未使用2已作废 */
    private String state;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public Integer getLattice_num() {
         return lattice_num;
     }

     public void setLattice_num(Integer lattice_num) {
         this.lattice_num = lattice_num;
     }

     public String getGift_picture() {
         return gift_picture;
     }

     public void setGift_picture(String gift_picture) {
         this.gift_picture = gift_picture;
     }

     public Date getCreateDate() {
         return createDate;
     }

     public void setCreateDate(Date createDate) {
         this.createDate = createDate;
     }

     public String getState() {
         return state;
     }

     public void setState(String state) {
         this.state = state;
     }
 }

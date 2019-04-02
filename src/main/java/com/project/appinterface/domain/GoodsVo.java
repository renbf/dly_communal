package com.project.appinterface.domain;

import java.io.Serializable;

public class  GoodsVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2929613195511571321L;
	//商品id
    private String goodsId;
    //商品名称
    private String goodsName;
    //商品价格
    private String price;
    //商品数量
    private int goodsNumber;
    //图片
    private String picture;
    //移动端选中状态
    private boolean isSelector = false;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public void setSelector(boolean selector) {
        isSelector = selector;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

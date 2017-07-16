package com.pyf.latte.ec.main.sort.content;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/16
 */
public class SectionContentItemEntity {

    private int goodsId;
    private String goodsName;
    private String goodsThumb;

    public SectionContentItemEntity() {
    }

    public SectionContentItemEntity(int goodsId, String goodsName, String goodsThumb) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsThumb = goodsThumb;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb;
    }
}
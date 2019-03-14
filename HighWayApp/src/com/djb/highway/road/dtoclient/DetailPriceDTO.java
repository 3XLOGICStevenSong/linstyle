package com.djb.highway.road.dtoclient;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年8月6日 上午10:33:40
 */
public class DetailPriceDTO {

    /**
     * 图片的url
     */
    private String pic_url;

    /**
     * 商品名字
     */
    private String goods_name;

    /**
     * 商品类型
     */
    private String goods_type;

    /**
     * 商品价格
     */
    private String goods_price;

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

}

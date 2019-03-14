package com.djb.highway.road.dtoclient;

import java.util.List;

/**
 * 类说明
 * 
 * @author LiWm E-mail:
 * @version 创建时间：2014年8月6日 上午10:33:40
 */
public class DetailPriceBaseDTO extends BaseClientDTO {
    List<DetailPriceDTO> list;

    public List<DetailPriceDTO> getList() {
        return list;
    }

    public void setList(List<DetailPriceDTO> list) {
        this.list = list;
    }

}

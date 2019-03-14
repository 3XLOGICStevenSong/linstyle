package cn.com.dbridge.jtraining.framework.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TrainCategoryVO{
    /**
     * トレーニング種別
     */
    private Integer trainType;

    /**
     * 種別名称
     */
    private String typeName;

    /**
     * 記録時間
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    
    /**
     * 有効フラグ(0:有効 1:無効)
     */
    private Byte useStatus;
}
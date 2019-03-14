package cn.com.dbridge.jtraining.dao.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
@Data
public class TrainCategoryPO implements Serializable {
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
    private Date insertTime;

    /**
     * 有効フラグ(0:有効 1:無効)
     */
    private Byte useStatus;

    /**
     * 作成日
     */
    private Timestamp insertDate;

    /**
     * 作成人
     */
    private String insertPerson;

    /**
     * 更新日
     */
    private Timestamp updateDate;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * t_train_category
     */
    private static final long serialVersionUID = 1L;

}
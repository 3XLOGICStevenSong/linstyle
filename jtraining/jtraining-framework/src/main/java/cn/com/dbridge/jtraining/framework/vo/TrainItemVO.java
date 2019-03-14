package cn.com.dbridge.jtraining.framework.vo;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TrainItemVO {

    /**
     * トレーニングID
     */
    private Integer trainId;

    /**
     * トレーニング種別
     */
    private Integer trainType;

    /**
     * トレーニングタイトル
     */
    private String trainTitle;

    /**
     * トレーニング教师
     */
    private String trainTeacher;

    /**
     * トレーニング紹介
     */
    private String trainDesc;

    /**
     * トレーニング画像
     */
    private String trainDraw;

    /**
     * 更新日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    private long count;

    /**
     * データ収集
     */
    private List<TrainMaterialVO> trainMaterialVOList;
    
    private List<TeacherInformation> teacherInformationVOList;
    
    private List<TrainCategoryInformationVO> trainCategoryInformationVOList;
}
package cn.com.dbridge.lifecare.dao.po;

import java.sql.Timestamp;

import lombok.Data;

/**
 * t_home_page_info
 * @author 
 */
@Data
public class HomePageInfoPO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题1
     */
    private String title1;

    /**
     * 标题2
     */
    private String title2;

    /**
     * 标题3
     */
    private String title3;

    /**
     * 标题4
     */
    private String title4;
    
    /**
     * 内容1
     */
    private String content1;

    /**
     * 内容2
     */
    private String content2;

    /**
     * 内容3
     */
    private String content3;

    /**
     * 内容4
     */
    private String content4;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 更新人
     */
    private Integer updateBy;

}
/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.com.dbridge.lifecare.framework.dto.web;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 操作日志查询DTO
 */
@Data
public class OperatorLogQueryDTO {

    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String module;

    /**
     * 方法名称
     */
    @ApiModelProperty(value = "方法名称")
    private String method;

    /**
     * 编码 0:请求成功 1：请求失败
     */
    @ApiModelProperty(value = "编码 0:请求成功 1：请求失败")
    private Byte code;

    /**
     * 员工号
     */
    @ApiModelProperty(value = "员工号")
    private String userCode;

    /**
     * 操作人IP
     */
    @ApiModelProperty(value = "操作人IP")
    private String ip;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数")
    private Integer offset;

    /**
     * 每页显示数
     */
    @ApiModelProperty(value = "每页显示数")
    private Integer limit;

}
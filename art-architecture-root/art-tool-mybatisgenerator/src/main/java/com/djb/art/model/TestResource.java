package com.djb.art.model;

import java.util.Date;

public class TestResource {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.NAME
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.DESCRIPTION
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.TEST
     *
     * @mbg.generated
     */
    private String test;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.RESULT
     *
     * @mbg.generated
     */
    private String result;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.STATUS
     *
     * @mbg.generated
     */
    private Boolean status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.CREATE_TIME
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_test_resource.UPDATE_TIME
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.ID
     *
     * @return the value of sys_test_resource.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.ID
     *
     * @param id the value for sys_test_resource.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.NAME
     *
     * @return the value of sys_test_resource.NAME
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.NAME
     *
     * @param name the value for sys_test_resource.NAME
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.DESCRIPTION
     *
     * @return the value of sys_test_resource.DESCRIPTION
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.DESCRIPTION
     *
     * @param description the value for sys_test_resource.DESCRIPTION
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.TEST
     *
     * @return the value of sys_test_resource.TEST
     *
     * @mbg.generated
     */
    public String getTest() {
        return test;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.TEST
     *
     * @param test the value for sys_test_resource.TEST
     *
     * @mbg.generated
     */
    public void setTest(String test) {
        this.test = test == null ? null : test.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.RESULT
     *
     * @return the value of sys_test_resource.RESULT
     *
     * @mbg.generated
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.RESULT
     *
     * @param result the value for sys_test_resource.RESULT
     *
     * @mbg.generated
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.STATUS
     *
     * @return the value of sys_test_resource.STATUS
     *
     * @mbg.generated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.STATUS
     *
     * @param status the value for sys_test_resource.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.CREATE_TIME
     *
     * @return the value of sys_test_resource.CREATE_TIME
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.CREATE_TIME
     *
     * @param createTime the value for sys_test_resource.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_test_resource.UPDATE_TIME
     *
     * @return the value of sys_test_resource.UPDATE_TIME
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_test_resource.UPDATE_TIME
     *
     * @param updateTime the value for sys_test_resource.UPDATE_TIME
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
package com.djb.ylt.user.dtoclient;


import com.djb.ylt.framework.dto.BaseClientDTO;





public class CommentClientDTO  extends BaseClientDTO{
	

	

    private String name;

    private String grade;

    private String createTime ;
    
    private String content;

	/**
	 * 返回name的值
	 * @return String name的值
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name的值
	 * @param  name name的值
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回grade的值
	 * @return String grade的值
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * 设置grade的值
	 * @param  grade grade的值
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * 返回createTime的值
	 * @return String createTime的值
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime的值
	 * @param  createTime createTime的值
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回content的值
	 * @return String content的值
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置content的值
	 * @param  content content的值
	 */
	public void setContent(String content) {
		this.content = content;
	}
    
  


    
}
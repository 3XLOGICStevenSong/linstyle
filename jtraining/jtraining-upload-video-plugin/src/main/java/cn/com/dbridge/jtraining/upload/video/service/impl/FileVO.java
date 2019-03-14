/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  FilePdfVO.java
 * @Package cn.com.dbridge.jtraining.framework.vo
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月28日 下午1:29:47
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.upload.video.service.impl;

/**
 * @ClassName:  FilePdfVO
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月28日 下午1:29:47
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */

public class FileVO {

    private String name;
    private Integer page;
    private long sencond;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public long getSencond() {
        return sencond;
    }
    public void setSencond(long sencond) {
        this.sencond = sencond;
    }

}

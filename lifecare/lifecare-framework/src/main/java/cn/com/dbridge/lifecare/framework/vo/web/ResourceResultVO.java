package cn.com.dbridge.lifecare.framework.vo.web;

import java.io.Serializable;

import com.alibaba.fastjson.JSONArray;

import lombok.Data;

/**
 * 
 * @ClassName:  ResourcePO
 * @Description: 资源PO
 * @author: linh
 * @date:   2018年12月26日 下午1:05:50
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class ResourceResultVO implements Serializable {


    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 957878591829432822L;
    private JSONArray resourceList;
}

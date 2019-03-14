package cn.com.dbridge.lifecare.dao.po;

import java.io.Serializable;

import lombok.Data;
/**
 * 
 * @ClassName:  DictTypePO
 * @Description:数据字典PO
 * @author: 陈健飞
 * @date:   2019年1月15日 下午7:09:52
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class DictTypePO implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 类别编码
     */
    private String code;

    /**
     * 类别名称
     */
    private String name;

    /**
     * t_dict_type
     */
    private static final long serialVersionUID = 1L;
}
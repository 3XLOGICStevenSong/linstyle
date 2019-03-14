package cn.com.dbridge.lifecare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.DictTypePO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileDictTypeDTO;
import cn.com.dbridge.lifecare.service.DictTypeService;
/**
 * 
 * @ClassName:  DictTypeServiceImpl
 * @Description:类别字典Service
 * @author: 陈健飞
 * @date:   2018年12月27日 下午7:01:50
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class DictTypeServiceImpl implements DictTypeService{

    @Override
    public int deleteDictTypeById(Integer id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int add(MobileDictTypeDTO mobileDictTypeDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public DictTypePO selectDictTypeById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DictTypePO> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateDictType(MobileDictTypeDTO mobileDictTypeDTO) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
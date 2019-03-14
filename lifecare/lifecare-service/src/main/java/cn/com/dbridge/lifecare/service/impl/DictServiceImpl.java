package cn.com.dbridge.lifecare.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.DictPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileDictDTO;
import cn.com.dbridge.lifecare.service.DictService;
/**
 * 
 * @ClassName:  DictServiceImpl
 * @Description:字典Service
 * @author: 陈健飞
 * @date:   2018年12月27日 下午7:02:04
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class DictServiceImpl implements DictService {

    @Override
    public int deleteDictById(Integer id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int add(MobileDictDTO mobileDictDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public DictPO selectDictById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DictPO> selectAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int updateDict(MobileDictDTO mobileDictDTO) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
package cn.com.dbridge.lifecare.service;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.DictTypePO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileDictTypeDTO;

public interface DictTypeService {
    int deleteDictTypeById(Integer id);

    int add(MobileDictTypeDTO mobileDictTypeDTO);

    DictTypePO selectDictTypeById(Integer id);

    List<DictTypePO> selectAll();

    int updateDictType(MobileDictTypeDTO mobileDictTypeDTO);
}
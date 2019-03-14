package cn.com.dbridge.lifecare.service;

import java.util.List;

import cn.com.dbridge.lifecare.dao.po.DictPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileDictDTO;

public interface DictService {
    int deleteDictById(Integer id);

    int add(MobileDictDTO mobileDictDTO);

    DictPO selectDictById(Integer id);

    List<DictPO> selectAll();

    int updateDict(MobileDictDTO mobileDictDTO);
}
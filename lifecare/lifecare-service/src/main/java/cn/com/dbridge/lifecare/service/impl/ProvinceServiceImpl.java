package cn.com.dbridge.lifecare.service.impl;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.ProvincePO;
import cn.com.dbridge.lifecare.dao.respository.ProvincePOMapper;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileProvinceDTO;
import cn.com.dbridge.lifecare.service.ProvinceService;
@Service
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    private ProvincePOMapper provincePOMapper;
    
    @Override
    public int deleteProvinceById(Integer provinceId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int add(MobileProvinceDTO mobileProvinceDTO) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ProvincePO getProvinceById(Integer provinceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProvincePO> queryAll() {
        List<ProvincePO> provincePOList = provincePOMapper.selectAll();
        if (CollectionUtils.isEmpty(provincePOList)) {
            return null;
        }
        return provincePOList;
    }

    @Override
    public int updateProvince(MobileProvinceDTO record) {
        // TODO Auto-generated method stub
        return 0;
    }

}
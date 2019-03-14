package cn.com.dbridge.lifecare.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.com.dbridge.lifecare.dao.po.WebUserManagePO;
import cn.com.dbridge.lifecare.dao.respository.OperatorLogPOMapper;
import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dto.web.OperatorLogQueryDTO;
import cn.com.dbridge.lifecare.framework.vo.web.OperatorLogVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.lifecare.dao.po.OperatorLogPO;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileOperatorLogDTO;
import cn.com.dbridge.lifecare.service.OperatorLogService;
@Service
public class OperatorLogServiceImpl implements OperatorLogService{
    @Autowired
    private OperatorLogPOMapper operatorLogPOMapper;
    @Override
    public int deleteOperatorLogById(Long id) {
        return operatorLogPOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int add(MobileOperatorLogDTO mobileOperatorLogDTO) {
        OperatorLogPO operatorLogPO = new OperatorLogPO();
        BeanUtils.copyProperties(mobileOperatorLogDTO,operatorLogPO);
        return operatorLogPOMapper.insert(operatorLogPO);
    }

    @Override
    public OperatorLogVO getOperatorLogById(Long id) {
        OperatorLogPO operatorLogPO = operatorLogPOMapper.selectByPrimaryKey(id);
        OperatorLogVO OperatorLogVO = new OperatorLogVO();
        BeanUtils.copyProperties(operatorLogPO,OperatorLogVO);
        return OperatorLogVO;
    }

    @Override
    public List<OperatorLogVO> queryAll() {
        List<OperatorLogPO> operatorLogPOList = operatorLogPOMapper.selectAll();
        OperatorLogVO operatorLogVO = null;
        List<OperatorLogVO> operatorLogVOList = new ArrayList<OperatorLogVO>();
        for(OperatorLogPO operatorLogPO:operatorLogPOList ){
            operatorLogVO = new OperatorLogVO();
            BeanUtils.copyProperties(operatorLogPO,operatorLogVO);
            operatorLogVOList.add(operatorLogVO);
        }
        return operatorLogVOList;
    }

    @Override
    public WebPageResult<List<OperatorLogVO>> queryLogInfo(OperatorLogQueryDTO operatorLogQueryDTO) throws ParseException {
    	OperatorLogPO reqPO = new OperatorLogPO();
        BeanUtils.copyProperties(operatorLogQueryDTO,reqPO);
        //页面处理结果
        WebPageResult<List<OperatorLogVO>> webPageResult = new WebPageResult<List<OperatorLogVO>>();
        List<OperatorLogVO> operatorLogVOList = new ArrayList<OperatorLogVO>();
        //分页数据
        Page<WebUserManagePO> pages = null;
        if (operatorLogQueryDTO.getOffset() != null && operatorLogQueryDTO.getLimit() != null) {
            pages = PageHelper.offsetPage(operatorLogQueryDTO.getOffset(), operatorLogQueryDTO.getLimit());
        }
        List<OperatorLogPO> operatorLogPOList = operatorLogPOMapper.selectLogInfo(reqPO);
        OperatorLogVO operatorLogVO = null;
        for (OperatorLogPO operatorLogPO : operatorLogPOList) {
            operatorLogVO = new OperatorLogVO();
            BeanUtils.copyProperties(operatorLogPO, operatorLogVO);
            operatorLogVOList.add(operatorLogVO);
        }
        webPageResult.setRows(operatorLogVOList);
        if (operatorLogQueryDTO.getOffset() != null && operatorLogQueryDTO.getLimit() != null) {
            webPageResult.setTotal(pages.getTotal());
        }
        return webPageResult;
    }
}
package cn.com.dbridge.lifecare.service;

import java.text.ParseException;
import java.util.List;

import cn.com.dbridge.lifecare.framework.base.WebPageResult;
import cn.com.dbridge.lifecare.framework.dto.mobile.MobileOperatorLogDTO;
import cn.com.dbridge.lifecare.framework.dto.web.OperatorLogQueryDTO;
import cn.com.dbridge.lifecare.framework.vo.web.OperatorLogVO;

public interface OperatorLogService {
    int deleteOperatorLogById(Long id);

    int add(MobileOperatorLogDTO MobileOperatorLogDTO);

    OperatorLogVO getOperatorLogById(Long id);

    List<OperatorLogVO> queryAll();

    WebPageResult<List<OperatorLogVO>> queryLogInfo(OperatorLogQueryDTO operatorLogQueryDTO) throws ParseException;
}
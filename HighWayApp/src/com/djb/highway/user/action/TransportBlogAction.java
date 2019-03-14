package com.djb.highway.user.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.DateUtil;
import com.djb.highway.framework.action.BaseAction;
import com.djb.highway.user.dto.TransportBlogDTO;
import com.djb.highway.user.dtoclient.WeiBoClientDTO;
import com.djb.highway.user.dtoclient.WeiBoDetailDTO;
import com.djb.highway.user.entity.TransportBlogEntity;
import com.djb.highway.user.service.ITransportBlogService;

@Controller("/TransportBlog")
public class TransportBlogAction extends BaseAction {

    @SuppressWarnings("unused")
    @Autowired
    @Qualifier("iTransportBlogService")
    private ITransportBlogService iTransportBlogService;

    public TransportBlogAction() {
        super();
    }

    /**
     * 
     * @param mapping
     *            The ActionMapping used to select this instance
     * @param form
     *            The optional ActionForm bean for this request
     * @param request
     *            The servlet request we are processing
     * @param response
     *            The servlet response we are creating
     * 
     * @exception Exception
     *                if business logic throws an exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward doGetTransportBlogList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        // Form表单参数
        TransportBlogDTO transportBlogDTO = (TransportBlogDTO) form;
        // 結果取得用DTO
        WeiBoClientDTO weiBoClientDTO = new WeiBoClientDTO();
        // 参数DTO
        TransportBlogEntity paramEntity = new TransportBlogEntity();

        List<TransportBlogEntity> resultList = new ArrayList<TransportBlogEntity>();
        List<WeiBoDetailDTO> WeiBoDetailDTOList = new ArrayList<WeiBoDetailDTO>();
        paramEntity.setBlog_group(transportBlogDTO.getBlog_group());
        // CommonUtil.reflectClass(transportBlogDTO, paramEntity);
        // test数据
        // paramEntity.setBlog_group("1");
        try {
            resultList = iTransportBlogService.getTransportBlogList(paramEntity);

            for (TransportBlogEntity transportBlogEntity : resultList) {
                WeiBoDetailDTO weiBoDetailDTO = new WeiBoDetailDTO();
                // 给WeiBoDetailDTO赋值
                weiBoDetailDTO.setWeiBoContent(transportBlogEntity.getBlog_content());

                // 处理时间格式

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");

                if (transportBlogEntity.getUpdate_time() != null && DateUtil.isToday(transportBlogEntity.getUpdate_time())) {
                    weiBoDetailDTO.setPublishTime(sdf.format(transportBlogEntity.getUpdate_time()));

                } else {
                    weiBoDetailDTO.setPublishTime(sdf2.format(transportBlogEntity.getUpdate_time()));
                }

                // 将weiBoDetailDTO添加进List中
                WeiBoDetailDTOList.add(weiBoDetailDTO);

            }

            weiBoClientDTO.setList(WeiBoDetailDTOList);
        } catch (Exception e) {
            weiBoClientDTO.setReturnCode("-2300");
            transportBlogDTO.setErrFlg(true);
        }
        if (!transportBlogDTO.isErrFlg()) {
            weiBoClientDTO.setReturnCode("0");
        }
        toJson(response, weiBoClientDTO);
        return null;

    }
}
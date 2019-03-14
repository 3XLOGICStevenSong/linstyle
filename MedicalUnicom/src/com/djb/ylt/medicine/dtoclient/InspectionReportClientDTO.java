package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class InspectionReportClientDTO extends BaseClientDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<InspectionReportDetailDTO> list;

    public List<InspectionReportDetailDTO> getList() {
        return list;
    }

    public void setList(List<InspectionReportDetailDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

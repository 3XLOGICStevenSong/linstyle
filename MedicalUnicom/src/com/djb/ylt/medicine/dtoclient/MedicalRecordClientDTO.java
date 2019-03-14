package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class MedicalRecordClientDTO extends BaseClientDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<MedicalRecordDetailDTO> list;

    public List<MedicalRecordDetailDTO> getList() {
        return list;
    }

    public void setList(List<MedicalRecordDetailDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class MedicineQuestionClientDTO extends BaseClientDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<MedicineQuestionDetailDTO> list;

    public List<MedicineQuestionDetailDTO> getList() {
        return list;
    }

    public void setList(List<MedicineQuestionDetailDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

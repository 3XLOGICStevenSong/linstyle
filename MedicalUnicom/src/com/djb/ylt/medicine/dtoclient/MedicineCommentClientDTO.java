package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class MedicineCommentClientDTO extends BaseClientDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<MedicineCommentDetailDTO> list;

    public List<MedicineCommentDetailDTO> getList() {
        return list;
    }

    public void setList(List<MedicineCommentDetailDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;


public class MedicineInfoClientDTO extends BaseClientDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<MedicineInfoDetailDTO> list;

    public List<MedicineInfoDetailDTO> getList() {
        return list;
    }

    public void setList(List<MedicineInfoDetailDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

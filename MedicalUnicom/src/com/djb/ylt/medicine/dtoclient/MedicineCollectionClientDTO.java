package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class MedicineCollectionClientDTO extends BaseClientDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<MedicineCollectionDetailDTO> list;

    public List<MedicineCollectionDetailDTO> getList() {
        return list;
    }

    public void setList(List<MedicineCollectionDetailDTO> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

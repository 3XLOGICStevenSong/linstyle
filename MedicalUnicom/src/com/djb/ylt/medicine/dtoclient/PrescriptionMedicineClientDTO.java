package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class PrescriptionMedicineClientDTO extends  BaseClientDTO{
    
    private static final long serialVersionUID = 1L;
    
    List<PrescriptionMedicineDetailDTO> list;
    
    public List<PrescriptionMedicineDetailDTO> getList() {
        return list;
    }
    public void setList(List<PrescriptionMedicineDetailDTO> list) {
        this.list = list;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}

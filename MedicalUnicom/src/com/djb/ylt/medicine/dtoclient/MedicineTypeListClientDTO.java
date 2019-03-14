package com.djb.ylt.medicine.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;



public class MedicineTypeListClientDTO extends BaseClientDTO {

    private List<String> medicineTypeList;

    private List<String> medicineForIllList;

    public List<String> getMedicineTypeList() {
        return medicineTypeList;
    }

    public void setMedicineTypeList(List<String> medicineTypeList) {
        this.medicineTypeList = medicineTypeList;
    }

    public List<String> getMedicineForIllList() {
        return medicineForIllList;
    }

    public void setMedicineForIllList(List<String> medicineForIllList) {
        this.medicineForIllList = medicineForIllList;
    }

}
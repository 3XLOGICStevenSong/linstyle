package com.djb.ylt.medicine.dtoclient;



import com.djb.ylt.framework.dto.BaseClientDTO;



public class PrescriptionMedicineDetailDTO extends BaseClientDTO {

    /**
     * 
     */
    private static final long serialVersionUID = -779223920215876907L;

   

  
    
    private MedicineInfoClientDTO medicineInfoDTOs;
    
    private PrescriptionInfoDetailDTO prescriptionInfoDTO;


  

   

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

   

    public PrescriptionInfoDetailDTO getPrescriptionInfoDTO() {
        return prescriptionInfoDTO;
    }

    public void setPrescriptionInfoDTO(PrescriptionInfoDetailDTO prescriptionInfoDTO) {
        this.prescriptionInfoDTO = prescriptionInfoDTO;
    }



    public MedicineInfoClientDTO getMedicineInfoDTOs() {
        return medicineInfoDTOs;
    }



    public void setMedicineInfoDTOs(MedicineInfoClientDTO medicineInfoDTOs) {
        this.medicineInfoDTOs = medicineInfoDTOs;
    }



  

   
  


   


  
}

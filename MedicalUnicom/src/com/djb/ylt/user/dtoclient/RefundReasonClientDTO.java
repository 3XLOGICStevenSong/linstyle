package com.djb.ylt.user.dtoclient;

import com.djb.ylt.framework.dto.BaseClientDTO;


public class RefundReasonClientDTO  extends BaseClientDTO{


	private Integer reasonId;

    private String reasonContent;

  

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonContent() {
        return reasonContent;
    }

    public void setReasonContent(String reasonContent) {
        this.reasonContent = reasonContent;
    }


}
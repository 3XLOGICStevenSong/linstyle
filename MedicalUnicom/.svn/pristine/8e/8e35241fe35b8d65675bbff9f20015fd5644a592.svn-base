package com.djb.ylt.user.entity;

public class RefundReasonEntity {
    private Integer reasonId;

    private String reasonContent;

    private String reasonFlag;

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

    public String getReasonFlag() {
        return reasonFlag;
    }

    public void setReasonFlag(String reasonFlag) {
        this.reasonFlag = reasonFlag;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RefundReasonEntity other = (RefundReasonEntity) that;
        return (this.getReasonId() == null ? other.getReasonId() == null : this.getReasonId().equals(other.getReasonId()))
            && (this.getReasonContent() == null ? other.getReasonContent() == null : this.getReasonContent().equals(other.getReasonContent()))
            && (this.getReasonFlag() == null ? other.getReasonFlag() == null : this.getReasonFlag().equals(other.getReasonFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReasonId() == null) ? 0 : getReasonId().hashCode());
        result = prime * result + ((getReasonContent() == null) ? 0 : getReasonContent().hashCode());
        result = prime * result + ((getReasonFlag() == null) ? 0 : getReasonFlag().hashCode());
        return result;
    }
}
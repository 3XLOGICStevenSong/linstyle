package com.djb.ylt.main_page.entity;

public class ServiceHotlineEntity {
    private String hotLine;

    public String getHotLine() {
        return hotLine;
    }

    public void setHotline(String hotLine) {
        this.hotLine = hotLine;
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
        ServiceHotlineEntity other = (ServiceHotlineEntity) that;
        return (this.getHotLine() == null ? other.getHotLine() == null : this.getHotLine().equals(other.getHotLine()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHotLine() == null) ? 0 : getHotLine().hashCode());
        return result;
    }
}
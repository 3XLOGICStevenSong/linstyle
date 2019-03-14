package com.djb.highway.bus.dtoclient;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class BusUserClientDTO extends BaseClientDTO {

    private Integer buser_id;

    private Integer groupid;

    private String name;
    
    private String tel;

    private String o;

    private String a;
    
    private String r;
    
    private Integer uid;
    
    private Integer v;
    
    public Integer getBuser_id() {
        return buser_id;
    }

    public void setBuser_id(Integer buser_id) {
        this.buser_id = buser_id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
    	   this.tel = tel;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
    
  
}
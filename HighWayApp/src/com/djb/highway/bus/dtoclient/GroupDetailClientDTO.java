
package com.djb.highway.bus.dtoclient;

import com.djb.highway.road.dtoclient.BaseClientDTO;

public class GroupDetailClientDTO extends BaseClientDTO{

    Integer groupid;

    String name;

    String isOwen;

    String password;

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

    public String getIsOwen() {
        return isOwen;
    }

    public void setIsOwen(String isOwen) {
        this.isOwen = isOwen;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    

}


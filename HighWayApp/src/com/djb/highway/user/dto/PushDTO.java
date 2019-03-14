package com.djb.highway.user.dto;


import java.util.List;


import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.user.entity.UserEntity;

public class PushDTO extends BaseDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 6458285003292444595L;
    private Integer p_id;
    private Integer u_id;
    private Integer h_id;
    private List<PushDTO> list;
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	
	public List<PushDTO> getList() {
		return list;
	}
	public void setList(List<PushDTO> list) {
		this.list = list;
	}
	public Integer getH_id() {
		return h_id;
	}
	public void setH_id(Integer h_id) {
		this.h_id = h_id;
	}


   

}
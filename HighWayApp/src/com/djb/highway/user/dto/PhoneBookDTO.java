package com.djb.highway.user.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;
import com.djb.highway.user.entity.UserEntity;

public class PhoneBookDTO extends PageDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 6458285003292444595L;
    private Integer p_id;
    private Integer u_id;
    private String tel_num;
    private Date insert_time;
    private Date update_time;
    private String memo;
    private UserDTO userDTO;
    private List<PhoneBookDTO> list;
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
	public String getTel_num() {
		return tel_num;
	}
	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
	}
	public Date getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public List<PhoneBookDTO> getList() {
		return list;
	}
	public void setList(List<PhoneBookDTO> list) {
		this.list = list;
	}

   

}
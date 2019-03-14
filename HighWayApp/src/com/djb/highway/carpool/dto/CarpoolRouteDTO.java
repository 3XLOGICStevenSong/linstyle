package com.djb.highway.carpool.dto;

import java.util.Date;
import java.util.List;

import com.djb.highway.framework.dto.PageDTO;

public class CarpoolRouteDTO extends PageDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3370946888384377014L;

	 private Integer cr_id;

	    private Integer pr_id;

	    private Integer dr_id;

	    private String insure_id;

	    private String lottery_id;

	    private String aboard_longitude;

	    private String aboard_latitude;

	    private String debus_longitude;

	    private String debus_latitude;

	    private Date aboard_time;

	    private Date debus_time;

	    private String status_flg;

	    private String memo;

	    private Date insert_time;

	    private Date update_time;
	    
	    private List<CarpoolRouteDTO> list;  

        public Integer getCr_id() {
            return cr_id;
        }

        public void setCr_id(Integer cr_id) {
            this.cr_id = cr_id;
        }

        public Integer getPr_id() {
            return pr_id;
        }

        public void setPr_id(Integer pr_id) {
            this.pr_id = pr_id;
        }

        public Integer getDr_id() {
            return dr_id;
        }

        public void setDr_id(Integer dr_id) {
            this.dr_id = dr_id;
        }

        public String getInsure_id() {
            return insure_id;
        }

        public void setInsure_id(String insure_id) {
            this.insure_id = insure_id;
        }

        public String getLottery_id() {
            return lottery_id;
        }

        public void setLottery_id(String lottery_id) {
            this.lottery_id = lottery_id;
        }

        public String getAboard_longitude() {
            return aboard_longitude;
        }

        public void setAboard_longitude(String aboard_longitude) {
            this.aboard_longitude = aboard_longitude;
        }

        public String getAboard_latitude() {
            return aboard_latitude;
        }

        public void setAboard_latitude(String aboard_latitude) {
            this.aboard_latitude = aboard_latitude;
        }

        public String getDebus_longitude() {
            return debus_longitude;
        }

        public void setDebus_longitude(String debus_longitude) {
            this.debus_longitude = debus_longitude;
        }

        public String getDebus_latitude() {
            return debus_latitude;
        }

        public void setDebus_latitude(String debus_latitude) {
            this.debus_latitude = debus_latitude;
        }

        public Date getAboard_time() {
            return aboard_time;
        }

        public void setAboard_time(Date aboard_time) {
            this.aboard_time = aboard_time;
        }

        public Date getDebus_time() {
            return debus_time;
        }

        public void setDebus_time(Date debus_time) {
            this.debus_time = debus_time;
        }

        public String getStatus_flg() {
            return status_flg;
        }

        public void setStatus_flg(String status_flg) {
            this.status_flg = status_flg;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
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

        public static long getSerialversionuid() {
            return serialVersionUID;
        }

        public List<CarpoolRouteDTO> getList() {
            return list;
        }

        public void setList(List<CarpoolRouteDTO> list) {
            this.list = list;
        }
	    
	    

}
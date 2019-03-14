package com.djb.highway.road.entity;

import java.util.Date;


public class OverpassDetailEntity extends BaseTravelPlanEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2132012783127288471L;

	private Integer o_id;

	private Integer od_id;

	private String entry_plaz_code;

	private String exit_plaz_code;

	private String ob_name;

	private String ob_desc;

	private String ob_length;

	private String ob_status;

	private String ob_pic;

	private Integer entry_plaz_id;

	private Integer exit_plaz_id;

	private String entry_stake_id;

	private String exit_stake_id;

	private Date create_time;

	private Date update_time;

    private Integer entry_highway_id;

    private String entry_highway_code;

    private String entry_highway_name;

    private Integer exit_highway_id;

    private String exit_highway_code;

    private String exit_highway_name;
	
	
	
	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public Integer getOd_id() {
		return od_id;
	}

	public void setOd_id(Integer od_id) {
		this.od_id = od_id;
	}

	public String getEntry_plaz_code() {
		return entry_plaz_code;
	}

	public void setEntry_plaz_code(String entry_plaz_code) {
		this.entry_plaz_code = entry_plaz_code;
	}

	public String getExit_plaz_code() {
		return exit_plaz_code;
	}

	public void setExit_plaz_code(String exit_plaz_code) {
		this.exit_plaz_code = exit_plaz_code;
	}

	public String getOb_name() {
		return ob_name;
	}

	public void setOb_name(String ob_name) {
		this.ob_name = ob_name;
	}

	public String getOb_desc() {
		return ob_desc;
	}

	public void setOb_desc(String ob_desc) {
		this.ob_desc = ob_desc;
	}

	public String getOb_length() {
		return ob_length;
	}

	public void setOb_length(String ob_length) {
		this.ob_length = ob_length;
	}

	public String getOb_status() {
		return ob_status;
	}

	public void setOb_status(String ob_status) {
		this.ob_status = ob_status;
	}

	public String getOb_pic() {
		return ob_pic;
	}

	public void setOb_pic(String ob_pic) {
		this.ob_pic = ob_pic;
	}

	public Integer getEntry_plaz_id() {
		return entry_plaz_id;
	}

	public void setEntry_plaz_id(Integer entry_plaz_id) {
		this.entry_plaz_id = entry_plaz_id;
	}

	public Integer getExit_plaz_id() {
		return exit_plaz_id;
	}

	public void setExit_plaz_id(Integer exit_plaz_id) {
		this.exit_plaz_id = exit_plaz_id;
	}

	public String getEntry_stake_id() {
		return entry_stake_id;
	}

	public void setEntry_stake_id(String entry_stake_id) {
		this.entry_stake_id = entry_stake_id;
	}

	public String getExit_stake_id() {
		return exit_stake_id;
	}

	public void setExit_stake_id(String exit_stake_id) {
		this.exit_stake_id = exit_stake_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	
	public Integer getEntry_highway_id() {
        return entry_highway_id;
    }

    public void setEntry_highway_id(Integer entry_highway_id) {
        this.entry_highway_id = entry_highway_id;
    }

    public String getEntry_highway_code() {
        return entry_highway_code;
    }

    public void setEntry_highway_code(String entry_highway_code) {
        this.entry_highway_code = entry_highway_code;
    }

    public String getEntry_highway_name() {
        return entry_highway_name;
    }

    public void setEntry_highway_name(String entry_highway_name) {
        this.entry_highway_name = entry_highway_name;
    }

    public Integer getExit_highway_id() {
        return exit_highway_id;
    }

    public void setExit_highway_id(Integer exit_highway_id) {
        this.exit_highway_id = exit_highway_id;
    }

    public String getExit_highway_code() {
        return exit_highway_code;
    }

    public void setExit_highway_code(String exit_highway_code) {
        this.exit_highway_code = exit_highway_code;
    }

    public String getExit_highway_name() {
        return exit_highway_name;
    }

    public void setExit_highway_name(String exit_highway_name) {
        this.exit_highway_name = exit_highway_name;
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
		OverpassDetailEntity other = (OverpassDetailEntity) that;
		return (this.getOd_id() == null ? other.getOd_id() == null : this
				.getOd_id().equals(other.getOd_id()))
				&& (this.getEntry_plaz_code() == null ? other
						.getEntry_plaz_code() == null : this
						.getEntry_plaz_code()
						.equals(other.getEntry_plaz_code()))
				&& (this.getExit_plaz_code() == null ? other
						.getExit_plaz_code() == null : this.getExit_plaz_code()
						.equals(other.getExit_plaz_code()))
				&& (this.getO_id() == null ? other.getO_id() == null : this
						.getO_id().equals(other.getO_id()))
				&& (this.getOb_name() == null ? other.getOb_name() == null
						: this.getOb_name().equals(other.getOb_name()))
				&& (this.getOb_desc() == null ? other.getOb_desc() == null
						: this.getOb_desc().equals(other.getOb_desc()))
				&& (this.getOb_length() == null ? other.getOb_length() == null
						: this.getOb_length().equals(other.getOb_length()))
				&& (this.getOb_status() == null ? other.getOb_status() == null
						: this.getOb_status().equals(other.getOb_status()))
				&& (this.getOb_pic() == null ? other.getOb_pic() == null : this
						.getOb_pic().equals(other.getOb_pic()))
				&& (this.getEntry_plaz_id() == null ? other.getEntry_plaz_id() == null
						: this.getEntry_plaz_id().equals(
								other.getEntry_plaz_id()))
				&& (this.getExit_plaz_id() == null ? other.getExit_plaz_id() == null
						: this.getExit_plaz_id()
								.equals(other.getExit_plaz_id()))
				&& (this.getEntry_stake_id() == null ? other
						.getEntry_stake_id() == null : this.getEntry_stake_id()
						.equals(other.getEntry_stake_id()))
				&& (this.getExit_stake_id() == null ? other.getExit_stake_id() == null
						: this.getExit_stake_id().equals(
								other.getExit_stake_id()))
				&& (this.getCreate_time() == null ? other.getCreate_time() == null
						: this.getCreate_time().equals(other.getCreate_time()))
				&& (this.getUpdate_time() == null ? other.getUpdate_time() == null
						: this.getUpdate_time().equals(other.getUpdate_time()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getOd_id() == null) ? 0 : getOd_id().hashCode());
		result = prime
				* result
				+ ((getEntry_plaz_code() == null) ? 0 : getEntry_plaz_code()
						.hashCode());
		result = prime
				* result
				+ ((getExit_plaz_code() == null) ? 0 : getExit_plaz_code()
						.hashCode());
		result = prime * result
				+ ((getO_id() == null) ? 0 : getO_id().hashCode());
		result = prime * result
				+ ((getOb_name() == null) ? 0 : getOb_name().hashCode());
		result = prime * result
				+ ((getOb_desc() == null) ? 0 : getOb_desc().hashCode());
		result = prime * result
				+ ((getOb_length() == null) ? 0 : getOb_length().hashCode());
		result = prime * result
				+ ((getOb_status() == null) ? 0 : getOb_status().hashCode());
		result = prime * result
				+ ((getOb_pic() == null) ? 0 : getOb_pic().hashCode());
		result = prime
				* result
				+ ((getEntry_plaz_id() == null) ? 0 : getEntry_plaz_id()
						.hashCode());
		result = prime
				* result
				+ ((getExit_plaz_id() == null) ? 0 : getExit_plaz_id()
						.hashCode());
		result = prime
				* result
				+ ((getEntry_stake_id() == null) ? 0 : getEntry_stake_id()
						.hashCode());
		result = prime
				* result
				+ ((getExit_stake_id() == null) ? 0 : getExit_stake_id()
						.hashCode());
		result = prime
				* result
				+ ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
		result = prime
				* result
				+ ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
		return result;
	}
}
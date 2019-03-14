package com.djb.highway.user.dtoclient;



import java.util.List;
import com.djb.highway.road.dtoclient.BaseClientDTO;

public class TuiSongClientDTO extends BaseClientDTO {

	private static final long serialVersionUID = 1L;
	
	private List<TuiSongDetailDTO> list;

	public List<TuiSongDetailDTO> getList() {
		return list;
	}

	public void setList(List<TuiSongDetailDTO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

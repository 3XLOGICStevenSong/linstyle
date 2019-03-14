package com.djb.ylt.main_page.dtoclient;

import java.util.List;

import com.djb.ylt.framework.dto.BaseClientDTO;

/**
 * @date 2016年12月13日 下午6:04:53
 * @author chengming 内容：android端首页轮播图的返回pojo类包含CarouselClientDTO，最终返回json
 *         修改履历：2016年12月13日 下午6:04:53 chengming
 */
public class CarouselListClientDTO extends BaseClientDTO {
	private List<CarouselClientDTO> carouselList;
	
	private String hotLine;

	
	

	/**
	 * 返回hotLine的值
	 * @return String hotLine的值
	 */
	public String getHotLine() {
		return hotLine;
	}

	/**
	 * 设置hotLine的值
	 * @param  hotLine hotLine的值
	 */
	public void setHotLine(String hotLine) {
		this.hotLine = hotLine;
	}

	/**
	 * 返回carouselList的值
	 * @return List<CarouselClientDTO> carouselList的值
	 */
	public List<CarouselClientDTO> getCarouselList() {
		return carouselList;
	}

	/**
	 * 设置carouselList的值
	 * @param  carouselList carouselList的值
	 */
	public void setCarouselList(List<CarouselClientDTO> carouselList) {
		this.carouselList = carouselList;
	}

}

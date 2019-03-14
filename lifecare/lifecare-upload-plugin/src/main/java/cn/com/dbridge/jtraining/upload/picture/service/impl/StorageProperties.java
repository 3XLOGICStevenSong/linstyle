package cn.com.dbridge.jtraining.upload.picture.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {
    private String location;
    private String showPath;
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

	public String getShowPath() {
		return showPath;
	}

	public void setShowPath(String showPath) {
		this.showPath = showPath;
	}
}

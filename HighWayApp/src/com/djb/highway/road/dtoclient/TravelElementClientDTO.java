package com.djb.highway.road.dtoclient;

import java.util.List;

public class TravelElementClientDTO extends BaseClientDTO {

    private PlazaClientDTO plazaDTO;

    private PlazaClientDTO exitPlazaDTO;

    // 入口立交
    private OverpassClientDTO entryOverpass;
    // 出口立交
    private OverpassClientDTO exitOverpass;

    private String weather;

    // 服务区
    private List<String> saIds;
    private List<String> saDrs;

    // 服务区
    private List<String> rciIds;
    private List<String> rciDrs;

    // 网友发布图片
    private List<String> udpIds;
    private List<String> udpDrs;

    public List<String> getSaIds() {
        return saIds;
    }

    public void setSaIds(List<String> saIds) {
        this.saIds = saIds;
    }

    public List<String> getSaDrs() {
        return saDrs;
    }

    public void setSaDrs(List<String> saDrs) {
        this.saDrs = saDrs;
    }

    public List<String> getRciIds() {
        return rciIds;
    }

    public void setRciIds(List<String> rciIds) {
        this.rciIds = rciIds;
    }

    public List<String> getRciDrs() {
        return rciDrs;
    }

    public void setRciDrs(List<String> rciDrs) {
        this.rciDrs = rciDrs;
    }

    public List<String> getUdpIds() {
        return udpIds;
    }

    public void setUdpIds(List<String> udpIds) {
        this.udpIds = udpIds;
    }

    public List<String> getUdpDrs() {
        return udpDrs;
    }

    public void setUdpDrs(List<String> udpDrs) {
        this.udpDrs = udpDrs;
    }

    public PlazaClientDTO getPlazaDTO() {
        return plazaDTO;
    }

    public void setPlazaDTO(PlazaClientDTO plazaDTO) {
        this.plazaDTO = plazaDTO;
    }

    public PlazaClientDTO getExitPlazaDTO() {
        return exitPlazaDTO;
    }

    public void setExitPlazaDTO(PlazaClientDTO exitPlazaDTO) {
        this.exitPlazaDTO = exitPlazaDTO;
    }

    public OverpassClientDTO getEntryOverpass() {
        return entryOverpass;
    }

    public void setEntryOverpass(OverpassClientDTO entryOverpass) {
        this.entryOverpass = entryOverpass;
    }

    public OverpassClientDTO getExitOverpass() {
        return exitOverpass;
    }

    public void setExitOverpass(OverpassClientDTO exitOverpass) {
        this.exitOverpass = exitOverpass;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

}
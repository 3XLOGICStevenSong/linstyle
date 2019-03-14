package com.djb.highway.road.dto.travel;

import java.util.List;

import com.djb.highway.framework.dto.BaseDTO;
import com.djb.highway.road.dto.OverpassDTO;
import com.djb.highway.road.dto.PlazaDTO;
import com.djb.highway.road.dto.WeatherForcastDTO;

public class TravelElementDTO extends BaseDTO {

    private static final long serialVersionUID = 2502254027506829776L;

    private PlazaDTO plazaDTO;

    private PlazaDTO exitPlazaDTO;

    // 入口立交
    private OverpassDTO entryOverpass;
    // 出口立交
    private OverpassDTO exitOverpass;

    private WeatherForcastDTO weatherForcastDTO;

    // 服务区
    private List<String> saIds;
    private List<String> saDrs;

    // 服务区
    private List<String> rciIds;
    private List<String> rciDrs;

    // 网友发布图片
    private List<String> udpIds;
    private List<String> udpDrs;

    public PlazaDTO getPlazaDTO() {
        return plazaDTO;
    }

    public void setPlazaDTO(PlazaDTO plazaDTO) {
        this.plazaDTO = plazaDTO;
    }

    public PlazaDTO getExitPlazaDTO() {
        return exitPlazaDTO;
    }

    public void setExitPlazaDTO(PlazaDTO exitPlazaDTO) {
        this.exitPlazaDTO = exitPlazaDTO;
    }

    public WeatherForcastDTO getWeatherForcastDTO() {
        return weatherForcastDTO;
    }

    public void setWeatherForcastDTO(WeatherForcastDTO weatherForcastDTO) {
        this.weatherForcastDTO = weatherForcastDTO;
    }

    public OverpassDTO getEntryOverpass() {
        return entryOverpass;
    }

    public void setEntryOverpass(OverpassDTO entryOverpass) {
        this.entryOverpass = entryOverpass;
    }

    public OverpassDTO getExitOverpass() {
        return exitOverpass;
    }

    public void setExitOverpass(OverpassDTO exitOverpass) {
        this.exitOverpass = exitOverpass;
    }

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

        TravelElementDTO other = (TravelElementDTO) that;

        if (this.plazaDTO != null) {
            return (this.plazaDTO.getH_id() == null ? other.plazaDTO.getH_id() == null : this.plazaDTO.getH_id().equals(other.plazaDTO.getH_id()))
                            && (this.plazaDTO.getPlaz_id() == null ? other.plazaDTO.getPlaz_id() == null : this.plazaDTO.getPlaz_id().equals(
                                            other.plazaDTO.getPlaz_id()))
                            && (this.plazaDTO.getPlaz_code() == null ? other.plazaDTO.getPlaz_code() == null : this.plazaDTO.getPlaz_code().equals(
                                            other.plazaDTO.getPlaz_code()))
                            && (this.plazaDTO.getPlaz_name() == null ? other.plazaDTO.getPlaz_name() == null : this.plazaDTO.getPlaz_name().equals(
                                            other.plazaDTO.getPlaz_name()));
        } else if (this.exitPlazaDTO != null) {
            return (this.exitPlazaDTO.getH_id() == null ? other.exitPlazaDTO.getH_id() == null : this.exitPlazaDTO.getH_id().equals(
                            other.exitPlazaDTO.getH_id()))
                            && (this.exitPlazaDTO.getPlaz_id() == null ? other.exitPlazaDTO.getPlaz_id() == null : this.exitPlazaDTO.getPlaz_id().equals(
                                            other.exitPlazaDTO.getPlaz_id()))
                            && (this.exitPlazaDTO.getPlaz_code() == null ? other.exitPlazaDTO.getPlaz_code() == null : this.exitPlazaDTO.getPlaz_code().equals(
                                            other.exitPlazaDTO.getPlaz_code()))
                            && (this.exitPlazaDTO.getPlaz_name() == null ? other.exitPlazaDTO.getPlaz_name() == null : this.exitPlazaDTO.getPlaz_name().equals(
                                            other.exitPlazaDTO.getPlaz_name()));
        } else if (this.entryOverpass != null) {
            return (this.entryOverpass.getO_id() == null ? other.entryOverpass.getO_id() == null : this.entryOverpass.getO_id().equals(
                            other.entryOverpass.getO_id()))
                            && (this.entryOverpass.getO_code() == null ? other.entryOverpass.getO_code() == null : this.entryOverpass.getO_code().equals(
                                            other.entryOverpass.getO_code()))
                            && (this.entryOverpass.getO_name() == null ? other.entryOverpass.getO_name() == null : this.entryOverpass.getO_name().equals(
                                            other.entryOverpass.getO_name()));
        } else if (this.exitOverpass != null) {
            return (this.exitOverpass.getO_id() == null ? other.exitOverpass.getO_id() == null : this.exitOverpass.getO_id().equals(
                            other.exitOverpass.getO_id()))
                            && (this.exitOverpass.getO_code() == null ? other.exitOverpass.getO_code() == null : this.exitOverpass.getO_code().equals(
                                            other.exitOverpass.getO_code()))
                            && (this.exitOverpass.getO_name() == null ? other.exitOverpass.getO_name() == null : this.exitOverpass.getO_name().equals(
                                            other.exitOverpass.getO_name()));
        } else {
            return false;
        }

    }

}
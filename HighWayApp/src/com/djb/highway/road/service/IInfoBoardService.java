package com.djb.highway.road.service;

import java.util.List;

import com.djb.highway.road.entity.InfoBoardEntity;

public interface IInfoBoardService {
    public void addInfoBoard(InfoBoardEntity infoBoard);

    public void deleteInfoBoard(InfoBoardEntity infoBoard);

    public void deleteInfoBoardBatch(List<InfoBoardEntity> list);

    public void updateInfoBoard(InfoBoardEntity infoBoard);

    public InfoBoardEntity getObject(InfoBoardEntity infoBoard);

    public List<InfoBoardEntity> getInfoBoardList();

    public List<InfoBoardEntity> getInfoBoardList(InfoBoardEntity infoBoard);

    public List<InfoBoardEntity> getInfoBoardPointList(InfoBoardEntity infoBoard);

}

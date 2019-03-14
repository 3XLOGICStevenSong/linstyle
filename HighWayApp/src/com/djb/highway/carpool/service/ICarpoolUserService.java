package com.djb.highway.carpool.service;

import java.util.List;

import com.djb.highway.carpool.entity.CarpoolUserEntity;

import com.djb.highway.framework.entity.PageModel;

public interface ICarpoolUserService {

    public void addCarpoolUser(CarpoolUserEntity carpoolUser);

    public void deleteCarpoolUser(CarpoolUserEntity carpoolUser);

    public void deleteCarpoolUserBatch(List<CarpoolUserEntity> list);

    public void updateCarpoolUser(CarpoolUserEntity carpoolUser);

    public CarpoolUserEntity getObject(CarpoolUserEntity carpoolUser);

    public List<CarpoolUserEntity> getCarpoolUserList();

    public PageModel findPagedList(CarpoolUserEntity carpoolUser);

    public List<CarpoolUserEntity> getCarpoolUserList(CarpoolUserEntity carpoolUser);

    public Integer countCarpoolUserNumber();
}
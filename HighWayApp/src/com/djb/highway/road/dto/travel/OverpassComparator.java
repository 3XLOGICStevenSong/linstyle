package com.djb.highway.road.dto.travel;

import java.util.Comparator;

import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.road.dto.OverpassDTO;

public class OverpassComparator implements Comparator<Object> {

    // 0:升序 1:降序
    private int sortType;
    // 画面类别(1:即时路况页面(主页),2:行程规划)
    private int screenType;

    @Override
    public int compare(Object arg0, Object arg1) {

        if (screenType == 1) {
            float value0 = CommonUtil.toFloat(((OverpassDTO) arg0).getEntry_stake_id(), 0);
            float value1 = CommonUtil.toFloat(((OverpassDTO) arg1).getEntry_stake_id(), 0);

            if (sortType == 1) {
                if (value0 > value1) {
                    return 1;
                } else if (value0 < value1) {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                if (value0 < value1) {
                    return 1;
                } else if (value0 > value1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        } else {
            int value0 = ((OverpassDTO) arg0).getSort();
            int value1 = ((OverpassDTO) arg1).getSort();
            if (value0 < value1) {
                return 1;
            } else if (value0 > value1) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    public OverpassComparator(int screenType, int sortType) {
        this.screenType = screenType;
        this.sortType = sortType;
    }

    public OverpassComparator(int screenType) {
        this.screenType = screenType;
    }

}

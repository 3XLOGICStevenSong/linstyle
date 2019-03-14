package com.djb.highway.common.util;

public class RoadInfoUtil {

    /**
     * 取得行驶方向
     * 
     * @param entryStakeId
     * @param eixtStakeId
     * @return
     */
    public static String getTravelDirection(String entryStakeId, String eixtStakeId) {

        float pStakeId = CommonUtil.toFloat(entryStakeId, 0);
        float pNextStakeId = CommonUtil.toFloat(eixtStakeId, 0);
        return pStakeId < pNextStakeId ? "0" : "1";
    }

    /**
     * 判断管制信息是否显示
     * 
     * @param elementHCode
     * @param travelElement
     * @param direction
     *            元素方向
     * @param startStakeId
     *            管制开始桩号
     * @param endStakeId
     *            管制结束桩号
     * @param entryStakeId
     *            入口收费站或立交桩号
     * @param eixtStakeId
     *            出口收费站或立交桩号
     * @return
     */
    public static boolean isShowRciElement(String elementHCode, String rciHCode, String direction, String startStakeId, String endStakeId, String entryStakeId,
                    String eixtStakeId) {

        if (rciHCode == null || elementHCode == null || !elementHCode.equals(rciHCode)) {
            return false;
        }

        boolean isShow = false;
        float fStartStakeId = CommonUtil.toFloat(startStakeId, 0);
        float fEndStakeId = CommonUtil.toFloat(endStakeId, 0);
        float entry_stake_id = CommonUtil.toFloat(entryStakeId, 0);
        float exit_stake_id = CommonUtil.toFloat(eixtStakeId, 0);

        String travelDirection = getTravelDirection(entryStakeId, eixtStakeId);
        if ("0".equals(travelDirection) && "1".equals(direction)) {
            isShow = judgeCrossNoOrder(fStartStakeId, fEndStakeId, entry_stake_id, exit_stake_id);
        } else if ("1".equals(travelDirection) && "2".equals(direction)) {
            isShow = judgeCrossNoOrder(fStartStakeId, fEndStakeId, entry_stake_id, exit_stake_id);
        } else if ("3".equals(direction)) {
            isShow = judgeCrossNoOrder(fStartStakeId, fEndStakeId, entry_stake_id, exit_stake_id);
        } else if ("0".equals(travelDirection) && "2".equals(direction)) {
            isShow = judgeCrossNoOrder(fStartStakeId, fEndStakeId, entry_stake_id, exit_stake_id);
        } else {
            isShow = false;
        }
        return isShow;
    }

    public static boolean judgeCrossOrder(float a1, float a2, float b1, float b2) {
        if ((a1 <= b1) && a2 >= b1) {
            return true;
        } else if ((a1 >= b1) && a1 <= b2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean judgeCrossNoOrder(float a1, float a2, float b1, float b2) {
        if (a1 >= a2) {
            if (b1 >= b2) {
                return judgeCrossOrder(a2, a1, b2, b1);
            } else {
                return judgeCrossOrder(a2, a1, b1, b2);
            }
        } else {
            if (b1 >= b2) {
                return judgeCrossOrder(a1, a2, b2, b1);
            } else {
                return judgeCrossOrder(a1, a2, b1, b2);
            }
        }

    }
}

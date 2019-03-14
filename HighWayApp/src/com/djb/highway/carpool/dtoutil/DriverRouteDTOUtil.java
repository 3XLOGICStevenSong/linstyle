/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : DTOUtil
 * Description        : データ保持クラスDTOUtilクラス
 *
 * 2011/07/28      Wangjiquan
 *
 *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
package com.djb.highway.carpool.dtoutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.djb.highway.carpool.dto.DriverRouteDTO;
import com.djb.highway.carpool.dtoclient.CarpoolUserClientDTO;
import com.djb.highway.carpool.dtoclient.DriverRouteDetailDTO;
import com.djb.highway.carpool.dtoclient.PassengerRouteDetailDTO;
import com.djb.highway.carpool.entity.DriverRouteEntity;
import com.djb.highway.carpool.entity.PassengerRouteEntity;
import com.djb.highway.common.util.CommonUtil;
import com.djb.highway.common.util.Constants;

/**
 * <H3>
 * データ保持クラスDTOUtilクラス.</H3>
 * 
 * <PRE>
 * Form ←→ BaseDTO
 * BaseDTO ←→ BaseEntity　の変換を行う。
 * </PRE>
 * 
 * @author Wangjiquan
 * @version 1.0 2011/07/28
 */
public class DriverRouteDTOUtil {

    /**
     * 
     * @param driverRouteEntity
     * @param detailParamDTO
     * @return
     */

    public static DriverRouteDetailDTO driverListBacktoAppClient(DriverRouteEntity driverRouteEntity, DriverRouteDetailDTO detailParamDTO) {

        detailParamDTO.setCharter(driverRouteEntity.getCharter_flg());

        detailParamDTO.setEndcity(driverRouteEntity.getEnd_city());

        detailParamDTO.setStartcity(driverRouteEntity.getStart_city());
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        if (driverRouteEntity.getStart_time().equals(driverRouteEntity.getEnd_time())) {
            detailParamDTO.setStarttime(sdf.format(driverRouteEntity.getStart_time()));
        } else {

            detailParamDTO.setStarttime(sdf.format(driverRouteEntity.getStart_time()) + "-" + sdf1.format(driverRouteEntity.getEnd_time()));
        }

        if (driverRouteEntity.getStart_time() != null) {

        }
        Date date = new Date();

        if (CommonUtil.addOneDay(driverRouteEntity.getEnd_time()).before(date)) {
            detailParamDTO.setState_flg("3");
        }
        detailParamDTO.setRemark(driverRouteEntity.getDr_memo());

        detailParamDTO.setDriver_route_id(driverRouteEntity.getDr_id());

        return detailParamDTO;
    }

    /**
     * 
     * @param passengerRouteEntity
     * @param searchRouteDetailDTO
     * @return
     */
    public static PassengerRouteDetailDTO getPassengerBacktoAppClient(PassengerRouteEntity passengerRouteEntity, PassengerRouteDetailDTO searchRouteDetailDTO) {
        if (passengerRouteEntity.getCharter_flg() != null) {
            searchRouteDetailDTO.setCharter(passengerRouteEntity.getCharter_flg());
        }
        if (passengerRouteEntity.getPr_id() != null) {
            searchRouteDetailDTO.setPassenger_route_id(passengerRouteEntity.getPr_id());
        }
        if (passengerRouteEntity.getEnd_area() != null) {
            searchRouteDetailDTO.setEndarea(passengerRouteEntity.getEnd_area());
        }
        if (passengerRouteEntity.getEnd_city() != null) {
            searchRouteDetailDTO.setEndcity(passengerRouteEntity.getEnd_city());
        }
        if (passengerRouteEntity.getPeople_count() != null) {
            searchRouteDetailDTO.setNumber(passengerRouteEntity.getPeople_count());
        }
        if (passengerRouteEntity.getStart_area() != null) {
            searchRouteDetailDTO.setStartarea(passengerRouteEntity.getStart_area());
        }
        if (passengerRouteEntity.getStart_city() != null) {
            searchRouteDetailDTO.setStartcity(passengerRouteEntity.getStart_city());
        }
        if (passengerRouteEntity.getState() != null) {
            searchRouteDetailDTO.setState(passengerRouteEntity.getState());
        }
        if (passengerRouteEntity.getPrice() != null) {
            searchRouteDetailDTO.setPrice(passengerRouteEntity.getPrice());
        }
        if (passengerRouteEntity.getPr_memo() != null) {
            searchRouteDetailDTO.setRemark(passengerRouteEntity.getPr_memo());
        }
        if (passengerRouteEntity.getStart_time() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            searchRouteDetailDTO.setStarttime(sdf.format(passengerRouteEntity.getStart_time()));

        }

        CarpoolUserClientDTO paramClientDTO = new CarpoolUserClientDTO();
        if (passengerRouteEntity.getCarpoolUserEntity().getInsert_time() != null) {
            paramClientDTO.setCreate_time(CommonUtil.dateToString(passengerRouteEntity.getCarpoolUserEntity().getInsert_time(), Constants.DATE_PATTERN_YYMMDD));
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getCu_tel() != null) {
            paramClientDTO.setTele(passengerRouteEntity.getCarpoolUserEntity().getCu_tel());
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getP_success_count() != null) {
            paramClientDTO.setSuccesscount(passengerRouteEntity.getCarpoolUserEntity().getP_success_count());
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getCu_nick() != null) {
            paramClientDTO.setNickname(passengerRouteEntity.getCarpoolUserEntity().getCu_nick());
        }
        searchRouteDetailDTO.setCarpoolUser(paramClientDTO);
        return searchRouteDetailDTO;
    }

    /**
     * 
     * @param passengerRouteEntity
     * @param searchRouteDetailDTO
     * @return
     */

    public static PassengerRouteDetailDTO getPassengerBacktoH5Client(DriverRouteEntity driverRouteEntity, DriverRouteDTO driverParamDTO) {

        driverParamDTO.setCharter_flg(driverRouteEntity.getCharter_flg());
        driverParamDTO.setStart_city(driverRouteEntity.getStart_city());
        driverParamDTO.setEnd_city(driverRouteEntity.getEnd_city());

        if (driverRouteEntity.getStart_time() != null && driverRouteEntity.getEnd_time() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

            if (driverRouteEntity.getStart_time().equals(driverRouteEntity.getEnd_time())) {
                driverParamDTO.setDriverGo_time(sdf.format(driverRouteEntity.getStart_time()));
            } else {

                driverParamDTO.setDriverGo_time(sdf.format(driverRouteEntity.getStart_time()) + "-" + sdf1.format(driverRouteEntity.getEnd_time()));
            }
        }

        driverParamDTO.setDr_id(driverRouteEntity.getDr_id());
        driverParamDTO.setCu_id(driverRouteEntity.getCu_id());
        return null;
    }
}

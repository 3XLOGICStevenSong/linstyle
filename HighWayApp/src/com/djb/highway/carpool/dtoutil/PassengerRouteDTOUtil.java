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

import org.apache.commons.beanutils.PropertyUtils;

import com.djb.highway.carpool.dto.CarpoolUserDTO;
import com.djb.highway.carpool.dto.PassengerRouteDTO;
import com.djb.highway.carpool.dtoclient.CarpoolUserClientDTO;
import com.djb.highway.carpool.dtoclient.PassengerRouteDetailDTO;

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
public class PassengerRouteDTOUtil {

    /**
     * 
     * @param passengerRouteEntity
     * @param searchRouteDetailDTO
     * @return
     */
    public static PassengerRouteDetailDTO doSearchBacktoAppClient(PassengerRouteEntity passengerRouteEntity, PassengerRouteDetailDTO searchRouteDetailDTO) {

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
    public static PassengerRouteDTO doSearchBacktoH5Client(PassengerRouteEntity passengerRouteEntity, PassengerRouteDTO passengerRouteDTO) {

        if (passengerRouteEntity.getCharter_flg() != null) {
            passengerRouteDTO.setCharter_flg(passengerRouteEntity.getCharter_flg());
        }
        if (passengerRouteEntity.getPr_id() != null) {
            passengerRouteDTO.setPr_id(passengerRouteEntity.getPr_id());
        }
        if (passengerRouteEntity.getEnd_area() != null) {
            passengerRouteDTO.setEnd_area(passengerRouteEntity.getEnd_area());
        }
        if (passengerRouteEntity.getEnd_city() != null) {
            passengerRouteDTO.setEnd_city(passengerRouteEntity.getEnd_city());
        }
        if (passengerRouteEntity.getPeople_count() != null) {
            passengerRouteDTO.setPeople_count(passengerRouteEntity.getPeople_count());
        }
        if (passengerRouteEntity.getStart_area() != null) {
            passengerRouteDTO.setStart_area(passengerRouteEntity.getStart_area());
        }
        if (passengerRouteEntity.getStart_city() != null) {
            passengerRouteDTO.setStart_city(passengerRouteEntity.getStart_city());
        }

        if (passengerRouteEntity.getPrice() != null) {
            passengerRouteDTO.setPrice(passengerRouteEntity.getPrice());
        }
        if (passengerRouteEntity.getPr_memo() != null) {
            passengerRouteDTO.setPr_memo(passengerRouteEntity.getPr_memo());
        }
        if (passengerRouteEntity.getState() != null) {
            passengerRouteDTO.setState(passengerRouteEntity.getState());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat(" HH:mm");
        if (passengerRouteEntity.getStart_time() != null) {
            // if
            // (passengerRouteEntity.getStart_time().equals(passengerRouteEntity.getEnd_time()))
            // {
            passengerRouteDTO.setGo_time(sdf.format(passengerRouteEntity.getStart_time()));
        }
        // else {

        // passengerRouteDTO.setGo_time(sdf.format(passengerRouteEntity.getStart_time())
        // + "-" + sdf1.format(passengerRouteEntity.getEnd_time()));
        // }

        CarpoolUserDTO paramClientDTO = new CarpoolUserDTO();
        if (passengerRouteEntity.getCarpoolUserEntity().getInsert_time() != null) {
            paramClientDTO.setCreate_time(CommonUtil.dateToString(passengerRouteEntity.getCarpoolUserEntity().getInsert_time(), Constants.DATE_PATTERN_YYMMDD));
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getCu_tel() != null) {
            String str=passengerRouteEntity.getCarpoolUserEntity().getCu_tel();
            String temp=str.substring(0, 3)+"*****"+str.substring(8, 11);
            paramClientDTO.setCu_tel(temp);
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getP_success_count() != null) {
            paramClientDTO.setP_success_count(passengerRouteEntity.getCarpoolUserEntity().getP_success_count());
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getCu_nick() != null) {
            paramClientDTO.setCu_nick(passengerRouteEntity.getCarpoolUserEntity().getCu_nick());
        }
        passengerRouteDTO.setCarpoolUser(paramClientDTO);

        return passengerRouteDTO;
    }
    
    /**
     * 
     * @param passengerRouteEntity
     * @param searchRouteDetailDTO
     * @return
     */
    public static PassengerRouteDTO doDriverBacktoH5Client(PassengerRouteEntity passengerRouteEntity, PassengerRouteDTO passengerRouteDTO) {

        if (passengerRouteEntity.getCharter_flg() != null) {
            passengerRouteDTO.setCharter_flg(passengerRouteEntity.getCharter_flg());
        }
        if (passengerRouteEntity.getPr_id() != null) {
            passengerRouteDTO.setPr_id(passengerRouteEntity.getPr_id());
        }
        if (passengerRouteEntity.getEnd_area() != null) {
            passengerRouteDTO.setEnd_area(passengerRouteEntity.getEnd_area());
        }
        if (passengerRouteEntity.getEnd_city() != null) {
            passengerRouteDTO.setEnd_city(passengerRouteEntity.getEnd_city());
        }
        if (passengerRouteEntity.getPeople_count() != null) {
            passengerRouteDTO.setPeople_count(passengerRouteEntity.getPeople_count());
        }
        if (passengerRouteEntity.getStart_area() != null) {
            passengerRouteDTO.setStart_area(passengerRouteEntity.getStart_area());
        }
        if (passengerRouteEntity.getStart_city() != null) {
            passengerRouteDTO.setStart_city(passengerRouteEntity.getStart_city());
        }

        if (passengerRouteEntity.getPrice() != null) {
            passengerRouteDTO.setPrice(passengerRouteEntity.getPrice());
        }
        if (passengerRouteEntity.getPr_memo() != null) {
            passengerRouteDTO.setPr_memo(passengerRouteEntity.getPr_memo());
        }
        if (passengerRouteEntity.getState() != null) {
            passengerRouteDTO.setState(passengerRouteEntity.getState());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat(" HH:mm");
        if (passengerRouteEntity.getStart_time() != null) {
            // if
            // (passengerRouteEntity.getStart_time().equals(passengerRouteEntity.getEnd_time()))
            // {
            passengerRouteDTO.setGo_time(sdf.format(passengerRouteEntity.getStart_time()));
        }
        // else {

        // passengerRouteDTO.setGo_time(sdf.format(passengerRouteEntity.getStart_time())
        // + "-" + sdf1.format(passengerRouteEntity.getEnd_time()));
        // }

        CarpoolUserDTO paramClientDTO = new CarpoolUserDTO();
        if (passengerRouteEntity.getCarpoolUserEntity().getInsert_time() != null) {
            paramClientDTO.setCreate_time(CommonUtil.dateToString(passengerRouteEntity.getCarpoolUserEntity().getInsert_time(), Constants.DATE_PATTERN_YYMMDD));
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getCu_tel() != null) {
            paramClientDTO.setCu_tel(passengerRouteEntity.getCarpoolUserEntity().getCu_tel());
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getP_success_count() != null) {
            paramClientDTO.setP_success_count(passengerRouteEntity.getCarpoolUserEntity().getP_success_count());
        }
        if (passengerRouteEntity.getCarpoolUserEntity().getCu_nick() != null) {
            paramClientDTO.setCu_nick(passengerRouteEntity.getCarpoolUserEntity().getCu_nick());
        }
        passengerRouteDTO.setCarpoolUser(paramClientDTO);

        return passengerRouteDTO;
    }
}

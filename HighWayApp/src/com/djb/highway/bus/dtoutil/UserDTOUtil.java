/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : DTOUtil
 * Description        : データ保持クラスDTOUtilクラス
 *
 * 2011/07/28      Wangjiquan
 *
 *+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
package com.djb.highway.bus.dtoutil;

import org.apache.commons.beanutils.PropertyUtils;

import com.djb.highway.user.dto.UserDTO;
import com.djb.highway.user.entity.UserEntity;

/**
 * <H3>
 * データ保持クラスDTOUtilクラス.
 * </H3>
 * <PRE>
 * Form ←→ BaseDTO
 * BaseDTO ←→ BaseEntity　の変換を行う。
 * </PRE>
 *
 * @author	Wangjiquan
 * @version	1.0   2011/07/28
 */
public class UserDTOUtil {


    /**
     * <H3>BaseDTO to BaseEntity</H3>
     *
     * @param dTO
     * @param entity
     * @return BaseEntity
     */
    public static UserEntity userDTOToUserEntity(UserDTO dTO,UserEntity entity) {
        try {
            PropertyUtils.copyProperties(entity, dTO);
        } catch (Exception e) {
            //// e.printStackTrace();
        }

        return entity;
    }

    /**
     * <H3>BaseEntity to BaseDTO</H3>
     *
     * @param dTO
     * @param entity
     * @return BaseDTO
     */
    public static UserDTO userEntityToUserDTO(UserDTO dTO,UserEntity entity) {
        try {
			PropertyUtils.copyProperties(dTO, entity);
        } catch (Exception e) {
            //// e.printStackTrace();
        }
        return dTO;
    }

}

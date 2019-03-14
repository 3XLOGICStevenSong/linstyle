package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.MyUserPO;
import cn.com.dbridge.jtraining.dao.po.UserPO;
import cn.com.dbridge.jtraining.dao.po.UserQueryPO;

public interface UserPOMapper {

    //后台
    int deleteByPrimaryKey(@Param("no") String no);

    //后台
    int insert(UserPO record);

    //后台
    UserPO selectByPrimaryKey(@Param("no") String no);

    //后台
    List<UserPO> selectAll(@Param("type") Byte type);

    //后台
    int updateByPrimaryKey(UserPO record);

    //后台
    List<UserPO> selectByUser(UserPO record);

    //后台
    String selectMaxWorkNo(@Param(value = "type") Byte type);

    //后台
    UserPO selectUserByNoOrName(@Param(value = "noOrName") String noOrName);

    //后台
    int updatePasswordByNo(UserPO record);

    //后台
    int updateByKey(UserPO record);

    //后台
    int updateStatusOnByKey(@Param(value = "no") String no);

    //后台
    int updateStatusOffByKey(@Param(value = "no") String no);

    List<String> selectName(@Param(value = "name") String name);
	// TODO 陳軍
	UserPO selectByNoPassword(UserQueryPO userQueryPO);

	// TODO 陳軍
	int updateByNo(UserPO record);

    //郭健
    UserPO selectPersonByNo(String no);

    //郭健
    List<UserPO> selectAllStudents();

    //郭健
    List<MyUserPO> selectAllTeachers();

    //郭健
    List<UserPO> selectPersonByTypeName(String typeName);

}
package cn.com.dbridge.jtraining.dao.respository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.dbridge.jtraining.dao.po.StudentEvaluatePO;

public interface StudentEvaluatePOMapper {
    int deleteByPrimaryKey(@Param("teacherId") String teacherId, @Param("studentId") String studentId);

    int insert(StudentEvaluatePO record);

    //后台
    List<StudentEvaluatePO> selectByPrimaryKey(
            @Param("studentId") String studentId);

    List<StudentEvaluatePO> selectAll();

    int updateByPrimaryKey(StudentEvaluatePO record);

    //获取老师对学生的评价信息
    StudentEvaluatePO selectEvaluate(StudentEvaluatePO record);

    //评价信息已存在时更新数据，评价信息不存在时追加数据
    int addOrUpdateEvaluate(StudentEvaluatePO record);
}
/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: StudentEvaluateService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description: 学生評価サービス層の実装
 * @author: 郭健
 * @date: 2018年12月6日 1:55:28 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dbridge.jtraining.dao.po.StudentEvaluatePO;
import cn.com.dbridge.jtraining.dao.respository.StudentEvaluatePOMapper;
import cn.com.dbridge.jtraining.framework.dto.AddOrUpdateEvaluateDTO;
import cn.com.dbridge.jtraining.framework.dto.EvaluatePersonDTO;
import cn.com.dbridge.jtraining.framework.vo.EvaluateInfoVO;
import cn.com.dbridge.jtraining.service.StudentEvaluateService;

/**
 * @ClassName:  StudentEvaluateServiceImpl
 * @Description:学生評価サービス層の実装
 * @author: 郭健
 * @date: 2018年12月6日 1:55:28 PM
 * 
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 * 注：このコンテンツは、DJB Information Technology Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
@Service
public class StudentEvaluateServiceImpl implements StudentEvaluateService {

    @Autowired
    private StudentEvaluatePOMapper studentEvaluatePOMapper;

    /**   
     * Title: selectEvaluate
     * Description:学生に関する教師の評価情報を入手する
     * @param evaluatePersonDTO クエリ条件
     * @return 評価情報
     * @author 郭健
     * @see cn.com.dbridge.jtraining.service.StudentEvaluateService#selectEvaluate(cn.com.dbridge.jtraining.framework.dto.EvaluatePersonDTO)   
     */
    @Override
    public EvaluateInfoVO selectEvaluate(EvaluatePersonDTO evaluatePersonDTO) {
        StudentEvaluatePO studentEvaluatePO = new StudentEvaluatePO();
        EvaluateInfoVO evaluateInfoVO = new EvaluateInfoVO();
        if (null != evaluatePersonDTO) {
            BeanUtils.copyProperties(evaluatePersonDTO, studentEvaluatePO);
            StudentEvaluatePO studentEvaluatePOQuery = studentEvaluatePOMapper
                    .selectEvaluate(studentEvaluatePO);
            if (null != studentEvaluatePOQuery) {
                BeanUtils.copyProperties(studentEvaluatePOQuery,
                        evaluateInfoVO);
            } else {
                evaluateInfoVO.setEvaluate("");
            }
        }
        return evaluateInfoVO;
    }

    /**   
     * Title: addOrUpdateEvaluate 
     * Description:評価情報が既に存在する場合にはデータを更新し、評価情報が存在しない場合にはデータを追加する 
     * @param addOrUpdateEvaluateDTO 運用情報
     * @return 影響を受けるレコードの数
     * @author 郭健
     * @see cn.com.dbridge.jtraining.service.StudentEvaluateService#addOrUpdateEvaluate(cn.com.dbridge.jtraining.framework.dto.AddOrUpdateEvaluateDTO)   
     */
    @Override
    public int addOrUpdateEvaluate(
            AddOrUpdateEvaluateDTO addOrUpdateEvaluateDTO) {
        //影響を受けるレコードの数
        int affectNum = 0;
        StudentEvaluatePO studentEvaluatePO = new StudentEvaluatePO();
        if (null != addOrUpdateEvaluateDTO
                && !StringUtils.isEmpty(addOrUpdateEvaluateDTO.getStudentId())
                        && !StringUtils.isEmpty(
                        addOrUpdateEvaluateDTO.getTeacherId())) {
            BeanUtils.copyProperties(addOrUpdateEvaluateDTO, studentEvaluatePO);
            //現在のコメントが存在するかどうかを判断する
            StudentEvaluatePO studentEvaluatePOQuery = studentEvaluatePOMapper
                    .selectEvaluate(studentEvaluatePO);
            //評価が空の場合
            if (null == studentEvaluatePOQuery) {
            	Timestamp insertDate = new Timestamp(System.currentTimeMillis());
                String insertPerson = addOrUpdateEvaluateDTO.getTeacherId();
                BeanUtils.copyProperties(addOrUpdateEvaluateDTO,
                        studentEvaluatePO);
                studentEvaluatePO.setInsertDate(insertDate);
                studentEvaluatePO.setEvaluateTime(insertDate);
                studentEvaluatePO.setInsertPerson(insertPerson);
                //評価を追加
                affectNum = studentEvaluatePOMapper.insert(studentEvaluatePO);
            } else {
            	Timestamp updateDate = new Timestamp(System.currentTimeMillis());
                String updatePerson = addOrUpdateEvaluateDTO.getTeacherId();
                String evaluateForAddOrUpdate = addOrUpdateEvaluateDTO
                        .getEvaluate();
                studentEvaluatePO.setEvaluate(evaluateForAddOrUpdate);
                studentEvaluatePO.setEvaluateTime(updateDate);
                studentEvaluatePO.setUpdateDate(updateDate);
                studentEvaluatePO.setUpdatePerson(updatePerson);
                //評価を更新
                affectNum = studentEvaluatePOMapper
                        .updateByPrimaryKey(studentEvaluatePO);
            }
        }
        return affectNum;
    }
}

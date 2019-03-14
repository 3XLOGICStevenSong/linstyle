/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  DownLoadList.java
 * @Package cn.com.dbridge.jtraining.service.impl
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 宁旭
 * @date:   2018年12月13日 上午10:45:19
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.dbridge.jtraining.dao.po.TecherPO;
import cn.com.dbridge.jtraining.dao.po.TrainCategoryInformationPO;
import cn.com.dbridge.jtraining.dao.respository.TrainCategoryPOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainItemPOMapper;
import cn.com.dbridge.jtraining.framework.vo.TeacherInformation;
import cn.com.dbridge.jtraining.framework.vo.TrainCategoryInformationVO;

/**
 * @ClassName:  DownLoadList
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: 宁旭
 * @date:   2018年12月13日 上午10:45:19
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
public class DownLoadList {

    @Autowired
    private TrainItemPOMapper trainItemPOMapper;
    @Autowired
    private TrainCategoryPOMapper trainCategoryPOMapper;

    /**后台
     * 
     * Title: queryUser
     * Description:
     * @param trainType
     * @return
     * @see cn.com.dbridge.jtraining.service.TrainItemService#queryUser(java.lang.Integer)
     */
    List<TeacherInformation> queryUser(Integer trainType) {
        List<TecherPO> userPOList = trainItemPOMapper
                .selectNameList(trainType);
        TeacherInformation userVO = null;
        List<TeacherInformation> userVOList = new ArrayList<TeacherInformation>();
        for (TecherPO UserListPO : userPOList) {
            userVO = new TeacherInformation();
            BeanUtils.copyProperties(UserListPO, userVO);
            userVOList.add(userVO);
        }
        return userVOList;
    }

    /**后台
     * 
     * Title: queryTrainCategoryList
     * Description:
     * @return
     * @see cn.com.dbridge.jtraining.service.TrainItemService#queryTrainCategoryList()
     */
    List<TrainCategoryInformationVO> queryTrainCategoryList() {
        List<TrainCategoryInformationPO> trainCategoryListPOList = trainCategoryPOMapper
                .selectAllName();
        TrainCategoryInformationVO trainCategoryListVO = null;
        List<TrainCategoryInformationVO> trainCategoryListVOList = new ArrayList<TrainCategoryInformationVO>();
        for (TrainCategoryInformationPO trainCategoryListPO : trainCategoryListPOList) {
            trainCategoryListVO = new TrainCategoryInformationVO();
            BeanUtils.copyProperties(trainCategoryListPO, trainCategoryListVO);
            trainCategoryListVOList.add(trainCategoryListVO);
        }
        return trainCategoryListVOList;
    }

}

/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  TrainItemService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 陈健飞 
 * @date:   2018年12月6日 下午1:55:59
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service;

import java.util.List;

import cn.com.dbridge.jtraining.framework.dto.PageDTO;
import cn.com.dbridge.jtraining.framework.dto.TeacherQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemUpdateDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainListDTO;
import cn.com.dbridge.jtraining.framework.vo.CategoryItemVO;
import cn.com.dbridge.jtraining.framework.vo.ItemVO;
import cn.com.dbridge.jtraining.framework.vo.MyTrainItemVO;
import cn.com.dbridge.jtraining.framework.vo.TeacherVO;
import cn.com.dbridge.jtraining.framework.vo.TrainItemVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceTVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceVO;

/**
 * @ClassName:  TrainItemService
 * @Description:トレーニング情報インターフェイス
 * @author: 陈健飞
 * @date:   2018年12月6日 下午1:55:59
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
public interface TrainItemService {

    /**后台
     * 
     * @Title: queryTrainItem
     * @Description: クエリのトレーニング情報
     * @param trainItemQueryDTO
     * @return トレーニング情報の結果
     */
    List<TrainItemVO> queryTrainItem(TrainItemQueryDTO trainItemQueryDTO);

    /**后台
     * 
     * @Title: addTrainItem
     * @Description: トレーニング情報を追加する
     * @param trainItemAddDTO
     * @return 返り主当値
     */
    int addTrainItem(TrainItemAddDTO trainItemAddDTO);

    /**后台
     * 
     * @Title: updateTrainItem
     * @Description: トレーニング情報を更新する)
     * @param trainItemUpdateDTO
     * @return 影響を受けるレコードの数
     */
    int updateTrainItemById(TrainItemUpdateDTO trainItemUpdateDTO);
    
    /**后台
     * 
     * @Title: getTrainItem
     * @Description: 主キーを使用してトレーニング情報とトレーニング資料を検索する)
     * @param trainId
     * @return トレーニング情報とトレーニング資料の情報の結果
     */
    TrainItemVO getTrainItemById(Integer trainId);

    /**后台
     * 
     * @Title: deleteTrainItem
     * @Description: トレーニング情報の削除
     * @param trainId
     * @return 影響を受けるレコードの数
     */
    int deleteTrainItem(Integer trainId);
    
    /**后台
     * 
     * @Title: queryCategoryItem
     * @Description: クエリ情報の初期化
     * @return トレーニング情報とトレーニングカテゴリの情報収集
     */
    CategoryItemVO queryCategoryItem(PageDTO pageDTO);

    /**后台
     * 
     * @Title: queryItemTeacher
     * @Description: シード情報を初期化する
     * @return 種のコレクション
     */
    ItemVO queryItem();

    /**后台
     * 
     * @Title: queryTeacher
     * @Description: すべての関連する教師情報をカテゴリ別に問い合わせる
     * @param trainType
     * @return 教師情報収集
     */
    TeacherVO queryTeacher(TeacherQueryDTO teacherQueryDTO);

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: getTrainSource
	 * @Description: トレーニング資料情報を検索する
	 * @param trainListDTO
	 * @return トレーニング資料情報
	 */
	TrainSourceVO getTrainSource(TrainListDTO trainListDTO);

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: queryTrainSource
	 * @Description: 教師のトレーニング資料を取得する
	 * @param teacherId
	 * @return トレーニング資料情報
	 */
	List<TrainSourceTVO> queryTrainSource(String teacherId);

    /**
     * 
     * @Title: selectAllTrainItem 
     * @Description: すべてのトレーニング情報のリストを入手する
     * @author 郭健
     * @return トレーニング情報
     */
    List<MyTrainItemVO> selectAllTrainItem();
}

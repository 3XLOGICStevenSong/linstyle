/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: TrainItemController.java
 * @Package cn.com.dbridge.jtraining.rest
 * @Description:トレーニング項目情報制御層
 * @author:
 * @date:2018年12月6日 1:55:59 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.dto.PageDTO;
import cn.com.dbridge.jtraining.framework.dto.TeacherQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainItemUpdateDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainListDTO;
import cn.com.dbridge.jtraining.framework.enums.ResponseCode;
import cn.com.dbridge.jtraining.framework.vo.CategoryItemVO;
import cn.com.dbridge.jtraining.framework.vo.ItemVO;
import cn.com.dbridge.jtraining.framework.vo.LearnedSourceVO;
import cn.com.dbridge.jtraining.framework.vo.LearningSourceVO;
import cn.com.dbridge.jtraining.framework.vo.MyTrainItemVO;
import cn.com.dbridge.jtraining.framework.vo.TeacherVO;
import cn.com.dbridge.jtraining.framework.vo.TrainItemVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceTVO;
import cn.com.dbridge.jtraining.framework.vo.TrainSourceVO;
import cn.com.dbridge.jtraining.service.LearnRecordService;
import cn.com.dbridge.jtraining.service.TrainItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName:TrainItemController
 * @Description:トレーニング項目情報制御層
 * @author:
 * @date:2018年12月6日 1:55:59 PM
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁じられています。
 */
@RestController
@Api(tags = "トレーニング情報")
@RequestMapping(value = "/v1/api")
public class TrainItemController {

    @Autowired
    private TrainItemService trainItemService;

	@Autowired
	private LearnRecordService learnRecordService;

    /**后台
     * 
     * @Title: getTrainItemById
     * @Description: 主キーに基づいてトレーニング情報を照会する
     * @param trainId
     * @return Result<TrainItemVO>
     */
    @ApiOperation(value = "主キーに基づいてトレーニング情報を照会する", notes = "主キーに基づいてトレーニング情報を照会する")
    @ApiImplicitParam(name = "trainId", value = "トレーニングID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/trainManage/select/{trainId}")
    @RequiresAuthentication
    public Result<TrainItemVO> getTrainItemById(
            @ApiParam(name = "trainId", value = "トレーニングID") @PathVariable(value = "trainId") Integer trainId) {
        return new Result<TrainItemVO>(HttpStatus.OK.value(),
                "主キーに基づいてトレーニング情報を照会する",
                trainItemService.getTrainItemById(trainId));
    }

    /**后台
     * 
     * @Title: addTrainItem
     * @Description: 追加のトレーニング情報
     * @param TrainItemAddDTO
     * @return Result<Object>
     */
    @ApiOperation(value = "追加のトレーニング情報", notes = "追加のトレーニング情報")
    @PostMapping("/trainManage/insert")
    @RequiresAuthentication
    public Result<Object> addTrainItem(
            @ApiParam(name = "TrainItemAddDTO", value = "トレーニング情報") @RequestBody @Valid TrainItemAddDTO TrainItemAddDTO) {
        return new Result<Object>(HttpStatus.OK.value(), "追加のトレーニング情報",
                trainItemService.addTrainItem(TrainItemAddDTO));
    }

    /**后台
     * 
     * @Title: deleteBackendPersonById
     * @Description: トレーニング情報の削除
     * @param trainId
     * @return Result<Object>
     */
    @ApiOperation(value = "トレーニング情報の削除", notes = "トレーニング情報の削除")
    @DeleteMapping("/trainManage/delete/{trainId}")
    @RequiresAuthentication
    public Result<Object> deleteBackendPersonById(
            @ApiParam(name = "trainId", value = "トレーニングID") @PathVariable(value = "trainId") Integer trainId) {
        trainItemService.deleteTrainItem(trainId);
        return new Result<Object>(HttpStatus.OK.value(), "トレーニング情報の削除");
    }

    /**后台
     * 
     * @Title: updateTrainItemById
     * @Description: トレーニング情報を更新する
     * @param TrainItemUpdateDTO
     * @return Result<Object>
     */
    @ApiOperation(value = "トレーニング情報を更新する", notes = "トレーニング情報を更新する")
    @PutMapping("/trainManage/update")
    @RequiresAuthentication
    public Result<Object> updateTrainItemById(
            @ApiParam(name = "trainItemUpdateDTO", value = "トレーニング情報") @RequestBody @Valid TrainItemUpdateDTO trainItemUpdateDTO) {
        trainItemService.updateTrainItemById(trainItemUpdateDTO);
        return new Result<Object>(HttpStatus.OK.value(), "トレーニング情報を更新する");
    }

    /**后台
     * 
     * @Title: queryTrainItem
     * @Description: クエリのトレーニング情報
     * @param trainCategoryQueryDTO
     * @return Result<List<TrainItemVO>>
     */
    @ApiOperation(value = "クエリのトレーニング情報", notes = "クエリのトレーニング情報")
    @PostMapping("/trainManage/query")
    @RequiresAuthentication
    public Result<List<TrainItemVO>> queryTrainItem(
            @RequestBody TrainItemQueryDTO trainCategoryQueryDTO) {
        return new Result<List<TrainItemVO>>(HttpStatus.OK.value(),
                "クエリのトレーニング情報",
                trainItemService.queryTrainItem(trainCategoryQueryDTO));
    }

	// TODO
    /**
	 * 陳軍
	 * 
	 * @Title: getTrainSource
	 * @Description: トレーニング資料情報を検索する
	 * @param trainListDTO
	 * @return Result<TrainSourceVO>
	 */
	@ApiOperation(value = "トレーニング資料情報を検索する", notes = "トレーニング資料情報を検索する")
	@PostMapping("/source/get")
	@RequiresAuthentication
	public Result<TrainSourceVO> getTrainSource(
			@RequestBody TrainListDTO trainListDTO) {
		TrainSourceVO trainSourceVO = trainItemService
				.getTrainSource(trainListDTO);
		if ((null != trainSourceVO.getTrainId())
				&& (0 != trainSourceVO.getTrainId().intValue())) {
			return new Result<TrainSourceVO>(HttpStatus.OK.value(),
					HttpStatus.OK.getReasonPhrase(), trainSourceVO);
		}
		return new Result<TrainSourceVO>(ResponseCode.NO_DATA_ERROR.getValue(),
				"対象データなし", trainSourceVO);
	}

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: queryTrainSource
	 * @Description: 教師のトレーニング資料を取得する
	 * @param teacherId
	 * @return Result<TrainSourceVO>
	 */
	@ApiOperation(value = "教師のトレーニング資料を取得する", notes = "教師のトレーニング資料を取得する")
	@ApiImplicitParam(name = "teacherId", value = "教师番号", required = true, dataType = "String", paramType = "path")
	@GetMapping("/source/load/{teacherId}")
	@RequiresAuthentication
	public Result<List<TrainSourceTVO>> queryTrainSource(
			@ApiParam(name = "teacherId", value = "教师番号") @PathVariable(value = "teacherId") String teacherId) {
		List<TrainSourceTVO> trainItemPOList = trainItemService
				.queryTrainSource(teacherId);
		if (null != trainItemPOList) {
			return new Result<List<TrainSourceTVO>>(HttpStatus.OK.value(),
					HttpStatus.OK.getReasonPhrase(), trainItemPOList);
		}
		return new Result<List<TrainSourceTVO>>(
				ResponseCode.NO_DATA_ERROR.getValue(), "対象データなし",
				trainItemPOList);
	}

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: queryTrainSource
	 * @Description: 学生の勉強中のトレーニング資料を取得する
	 * @param studentId
	 * @return Result<TrainItemVO>
	 */
	@ApiOperation(value = "学生の勉強中のトレーニング資料を取得する", notes = "学生の勉強中のトレーニング資料を取得する")
	@ApiImplicitParam(name = "studentId", value = "学生番号", required = true, dataType = "String", paramType = "path")
	@GetMapping("/learning/get/{studentId}")
	@RequiresAuthentication
	public Result<List<LearningSourceVO>> queryLearningSource(
			@ApiParam(name = "studentId", value = "学生番号") @PathVariable(value = "studentId") String studentId) {
		List<LearningSourceVO> learningSourceVOList = learnRecordService
				.queryLearningSource(studentId);
		if (null != learningSourceVOList) {
			return new Result<List<LearningSourceVO>>(HttpStatus.OK.value(),
					HttpStatus.OK.getReasonPhrase(),
					learningSourceVOList);
		}
		return new Result<List<LearningSourceVO>>(
				ResponseCode.NO_DATA_ERROR.getValue(), "対象データなし",
				learningSourceVOList);
	}

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: queryLearnedSource
	 * @Description: 学生の勉強完了のトレーニング資料を取得する
	 * @param studentId
	 * @return Result<TrainItemVO>
	 */
	@ApiOperation(value = "学生の勉強完了のトレーニング資料を取得する", notes = "学生の勉強完了のトレーニング資料を取得する")
	@ApiImplicitParam(name = "studentId", value = "学生番号", required = true, dataType = "String", paramType = "path")
	@GetMapping("/learned/get/{studentId}")
	@RequiresAuthentication
	public Result<List<LearnedSourceVO>> queryLearnedSource(
			@ApiParam(name = "studentId", value = "学生番号") @PathVariable(value = "studentId") String studentId) {
		List<LearnedSourceVO> learnedSourceVOList = learnRecordService
				.queryLearnedSource(studentId);
		if (null != learnedSourceVOList) {
			return new Result<List<LearnedSourceVO>>(HttpStatus.OK.value(),
					HttpStatus.OK.getReasonPhrase(),
					learnedSourceVOList);
		}
		return new Result<List<LearnedSourceVO>>(ResponseCode.NO_DATA_ERROR.getValue(),
				"対象データなし", learnedSourceVOList);
	}

    /**后台
     * 
     * @Title: queryCategoryItem
     * @Description: クエリのトレーニング情報
     * @return Result<CategoryItemVO>
     */
    @ApiOperation(value = "クエリのトレーニング情報", notes = "クエリのトレーニング情報")
    @PostMapping("/trainManage/load")
    @RequiresAuthentication
    public Result<CategoryItemVO> queryCategoryItem(
            @RequestBody PageDTO pageDTO) {
        return new Result<CategoryItemVO>(HttpStatus.OK.value(), "クエリのトレーニング情報",
                trainItemService.queryCategoryItem(pageDTO));
    }

    /**后台
     * 
     * @Title: queryCategoryItem
     * @Description: シード情報を初期化する
     * @return Result<ItemVO>
     */
    @ApiOperation(value = "シード情報を初期化する", notes = "シード情報を初期化する")
    @GetMapping("/trainType/load")
    @RequiresAuthentication
    public Result<ItemVO> queryItem() {
        return new Result<ItemVO>(HttpStatus.OK.value(), "シード情報を初期化する",
                trainItemService.queryItem());
    }

    /**后台
     * 
     * @Title: queryCategoryItem
     * @Description: 教師情報をタイプ別に初期化する
     * @return Result<TeacherVO
     */
    @ApiOperation(value = "教師情報をタイプ別に初期化する", notes = "教師情報をタイプ別に初期化する")
    @PostMapping("/trainTeacher/load")
    @RequiresAuthentication
    public Result<TeacherVO> queryTeacher(
            @ApiParam(name = "teacherQueryDTO", value = "質問教師") @RequestBody TeacherQueryDTO teacherQueryDTO) {
        return new Result<TeacherVO>(HttpStatus.OK.value(), "教師情報をタイプ別に初期化する",
                trainItemService.queryTeacher(teacherQueryDTO));
    }

    /**
     * 
     * @Title: selectAllTrainItem
     * @Description: すべてのトレーニング情報のリストを入手する
     * @author 郭健
     * @return MyTrainItemVO
     */
    @ApiOperation(value = "すべてのトレーニング情報のリストを入手する", notes = "すべてのトレーニング情報のリストを入手する")
    @GetMapping("/toppage/load")
    @RequiresAuthentication
    public Result<List<MyTrainItemVO>> selectAllTrainItem() {
        return new Result<List<MyTrainItemVO>>(HttpStatus.OK.value(),
                "トレーニング情報を取得しました",
                trainItemService.selectAllTrainItem());
    }


}

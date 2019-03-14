/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: TrainCategoryController.java
 * @Package cn.com.dbridge.jtraining.rest
 * @Description:トレーニング種別情報制御層
 * @author:
 * @date:2018年12月6日1:55:42 PM
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryUpdateDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainCategoryUpdateStatusDTO;
import cn.com.dbridge.jtraining.framework.vo.TrainCategoryVO;
import cn.com.dbridge.jtraining.service.TrainCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName:TrainCategoryController
 * @Description:トレーニング種別情報制御層
 * @author:
 * @date:2018年12月6日1:55:42 PM
 * 
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁じられています。
 */
@RestController
@Api(tags = "トレーニング種別")
@RequestMapping(value = "/v1/api")
public class TrainCategoryController {

    @Autowired
    private TrainCategoryService trainCategoryService;

    /**
     * @Title: addTrainCategory
     * @Description: 追加のトレーニングカテゴリ情報
     * @param trainCategoryAddDTO
     * @return Result<Object>
     */
    @ApiOperation(value = "追加のトレーニングカテゴリ情報", notes = "追加のトレーニングカテゴリ情報")
    @PostMapping("/trainType/insert")
    @RequiresAuthentication
    public Result<Object> addTrainCategory(
            @ApiParam(name = "trainCategoryAddDTO", value = "トレーニングカテゴリ情報クラス") @RequestBody @Valid TrainCategoryAddDTO trainCategoryAddDTO) {
        trainCategoryService.addTrainCategory(trainCategoryAddDTO);
        return new Result<Object>(HttpStatus.OK.value(), "追加のトレーニングカテゴリ情報");
    }

    /**后台
     * 
     * @Title: updateTrainCategoryById
     * @Description: トレーニングカテゴリ情報を更新する
     * @param trainCategoryUpdateDTO
     * @return Result<Object>
     */
    @ApiOperation(value = "トレーニングカテゴリ情報を更新する", notes = "トレーニングカテゴリ情報を更新する")
    @PutMapping("/trainType/update")
    @RequiresAuthentication
    public Result<Object> updateTrainCategoryById(
            @ApiParam(name = "trainCategoryUpdateDTO", value = "トレーニングカテゴリ情報クラス") @RequestBody @Valid TrainCategoryUpdateDTO trainCategoryUpdateDTO) {
        trainCategoryService.updateTrainCategoryById(trainCategoryUpdateDTO);
        return new Result<Object>(HttpStatus.OK.value(), "トレーニングカテゴリ情報を更新する");
    }

    /**后台
     * 
     * @Title: updateTrainCategory
     * @Description: トレーニングカテゴリ情報のステータスを設定する
     * @param trainCategoryUpdateDTO
     * @return Result<Object>
     */
    @ApiOperation(value = "トレーニングカテゴリ情報のステータスを設定する", notes = "トレーニングカテゴリ情報のステータスを設定する")
    @PutMapping("/trainType/status")
    @RequiresAuthentication
    public Result<Object> updateTrainCategory(
            @ApiParam(name = "trainCategoryUpdateDTO", value = "トレーニングカテゴリ情報クラス") @RequestBody @Valid TrainCategoryUpdateStatusDTO trainCategoryUpdateStatusDTO)
    {
        trainCategoryService.updateTrainCategory(trainCategoryUpdateStatusDTO);
        return new Result<Object>(HttpStatus.OK.value(),
                "トレーニングカテゴリ情報のステータスを設定する");
    }

    /**后台
     * 
     * @Title: queryTrainCategory
     * @Description: クエリのトレーニングカテゴリ情報
     * @param trainCategoryQueryDTO
     * @return Result<List<TrainCategoryVO>>
     */
    @ApiOperation(value = "クエリのトレーニングカテゴリ情報", notes = "クエリのトレーニングカテゴリ情報")
    @PostMapping("/trainType/query")
    @RequiresAuthentication
    public Result<List<TrainCategoryVO>> queryTrainCategory(
            @RequestBody TrainCategoryQueryDTO trainCategoryQueryDTO) {
        return new Result<List<TrainCategoryVO>>(HttpStatus.OK.value(),
                "クエリのトレーニングカテゴリ情報",
                trainCategoryService.queryTrainCategory(trainCategoryQueryDTO));
    }
}

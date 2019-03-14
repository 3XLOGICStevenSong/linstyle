/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: TrainController.java
 * @Package cn.com.dbridge.jtraining.rest
 * @Description:トレーニング教材情報制御層
 * @author:
 * @date:2018年12月6日 1:56:13 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.rest;

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
import cn.com.dbridge.jtraining.framework.dto.TrainAddDTO;
import cn.com.dbridge.jtraining.framework.dto.TrainUpdateDTO;
import cn.com.dbridge.jtraining.framework.vo.TrainVO;
import cn.com.dbridge.jtraining.service.TrainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName:TrainController
 * @Description:トレーニング教材情報制御層
 * @author:
 * @date:2018年12月6日 1:56:13 PM
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁じられています。
 */
@RestController
@Api(tags = "トレーニング教材")
@RequestMapping(value = "/v1/api")
public class TrainController {

    @Autowired
    private TrainService trainService;

    /**后台
     * 
     * @Title: getTrain
     * @Description: プライマリキーによるトレーニング教材の照会
     * @param sourceNo
     * @return Result<TrainVO>
     */
    @ApiOperation(value = "プライマリキーによるトレーニング教材の照会", notes = "プライマリキーによるトレーニング教材の照会")
    @ApiImplicitParam(name = "sourceNo", value = "トレーニング資料番号", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/resource/select/{sourceNo}")
    @RequiresAuthentication
    public Result<TrainVO> getTrain(
            @ApiParam(name = "sourceNo", value = "トレーニング資料番号") @PathVariable(value = "sourceNo") Integer sourceNo) {
        return new Result<TrainVO>(HttpStatus.OK.value(),
                "プライマリキーによるトレーニング教材の照会",
                trainService.getTrain(sourceNo));
    }

    /**后台
     * 
     * @Title: addTrain
     * @Description: 追加トレーニング教材
     * @param trainAddDTO
     * @return Result<Object>
     */
    @ApiOperation(value = "追加トレーニング教材", notes = "追加トレーニング教材")
    @PostMapping("/resource/insert")
    @RequiresAuthentication
    public Result<Object> addTrain(
            @ApiParam(name = "trainAddDTO", value = "トレーニング教材") @RequestBody @Valid TrainAddDTO trainAddDTO) {
        return new Result<Object>(HttpStatus.OK.value(), "追加トレーニング教材",
                trainService.addTrain(trainAddDTO));
    }

    /**后台
     * 
     * @Title: deleteTrain
     * @Description: トレーニング教材の削除
     * @param sourceNo
     * @return Result<Object>
     */
    @ApiOperation(value = "トレーニング教材の削除", notes = "トレーニング教材の削除")
    @DeleteMapping("/resource/delete/{sourceNo}")
    @RequiresAuthentication
    public Result<Object> deleteTrain(
            @ApiParam(name = "sourceNo", value = "トレーニング資料番号") @PathVariable(value = "sourceNo") Integer sourceNo) {
        trainService.deleteTrain(sourceNo);
        return new Result<Object>(HttpStatus.OK.value(), "トレーニング教材の削除");
    }

    /**后台
     * 
     * @Title: updateTrain
     * @Description: トレーニング教材の更新
     * @param TrainUpdateDTO
     * @return Result<Object>
     */
    @ApiOperation(value = "トレーニング教材の更新", notes = "トレーニング教材の更新")
    @PutMapping("/resource/update")
    @RequiresAuthentication
    public Result<Object> updateTrain(
            @ApiParam(name = "TrainUpdateDTO", value = "トレーニング教材") @RequestBody @Valid TrainUpdateDTO TrainUpdateDTO) {
        trainService.updateTrain(TrainUpdateDTO);
        return new Result<Object>(HttpStatus.OK.value(), "トレーニング教材の更新");
    }
}

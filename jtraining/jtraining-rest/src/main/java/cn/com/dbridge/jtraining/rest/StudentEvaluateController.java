/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: StudentEvaluateController.java
 * @Package cn.com.dbridge.jtraining.rest
 * @Description:学生評価情報制御層
 * @author:郭健
 * @date: 2018年12月6日 1:55:28PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.rest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.dto.AddOrUpdateEvaluateDTO;
import cn.com.dbridge.jtraining.framework.dto.EvaluatePersonDTO;
import cn.com.dbridge.jtraining.framework.vo.EvaluateInfoVO;
import cn.com.dbridge.jtraining.service.StudentEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: StudentEvaluateController
 * @Description:学生評価情報制御層
 * @author:郭健
 * @date:2018年12月6日 1:55:28PM
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注：このコンテンツは DBJ Information Technology Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁止されています。
 */
@RestController
@Api(tags = "評価関連")
@RequestMapping(value = "/v1/api")
public class StudentEvaluateController {
    @Autowired
    private StudentEvaluateService studentEvaluateService;

    /**
     * 
     * @Title: selectEvaluate 
     * @Description:学生に関する教師の評価情報を入手する
     * @param evaluatePersonDTO クエリ条件
     * @author: 郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "学生に関する教師の評価情報を入手する", notes = "学生に関する教師の評価情報を入手する")
    @PostMapping("/evaluate/get")
    @RequiresAuthentication
    public Result<EvaluateInfoVO> selectEvaluate(
            @RequestBody EvaluatePersonDTO evaluatePersonDTO) {
        return new Result<EvaluateInfoVO>(HttpStatus.OK.value(), "評価を正常に取得しました",
                studentEvaluateService.selectEvaluate(evaluatePersonDTO));
    }

    /**
     * 
     * @Title: addOrUpdateEvaluate 
     * @Description:評価情報が既に存在する場合にはデータを更新し、評価情報が存在しない場合にはデータを追加する
     * @param addOrUpdateEvaluateDTO 運用データ
     * @author: 郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "評価情報が既に存在する場合にはデータを更新し、評価情報が存在しない場合にはデータを追加する", notes = "評価情報が既に存在する場合にはデータを更新し、評価情報が存在しない場合にはデータを追加する")
    @PostMapping("/evaluate/upload")
    @RequiresAuthentication
    public Result<Integer> addOrUpdateEvaluate(
            @RequestBody AddOrUpdateEvaluateDTO addOrUpdateEvaluateDTO) {
        return new Result<Integer>(HttpStatus.OK.value(), "成功した運転評価",
                studentEvaluateService
                        .addOrUpdateEvaluate(addOrUpdateEvaluateDTO));
    }
}

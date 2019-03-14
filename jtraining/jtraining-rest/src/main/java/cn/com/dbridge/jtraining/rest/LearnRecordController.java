/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: LearnRecordController.java
 * @Package cn.com.dbridge.jtraining.rest
 * @Description:学習記録情報制御層
 * @author: 陈健飞
 * @date:2018年12月6日 1:55:19 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.rest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.dto.LearnRecordUpdateDTO;
import cn.com.dbridge.jtraining.framework.enums.ResponseCode;
import cn.com.dbridge.jtraining.service.LearnRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName:LearnRecordController
 * @Description:学習記録情報制御層
 * @author:
 * @date:2018年12月6日 1:55:19 PM
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁じられています。
 */
@RestController
@Api(tags = "勉強情報")
@RequestMapping(value = "/v1/api")
public class LearnRecordController {

	@Autowired
	private LearnRecordService learnRecordService;

    /**陈军
     * 
     * @Title: updateLearnRecord
     * @Description: 勉強記録情報の更新
     * @param learnRecordUpdateDTO
     * @return Result<Object>
     */
	@ApiOperation(value = "勉強記録情報の更新", notes = "勉強記録情報の更新")
	@PutMapping("/studyrecord/update")
	@RequiresAuthentication
	public Result<Object> updateLearnRecord(
			@ApiParam(name = "LearnRecordUpdateDTO", value = "勉強記録更新DTO") @RequestBody LearnRecordUpdateDTO learnRecordUpdateDTO) {
		int result = learnRecordService.updateLearnRecord(learnRecordUpdateDTO);
		if (0 < result) {
			return new Result<Object>(HttpStatus.OK.value(),
					HttpStatus.OK.getReasonPhrase(), result);
		}
		return new Result<Object>(ResponseCode.UPDATE_ERROR.getValue(),
				"更新されたデータなし", result);
	}

}

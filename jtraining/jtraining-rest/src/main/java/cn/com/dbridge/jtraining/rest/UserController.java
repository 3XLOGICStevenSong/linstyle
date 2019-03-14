/**
 * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title:UserController.java
 * @Package cn.com.dbridge.jtraining.rest
 * @Description:ユーザー情報制御層
 * @author:
 * @date:2018年12月6日 1:56:27 PM
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
 *              * 注：このコンテンツは、DJB Information Technology
 *             Co.Ltd.の内部流通のためのものであり、漏洩および他の商業目的での使用は禁じられています。
 */
package cn.com.dbridge.jtraining.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.auth.common.Constant;
import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.dto.PasswordChangeDTO;
import cn.com.dbridge.jtraining.framework.dto.PhoneUserDTO;
import cn.com.dbridge.jtraining.framework.dto.UserDTO;
import cn.com.dbridge.jtraining.framework.dto.UserSelectQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.UserUpdatePasswordDTO;
import cn.com.dbridge.jtraining.framework.dto.ValidateGroupOne;
import cn.com.dbridge.jtraining.framework.enums.ResponseCode;
import cn.com.dbridge.jtraining.framework.exception.CustomException;
import cn.com.dbridge.jtraining.framework.util.AesCipherUtil;
import cn.com.dbridge.jtraining.framework.vo.InformationUpdateVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneStudentVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneTeacherVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneUserVO;
import cn.com.dbridge.jtraining.framework.vo.StudentEvaluateVO;
import cn.com.dbridge.jtraining.framework.vo.UserVO;
import cn.com.dbridge.jtraining.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName:UserController
 * @Description:ユーザー情報制御層
 * @author:
 * @date:2018年12月6日 1:56:27 PM
 * 
 * @Copyright: 2018 www.tydic.com Inc.すべての権利を保有します。
  *注：このコンテンツは、DJB Information Technology Co.Ltd.が社内でのみ流通させており、他の商業目的で漏洩することは禁じられています。
 */
@RestController
@Api(tags = "ユーザー管理")
@RequestMapping(value = "/v1/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**后台
     * 
     * @Title: uploadUser
     * @Description: ユーザー情報を更新する
     * @param userDTO
     * @return ステータスコードとステータス
     */
    @ApiOperation(value = "添加修改", notes = "添加修改")
    @PostMapping(value = "/user/backendAdd")
    @RequiresAuthentication
    public Result<Object> add(
            @RequestBody @Validated({ValidateGroupOne.class}) UserDTO userDTO) {
        //定義追加変更判定フラグ
        int count = 0;
        //addメソッドを呼び出してオペランドを返します。
        count = userService.addUser(userDTO);
        //オペランドが0以下の場合
        if (count <= 0) {
            //投げられる例外を追加できませんでした
            throw new CustomException("新しい失敗");
        }
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", count);
    }

    @ApiOperation(value = "添加修改", notes = "添加修改")
    @PostMapping(value = "/user/backendUpdate")
    @RequiresAuthentication
    public Result<Object> Update(@RequestBody @Valid UserDTO userDTO) {
        //定義追加変更判定フラグ
        int count = 0;
        //オペランドを返すには、modifyメソッドを呼び出します。
        count = userService.updateUser(userDTO);
        //オペランドが0以下の場合
        if (count <= 0) {
            //ねじれた例外の修正に失敗しました
            throw new CustomException("変更に失敗しました");
        }
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", count);
    }

    /**后台
     * 
     * @Title: addno
     * @Description: 追加の機会、学生が拾うために
     * @param userDTO
     * @return ステータスコードとステータス
     */
    @ApiOperation(value = "追加の主キー", notes = "追加の主キー")
    @GetMapping(value = "/user/getno/{type}")
    @RequiresAuthentication
    public Result<Object> addno(@PathVariable(value = "type") Byte type) {
        //戻り値を取得するメソッドを呼び出す
        String no = userService.selectNo(type);
        //戻り値がnullの場合
        if (StringUtils.isEmpty(no)) {
            //例外クエリのスローに失敗しました
            throw new CustomException("クエリに失敗しました");
        }
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", no);
    }
    //    /**
    //     * 
    //     * @Title: selectByKey
    //     * @Description: 主キーを押して照会する
    //     * @param no
    //     * @return ステータスコードとステータス
    //     */
    //    @ApiOperation(value = "主キーを押して照会する", notes = "主キーを押して照会する")
    //    @GetMapping(value = "/user/load/{no}")
    //    public Result<Object> selectByKey(@PathVariable(value = "no") String no) {
    //        //1
    //        //出力オブジェクトの定義
    //        UserVO userVO = userService.getUserByAccount(no);
    //        //出力オブジェクトが空の場合
    //        if (null == userVO) {
    //            //例外クエリのスローに失敗しました
    //            throw new CustomException("クエリに失敗しました");
    //        }
    //        //状態コードを返し、操作は成功しました
    //        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", null);
    //    }

    /**后台
     * 
     * @Title: selectByUser
     * @Description: 条件によるクエリ
     * @param userSelectQueryDTO
     * @return
     */
    @ApiOperation(value = "入力条件によるクエリ", notes = "入力条件によるクエリ")
    @PostMapping(value = "/user/get")
    @RequiresAuthentication
    public Result<Object> selectByUser(
            @RequestBody UserSelectQueryDTO userSelectQueryDTO) {
        //出力コレクションを定義し、条件付きクエリメソッドを呼び出します。
        List<UserVO> queryList = userService.queryByUser(userSelectQueryDTO);
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", queryList);
    }

    /**后台
     * 
     * @Title: deleteByKey
     * @Description: 主キーを押して削除する
     * @param no
     * @return ステータスコード
     */
    @ApiOperation(value = "削除", notes = "削除")
    @RequiresAuthentication
    @DeleteMapping(value = "/user/delete/{no}")
    public Result<Object> deleteByKey(@PathVariable(value = "no") String no) {
        //1
        //オペランドを返すためにdeleteメソッドを呼び出す
        int count = userService.deleteUser(no);
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", count);
    }

    /**后台
     * @author 刘铭
     * @Title: selectAll
     * @Description: 1つのタイプのすべての情報を照会する
     * @param type
     * @return ステータスコード
     */
    @ApiOperation(value = "すべての情報を照会する", notes = "すべての情報を照会する")
    @PostMapping(value = "/user/load")
    @RequiresAuthentication
    public Result<Object> selectAll(@RequestBody UserDTO userDTO) {
        //すべてのクエリメソッドを出力コレクションに呼び出す
        List<UserVO> userVOList = userService.selectAll(userDTO);
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", userVOList);
    }

    /**后台
     * 
     * @Title: selectEvaluate
     * @Description: 学生評価を検索する
     * @param studentId
     * @return ステータスコード
     */
    @ApiOperation(value = "クエリ評価", notes = "クエリ評価")
    @GetMapping(value = "/student/preview/{studentId}")
    @RequiresAuthentication
    public Result<Object> selectEvaluate(
            @PathVariable(value = "studentId") String studentId) {
        //1
        //学生評価を検索のリスト
        List<StudentEvaluateVO> studentEvaluateVOList = userService
                .selectStudentEvaluate(studentId);
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功",
                studentEvaluateVOList);
    }

    /**后台
     * 
     * @Title: selectMessage
     * @Description: ジョブ番号、名前、パスワードを条件別に確認する
     * @param noOrName
     * @return  ステータスコード
     */
    @ApiOperation(value = "ユーザー情報を取得する", notes = "ユーザー情報を取得する")
    @GetMapping(value = "/password/get/{noOrName}")
    @RequiresAuthentication
    public Result<Object> selectMessage(
            @PathVariable(value = "noOrName") String noOrName) {
        //出力の対象
        UserVO userVO = userService.selectUserByNoOrName(noOrName);
        //状態コードを返し、操作は成功しました
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", userVO);
    }

    /**
     * @author 刘铭
     * @Title: updatePassword
     * @Description: 修改密码
     * @param userDTO
     * @return  返回状态及操作数
     */
    @ApiOperation(value = "ユーザー情報を変更する", notes = "ユーザー情報を変更する")
    @PutMapping(value = "/password/update")
    @RequiresAuthentication
    public Result<Object> updatePassword(
            @RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO) {
        if (userUpdatePasswordDTO.getPassword()
                .length() > Constant.PASSWORD_MAX_LEN) {
            throw new CustomException("最大20桁のパスワード");
        }
        String key = AesCipherUtil.enCrypto(userUpdatePasswordDTO.getNo()
                + userUpdatePasswordDTO.getPassword());
        userUpdatePasswordDTO.setPassword(key);
        int count = userService
                .updatePasswordByPrimaryKey(userUpdatePasswordDTO);
        
        if (count == 0) {
            throw new CustomException("変更に失敗しました");
        }
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", count);
    }

    /**后台
     * 
     * @Title: getUserMessage 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param no
     * @author 
     * @return
     */
    @ApiOperation(value = "ユーザー情報を変更する", notes = "ユーザー情報を変更する")
    @GetMapping(value = "/user/select/{no}")
    @RequiresAuthentication
    public Result<Object> getUserMessage(
            @PathVariable(value = "no") String no) {
        UserVO userVO = userService.selectUserByNoOrName(no);
        return new Result<Object>(HttpStatus.OK.value(), "操作された成功", userVO);
    }

	// TODO 陳軍
	@ApiOperation(value = "パスワードの更新", notes = "パスワードの更新")
	@PutMapping("/password/change")
	@RequiresAuthentication
	public Result<Object> updatePassword(
			@ApiParam(name = "passwordChangeDTO", value = "パスワード情報") @RequestBody PasswordChangeDTO passwordChangeDTO) {
		int result = userService.updatePassword(passwordChangeDTO);
		if (-1 != result) {
			return new Result<Object>(HttpStatus.OK.value(),
					HttpStatus.OK.getReasonPhrase(),
					result);
		} else {
			return new Result<Object>(ResponseCode.PASSWORD_ERROR.getValue(),
					ResponseCode.PASSWORD_ERROR.getReasonPhrase(),
					result);
		}
	}

	// TODO 陳軍
	@RequestMapping(path = "/updateInfor", consumes = {
			"multipart/form-data"}, method = RequestMethod.POST)
	@ApiOperation(value = "個人情報の更新", notes = "個人情報の更新")
	@RequiresAuthentication
	public Result<InformationUpdateVO> updateInfor(
            @ApiParam(name = "request", value = "個人情報") HttpServletRequest request)
            throws Exception {
		InformationUpdateVO informationUpdateVO = userService
				.updatePersonalInfor(request);
		if (0 < informationUpdateVO.getUpdatedCount()) {
			return new Result<InformationUpdateVO>(HttpStatus.OK.value(),
					HttpStatus.OK.getReasonPhrase(),
					informationUpdateVO);
		}
		return new Result<InformationUpdateVO>(
				ResponseCode.UPDATE_ERROR.getValue(), "更新されたデータなし",
				informationUpdateVO);
	}

    /**
     * 
     * @Title: selectPersonByNo 
     * @Description: 番号に基づいてユーザー情報を取得する
     * @param phoneUserDTO クエリ条件
     * @author:郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "番号に基づいてユーザー情報を取得する", notes = "番号に基づいてユーザー情報を取得する")
    @GetMapping("/user/get")
    @RequiresAuthentication
    public Result<PhoneUserVO> selectPersonByNo(
            PhoneUserDTO phoneUserDTO) {
        return new Result<PhoneUserVO>(HttpStatus.OK.value(), "情報を正常に取得しました",
                userService.selectPersonByNo(phoneUserDTO));
    }

    /**
     * 
     * @Title: selectAllStudents 
     * @Description:すべての学生情報を取得する
     * @param phoneStudentQueryDTO クエリ条件
     * @author:郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "すべての学生情報を取得する", notes = "すべての学生情報を取得する")
    @GetMapping("/student/load")
    @RequiresAuthentication
    public Result<List<PhoneStudentVO>> selectAllStudents() {
        return new Result<List<PhoneStudentVO>>(HttpStatus.OK.value(),
                "情報を正常に取得しました",
                userService.selectAllStudents());
    }

    /**
     * 
     * @Title: selectAllTeachers 
     * @Description:すべての教師の情報を取得する
     * @param phoneTeacherQueryDTO クエリ条件
     * @author:郭健
     * @return 動作ステータスとプロンプト
     */
    @ApiOperation(value = "すべての教師の情報を取得する", notes = "すべての教師の情報を取得する")
    @GetMapping("/teacher/load")
    @RequiresAuthentication
    public Result<List<PhoneTeacherVO>> selectAllTeachers() {
        return new Result<List<PhoneTeacherVO>>(HttpStatus.OK.value(),
                "情報を正常に取得しました",
                userService.selectAllTeachers());
    }
}

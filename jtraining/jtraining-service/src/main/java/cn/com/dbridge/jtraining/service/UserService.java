/**
 * All rights Reserved, Designed By dbridge.com.cn
 * @Title:  UserService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description:    TODO(用一句话描述该文件做什么) 
 * @author: 陈健飞 
 * @date:   2018年12月6日 下午1:56:27
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.dbridge.jtraining.framework.dto.PasswordChangeDTO;
import cn.com.dbridge.jtraining.framework.dto.PhoneUserDTO;
import cn.com.dbridge.jtraining.framework.dto.UserDTO;
import cn.com.dbridge.jtraining.framework.dto.UserSelectQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.UserUpdatePasswordDTO;
import cn.com.dbridge.jtraining.framework.vo.InformationUpdateVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneStudentVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneTeacherVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneUserVO;
import cn.com.dbridge.jtraining.framework.vo.StudentEvaluateVO;
import cn.com.dbridge.jtraining.framework.vo.UserLoginVO;
import cn.com.dbridge.jtraining.framework.vo.UserVO;

/**
 * @ClassName:  UserService
 * @Description:学生の管理
 * @author: 劉銘
 * @date:   2018年12月6日 下午1:56:27
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface UserService {
    
    /**登录
     * 
     * @Title: getByPrimaryKey
     * @Description: 主キーを使用したクエリ
     * @param no
     * @return クエリ結果
     */
    UserLoginVO getUserByAccount(String no);

    /**后台
     * 
     * @Title: addUser
     * @Description:ユーザー追加
     * @param userAddDTO
     * @return 追加のオペランドを返す
     */
    int addUser(UserDTO userDTO);

    /**后台
     * 
     * @Title: updateUser
     * @Description: ユーザーの変更
     * @param userAddDTO
     * @return 変更のオペランドを返す
     */
    int updateUser(UserDTO userDTO);

    /**后台
     * 
     * @Title: queryByUser
     * @Description:  基準による学生情報の問い合わせ
     * @param userSelectQueryDTO
     * @param offset
     * @param limit
     * @return クエリ情報
     */
    List<UserVO> queryByUser(UserSelectQueryDTO userSelectQueryDTO);

    /**后台
     * 
     * @Title: selectNo
     * @Description: 追加の機会、学生が拾うために
     * @param type
     * @return 追加の番号
     */
    String selectNo(Byte type);

    /**后台
     * 
     * @Title: deleteUser
     * @Description: ユーザーを削除
     * @param userDTO
     * @return オペランド
     */
    int deleteUser(String no);

    /**后台
     * @author 刘铭
     * @Title: selectAll
     * @Description: ユーザー情報タイプのチェック
     * @param type
     * @return ユーザー情報
     */
    List<UserVO> selectAll(UserDTO UserDTO);

    /**后台
     * 
     * @Title: selectStudentEvaluate
     * @Description: 学生評価の質問
     * @param studentId
     * @return 学生評価の収集
     */
    List<StudentEvaluateVO> selectStudentEvaluate(String studentId);

    /**后台
     * 
     * @Title: selectUserBynoOrName
     * @Description: 学生と教師のIDと名前を質問する
     * @param noOrName
     * @return 学生と教師のIDと名前
     * @author 
     */
    UserVO selectUserByNoOrName(String noOrName);

    /**后台
     * 
     * @Title: updatePasswordByPrimaryKey
     * @Description: 主キーを押してパスワードを変更する
     * @param no
     * @return オペランド
     */
    int updatePasswordByPrimaryKey(UserUpdatePasswordDTO userUpdatePasswordDTO);

	// TODO
	/**
	 * 陳軍
	 * 
	 * @Title: updatePassword
	 * @Description: パスワードを変更する
	 * @param passwordChangeDTO
	 * @return 更新件数
	 */
	int updatePassword(PasswordChangeDTO passwordChangeDTO);

	// TODO
	/**
     * 陳軍
     * 
     * @Title: updatePersonalInfor
     * @Description: 個人情報を変更する
     * @param request
     * @return 更新件数
     * @throws Exception 
     */
	InformationUpdateVO updatePersonalInfor(
            HttpServletRequest request) throws Exception;

    /**
     * 
     * @Title: selectPersonByNo 
     * @Description: 番号に基づいてユーザー情報を取得する 
     * @param phoneUserDTO クエリ条件
     * @author 郭健
     * @return ユーザー情報
     */
    PhoneUserVO selectPersonByNo(PhoneUserDTO phoneUserDTO);

    /**
     * 
     * @Title: selectAllStudents 
     * @Description: すべての学生情報のリストを取得する
     * @author 郭健
     * @return 学生情報のリスト
     */
    List<PhoneStudentVO> selectAllStudents();

    /**
     * 
     * @Title: selectAllTeachers 
     * @Description: すべての教師のリストを入手する
     * @author 郭健
     * @return 教師のリスト
     */
    List<PhoneTeacherVO> selectAllTeachers();
}

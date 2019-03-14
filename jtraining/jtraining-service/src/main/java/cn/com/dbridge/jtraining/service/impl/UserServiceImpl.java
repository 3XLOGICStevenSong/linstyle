/**
· * All rights Reserved, Designed By dbridge.com.cn
 * 
 * @Title: UserService.java
 * @Package cn.com.dbridge.jtraining.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 陈健飞
 * @date: 2018年12月6日 下午1:56:27
 * @version V1.0
 * @Copyright: 2018 dbridge.com.cn Inc. All rights reserved.
 *             注意：本内容仅限于必捷必信息技术有限公司 内部传阅，禁止外泄以及用于其他的商业目
 */
package cn.com.dbridge.jtraining.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.dbridge.jtraining.dao.po.MyUserForTeacherPO;
import cn.com.dbridge.jtraining.dao.po.MyUserPO;
import cn.com.dbridge.jtraining.dao.po.StudentEvaluatePO;
import cn.com.dbridge.jtraining.dao.po.TrainCategoryPO;
import cn.com.dbridge.jtraining.dao.po.UserPO;
import cn.com.dbridge.jtraining.dao.po.UserQueryPO;
import cn.com.dbridge.jtraining.dao.respository.LearnRecordPOMapper;
import cn.com.dbridge.jtraining.dao.respository.StudentEvaluatePOMapper;
import cn.com.dbridge.jtraining.dao.respository.TrainCategoryPOMapper;
import cn.com.dbridge.jtraining.dao.respository.UserPOMapper;
import cn.com.dbridge.jtraining.framework.dto.PasswordChangeDTO;
import cn.com.dbridge.jtraining.framework.dto.PhoneUserDTO;
import cn.com.dbridge.jtraining.framework.dto.UserDTO;
import cn.com.dbridge.jtraining.framework.dto.UserSelectQueryDTO;
import cn.com.dbridge.jtraining.framework.dto.UserUpdatePasswordDTO;
import cn.com.dbridge.jtraining.framework.exception.CustomAuthException;
import cn.com.dbridge.jtraining.framework.exception.CustomException;
import cn.com.dbridge.jtraining.framework.util.AesCipherUtil;
import cn.com.dbridge.jtraining.framework.util.AgeUtils;
import cn.com.dbridge.jtraining.framework.vo.InformationUpdateVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneStudentVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneTeacherVO;
import cn.com.dbridge.jtraining.framework.vo.PhoneUserVO;
import cn.com.dbridge.jtraining.framework.vo.StudentEvaluateVO;
import cn.com.dbridge.jtraining.framework.vo.UserLoginVO;
import cn.com.dbridge.jtraining.framework.vo.UserVO;
import cn.com.dbridge.jtraining.service.UserService;
import cn.com.dbridge.jtraining.upload.picture.controller.FileUploadController;

/**
 * @ClassName:  UserService
 * @Description:学生の管理サービス
 * @author: 劉銘
 * @date:   2018年12月6日 下午1:56:27
 * 
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserPOMapper userPOMapper;
    @Autowired
    private StudentEvaluatePOMapper studentEvaluatePOMapper;
    @Autowired
    private TrainCategoryPOMapper trainCategoryPOMapper;
	@Autowired
	private FileUploadController fileUploadController;
    @Autowired
    private LearnRecordPOMapper learnRecordPOMapper;
    /**后台
     * 
     * Title: addUser  
     * Description:  
     * @param userDTO
     * @return   
     * @see cn.com.dbridge.jtraining.service.UserService#addUser(cn.com.dbridge.jtraining.framework.dto.UserDTO)
     */
    @Override
    public int addUser(UserDTO userDTO) {
        //添加时查询编号是否已存在
        UserPO nullUserPO = userPOMapper.selectByPrimaryKey(userDTO.getNo());
        if (nullUserPO != null) {
            throw new CustomAuthException("该用户编号已存在，请重新输入");
        }
        Calendar birthday = Calendar.getInstance();
        if (null != userDTO.getBirthday()) {
            birthday.setTime(userDTO.getBirthday());
            int yearBirthday = birthday.get(Calendar.YEAR);
            int monthBirthday = birthday.get(Calendar.MONTH);
            int dayBirthday = birthday.get(Calendar.DAY_OF_MONTH);
            Calendar newDate = Calendar.getInstance();
            newDate.setTime(new Date());
            int yearNewDate = newDate.get(Calendar.YEAR);
            int monthNewDate = newDate.get(Calendar.MONTH);
            int dayNewDate = newDate.get(Calendar.DAY_OF_MONTH);
            //判断生日大于当前日期的情况
            if (yearBirthday > yearNewDate) {
                throw new CustomAuthException("生日错误");
            } else if (yearBirthday == yearNewDate
                    && monthBirthday > monthNewDate) {
                throw new CustomAuthException("生日错误");
            } else if (yearBirthday == yearNewDate
                    && monthBirthday == monthNewDate
                    && dayBirthday > dayNewDate) {
                throw new CustomAuthException("生日错误");
            }
        }
        //プロジェクトエンティティクラス
        UserPO userPO = new UserPO();
        String password = "123456";
        String key = AesCipherUtil.enCrypto(userDTO.getNo() + password);
        //初期パスワードを123456に設定する
        userDTO.setPassword(key);
        userDTO.setUseStatus((byte) 0);
        //入力をプロジェクトエンティティクラスに保存する
        BeanUtils.copyProperties(userDTO, userPO);
        String[] str = null;
        List<String> nameList = userPOMapper.selectName(userDTO.getName());
        if (!nameList.isEmpty() && nameList.size() > 1) {
            for (int i = 1; i < nameList.size(); i++) {
                if (i == nameList.size() - 1) {
                    if (nameList.get(i).contains("_")) {
                        str = nameList.get(i).split("_");
                        if (!StringUtils.isEmpty(str[1])) {
                            Integer index = Integer.parseInt(str[1]) + 1;
                            String nameNo = index.toString();
                            String name = str[0] + "_" + nameNo;
                            userPO.setName(name);
                        }
                    }
                }
            }
        } else if (nameList.size() == 1) {
            for (int i = 0; i < nameList.size(); i++) {
                String name = userDTO.getName() + "_" + "1";
                userPO.setName(name);
            }
        }

        userPO.setInsertDate(new Timestamp(System.currentTimeMillis()));
        userPO.setInsertPerson("Admin");
        //オペレーションリターンオペランドを追加する
        return userPOMapper.insert(userPO);
    }

    /**后台
     * 
     * Title: updateUser  
     * Description:  
     * @param userDTO
     * @return   
     * @see cn.com.dbridge.jtraining.service.UserService#updateUser(cn.com.dbridge.jtraining.framework.dto.UserDTO)
     */
    @Override
    public int updateUser(UserDTO userDTO) {
        //编辑时查询是否存在该编号
        UserPO nullUserPO = userPOMapper.selectByPrimaryKey(userDTO.getNo());
        if (nullUserPO == null) {
            throw new CustomAuthException("该用户编号不存在，请重新输入");
        }
        Calendar birthday = Calendar.getInstance();
        if (null != userDTO.getBirthday()) {
            birthday.setTime(userDTO.getBirthday());
            int yearBirthday = birthday.get(Calendar.YEAR);
            int monthBirthday = birthday.get(Calendar.MONTH);
            int dayBirthday = birthday.get(Calendar.DAY_OF_MONTH);
            Calendar newDate = Calendar.getInstance();
            newDate.setTime(new Date());
            int yearNewDate = newDate.get(Calendar.YEAR);
            int monthNewDate = newDate.get(Calendar.MONTH);
            int dayNewDate = newDate.get(Calendar.DAY_OF_MONTH);
            //判断生日大于当前日期的情况
            if (yearBirthday > yearNewDate) {
                throw new CustomAuthException("生日错误");
            } else if (yearBirthday == yearNewDate
                    && monthBirthday > monthNewDate) {
                throw new CustomAuthException("生日错误");
            } else if (yearBirthday == yearNewDate
                    && monthBirthday == monthNewDate
                    && dayBirthday > dayNewDate) {
                throw new CustomAuthException("生日错误");
            }
        }
        //プロジェクトエンティティクラス
        UserPO userPO = new UserPO();
        //入力をプロジェクトエンティティクラスに保存する
        BeanUtils.copyProperties(userDTO, userPO);
        //查询是否有重名
        String[] str = null;
        List<String> nameList = userPOMapper.selectName(userDTO.getName());
        if (!nameList.isEmpty() && nameList.size() > 1) {
            for (int i = 1; i < nameList.size(); i++) {
                if (i == nameList.size() - 1) {
                    if (nameList.get(i).contains("_")) {
                        str = nameList.get(i).split("_");
                        Integer index = Integer.parseInt(str[1]) + 1;
                        String nameNo = index.toString();
                        String name = str[0] + "_" + nameNo;
                        userPO.setName(name);
                    }
                }
            }
        } else if (nameList.size() == 1) {
            for (int i = 1; i < nameList.size(); i++) {
                String name = userDTO.getName() + "_" + "1";
                userPO.setName(name);
            }
        }
        userPO.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        userPO.setUpdatePerson("Admin");
        //変更操作の戻りオペランドを作成する
        return userPOMapper.updateByKey(userPO);
    }

    /**后台
     * 
     * Title: queryByUser  
     * Description:  
     * @param userSelectQueryDTO
     * @return   
     * @see cn.com.dbridge.jtraining.service.UserService#queryByUser(cn.com.dbridge.jtraining.framework.dto.UserSelectQueryDTO)
     */
    @Override
    public List<UserVO> queryByUser(UserSelectQueryDTO userSelectQueryDTO) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userSelectQueryDTO, userPO);
        //出力オブジェクトコレクションをインスタンス化する
        List<UserVO> userVOList = new ArrayList<UserVO>();
        if (null != userSelectQueryDTO.getOffset()
                && null != userSelectQueryDTO.getLimit()) {
            //改ページ設定
            PageHelper.startPage(userSelectQueryDTO.getOffset(),
                    userSelectQueryDTO.getLimit());
        }
        //条件付き照会メソッドを呼び出す
        List<UserPO> userPOList = userPOMapper.selectByUser(userPO);
        PageInfo<UserPO> pageInfo = new PageInfo<UserPO>(userPOList);
        //出力オブジェクトの定義
        UserVO userVO = null;
        //データベースマップをトラバースするエンティティクラスのコレクション
        for (UserPO userPOtemp : userPOList) {
            //出力オブジェクトをインスタンス化する
            userVO = new UserVO();
            if (userPOtemp.getTrainType() != null) {
                TrainCategoryPO trainCategoryPO = trainCategoryPOMapper
                        .selectByPrimaryKey(userPOtemp.getTrainType());
                if (trainCategoryPO != null) {
                    userVO.setTrainName(trainCategoryPO.getTypeName());
                }
            }
            //データベースにマップされたエンティティクラスオブジェクトを出力オブジェクトに割り当てる
            BeanUtils.copyProperties(userPOtemp, userVO);
            //取出生日和当前时间的年月日算出生日
            Calendar birthday = Calendar.getInstance();
            if (null != userPOtemp.getBirthday()) {
                birthday.setTime(userPOtemp.getBirthday());
                int yearBirthday = birthday.get(Calendar.YEAR);
                int monthBirthday = birthday.get(Calendar.MONTH);
                int dayBirthday = birthday.get(Calendar.DAY_OF_MONTH);
                Calendar newDate = Calendar.getInstance();
                newDate.setTime(new Date());
                int yearNewDate = newDate.get(Calendar.YEAR);
                int monthNewDate = newDate.get(Calendar.MONTH);
                int dayNewDate = newDate.get(Calendar.DAY_OF_MONTH);
                //判断生日大于当前日期的情况
                if (yearBirthday > yearNewDate) {
                    throw new CustomAuthException("生日错误");
                } else if (yearBirthday == yearNewDate
                        && monthBirthday > monthNewDate) {
                    throw new CustomAuthException("生日错误");
                } else if (yearBirthday == yearNewDate
                        && monthBirthday == monthNewDate
                        && dayBirthday > dayNewDate) {
                    throw new CustomAuthException("生日错误");
                } else if (yearBirthday == yearNewDate
                        && monthBirthday == monthNewDate
                        && dayBirthday == dayNewDate) {
                    userVO.setAge(0);
                }
                //计算年龄
                if (monthBirthday > monthNewDate) {
                    userVO.setAge(yearNewDate - yearBirthday - 1);
                } else if (monthBirthday < monthNewDate) {
                    userVO.setAge(yearNewDate - yearBirthday);
                } else {
                    if (dayBirthday > dayNewDate) {
                        userVO.setAge(yearNewDate - yearBirthday - 1);
                    } else {
                        userVO.setAge(yearNewDate - yearBirthday);
                    }
                }
            }
            userVO.setCount(pageInfo.getTotal());
            //出力オブジェクトを出力コレクションに追加する
            userVOList.add(userVO);
        }
        //出力コレクションを返す
        return userVOList;
    }

    /**后台
     * 
     * Title: selectNo  
     * Description:  
     * @param type
     * @return   
     * @see cn.com.dbridge.jtraining.service.UserService#selectNo(java.lang.Byte)
     */
    @Override
    public String selectNo(Byte type) {
        //データベース内の最大番号を問い合せる
        String addNo = userPOMapper.selectMaxWorkNo(type);
        if (null == addNo) {
            addNo = "0000";
        }
        //結果の値を整数に変換して1を加算する
        int addNoInt = Integer.parseInt(addNo) + 1;
        //数値変数の定義
        String no = String.format("%05d", addNoInt);
        //それが教師であるかどうかを判断する
        if (type == 1) {
            //結果の値を文字型+ Tに変換する
            no = "T" + no;
        }
        //学生かどうかを判断する
        else if (type == 2) {
            //結果の値を文字型+Sに変換する
            no = "S" + no;
        } else {
            no = "";
        }
        return no;
    }

    @Override
    public UserLoginVO getUserByAccount(String no) {
        //出力オブジェクトの定義
        UserLoginVO userLoginVO = new UserLoginVO();
        //情報を見つけるためにsqlを呼び出す
        UserPO userPO = userPOMapper.selectByPrimaryKey(no);
        //マップオブジェクトの値を出力オブジェクトに割り当てます
        if (null == userPO) {
            return null;
        }
        Date birthday = userPO.getBirthday();
        Integer age = AgeUtils.getAgeByBirthday(birthday);
        BeanUtils.copyProperties(userPO, userLoginVO);
        userLoginVO.setAge(age);
        //出力オブジェクトを返す
        return userLoginVO;
    }

    /**后台
     * 
     * Title: deleteUser  
     * Description:  
     * @param no
     * @return   
     * @see cn.com.dbridge.jtraining.service.UserService#deleteUser(java.lang.String)
     */
    @Override
    public int deleteUser(String no) {
        String str = no.substring(0, 1);
        int count = 0;
        if (str.equals("S")) {
            learnRecordPOMapper.deleteByStudentId(no);
            count = userPOMapper.deleteByPrimaryKey(no);
        } else if (str.equals("T")) {
            UserPO userPO = userPOMapper.selectByPrimaryKey(no);
            if (userPO.getUseStatus() == 0) {
                count = userPOMapper.updateStatusOffByKey(no);
            } else if (userPO.getUseStatus() == 1) {
                count = userPOMapper.updateStatusOnByKey(no);
            } else {
                return count;
            }
        } else {
            return count;
        }
        /*if (trainItemPOList.isEmpty()) {
            count = userPOMapper.deleteByPrimaryKey(no);
        }*/
        //削除オペランドを返す
        return count;
    }

    /**后台
     * 
     * Title: selectAll  
     * Description:  
     * @param type
     * @return   
     * @see cn.com.dbridge.jtraining.service.UserService#selectAll(byte)
     */
    @Override
    @Transactional
    public List<UserVO> selectAll(UserDTO userDTO) {
        //出力コレクションの定義
        List<UserVO> userVOList = new ArrayList<UserVO>();
        if (null != userDTO.getOffset() && null != userDTO.getLimit()) {
            //改ページ設定
            PageHelper.startPage(userDTO.getOffset(), userDTO.getLimit());
        }
        //情報を見つけるためにsqlを呼び出す
        List<UserPO> userPOList = userPOMapper.selectAll(userDTO.getType());
        PageInfo<UserPO> pageInfo = new PageInfo<UserPO>(userPOList);
        //出力オブジェクトの定義
        UserVO userVO = null;
        //地図コレクションをトラバースする
        for (UserPO userPO : userPOList) {
            //出力オブジェクトをインスタンス化する
            userVO = new UserVO();
            if (userPO.getTrainType() != null) {
                TrainCategoryPO trainCategoryPO = trainCategoryPOMapper
                        .selectByPrimaryKey(userPO.getTrainType());
                if (null != trainCategoryPO) {
                    userVO.setTrainName(trainCategoryPO.getTypeName());
                }
            }
            //マップオブジェクトの値を出力オブジェクトに割り当てます
            BeanUtils.copyProperties(userPO, userVO);
            if (null != userPO.getBirthday()) {
                Calendar birthday = Calendar.getInstance();
                birthday.setTime(userPO.getBirthday());
                int yearBirthday = birthday.get(Calendar.YEAR);
                int monthBirthday = birthday.get(Calendar.MONTH);
                int dayBirthday = birthday.get(Calendar.DAY_OF_MONTH);
                Calendar newDate = Calendar.getInstance();
                newDate.setTime(new Date());
                int yearNewDate = newDate.get(Calendar.YEAR);
                int monthNewDate = newDate.get(Calendar.MONTH);
                int dayNewDate = newDate.get(Calendar.DAY_OF_MONTH);
                /*if (yearBirthday > yearNewDate) {
                    throw new CustomAuthException("生日错误");
                } else if (yearBirthday == yearNewDate
                        && monthBirthday > monthNewDate) {
                    throw new CustomAuthException("生日错误");
                } else if (yearBirthday == yearNewDate
                        && monthBirthday == monthNewDate
                        && dayBirthday > dayNewDate) {
                    throw new CustomAuthException("生日错误");
                } else if (yearBirthday == yearNewDate
                        && monthBirthday == monthNewDate
                        && dayBirthday == dayNewDate) {
                    userVO.setAge(0);
                }*/
                if (monthBirthday > monthNewDate) {
                    userVO.setAge(yearNewDate - yearBirthday - 1);
                } else if (monthBirthday < monthNewDate) {
                    userVO.setAge(yearNewDate - yearBirthday);
                } else {
                    if (dayBirthday > dayNewDate) {
                        userVO.setAge(yearNewDate - yearBirthday - 1);
                    } else {
                        userVO.setAge(yearNewDate - yearBirthday);
                    }
                }
            }
            userVO.setCount(pageInfo.getTotal());
            //出力オブジェクトを出力コレクションに追加する
            userVOList.add(userVO);
        }

        //出力コレクションを返す
        return userVOList;
    }
    @Override
    @Transactional
    public List<StudentEvaluateVO> selectStudentEvaluate(String studentId) {
        //インスタンス化された出力コレクション
        List<StudentEvaluateVO> studentEvaluateVOList = new ArrayList<StudentEvaluateVO>();
        //SQLクエリを呼び出す
        List<StudentEvaluatePO> studentEvaluatePOList = studentEvaluatePOMapper
                .selectByPrimaryKey(studentId);
        //出力オブジェクトの定義
        StudentEvaluateVO studentEvaluateVO = null;
        //データベースマップコレクションをトラバースする
        for (StudentEvaluatePO studentEvaluatePO : studentEvaluatePOList) {
            //出力オブジェクトをインスタンス化する
            studentEvaluateVO = new StudentEvaluateVO();
            //sqlを呼び出して、教師の名前と教師の訓練カテゴリを問い合わせます
            UserPO userPO = userPOMapper
                    .selectByPrimaryKey(studentEvaluatePO.getTeacherId());
            if (null != userPO) {
                //教師の名前を出力オブジェクトに保存する
                studentEvaluateVO.setTeacherName(userPO.getName());
                String typeName = trainCategoryPOMapper
                        .selectTypeNameByTrainType(userPO.getTrainType());
                //トレーニングカテゴリ名
                studentEvaluateVO
                        .setTrainTypeName(typeName);
                //データベースマップオブジェクトを出力オブジェクトに格納する
                BeanUtils.copyProperties(studentEvaluatePO, studentEvaluateVO);
                //出力オブジェクトを出力コレクションに追加する
                studentEvaluateVOList.add(studentEvaluateVO);
            }
        }
        //出力コレクションを返す
        return studentEvaluateVOList;
    }

    /**后台
     * 
     * Title: selectUserBynoOrName  
     * Description:  
     * @param noOrName
     * @return   
     * @author 
     * @see cn.com.dbridge.jtraining.service.UserService#selectUserBynoOrName(java.lang.String)
     */
    @Override
    public UserVO selectUserByNoOrName(String noOrName) {
        //出力オブジェクトをインスタンス化する
        UserVO userVO = new UserVO();
        //SQLクエリを呼び出す
        UserPO userPO = userPOMapper.selectUserByNoOrName(noOrName);
        if (null == userPO) {
            return null;
        }
        if (userPO.getTrainType() != null) {
            TrainCategoryPO trainCategoryPO = trainCategoryPOMapper
                    .selectByPrimaryKey(userPO.getTrainType());
            if (null != trainCategoryPO) {
                userVO.setTrainName(trainCategoryPO.getTypeName());
            }
        }
        //クエリが管理者の場合
        if (!"Admin".equals(userPO.getNo())) {
            //クエリの結果を出力オブジェクトに渡す
            BeanUtils.copyProperties(userPO, userVO);
        }
        //出力オブジェクトを返す
        return userVO;
    }

    /**后台
     * 
     * Title: updatePasswordByPrimaryKey  
     * Description:  
     * @param userDTO
     * @return   
     * @see cn.com.dbridge.jtraining.service.UserService#updatePasswordByPrimaryKey(cn.com.dbridge.jtraining.framework.dto.UserDTO)
     */
    @Override
    public int updatePasswordByPrimaryKey(
            UserUpdatePasswordDTO userUpdatePasswordDTO) {
        //データベースマッピングオブジェクト
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userUpdatePasswordDTO, userPO);
        userPO.setNo(userUpdatePasswordDTO.getNo());
        //パスワードを変更
        int count = userPOMapper.updatePasswordByNo(userPO);
        //リターンコード
        return count;
    }

	// TODO 陳軍
	@Override
	@Transactional
	public int updatePassword(PasswordChangeDTO passwordChangeDTO) {
		UserQueryPO userQueryPO = new UserQueryPO();
		BeanUtils.copyProperties(passwordChangeDTO, userQueryPO);
		String key = AesCipherUtil.enCrypto(
				passwordChangeDTO.getNo() + passwordChangeDTO.getPassword());
		userQueryPO.setPassword(key);
		UserPO userPO = userPOMapper.selectByNoPassword(userQueryPO);
		int affectNum = -1;
		if (null != userPO) {
			userPO.setPassword(AesCipherUtil.enCrypto(passwordChangeDTO.getNo()
					+ passwordChangeDTO.getNewPassword()));
			affectNum = userPOMapper.updatePasswordByNo(userPO);
		}
		return affectNum;
	}

	// TODO 陳軍
	@Override
	@Transactional
	public InformationUpdateVO updatePersonalInfor(
            HttpServletRequest request) throws Exception {
		UserPO userPO = new UserPO();
		MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
		String no = params.getParameter("no");
		String name = params.getParameter("name");
		String birthdayTemp = params.getParameter("birthday");
		// beanを設定する
		userPO.setNo(no);
		userPO.setName(name);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = !StringUtils.isEmpty(birthdayTemp)
					? formatter.parse(birthdayTemp)
					: null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userPO.setBirthday(birthday);
		// 画像のアップロード
		MultipartFile file = params.getFile("file");
		String fileName = "";
		if (null != file) {
			fileName = fileUploadController.handelFileUpload(file)
                    .getData().getUrl();
			try {
				fileName = fileUploadController.handle(file).getData().getUrl();
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomException("ファイルのアップロード失敗!");
			}
			// 新のファイルパスを設定する
			userPO.setPersonDraw(fileName);
		}
		// ユーザーの個人情報を更新する
		int updatedCount = userPOMapper.updateByNo(userPO);
		UserPO userPO2 = userPOMapper.selectByPrimaryKey(no);
		InformationUpdateVO informationUpdateVO = new InformationUpdateVO();
		informationUpdateVO.setUpdatedCount(updatedCount);
		informationUpdateVO.setUrl(fileName);
		if (null != userPO2) {
			Date birthdayR = userPO2.getBirthday();
			informationUpdateVO.setNo(userPO2.getNo());
			informationUpdateVO.setName(userPO2.getName());
			informationUpdateVO.setBirthday(birthdayR);
			if (null != birthdayR) {
				int age = AgeUtils.getAgeByBirthday(birthdayR);
				informationUpdateVO.setAge(age);
			}
		}
		return informationUpdateVO;
	}

    /**
     * 
     * @Title: selectPersonByNo 
     * @Description: 番号に基づいてユーザー情報を取得する 
     * @param phoneUserDTO クエリ条件
     * @author:郭健
     * @return ユーザー情報
     */
    @Override
    public PhoneUserVO selectPersonByNo(PhoneUserDTO phoneUserDTO) {
        //データクラスを表示する
        PhoneUserVO phoneUserVO = new PhoneUserVO();
        //番号を取得する
        String no = phoneUserDTO.getNo();
        //番号でユーザー情報を照会する
        UserPO userPO = userPOMapper.selectPersonByNo(no);
        if (null != userPO) {
            //誕生日情報を入手する
            Date birthday = userPO.getBirthday();
            //年齢を計算する
            Integer age = AgeUtils.getAgeByBirthday(birthday);
            //表示データクラスにユーザー情報をロードする
            BeanUtils.copyProperties(userPO, phoneUserVO);
            //年齢を設定
            phoneUserVO.setAge(age);
            //表示データクラスを返す
        }
        return phoneUserVO;
    }

    /**
     * 
     * @Title: selectAllStudents 
     * @Description: すべての学生情報のリストを取得する
     * @param phoneStudentQueryDTO クエリ条件
     * @author:郭健
     * @return 学生情報のリスト
     */
    @Override
    public List<PhoneStudentVO> selectAllStudents() {
        //学生情報を取得する
        List<UserPO> userPOList = userPOMapper.selectAllStudents();
        //学生情報表示クラス
        PhoneStudentVO PhoneStudnetVO = null;
        List<PhoneStudentVO> phoneStudentVOList = new ArrayList<PhoneStudentVO>();
        if (!CollectionUtils.isEmpty(userPOList)) {
            for (UserPO userPO : userPOList) {
                Date birthday = userPO.getBirthday();
                PhoneStudnetVO = new PhoneStudentVO();
                BeanUtils.copyProperties(userPO, PhoneStudnetVO);
                Integer age = AgeUtils.getAgeByBirthday(birthday);
                PhoneStudnetVO.setAge(age);
                phoneStudentVOList.add(PhoneStudnetVO);
            }
        }
        return phoneStudentVOList;
    }

    /**
     * 
     * @Title: selectAllTeachers 
     * @Description: すべての教師のリストを入手する
     * @param phoneTeacherQueryDTO クエリ条件
     * @author:郭健
     * @return 教師のリスト
     */
    @Override
    public List<PhoneTeacherVO> selectAllTeachers() {
        //教師情報表示クラス
        PhoneTeacherVO phoneTeacherVO = null;
        //結果セットを表示する
        List<PhoneTeacherVO> phoneTeacherVOList = new ArrayList<PhoneTeacherVO>();
        //トレーニングカテゴリのクエリを取得する
        List<MyUserPO> myUserPOList = userPOMapper.selectAllTeachers();
        Set<String> typeNameSet = new HashSet<String>();
        //重複する種别名を削除する
        if (!CollectionUtils.isEmpty(myUserPOList)) {
            for (MyUserPO myUserPO : myUserPOList) {
                String typeName = myUserPO.getTypeName();
                typeNameSet.add(typeName);
            }
            Iterator<String> it = typeNameSet.iterator();
            MyUserForTeacherPO myUserForTeacherPO = null;
            List<MyUserForTeacherPO> myUserForTeacherPOList = null;
            while (it.hasNext()) {
                myUserForTeacherPOList = new ArrayList<MyUserForTeacherPO>();
                phoneTeacherVO = new PhoneTeacherVO();
                String currentTypeName = it.next();
                phoneTeacherVO.setTypeName(currentTypeName);
                //トレーニングカテゴリを通じて教師の情報を入手する
                List<UserPO> teacherInfoList = userPOMapper
                        .selectPersonByTypeName(currentTypeName);
                if (!CollectionUtils.isEmpty(teacherInfoList)) {
                    for (UserPO userPO : teacherInfoList) {
                        myUserForTeacherPO = new MyUserForTeacherPO();
                        BeanUtils.copyProperties(userPO, myUserForTeacherPO);
                        myUserForTeacherPO.setIntroduction(userPO.getRemarks());
                        myUserForTeacherPOList.add(myUserForTeacherPO);
                    }
                    phoneTeacherVO
                            .setMyUserForTeacherPOList(myUserForTeacherPOList);
                    phoneTeacherVOList.add(phoneTeacherVO);
                }
            }
        }
        return phoneTeacherVOList;
    }
}

package com.djb.art.sct.service.user;

import java.util.Random;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.djb.art.sct.model.User;
import com.djb.art.sct.service.UserService;

// com.djb.art.commons.service.AccountService
public class UserServiceImpl implements UserService {

	private static Logger log =  LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static int hashIterations = 2;
	private static String algorithmName = "SHA-512";	
	
	private static String randomSalt() {
            StringBuffer result = new StringBuffer();  
            for(int i=0;i<64;i++) {  
                result.append(Integer.toHexString(new Random().nextInt(16)));  
            }  
            return result.toString().toUpperCase();  
	}

	private static void encryptPassword(User user) {
        String newPassword = new SimpleHash(
        	UserServiceImpl.algorithmName,
        	user.getPassword(),
            ByteSource.Util.bytes(user.getCredentialsSalt()),
            UserServiceImpl.hashIterations
        ).toHex();
        user.setPassword(newPassword);
    }

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserByLoginCode(String loginCode) {
		return this.userRepository.selectUserByLoginCode(loginCode);
	}

	@Override
	public Integer createUser(User user) {
		user.setSalt(UserServiceImpl.randomSalt());
		UserServiceImpl.encryptPassword(user);
		Integer ret =userRepository.insertUser(user);
		if (null == ret || 0 == ret) {
			log.error("User creating is failed!user:" + user.getLoginCode());
		}
		return ret;
	}

	@Override
	public Integer updateLoginIp(Integer userId, String ip) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer changePassword(Integer useId, String oldPwd, String newPwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer disablesUser(Integer useId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getCurrentUser() {
		return this.userRepository.selectUserByLoginCode(this.getCurrentUserLoginCode());
	}

	@Override
	public String getCurrentUserLoginCode() {
		return SecurityUtils.getSubject().getPrincipal().toString();
	}

	@Override
	public User getUserByUserId(Integer userId) {
		return this.userRepository.selectUserByUserId(userId);
	}
	
}

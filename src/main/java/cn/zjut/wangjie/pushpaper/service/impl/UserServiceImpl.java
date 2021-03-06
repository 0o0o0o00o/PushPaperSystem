package cn.zjut.wangjie.pushpaper.service.impl;


import cn.zjut.wangjie.pushpaper.mapper.UserDao;
import cn.zjut.wangjie.pushpaper.pojo.User;
import cn.zjut.wangjie.pushpaper.service.UserService;
import cn.zjut.wangjie.pushpaper.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Resource private UserDao userDao;

	@Override
	public boolean register (User user) {
		user.setPassword(MD5Util.md5(user.getPassword()));
		if(userDao.addUser(user)==1) {
			return true;
		}
		else{ return false;}
	}

	@Override
	public User login(User user) {
		user.setPassword(MD5Util.md5(user.getPassword()));
		User newUser = userDao.findUser(user);
		if(newUser!=null){
			
			return newUser;
		}
		else{ return null;}
	}

    @Override
	public boolean isEmalExist(String email) {

		if (userDao.isEmailExit(email)>0){
			return true;
		}
		return false;

	
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
}

package service.impl;

import java.util.ArrayList;

import dao.UserDao;
import dao.impl.UserDaoimpl;
import domain.Manager;
import domain.User;
import service.UserService;
import utils.MD5utils;

public class UserServiceimpl implements UserService {
	
	private UserDao userDao=new UserDaoimpl();
	@Override
	public boolean UserLogin(String userID, String userPassword) {
		User nowUser=userDao.getUser(userID);
		if(nowUser==null)return false;
		String pass=new MD5utils(userPassword+nowUser.getPasswordSalt()).get32();
		System.out.println(pass);
		if(pass.equals(nowUser.getPassword()))return true;
		return false;
	}

	@Override
	public boolean UserReg(User user) {
		if(userDao.existUser(user.getUid()))return false;
		String rawPassword=user.getPassword();
		String salt="";
		for(int i=0;i<6;i++) {
			int intVal=(int)(Math.random()*26+97);
			salt=salt+(char)intVal;
		}
		user.setPasswordSalt(salt);
		String pass=new MD5utils(rawPassword+user.getPasswordSalt()).get32();
		user.setPassword(pass);
		userDao.addUser(user);
		return true;
	}

	@Override
	public boolean ChangeInfo(User user) {
		if(!userDao.existUser(user.getUid()))return false;
		User oldInfo=userDao.getUser(user.getUid());
		user.setPassword(oldInfo.getPassword());
		user.setPasswordSalt(oldInfo.getPasswordSalt());
		userDao.delUser(user.getUid());
		userDao.addUser(user);
		return true;
	}

	@Override
	public boolean ChangePass(String uid, String oldPassWord, String newPassword) {
		if(this.UserLogin(uid, oldPassWord)!=true)return false;
		User oldInfo=userDao.getUser(uid);
		oldInfo.setPassword(newPassword);
		userDao.delUser(oldInfo.getUid());
		this.UserReg(oldInfo);
		System.out.println("change");
		return true;
	}

	@Override
	public ArrayList<User> GetAll(int start,int end) {
		return userDao.getAll(start, end);
	}

	@Override
	public boolean delUser(String userID) {
		return userDao.delUser(userID);
	}

	@Override
	public User getUser(String userID) {
		return userDao.getUser(userID);
	}
	
	@Override
	public int getTotalNum() {
		return userDao.getTotalRec();
	}
	@Override
	public boolean existUser(String userID) {
		if(userDao.existUser(userID))return true;
		return false;
	}

}

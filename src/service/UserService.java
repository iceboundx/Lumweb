package service;

import java.util.ArrayList;

import domain.User;

public interface UserService {
	public boolean UserLogin(String userID,String userPassword);
	public boolean UserReg(User user);
	public boolean ChangeInfo(User user);
	public boolean ChangePass(String uid, String oldPassWord, String newPassword);
	public ArrayList<User>GetAll(int start,int end);
	public int getTotalNum();
	public boolean delUser(String userID);
	public User getUser(String userID);
	public boolean existUser(String userID);
}

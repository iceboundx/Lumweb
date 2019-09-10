package dao;
import java.util.ArrayList;

import domain.User;
public interface UserDao {
	public User getUser(String userID);
	public ArrayList<User> getAll(int start,int end);
	public boolean existUser(String userID);
	public boolean addUser(User user);
	public boolean delUser(String userID);
	public int getTotalRec();
}

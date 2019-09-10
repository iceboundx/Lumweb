package service;

import java.util.ArrayList;

import domain.Manager;

public interface ManagerService {
	public boolean login(String uid,String password);
	public boolean addMan(Manager man);
	public ArrayList<Manager> getList(int start,int end);
	public int getTotalNum();
	public Manager getMan(String uid);
	public boolean ChangePermission(String uid,int permission);
	public boolean delMan(String uid);
	
}

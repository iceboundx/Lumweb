package dao;

import java.util.ArrayList;

import domain.Manager;

public interface ManagerDao {
	public Manager getMan(String ID);
	public ArrayList<Manager> getAll(int start,int end);
	public boolean existMan(String ID);
	public boolean addMan(Manager manager);
	public boolean delMan(String ID);
	public int getTotalRec();
	
}

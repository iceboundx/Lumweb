package service.impl;

import java.util.ArrayList;

import dao.ManagerDao;
import dao.impl.ManagerDaoimpl;
import domain.Manager;
import service.ManagerService;
import utils.MD5utils;

public class ManagerServiceimpl implements ManagerService {

	private ManagerDao managerDao=new ManagerDaoimpl();
	@Override
	public boolean login(String uid, String password) {
		Manager nowMan=managerDao.getMan(uid);
		if(nowMan==null)return false;
		String pass=new MD5utils(password+nowMan.getPasswordsalt()).get32();
		if(pass.equals(nowMan.getPassword()))return true;
		return false;
	}
	public boolean addMan(Manager man) {
		Manager nowMan=managerDao.getMan(man.getUid());
		if(nowMan!=null)return false;
		String rawPassword=man.getPassword();
		String salt="";
		for(int i=0;i<8;i++) {
			int intVal=(int)(Math.random()*26+97);
			salt=salt+(char)intVal;
		}
		man.setPasswordsalt(salt);
		String pass=new MD5utils(rawPassword+man.getPasswordsalt()).get32();
		man.setPassword(pass);
		managerDao.addMan(man);
		return true;
	}	
	public ArrayList<Manager> getList(int start,int end){
		return managerDao.getAll(start, end);
	}
	public int getTotalNum() {
		return managerDao.getTotalRec();
	}
	public boolean ChangePermission(String uid,int permission) {
		Manager man=managerDao.getMan(uid);
		if(man==null)return false;
		man.setPermission(permission);
		managerDao.delMan(uid);
		managerDao.addMan(man);
		return true;
	}
	public boolean delMan(String uid) {
		return managerDao.delMan(uid);
	}
	public Manager getMan(String uid) {
		return managerDao.getMan(uid);
	}
}

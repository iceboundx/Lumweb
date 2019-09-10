package domain;

import java.util.Date;

public class User {
	private String uid;
	private String password;
	private String passwordSalt;
	private String nickname;
	private Date birthday;
	private String address;
	public String getUid() {
		return uid;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setUid(String uid) {
		this.uid=uid;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", password=" + password + ", passwordSalt=" + passwordSalt + ", nickname="
				+ nickname + ", birthday=" + birthday + ", address=" + address + "]";
	}
}

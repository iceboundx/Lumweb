package domain;

public class Manager {
	private String uid;
	private String password;
	private String passwordsalt;
	private int permission;
	private String name;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordsalt() {
		return passwordsalt;
	}
	public void setPasswordsalt(String passwordsalt) {
		this.passwordsalt = passwordsalt;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Manager [uid=" + uid + ", password=" + password + ", passwordsalt=" + passwordsalt + ", permission="
				+ permission + ", name=" + name + "]";
	}
}

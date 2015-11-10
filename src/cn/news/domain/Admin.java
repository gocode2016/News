package cn.news.domain;

import java.sql.Timestamp;

public class Admin {
	private Integer admId;
	private String admName;
	private String admPwd;
	private Timestamp lastTime;

	public Admin() {
		super();
	}

	public Admin(Integer admId, String admName, String admPwd, Timestamp time) {
		super();
		this.admId = admId;
		this.admName = admName;
		this.admPwd = admPwd;
		this.lastTime = time;
	}

	public Integer getAdmId() {
		return admId;
	}

	public void setAdmId(Integer admId) {
		this.admId = admId;
	}

	public String getAdmName() {
		return admName;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public String getAdmPwd() {
		return admPwd;
	}

	public void setAdmPwd(String admPwd) {
		this.admPwd = admPwd;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}


}

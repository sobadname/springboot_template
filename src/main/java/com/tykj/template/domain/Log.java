package com.tykj.template.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 系统日志
 */
@Entity
@Table(name = "sys_log")
public class Log extends IdEntity {
	private long user_id;
	private String user_ip;
	private String target;
	private String info;
	private String type;
	@Column(updatable = false)
	private Date create_time;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_ip() {
		return user_ip;
	}

	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "Log [user_id=" + user_id + ", user_ip=" + user_ip + ", target=" + target + ", info=" + info + ", type="
				+ type + ", create_time=" + create_time + "]";
	}

}

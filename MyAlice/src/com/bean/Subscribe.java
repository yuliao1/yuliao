package com.bean;

import java.util.Date;


/**
 * 订阅实体类
 * @author Administrator
	String createSubscribeTable = "create table subscribe("
			+ "id number primary key,"
			+ "userId varchar2(300),"
			+ "content varchar2(300)"
			+ ")";
	/**
 */
public class Subscribe {
	private int id;
	private String userId;
	private String content;
	private Date sendOfDate;
	public Subscribe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subscribe(int id, String userId, String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
	}

	public Subscribe(String userId, String content, Date sendOfDate) {
		super();
		this.userId = userId;
		this.content = content;
		this.sendOfDate = sendOfDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendOfDate() {
		return sendOfDate;
	}
	public void setSendOfDate(Date sendOfDate) {
		this.sendOfDate = sendOfDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((sendOfDate == null) ? 0 : sendOfDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscribe other = (Subscribe) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (sendOfDate == null) {
			if (other.sendOfDate != null)
				return false;
		} else if (!sendOfDate.equals(other.sendOfDate))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Subscribe [id=" + id + ", userId=" + userId + ", content="
				+ content + ", sendOfDate=" + sendOfDate + "]";
	}

	
}

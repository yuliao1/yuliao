package com.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.net.aso.b;

/**
 * 记录实体类
 * @author Administrator
 *	String createRecordTable = "create table record("
			+ "id number primary key,"
			+ "userId varchar2(300),"
			+ "question varchar2(300),"
			+ "answer varchar2(4000),"
			+ "createDate date"
			+ ")";
 */
public class Record {
	private int id;
	private String userId;
	private String question;
	private String answer;
	private Date createDate;
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Record(int id, String userId, String question, String answer,
			Date createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.question = question;
		this.answer = answer;
		this.createDate = createDate;
	}
	public Record(String userId, String question, String answer, Date createDate) {
		super();
		this.userId = userId;
		this.question = question;
		this.answer = answer;
		this.createDate = createDate;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
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
		Record other = (Record) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id != other.id)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
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
		StringBuffer buffer =new StringBuffer("<br>");
		if(createDate!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
			String dateStr = dateFormat.format(createDate);
			buffer.append(""+dateStr+":<br>");
		}
		if(question!=null){
			buffer.append("问题："+question+"<br>");
		}
		if(answer!=null){
			buffer.append("回答："+answer);
		}
		return buffer.toString();
	}
	
	
}

package com.whv.commons.utils.json;

public class AppData {

	/*状态*/
	private int status;

	/*状态*/
	private String message;

	/*状态*/
	private String currentTime;

	/*业务数据*/
	private Object data;
	/*拦截器数据*/
	private Object collections;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getCollections() {
		return collections;
	}

	public void setCollections(Object collections) {
		this.collections = collections;
	}
}

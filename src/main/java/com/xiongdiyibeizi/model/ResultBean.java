package com.xiongdiyibeizi.model;

public class ResultBean<T> {
	private String message;//返回信息
	private boolean success;//接口是否调用成功
	private T data;//返回数据
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}

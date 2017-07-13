package com.talan.rsa.entity.other;

public class Error {
	
	private int status;
	
	private String message;

	public Error(int l, String message) {
		this.status = l;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}	
}

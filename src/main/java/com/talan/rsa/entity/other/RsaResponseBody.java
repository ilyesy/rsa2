package com.talan.rsa.entity.other;

public class RsaResponseBody {
	
	private int status;
	
	private String message;

	public RsaResponseBody(int l, String message) {
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

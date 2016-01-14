package com.mytest.twitter;

public class TwitterCallerException extends RuntimeException {

	public TwitterCallerException(String message) {
		super();
		this.message = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179011527484723791L;

	public TwitterCallerException() {
		super();

	}

	public TwitterCallerException(String message, Throwable cause) {
		super(message, cause);

	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

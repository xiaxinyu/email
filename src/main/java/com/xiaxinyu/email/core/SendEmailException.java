package com.xiaxinyu.email.core;

/**
 * Created by Summer.Xiasz on 2016/06/02.
 */
public class SendEmailException extends Exception{
	private static final long serialVersionUID = 1L;

	public SendEmailException() {
	}

	public SendEmailException(String message) {
		super(message);
	}

	public SendEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendEmailException(Throwable cause) {
		super(cause);
	}
}

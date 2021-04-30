package com.financemanager.demo.site.exception;

public class NoSuchCategoryException extends Exception {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String message;
	
	public NoSuchCategoryException(String message){
	}

	public String getMessage() {
		return message;
	}
}

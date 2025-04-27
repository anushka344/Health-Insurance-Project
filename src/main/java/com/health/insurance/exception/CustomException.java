package com.health.insurance.exception;

public class CustomException extends RuntimeException {

private static final long serialVersionUID = 1L;
    
    private String data;
    
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, String data) {
		super(message);
		this.data = data;
	}
	
	
	public String getData() {
        return data;
    }
}

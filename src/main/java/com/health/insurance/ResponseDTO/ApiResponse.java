package com.health.insurance.ResponseDTO;

public class ApiResponse<T> {
    private String errorCode;
    private String errorDescription;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String errorCode, String errorDescription, T data) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.health.insurance.Utility;

import com.health.insurance.ResponseDTO.ApiResponse;

public class ResponseUtil {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("0", "Success", data);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

}

package com.example.itemRep.dto;

public class ErrorDto {
    private String message;
    private String code;
    private String serviceName;

    public ErrorDto(String message, String code, String serviceName) {
        this.message = message;
        this.code = code;
        this.serviceName = serviceName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

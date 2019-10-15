package com.akvasoft.construction.dto.common;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private static final long serialVersionUID = -5157496519575727614L;

    T result;
    private String errorMessage;
    private String errorCode;
    private boolean success = true;

    public Response() {
    }

    public Response(T result, boolean success) {
        this.result = result;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Response{");
        sb.append("result=").append(result);
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", errorCode=").append(errorCode);
        sb.append(", success=").append(success);
        sb.append('}');
        return sb.toString();
    }
}

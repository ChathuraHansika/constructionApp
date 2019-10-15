package com.akvasoft.construction.exception;

import com.akvasoft.construction.exception.ExceptionConstant.ExceptionType;

import java.io.Serializable;

public class ConstructionRuntimeException extends RuntimeException implements Serializable , BaseException {
    private static final long serialVersionUID = -6187544526863230937L;

    private ExceptionType exceptionType;

    public ConstructionRuntimeException(ExceptionType exceptionType) {
        super(exceptionType.toString());
        this.exceptionType = exceptionType;
    }

    public ConstructionRuntimeException(ExceptionType exceptionType, String message) {
        super(exceptionType.toString());
        this.exceptionType = exceptionType;
        this.exceptionType.setErrorMessage(message);
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getErrorCode() {
        if (this.exceptionType != null) {
            return this.exceptionType.getErrorCode();
        }
        return null;
    }

    public String getErrorMessage() {
        if (this.exceptionType != null) {
            return this.exceptionType.getErrorMessage();
        }
        return null;
    }
}

package com.akvasoft.construction.aop;

import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.exception.BaseException;
import com.akvasoft.construction.exception.ExceptionConstant.ExceptionType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.SQLException;

@Aspect
@Component
public class ResponseGeneratorAspect {
    private static final Logger logger = LoggerFactory.getLogger(ResponseGenerator.class);

    @Around("@annotation(ResponseGenerator)")
    public Response handleHttpRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("START: Response Generator ");

        try {
            return (Response) joinPoint.proceed();
        } catch (Throwable t) {
            logger.error("AID:Error Occurred {}", t);
            Response response = new Response();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            ResponseGenerator responseGenerator = method.getAnnotation(ResponseGenerator.class);
            if (t instanceof BaseException) {
                logger.error("Handled Exception {}", t);
                BaseException happyJobException = (BaseException) t;
                response.setErrorCode(happyJobException.getErrorCode());
                response.setErrorMessage(happyJobException.getErrorMessage());
                response.setSuccess(false);
            } else {
                if (t instanceof SQLException) {
                    response.setErrorCode(ExceptionType.BAD_REQUEST.getErrorCode());
                    response.setErrorMessage("Can not perform operation. Incorrect data");
                    response.setSuccess(false);
                }
                if (responseGenerator.ignoreError()) {
                    logger.warn("Ignored Error {}", t);
                    response.setSuccess(true);
                    return response;
                }
                if (!responseGenerator.errorCode().equals("")) {
                    logger.info("Exception Annotation overiden error-code{}, message {}",
                            responseGenerator.errorCode(), responseGenerator.errorMessage());
                    response.setSuccess(false);
                    response.setErrorCode(responseGenerator.errorCode());
                    response.setErrorMessage(responseGenerator.errorMessage());
                    return response;
                }
                response.setSuccess(false);
                response.setErrorCode(ExceptionType.SERVER_ERROR.getErrorCode());
                response.setErrorMessage(ExceptionType.SERVER_ERROR.getErrorMessage());
            }
            return response;
        }


    }
}

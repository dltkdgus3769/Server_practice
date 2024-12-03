package com.busanit501.spring_prac.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Log4j2
@ControllerAdvice
public class CommonExceptionAdvice {
    @ResponseBody
//    @ExceptionHandler(NumberFormatException.class)
//    public String exceptNumber(NumberFormatException numberFormatException) {
//        return "numberFormatException: " + numberFormatException.getMessage();
//    }
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {
        log.error("예외가 발생할 경우 동작하는 클래스 ");
        log.error(exception.getMessage());
        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>"+exception.getMessage()+"</li>");

        Arrays.stream(exception.getStackTrace()).forEach(t->buffer.append("<li>"+t+"</li>"));

        buffer.append("</ul>");
        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "custom404page";
    }


}

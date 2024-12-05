package com.busanit501.spring_prac.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

// 웹 브라우저에서, 체크박스 체크된 값이 문자열 on으로 전달 받으면,
// 시스템에서 자동으로 , true, false 형태로 자동 변환해주는 기능을 추가함.
public class CheckboxFormatter implements Formatter<Boolean> {

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if(text == null){
            return false;
        }
        boolean checkFinished = text.equals("on");
        return checkFinished;
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }
}

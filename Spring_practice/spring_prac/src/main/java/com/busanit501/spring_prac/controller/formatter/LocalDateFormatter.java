package com.busanit501.spring_prac.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LocalDateFormatter implements Formatter<LocalDate> {
    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        LocalDate localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate;
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        String formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
        return formatter;
    }
}

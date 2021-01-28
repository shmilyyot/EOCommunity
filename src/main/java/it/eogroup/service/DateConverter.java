package it.eogroup.service;

import org.springframework.core.convert.converter.Converter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//String转LocalDate辅助类
public class DateConverter implements Converter<String, LocalDate> {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String time) {
        return LocalDate.parse(time, fmt);
    }
}


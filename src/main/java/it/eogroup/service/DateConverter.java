package it.eogroup.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//String转LocalDate辅助类
public class DateConverter implements Converter<String, LocalDate> {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String time) {
        //设置出生日期缺省值
        if(time.equals("")) time = "1970-01-01";
        return LocalDate.parse(time, fmt);
    }
}


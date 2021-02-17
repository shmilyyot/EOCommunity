package it.eogroup.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//String转LocalDate辅助类
@Repository("dateConverter")
public class DateConverter implements Converter<String, LocalDate> {

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd ");

    //String转LocalDate
    @Override
    public LocalDate convert(String time) {
        //设置出生日期缺省值
        if (time.equals("")) {
            time = "1970-01-01";
        }
        return LocalDate.parse(time, fmt);
    }

    //LocalDate转String
    public String convertString(LocalDate date) {
        String dateStr = date.format(fmt);
        return dateStr;
    }

//    public String LocalconverString(LocalDateTime localDateTime){
//        String datestr = localDateTime.format()
//    }

}


package com.learn.mentee.util.dataparser.impl;

import com.learn.mentee.util.dataparser.DateParser;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateParserImpl implements DateParser {

    @Override
    public Date parseDate(String dateToParse) {

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat DateFor = new SimpleDateFormat(pattern);

        Date parsedDate;
        try {
            parsedDate = DateFor.parse(dateToParse);
        } catch (ParseException e) {
            parsedDate = new Date();
            e.printStackTrace();
        }

        return parsedDate;
    }
}

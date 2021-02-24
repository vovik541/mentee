package com.learn.mentee.util.dateparser.impl;

import com.learn.mentee.util.dataparser.impl.DateParserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DateParserImplTest {

    @InjectMocks
    private DateParserImpl dateParser;

    private final String dateToParse = "2020-01-01";
    private final String pattern = "yyyy-MM-dd";

    @Test
    public void parseDateTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        Date expectedParsedDate;
        Date parsedDate;

        expectedParsedDate = dateFormat.parse(dateToParse);

        parsedDate = dateParser.parseDate(dateToParse);
        assertEquals(expectedParsedDate, parsedDate);
    }
}
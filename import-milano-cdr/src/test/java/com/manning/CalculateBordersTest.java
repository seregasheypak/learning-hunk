package com.manning;

import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CalculateBordersTest {


    public static final long MILLIS_2013_12_01 = 1385845200000L;
    public static final long MILLIS_2013_12_02 = 1385931600000L;

    //@Test
    public void testPathToYmd() throws ParseException {
        assertThat(CalculateBorders.pathToYMD("/some/path/2013/12/01"), equalTo("2013/12/01"));
    }

    //@Test
    public void testYmdToMillis() throws ParseException {
        assertThat(CalculateBorders.parseYMD("2013/12/01"), equalTo(MILLIS_2013_12_01));
    }

   // @Test
    public void testGetNextDay(){
        assertThat(CalculateBorders.getNextDay(MILLIS_2013_12_01), equalTo(MILLIS_2013_12_02));
    }


}
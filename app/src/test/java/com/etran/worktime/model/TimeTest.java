package com.etran.worktime.model;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Erwin on 18.12.2016.
 */

public class TimeTest {

    @Test
    public void test_Time_Instantiation_01() {
        Time time = new Time();
        assertTrue(time.toString().equals("00:00"));
    }

    @Test
    public void test_Time_Instantiation_02() {
        Time time1 = new Time(6, 50);
        assertTrue(time1.toString().equals("06:50"));

        Time time2 = new Time(10, 3);
        assertTrue(time2.toString().equals("10:03"));

        Time time3 = new Time(4, 75);
        assertTrue(time3.toString().equals("04:00"));
    }

    @Test
    public void test_Time_Instantiation_03() {
        Time time1 = new Time(7.5);
        assertTrue(time1.toString().equals("07:30"));
    }

}

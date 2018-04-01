package com.ubs.opsit.interviews;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeUtilTest {
    private String time;
    private TimeUtil util;

    @Before
    public void setup(){
        time="13:17:01";
        util= new TimeUtil();
    }

    @Test
    public void testGetSeconds() throws Exception {
        int seconds = util.getSeconds(time);
        assertThat(seconds).isEqualTo(01);
    }

    @Test
    public void testGetMinutes() throws Exception {
        int minutes = util.getMinutes(time);
        assertThat(minutes).isEqualTo(17);
    }

    @Test
    public void testGetHours() throws Exception {
        int hours = util.getHours(time);
        assertThat(hours).isEqualTo(13);
    }

}
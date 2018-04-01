package com.ubs.opsit.interviews;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LampTest {
    private Lamp redLamp;
    private Lamp yellowLamp;
    public enum DISPLAY{R,Y,O};

    @Before
    public void setup(){
        redLamp = new Lamp(Lamp.COLOR.RED);
        yellowLamp = new Lamp(Lamp.COLOR.YELLOW);
    }

    @Test
    public void testOffYellowLampDisplay() throws Exception {
        assertThat(DISPLAY.valueOf(yellowLamp.getDisplay())).isEqualTo(DISPLAY.O);
    }

    @Test
    public void testOnYellowLampDisplay() throws Exception {
        yellowLamp.setOn();
        assertThat(DISPLAY.valueOf(yellowLamp.getDisplay())).isEqualTo(DISPLAY.Y);
    }

    @Test
    public void testOffRedLampDisplay() throws Exception{
        assertThat(DISPLAY.valueOf(redLamp.getDisplay())).isEqualTo(DISPLAY.O);
    }

    @Test
    public void testOnRedLampDisplay() throws Exception{
        redLamp.setOn();
        assertThat(DISPLAY.valueOf(redLamp.getDisplay())).isEqualTo(DISPLAY.R);
    }
}
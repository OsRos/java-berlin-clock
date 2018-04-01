package com.ubs.opsit.interviews;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BerlinClockTest {
    BerlinClock clock;

    @Before
    public void setup(){
        BerlinClock.Builder builder = new BerlinClock.Builder().setup();
        clock=builder.build();
    }

    @Test
    public void convertTime() throws Exception {
        String display = "O\n" +
                "RROO\n" +
                "RRRO\n" +
                "YYROOOOOOOO\n" +
                "YYOO";
        String output = clock.convertTime("13:17:01");
        assertThat(output).isEqualTo(display);
    }

    @Test
    public void testGetSecondsLampDisplay() throws Exception {
        clock.setSecondsLamp(11);
        assertThat(clock.getDisplay(clock.getSecondsLamp())).isEqualTo("O") ;
    }

    @Test
    public void testGetUpperHoursLampsDisplay() throws Exception {
        clock.setUpperHoursLamps(23);
        assertThat(clock.getDisplay(clock.getUpperHoursLamps())).isEqualTo("RRRR") ;
    }

    @Test
    public void testGetLowerHoursLampsDisplay() throws Exception {
        clock.setLowerHoursLamps(23);
        assertThat(clock.getDisplay(clock.getLowerHoursLamps())).isEqualTo("RRRO") ;
    }

    @Test
    public void testGetUpperMinutesLampsDisplay() throws Exception {
        clock.setUpperMinutesLamps(57);
        assertThat(clock.getDisplay(clock.getUpperMinutesLamps())).isEqualTo("YYRYYRYYRYY") ;
    }

    @Test
    public void testGetLowerMinutesLampsDisplay() throws Exception {
        clock.setLowerMinutesLamps(57);
        assertThat(clock.getDisplay(clock.getLowerMinutesLamps())).isEqualTo("YYOO") ;
    }

    @Test
    public void testInvalidSecondsLamp() throws Exception{
        BerlinClock.Builder builder = new BerlinClock.Builder().setup();
        builder.secondsLamp(new Lamp(Lamp.COLOR.RED));
        Exception caughtException = null;
        try{
            builder.build();
        }catch(Exception e){
            caughtException=e;
        }
        assertThat(caughtException).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testInvalidUpperHoursLamps() throws Exception{
        BerlinClock.Builder builder = new BerlinClock.Builder().setup();
        builder.upperHoursLamps(new Lamp[]{new Lamp(Lamp.COLOR.RED)});
        Exception caughtException = null;
        try{
            builder.build();
        }catch(Exception e){
            caughtException=e;
        }
        assertThat(caughtException).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testInvalidLowerHoursLamps() throws Exception{
        BerlinClock.Builder builder = new BerlinClock.Builder().setup();
        builder.lowerHoursLamps(new Lamp[]{
                new Lamp(Lamp.COLOR.RED),new Lamp(Lamp.COLOR.RED),
                new Lamp(Lamp.COLOR.RED),new Lamp(Lamp.COLOR.YELLOW)
                });
        Exception caughtException = null;
        try{
            builder.build();
        }catch(Exception e){
            caughtException=e;
        }
        assertThat(caughtException).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testInvalidUpperMinutesLamp() throws Exception{
        BerlinClock.Builder builder = new BerlinClock.Builder().setup();
        builder.upperMinutesLamps(new Lamp[]{
                new Lamp(Lamp.COLOR.YELLOW),new Lamp(Lamp.COLOR.YELLOW),new Lamp(Lamp.COLOR.RED),
                new Lamp(Lamp.COLOR.RED),new Lamp(Lamp.COLOR.YELLOW),new Lamp(Lamp.COLOR.RED),
                new Lamp(Lamp.COLOR.YELLOW),new Lamp(Lamp.COLOR.YELLOW),new Lamp(Lamp.COLOR.RED)
        });
        Exception caughtException = null;
        try{
            builder.build();
        }catch(Exception e){
            caughtException=e;
        }
        assertThat(caughtException).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testInvalidLowerMinutesLamp() throws Exception{
        BerlinClock.Builder builder = new BerlinClock.Builder().setup();
        builder.lowerHoursLamps(new Lamp[]{
                new Lamp(Lamp.COLOR.YELLOW),new Lamp(Lamp.COLOR.YELLOW),
                new Lamp(Lamp.COLOR.YELLOW),new Lamp(Lamp.COLOR.RED)
        });

        Exception caughtException = null;
        try{
            builder.build();
        }catch(Exception e){
            caughtException=e;
        }
        assertThat(caughtException).isInstanceOf(IllegalStateException.class);
    }

}
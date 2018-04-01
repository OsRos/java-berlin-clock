package com.ubs.opsit.interviews;

public class BerlinClock implements TimeConverter {

    private Lamp secondsLamp;
    private Lamp[] upperHoursLamps;
    private Lamp[] lowerHoursLamps;
    private Lamp[] upperMinutesLamps;
    private Lamp[] lowerMinutesLamps;
    private TimeUtil timeUtil;
    private static final String DISPLAY_SEPERATOR="\n";

    private BerlinClock(Lamp secondsLamp,
                        Lamp[] upperHoursLamps, Lamp[] lowerHoursLamps,
                        Lamp[] upperMinutesLamps, Lamp[] lowerMinutesLamps,
                        TimeUtil timeUtil){
        this.secondsLamp=secondsLamp;
        this.upperHoursLamps=upperHoursLamps;
        this.lowerHoursLamps=lowerHoursLamps;
        this.upperMinutesLamps=upperMinutesLamps;
        this.lowerMinutesLamps=lowerMinutesLamps;
        this.timeUtil=timeUtil;
    }

    @Override
    public String convertTime(String aTime) {
        setAllLamps(aTime);
        return getDisplay();
    }

    void setAllLamps(String time) {
        this
        .setSecondsLamp(timeUtil.getSeconds(time))
        .setUpperHoursLamps(timeUtil.getHours(time))
        .setLowerHoursLamps(timeUtil.getHours(time))
        .setUpperMinutesLamps(timeUtil.getMinutes(time))
        .setLowerMinutesLamps(timeUtil.getMinutes(time));
    }

    String getDisplay(){
        StringBuffer display= new StringBuffer();
        display.append(getDisplay(secondsLamp)).append(DISPLAY_SEPERATOR)
                .append(getDisplay(upperHoursLamps)).append(DISPLAY_SEPERATOR)
                .append(getDisplay(lowerHoursLamps)).append(DISPLAY_SEPERATOR)
                .append(getDisplay(upperMinutesLamps)).append(DISPLAY_SEPERATOR)
                .append(getDisplay(lowerMinutesLamps));
        return display.toString();
    }

    BerlinClock setSecondsLamp(int seconds) {
        if (seconds%2==0){
            secondsLamp.setOn();
        }
        else{
            secondsLamp.setOff();
        }
        return this;
    }

    Lamp getSecondsLamp() {
        return secondsLamp;
    }

    BerlinClock setUpperHoursLamps(int hours) {
        int lampsOn = hours/5;
        setLamps(upperHoursLamps,lampsOn);
        return this;
    }

    Lamp[] getUpperHoursLamps() {
        return upperHoursLamps;
    }

    BerlinClock setLowerHoursLamps(int hours) {
        int lampsOn = hours%5;
        setLamps(lowerHoursLamps,lampsOn);
        return this;
    }

    Lamp[] getLowerHoursLamps() {
        return lowerHoursLamps;
    }

    BerlinClock setUpperMinutesLamps(int minutes) {
        int lampsOn = minutes/5;
        setLamps(upperMinutesLamps,lampsOn);
        return this;
    }

    Lamp[] getUpperMinutesLamps() {
        return upperMinutesLamps;
    }

    BerlinClock setLowerMinutesLamps(int minutes) {
        int lampsOn = minutes%5;
        setLamps(lowerMinutesLamps,lampsOn);
        return this;
    }

    Lamp[] getLowerMinutesLamps() {
        return lowerMinutesLamps;
    }

    void setLamps(Lamp[] lamps, int lampsOn) {
        for (int i=0;i<lamps.length;i++){
            if (i<lampsOn){
                lamps[i].setOn();
            }
            else{
                lamps[i].setOff();
            }
        }
    }

    String getDisplay(Lamp... lamps){
        StringBuffer display = new StringBuffer();
        for (Lamp lamp:lamps){
            display.append(lamp.getDisplay());
        }
        return display.toString();
    }

    public static class Builder{
        private Lamp secondsLamp;
        private Lamp[] upperHoursLamps;
        private Lamp[] lowerHoursLamps;
        private Lamp[] upperMinutesLamps;
        private Lamp[] lowerMinutesLamps;
        private TimeUtil timeUtil;
        private Builder setupLamps(){
            return  this
                    .secondsLamp(new Lamp(Lamp.COLOR.YELLOW))
                    .upperHoursLamps(duplicateLamps(new Lamp(Lamp.COLOR.RED),4))
                    .lowerHoursLamps(duplicateLamps(new Lamp(Lamp.COLOR.RED),4))
                    .lowerMinutesLamps(duplicateLamps(new Lamp(Lamp.COLOR.YELLOW),4))
                    .upperMinutesLamps(getUpperMinutesLamps());
        }

        private Lamp[] duplicateLamps(Lamp lamp, int n){
            Lamp[] lamps = new Lamp[n];
            for (int i=0;i<n;i++){
                lamps[i]=new Lamp(lamp.getColor());
            }
            return lamps;
        }

        private Lamp[] getUpperMinutesLamps(){
            Lamp[] upperMinutesLamps = new Lamp[11];
            for (int i=0;i < 11;i++){
                if (isQuarterLamp(i)){
                    upperMinutesLamps[i] = new Lamp(Lamp.COLOR.RED);
                }
                else{
                    upperMinutesLamps[i] = new Lamp(Lamp.COLOR.YELLOW);
                }
            }
            return upperMinutesLamps;
        }

        private boolean isQuarterLamp(int lampPosition){
            return (((lampPosition+1)%3)==0);
        }

        public Builder setup(){
            setupLamps();
            this.timeUtil= TimeUtil.getInstance();
            return this;
        }

        public Builder secondsLamp(Lamp lamp){
            this.secondsLamp=lamp;
            return this;
        }

        public Builder upperHoursLamps(Lamp[] lamps){
            this.upperHoursLamps=lamps;
            return this;
        }

        public Builder lowerHoursLamps(Lamp[] lamps){
            this.lowerHoursLamps=lamps;
            return this;
        }

        public Builder upperMinutesLamps(Lamp[] lamps){
            this.upperMinutesLamps=lamps;
            return this;
        }

        public Builder lowerMinutesLamps(Lamp[] lamps){
            this.lowerMinutesLamps=lamps;
            return this;
        }

        public Builder timeUtil(TimeUtil timeUtil){
            this.timeUtil=timeUtil;
            return this;
        }

        private void validate(BerlinClock clock){
            this
            .validateSecondsLamp(clock.getSecondsLamp())
            .validateUpperHoursLamp(clock.getUpperHoursLamps())
            .validateLowerHoursLamp(clock.getLowerHoursLamps())
            .validateUpperMinutesLamp(clock.getUpperMinutesLamps())
            .validateLowerMinutesLamp(clock.getLowerMinutesLamps());
        }

        private Builder validateSecondsLamp(Lamp lamp){
            if (!Lamp.COLOR.YELLOW.equals(lamp.getColor())){
                throw new IllegalStateException("Seconds lamp should be yellow");
            }
            return this;
        }

        private Builder validateUpperHoursLamp(Lamp[] lamps){
            if ((lamps == null)||(lamps.length != 4)){
                throw new IllegalStateException("Upper hours lamps size needs to be 4");

            }

            for (Lamp lamp : lamps) {
                if (!Lamp.COLOR.RED.equals(lamp.getColor())) {
                    throw new IllegalStateException("Upper hours lamp should be red");
                }
            }
            return this;
        }

        private Builder validateLowerHoursLamp(Lamp[] lamps){
            if ((lamps == null)||(lamps.length != 4)){
                throw new IllegalStateException("Lower hours lamps size needs to be 4");

            }

            for (Lamp lamp : lamps) {
                if (!Lamp.COLOR.RED.equals(lamp.getColor())) {
                    throw new IllegalStateException("Lower hours lamp should be red");
                }
            }
            return this;
        }

        private Builder validateUpperMinutesLamp(Lamp[] lamps){
            if ((lamps == null)||(lamps.length != 11)){
                throw new IllegalStateException("Upper minutes lamps size needs to be 11");

            }

            for (int i=0;i<11;i++) {
                if (isQuarterLamp(i)) {
                    if (!Lamp.COLOR.RED.equals(lamps[i].getColor())) {
                        throw new IllegalStateException("Upper minutes lamp should be red at position "+i);
                    }
                }
                else{
                    if (!Lamp.COLOR.YELLOW.equals(lamps[i].getColor())) {
                        throw new IllegalStateException("Upper minutes lamp should be yellow at position "+i);
                    }
                }
            }
            return this;
        }

        private Builder validateLowerMinutesLamp(Lamp[] lamps){
            if ((lamps == null)||(lamps.length != 4)){
                throw new IllegalStateException("Lower minutes lamps size needs to be 4");

            }

            for (Lamp lamp : lamps) {
                if (!Lamp.COLOR.YELLOW.equals(lamp.getColor())) {
                    throw new IllegalStateException("Lower minutes lamp should be yellow");
                }
            }
            return this;
        }

        public BerlinClock build(){
            BerlinClock clock= new BerlinClock(
                    secondsLamp,
                    upperHoursLamps,lowerHoursLamps,
                    upperMinutesLamps,lowerMinutesLamps,
                    timeUtil
            );
            validate(clock);
            return clock;
        }
    }

}

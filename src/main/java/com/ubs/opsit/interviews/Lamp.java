package com.ubs.opsit.interviews;

public class Lamp {
    public enum COLOR{
        RED("R"),YELLOW("Y");
        private String value;
        COLOR(String value){
            this.value=value;
        }
        public String getValue(){
            return value;
        }
    };
    private static final String OFF="O";

    private boolean on=false;
    private COLOR color;

    public Lamp(COLOR color){
        this.color=color;
    }

    public void setOn(){
        on=true;
    }

    public void setOff(){
        on=false;
    }

    public String getDisplay(){
        String value = on?color.getValue():OFF;
        return value;
    }

    public COLOR getColor(){
        return color;
    }
}

package com.mazeChallenge.main;

public class Gate {
    public static final String WITH_SENSOR = "$";
    public static final String WITHOUT_SENSOR = "|";
    private String gateType;


    public Gate(String gateType) {
        this.gateType = gateType;
    }


}

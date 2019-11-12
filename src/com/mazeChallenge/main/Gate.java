package com.mazeChallenge.main;

public class Gate {
    public static final String WITH_SENSOR = "$";
    public static final String WITHOUT_SENSOR = "|";
    private String gateType;
    private boolean isClosed;


    public Gate(String gateType) {
        this.gateType = gateType;
    }


    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}

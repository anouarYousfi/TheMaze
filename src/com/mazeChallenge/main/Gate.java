package com.mazeChallenge.main;

public class Gate {
    private GateType gateType;
    private boolean isClosed;

    public Gate(GateType gateType) {
        this.isClosed=false;
        this.gateType=gateType;
    }


    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public GateType getGateType() {
        return gateType;
    }
    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }
}

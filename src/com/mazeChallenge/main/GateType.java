package com.mazeChallenge.main;

import java.util.logging.Logger;

public enum GateType {

    SENSORED("$") , UNSENSORED("|");

    private String type;
     GateType(String type){
        this.type=type;
    }
}

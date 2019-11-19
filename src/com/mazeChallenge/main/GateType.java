package com.mazeChallenge.main;

public enum GateType {
    SENSORED("$") , UNSENSORED("|");
    private String type;
     GateType(String type){
        this.type=type;

    }
    public String getValue(){
         return type;
    }
}

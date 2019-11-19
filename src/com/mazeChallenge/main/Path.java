package com.mazeChallenge.main;

public class Path {
    private final Room room1;
    private final Room room2;
    private final Gate gate;

    private Path(Room room1, Room room2, Gate gate) {
        this.room1 = room1;
        this.room2 = room2;
        this.gate = gate;
    }
    public GateType getGateType() {
        return gate.getGateType();
    }
    public boolean areRoomsInPath(Room currentRoom, Room targetRoom) {
        if ((room1.equals(currentRoom) && room2.equals(targetRoom)) || (room2.equals(currentRoom) && room1.equals(targetRoom)))
            return true;
        else
            return false;
    }

    public void closeGate() {
        this.gate.setClosed(true);
    }

    public boolean isGateClosed() {
        return gate.isClosed();
    }


    public static class Builder {
        private  Room room1;
        private  Room room2;
        private  Gate gate;

        public Builder setRoom1(Room room1) {
            this.room1 = room1;
            return this;
        }

        public Builder setRoom2(Room room2) {
            this.room2 = room2;
            return this;
        }
        public Builder setGate(Gate gate) {
            this.gate = gate;
            return this;
        }
        public Path build(){

            return new Path(room1,room2,gate);
        }
    }




}

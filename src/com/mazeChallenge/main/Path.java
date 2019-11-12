package com.mazeChallenge.main;

public class Path {
    public Path(Room room1, Room room2, Gate gate) {
        this.room1 = room1;
        this.room2 = room2;
        this.gate = gate;
    }

    private Room room1;
    private Room room2;
    private Gate gate;



    public Room getRoom1() {
        return room1;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public void setRoom2(Room room2) {
        this.room2 = room2;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }


    public boolean areRoomsInPath(Room currentRoom, Room targetRoom) {
        if (room1.equals(currentRoom) && room2.equals(targetRoom) )
            return true;
        else
            return false;
    }
}

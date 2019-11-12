package com.mazeChallenge.main;

public class Room {
    private String roomName;


    public Room(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public boolean equals(Object obj) {
        return this.roomName.equals(((Room) obj).roomName);
    }
}

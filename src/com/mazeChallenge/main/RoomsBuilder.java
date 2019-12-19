package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.List;

public class RoomsBuilder {
    private static final int ROOM2_INDEX = 1;
    private static final int ROOM1_INDEX = 0;
    private String[] mazePaths;

    public RoomsBuilder withPaths(String... mazePaths) {
        this.mazePaths = mazePaths;
        return this;
    }

    public List<Room> build() {
        List<Room> rooms = new ArrayList<>();
        for (String pathComponents : mazePaths) {
            String[] roomsNames = pathComponents.split("[$|]");
            rooms.add(new Room(roomsNames[ROOM1_INDEX]));
            rooms.add(new Room(roomsNames[ROOM2_INDEX]));
        }
        return rooms;
    }
}

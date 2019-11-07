package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomBuilder {
    public List<Room> buildRooms(String[] pathNames) {

        final List<Room> rooms = new ArrayList<>();

        for (String pathName : pathNames) {
            String[] roomsNames = pathName.split("[$|]");
            if(!rooms.stream().anyMatch(room -> room.getRoomName().equals(roomsNames[0])))
            rooms.add(new Room(roomsNames[0]));
            if(!rooms.stream().anyMatch(room -> room.getRoomName().equals(roomsNames[1])))
                rooms.add(new Room(roomsNames[1]));

        }


        return rooms;
    }
}

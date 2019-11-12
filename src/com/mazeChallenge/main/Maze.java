package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Maze {

    private List<Room> existingRooms;
    private List<Path> possiblePaths;
    private Room currentRoom;
    PathBuilder pathBuilder;


    public Maze(String... pathNames) {
        pathBuilder = new PathBuilder();
        this.possiblePaths = buildPaths(pathNames);
        this.existingRooms = buildRooms(pathNames);
        currentRoom = null;


    }

    public void popIn(String startingRoomName) {
        currentRoom = existingRooms.stream().filter(r -> r.getRoomName().equals(startingRoomName)).findAny().get();
    }

    public void walkTo(String targetRoomName) throws IllegalMoveException {
        Room targetRoom = existingRooms.stream().filter(r -> r.getRoomName().equals(targetRoomName)).findAny().orElseThrow(() -> new IllegalMoveException("illegal move , room does not exist"));
        if (!existingRooms.contains(targetRoom))
            throw new IllegalMoveException("illegal move !!");

        else if (!isPossiblePath(targetRoom))
            throw new IllegalMoveException("illegal move !!");

        else
            currentRoom = targetRoom;


    }

    public List<Path> buildPaths(String[] pathNames) {
        final List<Path> paths = new ArrayList<>();

        for (String p : pathNames)
            paths.add(pathBuilder.buildPath(p));

        return paths;
    }

    public List<Room> buildRooms(String[] pathNames) {

        final List<Room> rooms = new ArrayList<>();

        for (String pathName : pathNames) {
            String[] roomsNames = pathName.split("[$|]");
            if (!rooms.stream().anyMatch(room -> room.getRoomName().equals(roomsNames[0])))
                rooms.add(new Room(roomsNames[0]));
            if (!rooms.stream().anyMatch(room -> room.getRoomName().equals(roomsNames[1])))
                rooms.add(new Room(roomsNames[1]));
        }


        return rooms;
    }

    private boolean isPossiblePath(Room targetRoom) {
        return possiblePaths.stream().anyMatch(r -> r.areRoomsInPath(currentRoom, targetRoom));

    }
}

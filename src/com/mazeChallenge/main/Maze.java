package com.mazeChallenge.main;

import java.util.List;
import java.util.Set;

public class Maze {

    private List<Room> existingRooms;
    private List<Path> possiblePaths;
    private Room currentRoom;


    public Maze(String... pathNames) {
         PathBuilder pathBuilder = new PathBuilder();
        final RoomBuilder roomBuilder = new RoomBuilder();
        this.possiblePaths = pathBuilder.buildPaths(pathNames);
        this.existingRooms = roomBuilder.buildRooms(pathNames);
        currentRoom = null;


    }

    public void popIn(String startingRoomName) {
        currentRoom = existingRooms.stream().filter(r -> r.getRoomName().equals(startingRoomName)).findAny().get();
    }

    public void walkTo(String targetRoomName) throws IllegalMoveException {
        Room targetRoom = existingRooms.stream().filter(r -> r.getRoomName().equals(targetRoomName)).findAny().orElseThrow(() -> new IllegalMoveException("illegal move , room does not exist"));
        if(!existingRooms.contains(targetRoom))
            throw new IllegalMoveException("illegal move !!");

        else if (!isPossiblePath(targetRoom))
            throw new IllegalMoveException("illegal move !!");

        else
        currentRoom = targetRoom;


    }

    private boolean isPossiblePath(Room targetRoom) {
        return possiblePaths.stream().anyMatch(r -> r.areRoomsInPath(currentRoom, targetRoom));

    }
}

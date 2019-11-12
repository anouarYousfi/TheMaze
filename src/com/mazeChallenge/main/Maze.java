package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.List;


public class Maze {

    private List<Room> existingRooms;
    private List<Path> possiblePaths;
    private Room currentRoom;
    private Room previousRoom;
    PathBuilder pathBuilder;


    public Maze(String... pathNames) {
        pathBuilder = new PathBuilder();
        this.possiblePaths = buildPaths(pathNames);
        this.existingRooms = buildRooms(pathNames);
        currentRoom = null;
        previousRoom = null;


    }

    public void popIn(String startingRoomName) {
        currentRoom = existingRooms.stream().filter(r -> r.getRoomName().equals(startingRoomName)).findAny().get();
    }

    public void walkTo(String targetRoomName) throws IllegalMoveException, ClosedDoorException {
        Room targetRoom = existingRooms.stream().filter(r -> r.getRoomName().equals(targetRoomName)).findAny().orElseThrow(() -> new IllegalMoveException("illegal move , room does not exist"));

        if (!isPathToRoomLegal(targetRoom))
            throw new IllegalMoveException("illegal move !!");

        else if (isPathToRoomClosed(targetRoom))
            throw new ClosedDoorException("closed path !!");

        else {
            previousRoom = currentRoom;
            currentRoom = targetRoom;
        }

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

    private boolean isPathToRoomLegal(Room targetRoom) {
        return possiblePaths.stream().anyMatch(r -> r.areRoomsInPath(currentRoom, targetRoom));

    }

    public void closeLastDoor() throws DoorAlreadyClosedException {
        Path pathToBeClosed = possiblePaths.stream().filter(p -> p.areRoomsInPath(previousRoom, currentRoom)).findAny().orElse(null);

        if (pathToBeClosed.isGateClosed() == true)
            throw new DoorAlreadyClosedException("this is an already closed door");
        else
            pathToBeClosed.closeGate();


    }

    private boolean isPathToRoomClosed(Room targetRoom) {

        Path path = possiblePaths.stream().filter(p -> p.areRoomsInPath(currentRoom, targetRoom)).findFirst().orElse(null);
        return path.isGateClosed();

    }


}

package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Maze {

    private Set<Room> existingRooms;
    private List<Path> existingPaths;
    private List<String> detectedPathNames;
    private Room currentRoom;
    private Room previousRoom;


    public Maze(String... pathNames) {

        this.existingPaths = MazeResolver.resolvePaths(pathNames);
        this.existingRooms = MazeResolver.resolveRooms(pathNames);
        this.detectedPathNames =new ArrayList<>();
        this.currentRoom = null;
        this.previousRoom = null;


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
            saveTraversedPath();
        }

    }

  private void saveTraversedPath() {
       if (isPathToRoomSensored()) {
            detectedPathNames.add(previousRoom.getRoomName() + currentRoom.getRoomName());

      }

    }

   private boolean isPathToRoomSensored() {
        Path path = existingPaths.stream().filter(p -> p.areRoomsInPath(previousRoom, currentRoom)).findFirst().orElse(null);
        return GateType.SENSORED.equals(path.getGateType());
    }

    private boolean isPathToRoomLegal(Room targetRoom) {
        return existingPaths.stream().anyMatch(r -> r.areRoomsInPath(currentRoom, targetRoom));

    }

    public void closeLastDoor() throws DoorAlreadyClosedException {
        Path pathToBeClosed = existingPaths.stream().filter(p -> p.areRoomsInPath(previousRoom, currentRoom)).findAny().orElse(null);

        if (pathToBeClosed.isGateClosed() == true)
            throw new DoorAlreadyClosedException("this is an already closed door");
        else
            pathToBeClosed.closeGate();


    }

    private boolean isPathToRoomClosed(Room targetRoom) {

        Path path = existingPaths.stream().filter(p -> p.areRoomsInPath(currentRoom, targetRoom)).findFirst().orElse(null);
        return path.isGateClosed();

    }


    public String readSensors() {
      return   detectedPathNames.stream().collect(Collectors.joining(";"));

    }
}

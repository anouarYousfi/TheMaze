package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.List;




public class PathsBuilder {

    private static final int ROOM2_INDEX = 1;
    private static final int ROOM1_INDEX = 0;
    private static final String SENSORED_GATE = "$";
    private static final String UNSENSORED_GATE ="|" ;
    private List<Room> mazeRooms;
    private String[] mazePaths;


    public PathsBuilder withPaths(String... mazePaths) {
        this.mazePaths = mazePaths;
        return this;
    }

    public PathsBuilder withRooms(List<Room> mazeRooms) {
        this.mazeRooms = mazeRooms;
        return this;
    }

    public List<Path> build() {
        List<Path> paths = new ArrayList<>();
        Path path =null;
        int firstRoomInPathIndex = 0;
        for (String pathComponents : mazePaths) {

            if (pathComponents.contains(UNSENSORED_GATE)) {
                 path = new Path(mazeRooms.get(firstRoomInPathIndex),mazeRooms.get(firstRoomInPathIndex+ROOM2_INDEX), new Gate(GateType.UNSENSORED));

            } else if (pathComponents.contains(SENSORED_GATE)) {
                 path = new Path(mazeRooms.get(firstRoomInPathIndex),mazeRooms.get(firstRoomInPathIndex+ROOM2_INDEX), new Gate(GateType.SENSORED));

            }
            paths.add(path);
            firstRoomInPathIndex+=2;
        }
        return paths;
    }
}

package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class MazeResolver {

    public static final int ROOM1_INDEX = 0;
    public static final int ROOM2_INDEX = 1;
    public static final String SENSORED_GATE = "$";
    public static final String UNSENSORED_GATE = "|";

    private MazeResolver() {
    }

    public static List<Path> resolvePaths(String... pathsComponents) {

        List<Path> paths = new ArrayList<>();
        Gate gate = null;

        for (String pathComponents : pathsComponents) {
            String[] roomsNames = pathComponents.split("[$|]");
            Room room1 = new Room(roomsNames[ROOM1_INDEX]);
            Room room2 = new Room(roomsNames[ROOM2_INDEX]);

            if (pathComponents.contains(SENSORED_GATE)) {
                Path path = new Path.Builder()
                        .setRoom1(room1)
                        .setRoom2(room2)
                        .setGate(new Gate())
                        .build();
                paths.add(path);
            } else if (pathComponents.contains(UNSENSORED_GATE)) {
                Path path = new Path.Builder()
                        .setRoom1(room1)
                        .setRoom2(room2)
                        .setGate(new Gate())
                        .withSensor()
                        .build();
                paths.add(path);

            }
        }
        return paths;
    }

    public static Set<Room> resolveRooms(String... pathsComponents) {
        Set<Room> rooms = new HashSet<>();
        for (String pathComponents : pathsComponents) {
            String[] roomsNames = pathComponents.split("[$|]");
            rooms.add(new Room(roomsNames[ROOM1_INDEX]));
            rooms.add(new Room(roomsNames[ROOM2_INDEX]));
        }
        return rooms;
    }
}

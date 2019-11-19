package com.mazeChallenge.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class MazeResolver {

    private MazeResolver() {
    }

    public static List<Path> resolvePaths(String... pathsComponents) {

        List<Path> paths = new ArrayList<>();
        Gate gate = null;

        for (String pathComponents : pathsComponents) {
            String[] roomsNames = pathComponents.split("[$|]");
            Room room1 = new Room(roomsNames[0]);
            Room room2 = new Room(roomsNames[1]);


            if (pathComponents.contains("$")) {
                gate = new Gate(GateType.SENSORED);
            }
            else if (pathComponents.contains("|")) {
                gate = new Gate(GateType.UNSENSORED);
            }

            Path.Builder pathBuilder = new Path.Builder();
            Path path = new Path.Builder()
                    .setRoom1(room1)
                    .setRoom2(room2)
                    .setGate(gate)
                    .build();
            paths.add(path);
        }
        return paths;
    }

    public static Set<Room> resolveRooms(String... pathsComponents) {
        Set<Room> rooms = new HashSet<>();
        for (String pathComponents : pathsComponents) {
            String[] roomsNames = pathComponents.split("[$|]");
            rooms.add(new Room(roomsNames[0]));
            rooms.add(new Room(roomsNames[1]));
        }
        return rooms;
    }
}

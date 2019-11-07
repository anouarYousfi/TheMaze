package com.mazeChallenge.main;


import java.util.ArrayList;
import java.util.List;

public class PathBuilder {

    public List<Path> buildPaths(String[] pathNames) {
        final List<Path> paths = new ArrayList<>();

        for (String p : pathNames)
            paths.add(buildPath(p));

        return paths;
    }

    public Path buildPath(String pathName) {
        Path path = null;

        String[] roomsNames = pathName.split("[$|]");
        Room room1 = new Room(roomsNames[0]);
        Room room2 = new Room(roomsNames[1]);

        if (pathName.contains("$"))
            path = new Path(room1, room2, new Gate(Gate.WITH_SENSOR));

        else if (pathName.contains("|"))
            path = new Path(room1, room2, new Gate(Gate.WITHOUT_SENSOR));

        return path;
    }
}

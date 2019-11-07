package com.mazeChallenge.test;

import com.mazeChallenge.main.IllegalMoveException;
import com.mazeChallenge.main.Maze;
import com.mazeChallenge.main.ClosedDoorException;
import org.junit.Test;

public class MazeTest {

    /**
     * each letter represent a room
     * each | represent a gate between two room
     * each $ represent a gate with a sensor on
     */
    @Test
    public void be_Walkable_Till_The_End() throws IllegalMoveException, ClosedDoorException {
        Maze mz = new Maze("A$B", "A$C", "C|E", "B$D", "B|E", "E$F", "D$F", "F|G");
        mz.popIn("A");
        mz.walkTo("B");
        mz.walkTo("E");
        mz.walkTo("F");
        mz.walkTo("G");
    }

    @Test(expected = IllegalMoveException.class)
    public void refuse_Illegal_Move() throws IllegalMoveException, ClosedDoorException {
        Maze mz = new Maze("A$B", "A$C", "B$D");
        mz.popIn("A");
        mz.walkTo("B");
        mz.walkTo("E"); // room E does not exist in the mz
    }

    @Test(expected = IllegalMoveException.class)
    public void refuse_Move_Without_Path() throws IllegalMoveException, ClosedDoorException {
        Maze mz = new Maze("A$B", "A$C", "B$D");
        mz.popIn("A");
        mz.walkTo("B");
        mz.walkTo("C"); // Can not reach C from B
    }



}
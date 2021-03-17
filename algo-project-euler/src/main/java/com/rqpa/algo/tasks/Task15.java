package com.rqpa.algo.tasks;

import java.util.LinkedList;

import com.rqpa.algo.coordinates.TwoDimensionalIntCoordinates;

/*
Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.


How many such routes are there through a 20×20 grid?
 */
public class Task15
{
    public static void main(String[] args)
    {
        System.out.println(findNumberOfRoutesFrom0_0to20_20());
    }

    public static long findNumberOfRoutesFrom0_0to20_20()
    {
        int gridWidth = 20;
        int gridHeight = 20;
        TwoDimensionalIntCoordinates startingCoordinates = new TwoDimensionalIntCoordinates(0, 0);
        long[][] startingPositionToRoutes = new long[gridHeight + 1][gridWidth + 1];
        startingPositionToRoutes[gridHeight][gridWidth] = 1;
        LinkedList<TwoDimensionalIntCoordinates> coordStack = new LinkedList<>();
        coordStack.add(startingCoordinates);

        while (!coordStack.isEmpty())
        {
            TwoDimensionalIntCoordinates position = coordStack.peekLast();
            if (startingPositionToRoutes[position.getX()][position.getY()] != 0)
            {
                coordStack.removeLast();
                continue;
            }

            if (position.getX() == gridWidth || position.getY() == gridHeight)
            {
                startingPositionToRoutes[position.getX()][position.getY()] = 1;
                coordStack.removeLast();
                continue;
            }

            TwoDimensionalIntCoordinates rightPos = new TwoDimensionalIntCoordinates(
                    position.getX() + 1,
                    position.getY()
            );
            TwoDimensionalIntCoordinates downPos = new TwoDimensionalIntCoordinates(
                    position.getX(),
                    position.getY() + 1
            );
            long rightRoutes = startingPositionToRoutes[rightPos.getX()][rightPos.getY()];
            long downRoutes = startingPositionToRoutes[downPos.getX()][downPos.getY()];
            if (rightRoutes == 0)
            {
                coordStack.addLast(rightPos);
            }
            if (downRoutes == 0)
            {
                coordStack.addLast(downPos);
            }
            if (rightRoutes != 0 && downRoutes != 0)
            {
                startingPositionToRoutes[position.getX()][position.getY()] = downRoutes + rightRoutes;
                coordStack.removeLast();
            }
        }

        return startingPositionToRoutes[startingCoordinates.getX()][startingCoordinates.getY()];
    }
}

package solvers;

import maze.FillRect;
import maze.TileTypes;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {
    /**
     * triggers the solver
     * @param mazeArray - array of the maze
     * @param entryX - x coordinate of the entrance
     * @param entryY - y coordinate of the entrance
     * @param exitX - x coordinate of the exit
     * @param exitY - y coordinate of the exit
     */
    public static void solve(int[][] mazeArray, int entryX, int entryY, int exitX, int exitY){
        Point[] path = new BFS().findPath(mazeArray, new Point(entryX, entryY), new Point(exitX, exitY));
        System.out.println("The way:");
        for (Point value : path) {
            System.out.format("(%d,%d) ", value.x, value.y);
            mazeArray[value.y][value.x] = TileTypes.FOUNDPATH.getIntValue();
        }
        FillRect.paintMeAMaze(mazeArray);
    }

    /**
     * Finds the path
     * @param mazeArray - array of the maze
     * @param position - current position
     * @param destination - destination (exit point of the maze)
     * @return array of Points that create the path
     */
    public Point[] findPath(final int[][] mazeArray, final Point position, final Point destination) {
        if (isOutOfMap(mazeArray, position.x, position.y) || isOutOfMap(mazeArray, destination.x, destination.y) || isBlocked(mazeArray, position.x, position.y) || isBlocked(mazeArray, destination.x, destination.y)) {
            return null;
        }

        Queue<Point> queue1 = new ArrayDeque<>();
        Queue<Point> queue2 = new ArrayDeque<>();
        queue1.add(position);
        mazeArray[position.y][position.x] = -1;

        for (int i = 2; !queue1.isEmpty(); i++) {
            for (Point point : queue1) {
                if (point.x == destination.x && point.y == destination.y) {
                    return arrived(mazeArray, i - 1, point);
                }
                final Queue<Point> finalQueue = queue2;
                final int finalStepCount = i;
                lookAround(mazeArray, point, (x, y) -> {
                    if (isBlocked(mazeArray, x, y)) {
                        return;
                    }
                    Point e = new Point(x, y);
                    finalQueue.add(e);
                    mazeArray[e.y][e.x] = -finalStepCount;
                });
            }
            queue1 = queue2;
            queue2 = new ArrayDeque<>();
        }

        return null;
    }

    private static boolean isOutOfMap(int[][] mazeArray, int x, int y) {
        return x < 0 || y < 0 || mazeArray.length <= y || mazeArray[0].length <= x;
    }

    private boolean isBlocked(int[][] map, int x, int y) {
        final int i = map[y][x];
        return i < 0 || i == TileTypes.WALL.getIntValue();
    }

    private Point[] arrived(int[][] map, int size, Point p) {
        final Point[] optimalPath = new Point[size];
        computeSolution(map, p.x, p.y, size, optimalPath);
        return optimalPath;
    }

    private void computeSolution(int[][] map, int x, int y, int stepCount, Point[] optimalPath) {
        if (isOutOfMap(map, x, y) || map[y][x] == TileTypes.PATH.getIntValue() || map[y][x] != -stepCount) {
            return;
        }
        final Point p = new Point(x, y);
        optimalPath[stepCount - 1] = p;
        lookAround(map, p, (x1, y1) -> computeSolution(map, x1, y1, stepCount - 1, optimalPath));
    }

    private void lookAround(int[][] map, Point p, Callback callback) {
        callback.look(map, p.x + 1, p.y);
        callback.look(map, p.x - 1, p.y);
        callback.look(map, p.x, p.y + 1);
        callback.look(map, p.x, p.y - 1);
    }

    private interface Callback {
        default void look(final int[][] map, final int x, final int y) {
            if (isOutOfMap(map, x, y)) {
                return;
            }
            onLook(x, y);
        }
        void onLook(int x, int y);
    }
}
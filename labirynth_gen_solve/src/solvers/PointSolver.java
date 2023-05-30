package solvers;

import maze.FillRect;
import maze.TileTypes;

import java.awt.*;
import java.util.ArrayList;

public class PointSolver {
    /**
     * Movement options:
     * Example: (-1,0) is move left and don't go up or down
     */
    private static final int[] row = { -1, 1, 0, 0};
    private static final int[] col = { 0, 0, 1, -1};

    /**
     *
     * @param mazeArray array containing the maze
     * @param xEntry x coordinate of the entry
     * @param yEntry y coordinate of the entry
     * Starts the solver and prints the result
     */
    public static void startSolver(int[][] mazeArray, int xEntry, int yEntry){
        ArrayList<Point> cords = new ArrayList<>();
        System.out.format("The way:\n(%d,%d) ", xEntry, yEntry);
        int[][] mazeToPaint = mazeArray;
        solve(mazeArray, new Point(xEntry, yEntry), cords);
        for(int i = cords.size() - 1; i > 0 ; --i){
            System.out.format("(%d,%d) ", cords.get(i).x, cords.get(i).y);
            mazeToPaint[cords.get(i).y][cords.get(i).x] = TileTypes.FOUNDPATH.getIntValue();
        }
        mazeToPaint[yEntry][xEntry] = TileTypes.FOUNDPATH.getIntValue();
        FillRect.paintMeAMaze(mazeToPaint);
    }

    /**
     * @param mazeArray array containing the maze
     * @param entry entry point of the maze
     * @param cords list of points that make the path
     * @return true if the path exists
     */
    public static boolean solve(int[][] mazeArray, Point entry, ArrayList<Point> cords){
        if(mazeArray[entry.y][entry.x] == TileTypes.EXIT.getIntValue()){
            cords.add(new Point(entry.x, entry.y));
            return true;
        }
        if((mazeArray[entry.y][entry.x] == TileTypes.PATH.getIntValue()) || (mazeArray[entry.y][entry.x] == TileTypes.ENTRY.getIntValue())){
            mazeArray[entry.y][entry.x] = TileTypes.VISITED.getIntValue();
            for (int i = 0; i < 4; i++){
                if(solve(mazeArray, new Point(entry.x + row[i], entry.y + col[i]), cords)){
                    cords.add(new Point(entry.x + row[i], entry.y + col[i]));
                    return true;
                }
            }
        }
        return false;
    }
}
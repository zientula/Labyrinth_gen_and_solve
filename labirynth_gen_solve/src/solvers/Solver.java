package solvers;

import maze.TileTypes;

import java.util.List;

public class Solver {
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
     * @param xCords x coordinates
     * @param yCords y coordinates
     * @return true if the path exists
     */
    public static boolean solve(int[][] mazeArray, int xEntry, int yEntry, List<Integer> xCords, List<Integer> yCords){
        if(mazeArray[yEntry][xEntry] == TileTypes.EXIT.getIntValue()){
            xCords.add(xEntry);
            yCords.add(yEntry);
            return true;
        }

        if((mazeArray[yEntry][xEntry] == TileTypes.PATH.getIntValue()) || (mazeArray[yEntry][xEntry] == TileTypes.ENTRY.getIntValue())){
            mazeArray[yEntry][xEntry] = TileTypes.VISITED.getIntValue();
            for (int i = 0; i < 4; i++){
                if(solve(mazeArray, xEntry + row[i], yEntry + col[i], xCords, yCords)){
                    xCords.add(xEntry);
                    yCords.add(yEntry);
                    return true;
                }
            }
        }
        return false;
    }
}
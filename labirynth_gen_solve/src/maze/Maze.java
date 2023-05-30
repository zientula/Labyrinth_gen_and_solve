package maze;

import solvers.BFS;
import solvers.PointSolver;
import solvers.Solver;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    /**
     * x and y sizes of the maze
     */
    private int sizeX;
    private int sizeY;

    /**
     * bias - option in Eller's
     */
    private int bias;
    /**
     * x and y coordinates of maze entry
     */
    private int entryX;
    private int entryY;
    /**
     * x and y coordinates of maze exit
     */
    private int exitX;
    private int exitY;

    /**
     * source of the maze
     */
    private String fileName;

    /**
     * x and y coordinates of the path
     */
    private List<Integer> xCords = new ArrayList<Integer>();
    private List<Integer> yCords = new ArrayList<Integer>();

    public Maze(String fileName, int sizeX, int sizeY, int entryX, int entryY, int exitX, int exitY){
        this.fileName = fileName;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.entryX = entryX;
        this.entryY = entryY;
        this.exitX = exitX;
        this.exitY = exitY;
    }

    public int getEntryX() {
        return entryX;
    }

    public void setEntryX(int entryX) {
        this.entryX = entryX;
    }

    public int getEntryY() {
        return entryY;
    }

    public void setEntryY(int entryY) {
        this.entryY = entryY;
    }

    public int getExitX() {
        return exitX;
    }

    public void setExitX(int exitX) {
        this.exitX = exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public void setExitY(int exitY) {
        this.exitY = exitY;
    }

    /**
     * Triggers the solver (and prints the result)
     * @param mazeArray array of the maze
     * @param maze maze
     */
    public void DFSSolve(int[][] mazeArray, Maze maze){
        if(Solver.solve(mazeArray, maze.getEntryX(), maze.getEntryY(), xCords, yCords)){
            System.out.println("The way: ");
            for(int i = xCords.size() - 1; i > 0; --i){
                System.out.format("(%d,%d) ", xCords.get(i), yCords.get(i));
            }
            System.out.format("(%d,%d) ", maze.getExitX(), maze.getExitY());
        }
        else{
            System.out.println("The way does not exist.");
        }
    }
    public void PointSolve(int[][] mazeArray, Maze maze){
        PointSolver.startSolver(mazeArray, maze.getEntryX(), maze.getEntryY());
    }
    public void BFSSolve(int[][] mazeArray, Maze maze){
        BFS.solve(mazeArray, maze.getEntryX(), maze.getEntryY(), maze.getExitX(), maze.getExitY());
    }
    public Maze(String fileName, int sizeX, int sizeY, int bias){
        this.fileName = fileName;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.bias = bias;
    }

    public Maze(){}
    public int getBias() {
        return bias;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }

    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public String getFileName() {
        return fileName;
    }
}
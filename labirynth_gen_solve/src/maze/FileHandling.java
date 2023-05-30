package maze;

import maze.Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandling{
    public static int[][] readMazeFile(Maze maze){
        int[][] mazeArray;
        try (BufferedReader br = new BufferedReader(new FileReader(maze.getFileName()))) {
            //MAZE FROM FILE TO ARRAY
            mazeArray = new int[maze.getSizeX()][maze.getSizeY()];
            for (int i=0; i<mazeArray.length; i++) {
                String[] line = Files.readAllLines(Paths.get(maze.getFileName())).get(i).split("");
                for (int j=0; j<line.length; j++) {
                    mazeArray[i][j] = Integer.parseInt(line[j]);
                }
            }
            return mazeArray;
        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }
}

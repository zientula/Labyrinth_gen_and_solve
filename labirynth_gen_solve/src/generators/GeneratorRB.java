package generators;

import maze.FillRect;

import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

/**
 *
 * Class responsible for generating, displaying and saving mazes generated with recursive backtracking algorythm.
 *
 */
public class GeneratorRB {
    private final int x;
    private final int y;
    private final int[][] maze;

    /**
     * Constructor
     *
     * @param x is maze's length
     * @param y is maze's width
     */

    public GeneratorRB(int x, int y) {
        this.x = x;
        this.y = y;
        maze = new int[this.x][this.y];
        //we'll start carving maze from point (0,0)
        createMaze(0, 0);
    }

    /**
     * Enum containing directions that should be tried form the current cell
     */
    private enum DIRECT {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private DIRECT opposite;

        private DIRECT(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }

        static {
            N.opposite = S;
            S.opposite = N;
            W.opposite = E;
            E.opposite = W;
        }
    }

    /**
     * Method responsible for checking if the cell we are planning to carve in is valid
     */
    private static boolean between(int val, int up) {
        return (val >= 0) && (val < up);
    }

    /**
     * Method responsible for carving paths in maze, hence generating it.
     *
     * @param kx is the abscissa value of the starting point.
     * @param ky is the ordinate value of the starting point.
     */
    private void createMaze(int kx, int ky) {
        DIRECT[] directions = DIRECT.values();
        //we shuffle directions in order to make path meander, hence maze will be more random
        Collections.shuffle(Arrays.asList(directions));
        for (DIRECT direct : directions) {
            int nx = kx + direct.dx;
            int ny = ky + direct.dy;
            //we check if the cell we are planning to carve into is valid - meaning it is in bounds of our maze and has not yet been visited
            if (between(nx, x) && between(ny, y) && (maze[nx][ny] == 0)) {
                //we carve a passage out of our current cell and recursively call createMaze method
                maze[kx][ky] |= direct.bit;
                maze[nx][ny] |= direct.opposite.bit;
                createMaze(nx, ny);
            }
        }
    }

    /**
     * Method responsible for displaying numerical maze.
     */
    public void displayNumbers() {
        //first, we display upper boundary with exit
        System.out.print("131");
        for (int i = 0; i < x - 1; i++) {
            System.out.print("11");
        }
        System.out.println("");
        //then we display first line of vertical walls
        for (int j = 0; j < x; j++) {
            System.out.print((maze[j][0] & 8) == 0 ? "10" : "00");
        }
        System.out.println("1");
        //now we display the rest of the maze without the lower boundary
        for (int i = 1; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 1) == 0 ? "11" : "10");
            }
            System.out.println("1");
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 8) == 0 ? "10" : "00");
            }
            System.out.println("1");
        }
        //finally, we display the lower boundary with entrance
        for (int i = 0; i < x - 1; i++) {
            System.out.print("11");
        }
        System.out.println("121");
    }

    /**
     * Method responsible for displaying visual maze.
     */
    public void displayVisuals() {
        //first, we display upper boundary with exit
        System.out.print("+ ^ +");
        for (int i = 0; i < x - 1; i++) {
            System.out.print("---+");
        }
        System.out.println("");
        //then we display first line of vertical walls
        for (int j = 0; j < x; j++) {
            System.out.print((maze[j][0] & 8) == 0 ? "|   " : "    ");
        }
        System.out.println("|");
        //now we display the rest of the maze without the lower boundary
        for (int i = 1; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            for (int j = 0; j < x; j++) {
                System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        //finally, we display the lower boundary with entrance
        for (int i = 0; i < x - 1; i++) {
            System.out.print("+---");
        }
        System.out.println("+ ^ +");
    }

    /**
     * Method responsible for saving visual maze to file.
     *
     * @throws IOException if there's anything wrong with saving maze to file.
     */
    public void saveVisuals() throws IOException {
        //we begin with introducing a scanner to scan the name of the file
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the name of the file you want to save your VISUAL maze in:\n(Remember don't make any spaces! Maze will be saved in a file with .txt extension, so just write the file's name without any extensions) ");
        String zapisV = sc.next();
        //we use PrntWriter to save the maze to file, the rest of the process is just the same as in display methods
        PrintWriter labvis = new PrintWriter(zapisV + ".txt");
        labvis.print("+ ^ +");
        for (int i = 0; i < x - 1; i++) {
            labvis.print("---+");
        }
        labvis.println("");
        for (int j = 0; j < x; j++) {
            labvis.print((maze[j][0] & 8) == 0 ? "|   " : "    ");
        }
        labvis.println("|");
        for (int i = 1; i < y; i++) {
            for (int j = 0; j < x; j++) {
                labvis.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            labvis.println("+");
            for (int j = 0; j < x; j++) {
                labvis.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
            }
            labvis.println("|");
        }
        for (int i = 0; i < x - 1; i++) {
            labvis.print("+---");
        }
        labvis.println("+ ^ +");
        labvis.close();
        //we close the file and show the user what's the file's name
        System.out.println("Your visual maze has been successfully saved in a file named " + zapisV + ".txt !!!");
    }

    /**
     * Method responsible for saving numerical maze to file.
     *
     * @throws IOException if there's anything wrong with saving maze to file.
     */
    public void saveNumbers() throws IOException {
        //we begin with introducing a scanner to scan the name of the file
        Scanner sb = new Scanner(System.in);
        System.out.println("Write the name of the file you want to save your NUMERICAL maze in:\n(Remember don't make any spaces! Maze will be saved in a file with .txt extension, so just write the file's name without any extensions) ");
        String zapisN = sb.next();
        //we use PrntWriter to save the maze to file, the rest of the process is just the same as in display methods
        PrintWriter testowanie = new PrintWriter(zapisN + ".txt");
        testowanie.print("131");
        for (int i = 0; i < x - 1; i++) {
            testowanie.print("11");
        }
        testowanie.println("");
        for (int j = 0; j < x; j++) {
            testowanie.print((maze[j][0] & 8) == 0 ? "10" : "00");
        }
        testowanie.println("1");
        for (int i = 1; i < y; i++) {
            for (int j = 0; j < x; j++) {
                testowanie.print((maze[j][i] & 1) == 0 ? "11" : "10");
            }
            testowanie.println("1");
            for (int j = 0; j < x; j++) {
                testowanie.print((maze[j][i] & 8) == 0 ? "10" : "00");
            }
            testowanie.println("1");
        }
        for (int i = 0; i < x - 1; i++) {
            testowanie.print("11");
        }
        testowanie.println("121");
        testowanie.close();
        //we close the file and show the user what's the file's name
        System.out.println("Your numerical maze has been successfully saved in a file named " + zapisN + ".txt !!!");
    }

    /**
     * Method responsible for creating a maze as integer two-dimensional table of numbers that are in the tile type class and showing this maze in another computer window. Works only for square mazes.
     */
    public void convert() {
        //first we initialize the table
        int[][] labo = new int[2 * x + 1][2 * y + 1];
        int sy = 2 * x + 1;
        int sx = 2 * y + 1;
        //starting with the exit
        labo[0][0] = 1;
        labo[0][1] = 3;
        labo[0][2] = 1;
        //the north boundary
        for (int i = 0; i < sy - 3; i++) {
            labo[0][i + 3] = 1;
        }
        //first line of walls
        for (int j = 0; j < y; j++) {
            if ((maze[j][0] & 8) == 0) {
                labo[1][j * 2] = 1;
            } else {
                labo[1][j * 2] = 0;
            }
            labo[1][j * 2 + 1] = 0;
        }
        labo[1][sy - 1] = 1;
        //inserting the rest of the maze into the table
        for (int i = 1; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if ((maze[j][i] & 1) == 0) {
                    labo[i * 2][j * 2] = 1;
                    labo[i * 2][j * 2 + 1] = 1;
                } else {
                    labo[i * 2][j * 2] = 1;
                    labo[i * 2][j * 2 + 1] = 0;
                }
            }
            labo[i * 2][sy - 1] = 1;
            for (int j = 0; j < y; j++) {
                if ((maze[j][i] & 8) == 0) {
                    labo[i * 2 + 1][j * 2] = 1;
                } else {
                    labo[i * 2 + 1][j * 2] = 0;
                }
                labo[i * 2 + 1][j * 2 + 1] = 0;
            }
            labo[i * 2 + 1][sy - 1] = 1;
        }
        //southern (botttom) boundary
        for (int i = 0; i < sy - 3; i++) {
            labo[sx - 1][i] = 1;
        }
        //the entrance
        labo[sx - 1][sy - 3] = 1;
        labo[sx - 1][sy - 2] = 2;
        labo[sx - 1][sy - 1] = 1;
        //at last we display this table in another window as a beautiful, visual maze
        FillRect.paintMeAMaze(labo);

    }
}

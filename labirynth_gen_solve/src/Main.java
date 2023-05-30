import generators.Generator;
import generators.GeneratorRB;
import maze.FileHandling;
import maze.Maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class containing interface of our program with generators, solvers and all of their methods.
 */
public class Main {
    /**
     * Method where the main part of the interface is.
     */
    public static void main(String[] args) {
        //PLACE TO DEFINE YOUR MAZE
        //We don't really need that
        //Maze maze = new Maze("testMaze2.txt", 12,12,1,1,8,10);
        //maze.solve(FileHandling.readMazeFile(maze), maze);
        int option = 10;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                System.out.println("\n ==========================================\n||Welcome to the Maze Madness Program!!!!||\n ==========================================\nHere you can generate different types of mazes and get solutions to the ones you failed to solve.");
                System.out.println("Choose an option: \n1. Solver \n2. Generator \n3. Show authors \n4. Exit");
                option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1 -> {
                        solvers();
                        System.out.println("\nChoose:\n0 - if you want to go back to the main menu.\n1 - if you want to exit.");
                        int option2 = Integer.parseInt(reader.readLine());
                        switch (option2) {
                            case 1:
                                System.out.println("Stopping...");
                                Thread.sleep(200);
                                return;
                            case 0:
                                break;
                        }
                    }
                    case 2 -> {
                        generators();
                        System.out.println("\nChoose:\n0 - if you want to go back to the main menu.\n1 - if you want to exit.");
                        int option2 = Integer.parseInt(reader.readLine());
                        switch (option2) {
                            case 1:
                                System.out.println("Stopping...");
                                Thread.sleep(200);
                                return;
                            case 0:
                                break;
                        }
                    }
                    case 3 -> {
                        System.out.println("Authors:\n- Mateusz Szostak\n- Emilia Wątor \n- Adam Zientek");
                        System.out.println("Choose:\n0 - if you want to go back to the main menu.\n1 - if you want to exit.");
                        int option3 = Integer.parseInt(reader.readLine());
                        switch (option3) {
                            case 1:
                                System.out.println("Stopping...");
                                Thread.sleep(200);
                                return;
                            case 0:
                                break;
                        }
                    }
                    case 4 -> {
                        System.out.println("Stopping...");
                        Thread.sleep(200);
                        return;
                    }
                }
            } catch (NumberFormatException | InterruptedException | IOException e) {
                System.out.println("This is not a number!");
            }
        } while (option != 0);
    }

    private static void solvers() throws IOException, InterruptedException {
        int option1 = 10;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                System.out.println("SOLVER\nChoose an option: \n1. Standard maze\n2. Other maze (advanced!)  \n3. Exit");
                option1 = Integer.parseInt(reader.readLine());
                switch (option1) {
                    case 1 -> {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Enter filename:");
                        String filename = reader.readLine();
                        System.out.println("Enter the x size of your maze:");
                        int xSize = sc.nextInt();
                        System.out.println("Enter the y size of your maze:");
                        int ySize = sc.nextInt();
                        Maze mazeToSolve = new Maze(filename, xSize, ySize, 1, 0, xSize - 2, ySize - 1);
                        System.out.println("Choose the solver:\n1. DFS (doesn't work with REALLY BIG mazes)\n2. BFS");
                        int solver = sc.nextInt();
                        switch (solver) {
                            case 1 -> {
                                mazeToSolve.PointSolve(FileHandling.readMazeFile(mazeToSolve), mazeToSolve);
                                return;
                            }
                            case 2 -> {
                                mazeToSolve.BFSSolve((FileHandling.readMazeFile(mazeToSolve)), mazeToSolve);
                                return;
                            }
                        }
                    }
                    case 2 -> {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Enter filename:");
                        String filename = reader.readLine();
                        System.out.println("Enter the x size of your maze:");
                        int xSize = sc.nextInt();
                        System.out.println("Enter the y size of your maze:");
                        int ySize = sc.nextInt();
                        System.out.println("Enter the x entry coordinate of your maze:");
                        int entryX = sc.nextInt();
                        System.out.println("Enter the y entry coordinate of your maze:");
                        int entryY = sc.nextInt();
                        System.out.println("Enter the x exit coordinate of your maze:");
                        int exitX = sc.nextInt();
                        System.out.println("Enter the y exit coordinate of your maze:");
                        int exitY = sc.nextInt();
                        Maze mazeToSolve = new Maze(filename, xSize, ySize, entryX, entryY, exitX, exitY);
                        System.out.println("Choose the solver:\n1. DFS (doesn't work with REALLY BIG mazes)\n2. BFS");
                        int solver = sc.nextInt();
                        switch (solver) {
                            case 1 -> {
                                mazeToSolve.PointSolve(FileHandling.readMazeFile(mazeToSolve), mazeToSolve);
                                return;
                            }
                            case 2 -> {
                                mazeToSolve.BFSSolve((FileHandling.readMazeFile(mazeToSolve)), mazeToSolve);
                                return;
                            }
                            case 3 -> {
                                System.out.println("Stopping...");
                                Thread.sleep(200);
                                return;
                            }
                        }
                    }
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("This is not a number!");
            }
        } while (option1 != 0);
    }

    /**
     * Method containing interface for both Recursive backtracking and Eller's generators. Created to make main method more transparent and easier to analyze.
     */
    private static void generators() {
        int optionG = 10;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                System.out.println("GENERATOR");
                System.out.println("Choose the type of a generator:\n1 - Recursive backtracking generator.\n2 - Eller's generator.\n3 - View info about generators.");
                optionG = Integer.parseInt(reader.readLine());
                switch (optionG) {
                    case 1 -> {
                        System.out.println("Choose the generation type:\n1 - Difficulty levels.\n2 - Your own maze");
                        int optionGD = Integer.parseInt(reader.readLine());
                        switch (optionGD) {
                            case 1 -> {
                                System.out.println("Choose your level:\n1 - Easy\n2 - Medium \n3 - Hard \n4 - REALLY HARD (HAHAHA GOOD LUCK SUCKER!)");
                                optionGD = Integer.parseInt(reader.readLine());
                                if(Integer.parseInt(String.valueOf(optionGD)) == 1){
                                    GeneratorRB labyrinth = new GeneratorRB(20, 20);
                                    labyrinth.displayVisuals();
                                    labyrinth.convert();
                                }
                                else if(Integer.parseInt(String.valueOf(optionGD)) == 2){
                                    GeneratorRB labyrinth = new GeneratorRB(40, 40);
                                    labyrinth.displayVisuals();
                                    labyrinth.convert();
                                }
                                else if(Integer.parseInt(String.valueOf(optionGD)) == 3){
                                    GeneratorRB labyrinth = new GeneratorRB(60, 60);
                                    labyrinth.displayVisuals();
                                    labyrinth.convert();
                                }
                                else if(Integer.parseInt(String.valueOf(optionGD)) == 4){
                                    GeneratorRB labyrinth = new GeneratorRB(100, 100);
                                    labyrinth.displayVisuals();
                                    labyrinth.convert();
                                }
                                return;
                            }
                            case 2 -> {
                                try {
                                    System.out.println("RECURSIVE BACKTRACKING GENERATOR");
                                    Scanner sc = new Scanner(System.in);
                                    System.out.println("Give length of your maze:");
                                    int x = sc.nextInt();
                                    if (x <= 0) {
                                        System.out.println("You can't give length lower than 1!\n");
                                        break;
                                    }
                                    System.out.println("Give width of your maze:");
                                    int y = sc.nextInt();
                                    if (y <= 0) {
                                        System.out.println("You can't give width lower than 1!\n");
                                        break;
                                    }
                                    GeneratorRB labyrinth = new GeneratorRB(x, y);
                                    System.out.println("RECURSIVE BACKTRACKING GENERATOR");
                                    System.out.println("Choose what do you wanna do with your maze:\n1 - display generated maze \n2 - save generated maze to file \n3 - both");
                                    int optionRB = Integer.parseInt(reader.readLine());
                                    switch (optionRB) {
                                        case 1:
                                            System.out.println("RECURSIVE BACKTRACKING GENERATOR");
                                            System.out.println("Choose:\n1 - if you want to display visual maze\n2 - if you want to display extra visual maze in another window (Squares only!!!!)\n3 - if you want to display maze built out of numbers \n4 - if you want to display normal visual and numerical");
                                            int optionRB1 = Integer.parseInt(reader.readLine());
                                            switch (optionRB1) {
                                                case 1 -> {
                                                    labyrinth.displayVisuals();
                                                    return;
                                                }
                                                case 2 -> {
                                                    try {
                                                        Scanner sw = new Scanner(System.in);
                                                        System.out.println("Give the size of the maze's edge:");

                                                        int z = sw.nextInt();
                                                        if (z <= 0) {
                                                            System.out.println("The given number cannot be lower than 1 !!!\n");
                                                            return;
                                                        }
                                                        GeneratorRB mazey = new GeneratorRB(z, z);
                                                        mazey.convert();
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("You have to write a number!!!\n");
                                                    }
                                                    return;
                                                }
                                                case 3 -> {
                                                    labyrinth.displayNumbers();
                                                    return;
                                                }
                                                case 4 -> {
                                                    System.out.println("Visual:\n");
                                                    labyrinth.displayVisuals();
                                                    System.out.println("\nNumerical:\n");
                                                    labyrinth.displayNumbers();
                                                    return;
                                                }
                                            }

                                        case 2:
                                            System.out.println("RECURSIVE BACKTRACKING GENERATOR");
                                            System.out.println("Choose:\n1 - if you want to save visual maze to file\n2 - if you want to save maze built out of numbers to file\n3 - if you want to save both");
                                            int optionRB2 = Integer.parseInt(reader.readLine());
                                            switch (optionRB2) {
                                                case 1 -> {
                                                    labyrinth.saveVisuals();
                                                    System.out.println("\n");
                                                    return;
                                                }
                                                case 2 -> {
                                                    labyrinth.saveNumbers();
                                                    System.out.println("\n");
                                                    return;
                                                }
                                                case 3 -> {
                                                    labyrinth.saveVisuals();
                                                    System.out.println("\n\n");
                                                    labyrinth.saveNumbers();
                                                    System.out.println("\n");
                                                    return;
                                                }
                                            }
                                        case 3:
                                            System.out.println("RECURSIVE BACKTRACKING GENERATOR");
                                            System.out.println("Choose:\n1 - if you want to display & save visual maze to file\n2 - if you want to display & save maze built out of numbers to file\n3 - if you want to display & save both visual and numerical mazes\n4 - if you want to display extra visual maze in another computer window  and save its numerical copy to file (Squares only!!!!!)");
                                            int optionRB3 = Integer.parseInt(reader.readLine());
                                            switch (optionRB3) {
                                                case 1 -> {
                                                    labyrinth.displayVisuals();
                                                    System.out.println("\n");
                                                    labyrinth.saveVisuals();
                                                    System.out.println("\n");
                                                    return;
                                                }
                                                case 2 -> {
                                                    labyrinth.displayNumbers();
                                                    System.out.println("\n");
                                                    labyrinth.saveNumbers();
                                                    System.out.println("\n");
                                                    return;
                                                }
                                                case 3 -> {
                                                    System.out.println("VISUAL MAZE:\n");
                                                    labyrinth.displayVisuals();
                                                    System.out.println("\n");
                                                    labyrinth.saveVisuals();
                                                    System.out.println("\nNUMERICAL MAZE:\n");
                                                    labyrinth.displayNumbers();
                                                    System.out.println("\n");
                                                    labyrinth.saveNumbers();
                                                    System.out.println("\n");
                                                    return;
                                                }
                                                case 4 -> {
                                                    try {
                                                        Scanner sw = new Scanner(System.in);
                                                        System.out.println("Give the size of the maze's edge:");

                                                        int z = sw.nextInt();
                                                        if (z <= 0) {
                                                            System.out.println("The given number cannot be lower than 1 !!!\n");
                                                            return;
                                                        }
                                                        GeneratorRB mazey = new GeneratorRB(z, z);
                                                        mazey.convert();
                                                        mazey.saveNumbers();
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("You have to write a number!!!\n");
                                                    }
                                                    return;
                                                }
                                            }
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("That's not a number!\n");
                                }
                            }
                        }

                    }
                    case 2 -> {
                        Scanner scanner = new Scanner(System.in);

                        Generator g = new Generator();
                        Maze m = new Maze();
                        //todo: exceptions for size & no letters
                        System.out.print("Enter size: ");
                        m.setSizeX(scanner.nextInt());

                        System.out.println("Choose bias: ");
                        System.out.println("1 - no bias");
                        System.out.println("2 - horizontal");
                        System.out.println("3 - vertical ");
                        System.out.println("4 - ultra horizontal");
                        System.out.println("5 - ultra vertical");

                        boolean check;
                        do {
                            try {
                                m.setBias(scanner.nextInt());
                                if (m.getBias() < 1 || m.getBias() > 5) {
                                    throw new InvalidCharacter();
                                }
                                check = true;
                                g.driver(m.getSizeX(), m.getBias());
                            } catch (InvalidCharacter ic) {
                                check = false;
                                System.out.println("You cannot choose this value.");
                            }
                        } while (!check);
                        System.out.println("Choose what do you wanna do with your maze: ");
                        System.out.println("1 - display maze");
                        //todo: System.out.println("2 - display visual maze");
                        System.out.println("2 - save generated maze to file");
                        System.out.println("3 - both");
                        int choice = Integer.parseInt(reader.readLine());
                        switch (choice) {
                            case 1 -> {
                                g.display(m.getSizeX());
                                return;
                            }
                            case 2 -> {
                                g.saveToFile(m.getSizeX());
                                return;
                            }
                            case 3 -> {
                                g.display(m.getSizeX());
                                g.saveToFile(m.getSizeX());
                                return;
                            }
                            default -> {
                                System.out.println("There is no such an option.");
                                return;
                            }
                        }

                    }
                    case 3 -> {
                        System.out.println("Recursive backtracking generator helps you create mazes in both squares and rectangles.\nEller's generator creates only squares. However, you can decide if your maze will contain more vertical or horizontal walls\n");
                    }
                }

            } catch (NumberFormatException | IOException e) {
                System.out.println("This is not a number!");
            }
        } while (optionG != 0);
    }

    static public void niceMaze() {

    }

    static class InvalidCharacter extends IOException {
        public InvalidCharacter() {
        }
    }
}
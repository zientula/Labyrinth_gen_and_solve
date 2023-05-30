package generators;
/**
 *   class to implement the algorithm
 *   this generator gives only square labyrinths but bias may be chosen
 *   http://www.neocomputer.org/projects/eller.html
 */
import maze.FillRect;
import maze.TileTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Generator {
    /**
     * creating a two-dimensional array for a maze
     */
     static int[][] maze;
     static Random random = new Random();

     public void driver(int sizeX, int bias) throws IOException {
        maze = new int[2 * sizeX + 1][2 * sizeX + 1];
        Cell[] currentRow =new Cell[sizeX];

        for(int i=0; i < sizeX; i++) {
            currentRow[i] = new Cell(0, i);
        }
        //creating an array without upper and left boundary and filling it up with zeros
        for(int i=2; i <= 2*sizeX; i++){
            for(int j=2; j <= 2*sizeX; j++)
                maze[i][j]=0;
        }

        Generator gen = new Generator();
        for(int i=0; i < sizeX; i++){
            currentRow = gen.makeSet(currentRow);
            currentRow = gen.makeRightWalls(currentRow, bias);
            currentRow = gen.makeBottomWall(currentRow, bias);
            if(i == sizeX-1)
                currentRow = gen.lastRow(currentRow); //and then output the final row:
            gen.outputCurrentRow(currentRow,i);
            if(i != sizeX-1)
                currentRow = gen.generateNextRow(currentRow);
        }
        //Creating boundaries (especially upper and left boundary)
        for(int i=0;i<=2*sizeX;i++){
            maze[i][0]//up
                    =maze[i][2*sizeX]=1;//down
        }
        for(int i =0; i < 2*sizeX; i++){
            maze[0][i]//left
                    =maze[2*sizeX][i]=1;//right
        }
        for(int i=2;i<=2*sizeX;i+=2)
            for(int j=2;j<=2*sizeX;j+=2)
                maze[i][j]=1;
        //entry
        maze[0][1]=2;
        //exit
        maze[2*sizeX][2*sizeX-1]=3;

     }

    /**
     * method to dislay the labyrinth in numbers
     * @param size
     */
     public void display(int size){
         /*for(int i=0;i<2*size+1;i++){
             System.out.println();
             for(int j=0;j<2*size+1;j++)
                 System.out.print(maze[i][j]);
         }*/
         FillRect.paintMeAMaze(maze);
         System.out.println();
     }

    /**
     * saves the labyrinth to the file
     * @param size
     * @throws FileNotFoundException
     */
    public void saveToFile(int size) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the name of the file you want to save " +
                "your VISUAL maze in:\n(Remember don't make any spaces! " +
                "Maze will be saved in a file with .txt extension, " +
                "so just write the file's name without any extensions) ");
        String save = sc.next();
        PrintWriter toFile = new PrintWriter(save + ".txt");
        for(int i=0;i<2*size+1;i++){
            for(int j=0;j<2*size+1;j++){
                toFile.print(maze[i][j]);
            }
            toFile.println();
        }
        System.out.println();
        FillRect.paintMeAMaze(maze);
        toFile.close();
    }
    /**
     * this method groups ungrouped cells to form new sets
     */
    private Cell[] makeSet(Cell[] currentRow) {
        int index = 0;
        while (index < currentRow.length){
            //
            Cell cell = currentRow[index];
            index++;
            //if a cell does not belong to any set, create a new set
            if(cell.getSet() == null) {
                List<Cell> list = new ArrayList<>();
                list.add(cell);
                cell.setSet(list);
            }
        }
        return currentRow;

    }
    /**
     * this method randomly decides to add a right wall or not
     * bias as an option
     */
    private Cell[] makeRightWalls(Cell[] currentRow, int bias) {
        if (bias == 1 ) {
            for (int i = 1; i < currentRow.length; i++) {
                if (isContainedInList(currentRow[i - 1].getSet(), currentRow[i])) { //members of the same set: always add a wall between them to prevent loops
                    currentRow[i - 1].setRight(true);
                    continue;
                }
                if (random.nextBoolean())
                    //if drawn 1(true) add a right wall
                    currentRow[i - 1].setRight(true);
                else
                    currentRow = merge(currentRow, i); //if not divided by wall, merge them together
            }
        }
        if (bias == 2 ) {
            for (int i = 1; i < currentRow.length; i++) {
                if (isContainedInList(currentRow[i - 1].getSet(), currentRow[i])) {
                    currentRow[i - 1].setRight(true);
                    continue;
                }
                if ((random.nextInt(100 - 1) + 1) > 75)
                    currentRow[i - 1].setRight(true);
                else
                    currentRow = merge(currentRow, i);
            }
        }
        if (bias == 3 ) {
            for (int i = 1; i < currentRow.length; i++) {
                if (isContainedInList(currentRow[i - 1].getSet(), currentRow[i])) {
                    currentRow[i - 1].setRight(true);
                    continue;
                }
                if ((random.nextInt(100 - 1) + 1) < 75)
                    currentRow[i - 1].setRight(true);
                else
                    currentRow = merge(currentRow, i);
            }
        }
        if (bias == 4 ) {
            for (int i = 1; i < currentRow.length; i++) {
                if (isContainedInList(currentRow[i - 1].getSet(), currentRow[i])) {
                    currentRow[i - 1].setRight(true);
                    continue;
                }
                if ((random.nextInt(100 - 1) + 1) > 90)
                    currentRow[i - 1].setRight(true);
                else
                    currentRow = merge(currentRow, i);
            }
        }
        if (bias == 5 ) {
            for (int i = 1; i < currentRow.length; i++) {
                if (isContainedInList(currentRow[i - 1].getSet(), currentRow[i])) {
                    currentRow[i - 1].setRight(true);
                    continue;
                }
                if ((random.nextInt(100 - 1) + 1) < 90)
                    currentRow[i - 1].setRight(true);
                else
                    currentRow = merge(currentRow, i);
            }
        }

        return currentRow;
    }
    /**
     *  this method checks if two cells belong to the same set (in here for horizontal check)
     */
    private boolean isContainedInList(List<Cell> set, Cell cell) {
        for(Cell i : set) {
            if(i==cell)
                return true;
        }
        return false;
    }
    /**
     * merging two cells into one set
     */
    private Cell[] merge(Cell[] currentRow, int i) {
        List<Cell> currentList = currentRow[i-1].getSet();
        List<Cell> nextList = currentRow[i].getSet();
        for(Cell j : nextList) {
            currentList.add(j); //add to the current list elements from the next list to create one set (from two different)
            j.setSet(currentList);
        }
        return currentRow;
    }
    /**
     * creating bottom walls (in a random way)
     * bias as an option
     */
    private Cell[] makeBottomWall(Cell[] currentRow, int bias) {
        if (bias == 1) {
            for (int i = 0; i < currentRow.length; i++) {
                for (Cell x : currentRow[i].getSet()) x.setDown(true);
                //do until in the same set is at least one cell without a bottom wall
                while (isNoBottomWallInSet(currentRow[i].getSet())) {
                    do {
                        //take a set and a random cell from this set and assign it no bottom wall
                        currentRow[i].getSet().get(random.nextInt(currentRow[i].getSet().size())).setDown(false);
                    } while (random.nextBoolean());
                }
            }
        }
        if (bias == 2) {
            for (int i = 0; i < currentRow.length; i++) {
                for (Cell x : currentRow[i].getSet()) x.setDown(true);
                while (isNoBottomWallInSet(currentRow[i].getSet())) {
                    do {
                        currentRow[i].getSet().get(random.nextInt(currentRow[i].getSet().size())).setDown(false);
                    } while ((random.nextInt(100 - 1) + 1) < 75);
                }
            }
        }
        if (bias == 3) {
            for (int i = 0; i < currentRow.length; i++) {
                for (Cell x : currentRow[i].getSet()) x.setDown(true);
                while (isNoBottomWallInSet(currentRow[i].getSet())) {
                    do {
                        currentRow[i].getSet().get(random.nextInt(currentRow[i].getSet().size())).setDown(false);
                    } while ((random.nextInt(100 - 1) + 1) > 75);
                }
            }
        }
        if (bias == 4) {
            for (int i = 0; i < currentRow.length; i++) {
                for (Cell x : currentRow[i].getSet()) x.setDown(true);
                while (isNoBottomWallInSet(currentRow[i].getSet())) {
                    do {
                        currentRow[i].getSet().get(random.nextInt(currentRow[i].getSet().size())).setDown(false);
                    } while ((random.nextInt(100 - 1) + 1) < 90);
                }
            }
        }
        if (bias == 5) {
            for (int i = 0; i < currentRow.length; i++) {
                for (Cell x : currentRow[i].getSet()) x.setDown(true);
                while (isNoBottomWallInSet(currentRow[i].getSet())) {
                    do {
                        currentRow[i].getSet().get(random.nextInt(currentRow[i].getSet().size())).setDown(false);
                    } while ((random.nextInt(100 - 1) + 1) > 90);
                }
            }
        }
        return currentRow;
    }
    /**
     * there must be at least one no-bottom wall in set: this method checks if it exists
     */
    private boolean isNoBottomWallInSet(List<Cell> set){
        boolean result = true;
        for(Cell x: set)
            //compares with the previous cell from the same set
            result = result && x.isDown();
        return result;
    }
    /**
     * this method terminates the process of creating a row
     */
    private void outputCurrentRow(Cell[] currentRow, int rowPos){
        rowPos = 2*rowPos + 1;//
        for(int i = 0; i < currentRow.length; i++){
            if(currentRow[i].isRight()) //if the right wall exists
                maze[rowPos][2*i+2]=1;

            if(currentRow[i].isDown())  //if the down wall exists
                maze[rowPos+1][2*i+1]=1;
        }
    }

    /**
     * method for generating a new row
     */
    private Cell[] generateNextRow(Cell[] previousRow){
        for(int i = 0; i < previousRow.length; i++) {
            previousRow[i].setRight(false);//removing all right walls
            previousRow[i].setX(previousRow[i].getX() + 1);
            //removing cells with a bottom-wall from their set
            if(previousRow[i].isDown()) {
                previousRow[i].getSet().remove(findPos(previousRow[i].getSet(), previousRow[i]));
                previousRow[i].setSet(null);
                previousRow[i].setDown(false);
            }
        }
        return previousRow;
    }
    /**
     *  this method checks if cells are members of different sets (in here for vertical check)
     */
    private int findPos(List<Cell> set, Cell x){
        Cell[] tmpArray = new Cell[set.size()]; //new array of the set's size
        tmpArray = set.toArray(tmpArray); //creating an array from a set list
        for(int i=0; i < tmpArray.length; i++)
            if(tmpArray[i]==x)
                return i;
        //
        return -1;

    }
    /**
     * the lastRow method makes all the cells connected so that there always is a path
     */
    private Cell[] lastRow(Cell[] row) {
        for(int i = 1; i < row.length; i++) {
            //If the current cell and the cell to the right are members of a different set:
            if(findPos(row[i-1].getSet(),row[i]) == -1) {
                //Remove the right wall
                row[i-1].setRight(false);
                //Union the sets to which the current cell and cell to the right are members.
                row=merge(row,i);
            }
        }
        return row;
    }
}

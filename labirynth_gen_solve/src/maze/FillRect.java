package maze;

import maze.TileTypes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class FillRect {
    /**
     * Class and method creating the grid
     */
    public static class Grid extends JPanel {
        //notes:
        //10 works well for around 100x100
        //5 works well for around 200x200
        private int multiplier;
        private final List<Point> fillCells;
        private final List<Point> fillEntrEx;
        private final List<Point> fillPath;

        public Grid() {
            fillCells = new ArrayList<>();
            fillEntrEx = new ArrayList<>();
            fillPath = new ArrayList<>();
            multiplier = 10;
        }

        /**
         * Paints the maze
         * @param g - graphics
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Point fillCell : fillCells) {
                int cellX = multiplier + (fillCell.x * multiplier);
                int cellY = multiplier + (fillCell.y * multiplier);
                g.setColor(Color.BLACK);
                g.fillRect(cellX, cellY, multiplier, multiplier);
            }
            for (Point fillCell : fillEntrEx) {
                int cellX = multiplier + (fillCell.x * multiplier);
                int cellY = multiplier + (fillCell.y * multiplier);
                g.setColor(Color.RED);
                g.fillRect(cellX, cellY, multiplier, multiplier);
            }
            for (Point fillCell : fillPath) {
                int cellX = multiplier + (fillCell.x * multiplier);
                int cellY = multiplier + (fillCell.y * multiplier);
                g.setColor(Color.BLUE);
                g.fillRect(cellX, cellY, multiplier, multiplier);
            }
        }

        /**
         * Triggers filling wall cells
         * @param x - x coordinate of the cell
         * @param y - y coordinate of the cell
         */
        public void fillCell(int x, int y) {
            fillCells.add(new Point(x, y));
            repaint();
        }
        /**
         * Triggers filling Entrance/Exit cells
         * @param x - x coordinate of the cell
         * @param y - y coordinate of the cell
         */
        public void fillEntrExCell(int x, int y){
            fillEntrEx.add(new Point(x,y));
            repaint();
        }
        /**
         * Triggers filling path cells
         * @param x - x coordinate of the cell
         * @param y - y coordinate of the cell
         */
        public void fillPath(int x, int y){
            fillPath.add(new Point(x,y));
            repaint();
        }
    }

    /**
     * Starts painting the maze, creates JFrame window
     * @param mazeArray - maze to paint
     */
    public static void paintMeAMaze(int[][] mazeArray){
        EventQueue.invokeLater(() -> {
            Grid grid = new Grid();
            JFrame window = new JFrame();
            window.setSize(800, 800);
            //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.add(grid);
            window.setVisible(true);

            if(mazeArray.length <= 100){
                grid.multiplier = 10;
            }
            else if(mazeArray.length <= 202){
                grid.multiplier = 5;
            }
            else if(mazeArray.length <= 252){
                grid.multiplier = 4;
            }
            else if(mazeArray.length <= 302){
                grid.multiplier = 3;
            }
            else if(mazeArray.length <= 402){
                grid.multiplier = 2;
            }
            else{
                grid.multiplier = 1;
            }
            for (int i = 0; i < mazeArray.length; i++) {
                for (int j = 0; j < mazeArray.length; j++) {
                    if (mazeArray[i][j] == TileTypes.WALL.getIntValue()) {
                        grid.fillCell(j, i);
                    } else if (mazeArray[i][j] == TileTypes.ENTRY.getIntValue() || mazeArray[i][j] == TileTypes.EXIT.getIntValue()) {
                        grid.fillEntrExCell(j, i);
                    } else if (mazeArray[i][j] == TileTypes.FOUNDPATH.getIntValue()){
                        grid.fillPath(j, i);
                    }
                }
            }
        });
    }
}
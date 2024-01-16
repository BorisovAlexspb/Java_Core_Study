package edu.project2;

import edu.project2.DFS.DeepFirstSearch;
import edu.project2.Kruskal.Edge;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings({"RegexpSinglelineJava", "ReturnCount"})
public class Maze {
    // Define class variables
    public static final int N = 1;
    public static final int S = 2;
    public static final int E = 4;
    public static final int W = 8;

    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;
    public static final int NUMBER_TO_MAKE_NEW_COORDINATE = 20;

    protected Random random = null;
    protected Scanner sc;
    protected List<Edge> adjlists = new ArrayList<>();
    public int w = 0;
    public int h = 0;
    protected int[][] grid = null;

    // Define class methods
    public static int dx(int direction) {
        switch (direction) {
            case Maze.E:
                return +1;
            case Maze.W:
                return -1;
            case Maze.N:
            case Maze.S:
                return 0;
            default:
        }
        // error condition, but should never reach here
        return -1;
    }

    public static int dy(int direction) {
        switch (direction) {
            case Maze.E:
            case Maze.W:
                return 0;
            case Maze.N:
                return -1;
            case Maze.S:
                return 1;
            default:
        }
        // error condition, but should never reach here
        return -1;
    }

    public static int opposite(int direction) {
        switch (direction) {
            case Maze.E:
                return Maze.W;
            case Maze.W:
                return Maze.E;
            case Maze.N:
                return Maze.S;
            case Maze.S:
                return Maze.N;
            default:
        }
        // error condition, but should never reach here
        return -1;
    }

    public Maze() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Maze(int w, int h) {
        initialize(w, h);
        random = new Random();
        sc = new Scanner(System.in);
        //searchGen = new SearchGenerator();
    }

    private void initialize(int w, int h) {
        this.w = w;
        this.h = h;
        grid = new int[h][w];
        for (int j = 0; j < h; ++j) {
            for (int i = 0; i < w; ++i) {
                grid[j][i] = 0;
            }
        }
    }

    public void draw() {
        // draw the "top" line
        System.out.print(" ");
        for (int i = 0; i < (w * 2 - 1); ++i) {
            System.out.print("_");
        }
        System.out.println("");

        // draw each row
        for (int j = 0; j < h; ++j) {
            System.out.print("|");
            for (int i = 0; i < w; ++i) {
                // render "bottom" using the "S" switch
                System.out.print((grid[j][i] & Maze.S) != 0 ? " " : "_");

                // render "side" using "E" switch
                if ((grid[j][i] & Maze.E) != 0) {
                    System.out.print(((grid[j][i] | grid[j][i + 1]) & Maze.S) != 0 ? " " : "_");
                } else {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }

        // output maze metadata
        outputMetadata();
    }

    protected void outputMetadata() {
        String meta = " " + w + " " + h;
        System.out.println(meta);
    }

    public void findTheWay(Edge start, Edge end) {
        List<Edge> colorWay = new DeepFirstSearch(w, h, adjlists).getShortWay(start, end);
        drawTheWay(colorWay);
    }

    public void drawTheWay(List<Edge> vertexToColor) {
        System.out.print(" ");
        for (int i = 0; i < (w * 2) - 1; ++i) {
            System.out.print("_");
        }
        System.out.println("");

        for (int y = 0; y < grid.length; ++y) {
            System.out.print("|");
            for (int x = 0; x < grid[0].length; ++x) {
                boolean check = false;
                for (int i = 0; i < vertexToColor.size(); i++) {
                    if (vertexToColor.get(i).getX() == x && vertexToColor.get(i).getY() == y) {
                        check = true;
                    }
                }
                if (check) {
                    System.out.print("#");
                } else {
                    System.out.print(((grid[y][x] & Maze.S) != 0) ? " " : "_");

                }

                if ((grid[y][x] & Maze.E) != 0) {
                    if (check) {
                        System.out.print("#");
                    } else {
                        System.out.print((((grid[y][x] | grid[y][x + 1]) & Maze.S) != 0) ? " " : "_");
                    }
                } else {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public List<Edge> getAdjlists() {
        return adjlists;
    }
}

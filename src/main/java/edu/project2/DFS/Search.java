package edu.project2.DFS;

import edu.project2.Kruskal.Edge;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;

public class Search {
    private int width;
    private int height;
    public boolean[][] isVisited;
    protected List<Edge> adjLists;

    public Search(int width, int height, List<Edge> adjLists) {
        this.width = width;
        this.height = height;
        isVisited = new boolean[height][width];
        this.adjLists = adjLists;
    }

    private boolean isCoordinateValid(int x, int y) {
        return ((x >= 0) && (x < width) && (y >= 0) && (y < height));
    }

    public List<Edge> getNeighbours(int x, int y) {
        List<Edge> neighbour = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            int xChange = x + dir.getDx();
            int yChange = y + dir.getDy();
            if (isCoordinateValid(xChange, yChange)) {
                for (int i = 0; i < adjLists.size(); i++) {
                    if (adjLists.get(i).getX() == (x * Maze.NUMBER_TO_MAKE_NEW_COORDINATE + y)
                        &&
                        adjLists.get(i).getY() == (xChange * Maze.NUMBER_TO_MAKE_NEW_COORDINATE + yChange)) {
                        neighbour.add(new Edge(xChange, yChange));
                    } else if (adjLists.get(i).getY() == (x * Maze.NUMBER_TO_MAKE_NEW_COORDINATE + y)
                        &&
                        adjLists.get(i).getX() == (xChange * Maze.NUMBER_TO_MAKE_NEW_COORDINATE + yChange)) {
                        neighbour.add(new Edge(xChange, yChange));
                    }
                }
            }
        }
        return neighbour;
    }
}

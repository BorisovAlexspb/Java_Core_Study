package edu.hw9.Task3;

import edu.project2.DFS.Search;
import edu.project2.Kruskal.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DeepFirstSearchParallel extends Search {
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    List<Edge> path;

    public DeepFirstSearchParallel(int width, int height, List<Edge> adjLists) {
        super(width, height, adjLists);
        path = new ArrayList<Edge>();
    }

    public List<Edge> getShortWay(Edge start, Edge end) {
        boolean result = forkJoinPool.invoke(new ParallelDfsSolver(start, end));
        if (!result) {
            return Collections.emptyList();
        }
        path.add(end);
        return path;
    }

    private class ParallelDfsSolver extends RecursiveTask<Boolean> {
        private final Edge coordinate;
        private final Edge end;

        ParallelDfsSolver(Edge coordinate, Edge end) {
            this.coordinate = coordinate;
            this.end = end;
        }

        @Override
        protected Boolean compute() {
            List<Edge> coordinatesForFork = new ArrayList<>();
            coordinatesForFork.add(coordinate);
            int x = coordinate.getX();
            int y = coordinate.getY();
            isVisited[y][x] = true;
            if ((x == end.getX()) && (y == end.getY())) {
                return true;
            }
            List<ParallelDfsSolver> forks = new ArrayList<>();
            List<Edge> neighbours = getNeighbours(x, y);

            for (Edge neighbour : neighbours) {
                if (!isVisited[neighbour.getY()][neighbour.getX()]) {
                    ParallelDfsSolver task = new ParallelDfsSolver(neighbour, end);
                    task.fork();
                    forks.add(task);
                }
            }

            for (ParallelDfsSolver task : forks) {
                if (task.join()) {
                    path.addAll(coordinatesForFork);
                    return true;
                }
            }
            return false;
        }
    }
}



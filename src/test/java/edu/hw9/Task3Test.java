package edu.hw9;

import edu.hw9.Task3.DeepFirstSearchParallel;
import edu.project2.Kruskal.Edge;
import edu.project2.Kruskal.Kruskal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task3Test {
    private static Stream<Arguments> provideWayInMaze() {
        return Stream.of(
            Arguments.of(new Kruskal(10, 10), new Edge(0, 0), new Edge(3, 3)),
            Arguments.of(new Kruskal(5, 5), new Edge(0, 0), new Edge(3, 3)),
            Arguments.of(new Kruskal(10, 5), new Edge(0, 0), new Edge(3, 3))
        );
    }

    @ParameterizedTest
    @DisplayName("Test Maze Find Correct Way")
    @MethodSource("provideWayInMaze")
    void checkWayTest(Kruskal kruskal, Edge start, Edge end) {
        //kruskal.draw();
        var adjlists = kruskal.getAdjlists();
        var res = new DeepFirstSearchParallel(kruskal.w, kruskal.h, adjlists).getShortWay(start, end);
        Assertions.assertThat(res.get(res.size() - 1).getX()).isEqualTo(end.getX());
        Assertions.assertThat(res.get(res.size() - 1).getY()).isEqualTo(end.getY());
    }
}

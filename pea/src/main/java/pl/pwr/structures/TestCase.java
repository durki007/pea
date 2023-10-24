package pl.pwr.structures;

import java.util.Optional;

/**
 * TestCase
 */
public class TestCase {
    public final MatrixGraph graph;
    public final int minPathLength;
    public final Optional<VertexList> minPath;

    public TestCase(MatrixGraph graph, int minPathLength, VertexList minPath) {
        this.graph = graph;
        this.minPathLength = minPathLength;
        this.minPath = Optional.of(minPath);
    }

    public TestCase(MatrixGraph graph, int minPathLength) {
        this.graph = graph;
        this.minPathLength = minPathLength;
        this.minPath = Optional.empty();
    }
}

package pl.pwr.structures;

import java.util.Optional;

public class TSPSolution {
    public final int minPathLength;
    public final Optional<VertexArray> minPath;

    public TSPSolution(int minPathLength, VertexArray minPath) {
        this.minPathLength = minPathLength;
        this.minPath = Optional.of(minPath);
    }

    public TSPSolution(int minPathLength) {
        this.minPathLength = minPathLength;
        this.minPath = Optional.empty();
    }
}

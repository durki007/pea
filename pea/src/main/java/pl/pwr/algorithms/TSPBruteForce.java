package pl.pwr.algorithms;

import java.util.NoSuchElementException;

import pl.pwr.structures.*;

/**
 * TSPBruteForce
 */
public class TSPBruteForce implements TSPAlgorithm {

    private final TSPInstance instance;

    public TSPBruteForce(TSPInstance instance) {
        this.instance = instance;
    }

    public TSPSolution solve() {
        var graph = instance.getGraph();
        var vertexCount = graph.getVertexCount();
        var currentPath = new VertexArray(vertexCount);
        var minPath = currentPath.getArray();
        long minPathLength = calculatePathLength(graph, currentPath);

        while (currentPath.hasNextPermutation()) {
            currentPath.nextPermutation();
            try {
                var pathLength = calculatePathLength(graph, currentPath);
                if (pathLength < minPathLength) {
                    minPathLength = pathLength;
                    minPath = currentPath.getArray().clone();
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
        return new TSPSolution((int) minPathLength, new VertexArray(minPath));
    }

    private long calculatePathLength(MatrixGraph graph, VertexArray path) throws NoSuchElementException {
        long pathLength = 0;
        for (int i = 0; i < path.size(); i++) {
            int from = path.get(i);
            int to = path.get((i + 1) % path.size());
            pathLength += graph.getEdge(from, to).orElseThrow();
        }
        return pathLength;
    }
}

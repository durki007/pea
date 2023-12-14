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
        long minPathLength = graph.calculatePathLength(currentPath);

        while (currentPath.hasNextPermutation()) {
            currentPath.nextPermutation();
            try {
                var pathLength = graph.calculatePathLength(currentPath);
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
}

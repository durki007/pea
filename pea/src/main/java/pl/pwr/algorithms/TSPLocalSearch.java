package pl.pwr.algorithms;

import pl.pwr.structures.*;
import pl.pwr.structures.TSPSolution;

// Simple local search algorithm for TSP
// Using 2-opt algorithm
public class TSPLocalSearch implements TSPAlgorithm {
    private final TSPInstance instance;

    public TSPLocalSearch(TSPInstance instance) {
        this.instance = instance;
    }

    public TSPSolution solve() {
        // Input data
        var graph = instance.getGraph();
        var vertexCount = graph.getVertexCount();
        var currentPath = VertexArray.generateRandom(vertexCount);
        // Local search variables
        var bestPath = currentPath.getArray().clone();
        var bestCost = graph.calculatePathLength(currentPath);
        var currentCost = bestCost;

        // Local search loop
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 0; i < vertexCount - 1; i++) {
                for (int j = i + 1; j < vertexCount; j++) {
                    // Swap two edges
                    currentPath.twoOptSwap(i, j);
                    // Calculate new path length
                    currentCost = graph.calculatePathLength(currentPath);
                    // Check if new path is better
                    if (currentCost < bestCost) {
                        // Update best path
                        bestPath = currentPath.getArray().clone();
                        bestCost = currentCost;
                        improved = true;
                    }
                    // Swap back
                    currentPath.twoOptSwap(i, j);
                }
            }
            // Update current path
            currentPath = new VertexArray(bestPath);
        }

        return new TSPSolution((int) bestCost, new VertexArray(bestPath));
    }
}

package pl.pwr.algorithms;

import pl.pwr.structures.TSPInstance;
import pl.pwr.structures.TSPSolution;
import pl.pwr.structures.VertexArray;

public class TSPTabuSearch implements TSPAlgorithm {

    private final TSPInstance instance;

    public TSPTabuSearch(TSPInstance instance) {
        this.instance = instance;
    }

    // Tabu search algorithm for TSP
    // Using 2-opt algorithm
    public TSPSolution solve() {
        // Input data
        var graph = instance.getGraph();
        var vertexCount = graph.getVertexCount();
        var currentPath = VertexArray.generateRandom(vertexCount);
        // Local search variables
        var bestPath = currentPath.getArray().clone();
        var bestCost = graph.calculatePathLength(currentPath);
        var currentCost = bestCost;
        // Tabu list
        var tabuList = new TabuList(vertexCount);
        // Tabu list parameters
        var tabuSize = vertexCount / 2;
        var tabuTenure = vertexCount / 2;
        // Tabu search loop
        var iteration = 0;
        while (iteration < 10000) {
            var bestMove = new int[]{0, 0};
            var bestMoveCost = Long.MAX_VALUE;
            // For each pair of edges
            for (var i = 0; i < vertexCount - 1; i++) {
                for (var j = i + 1; j < vertexCount; j++) {
                    // Swap edges
                    var newPath = new VertexArray(currentPath.getArray().clone());
                    newPath.twoOptSwap(i, j);
                    var newCost = graph.calculatePathLength(newPath);
                    // Check if move is allowed
                    if (!tabuList.contains(i, j) && newCost < bestMoveCost) {
                        bestMoveCost = newCost;
                        bestMove = new int[]{i, j};
                    }
                }
            }
            // Update solution
//            currentPath = currentPath.swap(bestMove[0], bestMove[1]);
            currentPath.twoOptSwap(bestMove[0], bestMove[1]);
            currentCost = bestMoveCost;
            // Update best solution
            if (currentCost < bestCost) {
                bestPath = currentPath.getArray().clone();
                bestCost = currentCost;
            }
            // Update tabu list
            // Add reversed move to tabu list
            tabuList.add(bestMove[1], bestMove[0]);
            // Decrease tabu tenure
            tabuTenure--;
            if (tabuTenure == 0) {
                // If tabu tenure is 0, clear tabu list
                tabuList = new TabuList(tabuSize);
                // Reset tabu tenure
                tabuTenure = tabuSize;
            }
            iteration++;
        }

        return new TSPSolution((int) bestCost, new VertexArray(bestPath));
    }

    private static class TabuList {
        private final int[][] tabuList;
        private final int tabuSize;
        private int head;

        public TabuList(int tabuSize) {
            this.tabuSize = tabuSize;
            tabuList = new int[tabuSize][2];
            head = 0;
        }

        public void add(int i, int j) {
            tabuList[head] = new int[]{i, j};
            head = (head + 1) % tabuSize;
        }

        public boolean contains(int i, int j) {
            for (var tabu : tabuList) {
                if (tabu[0] == i && tabu[1] == j) {
                    return true;
                }
            }
            return false;

        }
    }

}


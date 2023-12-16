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
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 0; i < vertexCount - 1; i++) {
                for (int j = i + 1; j < vertexCount; j++) {
                    // Check if move is not tabu
                    if (!tabuList.contains(i, j)) {
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
            }
            // Update current path
            currentPath = new VertexArray(bestPath);

            // Update tabu list
            for (int i = 0; i < vertexCount - 1; i++) {
                for (int j = i + 1; j < vertexCount; j++) {
                    if (tabuList.contains(i, j)) {
                        tabuList.add(i, j);
                    }
                }
            }
            // Decrease tabu tenure
            tabuTenure--;
            // Check tabu tenure
            if (tabuTenure == 0) {
                tabuTenure = vertexCount / 2;
                tabuSize--;
                if (tabuSize == 0) {
                    tabuSize = vertexCount / 2;
                }
            }
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


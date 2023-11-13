package pl.pwr.algorithms;

import pl.pwr.structures.TSPInstance;
import pl.pwr.structures.TSPSolution;

public class TSPDynamicRecursive implements TSPAlgorithm {

    private final TSPInstance instance;
    private int n;
    private long maskAll;
    private int[][] dp;

    public TSPDynamicRecursive(TSPInstance instance) {
        this.instance = instance;
    }

    private int tsp(long mask, int s) {
        if (mask == maskAll) {
            return Math.toIntExact(instance.getGraph().getEdgeSafe(s, 0));
        }
        if (dp[s][(int) mask] != 0) {
            return dp[s][(int) mask];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((mask & (1L << i)) == 0) {
                min = (int) Math.min(min, tsp(mask | (1L << i), i) + instance.getGraph().getEdgeSafe(s, i));
            }
        }
        dp[s][(int) mask] = min;
        return min;
    }

    // Dynamic programming
    // Held-Karp algorithm
    public TSPSolution solve() {
        var graph = instance.getGraph();
        n = graph.getVertexCount();
        if (n >= 64) {
            throw new IllegalArgumentException("Graph is too big for dynamic programming");
        }
        maskAll = (1L << n) - 1;
        // Initialize DP table
        dp = new int[n][1 << n];
        return new TSPSolution(tsp(1, 0));
    }
}

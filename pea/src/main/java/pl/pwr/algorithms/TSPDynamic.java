package pl.pwr.algorithms;

import pl.pwr.structures.TSPInstance;
import pl.pwr.structures.TSPSolution;

public class TSPDynamic implements TSPAlgorithm {
    private final TSPInstance instance;

    public TSPDynamic(TSPInstance instance) {
        this.instance = instance;
    }

    public TSPSolution solve() {
        var graph = instance.getGraph();
        var n = graph.getVertexCount();
        if (n >= 64) {
            throw new IllegalArgumentException("Graph is too big for dynamic programming");
        }
        var dp = new long[n][1 << n];
        // Initialize dp array - vertex 0 is the starting point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << n); j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = graph.getEdgeSafe(0, i);
        }

        // Fill dp array
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) continue; // Skip if i-th (vertex) is not in mask
                dp[i][mask] = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if ((mask ^ (1 << j)) == 0) continue;
//                    if (i == j) continue; // Skip if i == j (same vertex)
                    dp[i][mask] = Math.min(dp[i][mask], dp[j][mask ^ (1 << i)] + graph.getEdgeSafe(j, i));
                }
            }
        }


        // Find the shortest path
        long minPathLength = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minPathLength = Math.min(minPathLength, dp[i][(1 << n) - 1] + graph.getEdgeSafe(i, 0));
        }

        if (minPathLength >= Integer.MAX_VALUE) throw new IllegalArgumentException("Cannot cast result to int");

        return new TSPSolution((int) minPathLength);
    }

    // Overflow Safe Min
    private Long OVSafeMin(Long a, Long b) {
        if (a <= 0) return b;
        if (b <= 0) return a;
        return Math.min(a, b);
    }
}

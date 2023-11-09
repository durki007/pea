package pl.pwr.structures;

import java.util.ArrayList;

/**
 * FullGraphFactory
 */
public class FullGraphFactory {
    private FullGraphFactory() throws IllegalStateException {
        throw new IllegalStateException("FullGraphFactory class");
    }
    public static TSPInstance generateRandom(int vertexCount) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        // Fill with empty values
        for (int i = 0; i < vertexCount; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < vertexCount; j++) {
                if (i == j) {
                    row.add(null);
                } else {
                    row.add(100);
                }
            }
            matrix.add(row);
        }
        // Generate random vertex permutation
        VertexArray vertexArray = VertexArray.generateRandom(vertexCount);
        // Generate path based on random permutation
        for (int i = 0; i < vertexArray.size(); i++) {
            int from = vertexArray.get(i);
            int to = vertexArray.get((i + 1) % vertexArray.size());
            matrix.get(from).set(to, 1);
            matrix.get(to).set(from, 1);
        }
        int minPathLength = vertexArray.size();
        return new TSPInstance(new MatrixGraph(matrix), minPathLength, vertexArray);
    }
}

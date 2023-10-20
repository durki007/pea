package pl.pwr.structures;

import java.util.ArrayList;
import java.util.Optional;

import pl.pwr.structures.MatrixGraph;
import pl.pwr.structures.VertexList;

/**
 * FullGraphFactory
 */
public class FullGraphFactory {

    public static MatrixGraph generateRandom(int vertexCount) {
        ArrayList<ArrayList<Optional<Integer>>> matrix = new ArrayList<ArrayList<Optional<Integer>>>();
        // Fill with empty values
        for (int i = 0; i < vertexCount; i++) {
            ArrayList<Optional<Integer>> row = new ArrayList<Optional<Integer>>();
            for (int j = 0; j < vertexCount; j++) {
                if (i == j) {
                    row.add(Optional.empty());
                } else {
                    row.add(Optional.of(100));
                }
            }
            matrix.add(row);
        }
        // Generate random vertex permutation
        VertexList vertexList = VertexList.generateRandom(vertexCount);
        // Generate path based on random permutation
        for (int i = 0; i < vertexList.size(); i++) {
            int from = vertexList.get(i);
            int to = vertexList.get((i + 1) % vertexList.size());
            // TODO: check if correct
            matrix.get(from).set(to, Optional.of(1));
            matrix.get(to).set(from, Optional.of(1));
        }
        return new MatrixGraph(matrix);
    }
}

package pl.pwr.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * MatrixGraph
 */
public record MatrixGraph(ArrayList<ArrayList<Integer>> matrix, int vertexCount) {

    public MatrixGraph(List<ArrayList<Integer>> matrix) throws IllegalArgumentException {
        this(new ArrayList<>(matrix), matrix.size());
        if (matrix.size() != matrix.get(0).size()) {
            throw new IllegalArgumentException("MatrixGraph must be a square matrix");
        }
    }

    public Integer getEdge(int v, int u) {
        return matrix.get(v).get(u);
    }

    // Safe version of getEdge - returns 0 if edge does not exist
    public Long getEdgeSafe(int v, int u) {
        var edge = matrix.get(v).get(u);
        if (edge == null) {
            return (long) 0;
        }
        return (long) matrix.get(v).get(u);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void display() {
        System.out.println("MatrixGraph:");
        for (var row : matrix) {
            for (var cell : row) {
                if (cell != null) {
                    System.out.printf("%3d ", cell);
                } else {
                    System.out.print("  X ");
                }
            }
            System.out.println();
        }
    }
    public long calculatePathLength(VertexArray path) {
        long pathLength = 0;
        for (int i = 0; i < path.size(); i++) {
            int from = path.get(i);
            int to = path.get((i + 1) % path.size());
            pathLength += getEdge(from, to);
            if (getEdge(from, to) == null)
                throw new NoSuchElementException("Edge from " + from + " to " + to + " does not exist");
        }
        return pathLength;
    }

}

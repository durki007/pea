package pl.pwr.structures;

import java.util.ArrayList;
import java.util.List;

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

}

package pl.pwr.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MatrixGraph
 */
public class MatrixGraph {

    private final ArrayList<ArrayList<Optional<Integer>>> matrix;
    private final int vertexCount;

    public MatrixGraph(List<ArrayList<Optional<Integer>>> matrix) throws IllegalArgumentException {
        this.matrix = new ArrayList<>(matrix);
        this.vertexCount = matrix.size();
        if (matrix.size() != matrix.get(0).size()) {
            throw new IllegalArgumentException("MatrixGraph must be a square matrix");
        }
    }

    public Optional<Integer> getEdge(int v, int u) {
        return matrix.get(v).get(u);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void display() {
        System.out.println("MatrixGraph:");
        for (ArrayList<Optional<Integer>> row : matrix) {
            for (Optional<Integer> cell : row) {
                if (cell.isPresent()) {
                    System.out.printf("%3d ", cell.get());
                } else {
                    System.out.print("  X ");
                }
            }
            System.out.println();
        }
    }

}

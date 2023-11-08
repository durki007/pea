package pl.pwr.structures;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;

/**
 * MatrixGraph
 */
public class MatrixGraph {

    private final ArrayList<ArrayList<Optional<Integer>>> matrix;

    public MatrixGraph(ArrayList<ArrayList<Optional<Integer>>> matrix) {
        this.matrix = matrix;
    }


    public static void test() {
        System.out.println("Hello World!");
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

    public int getVertexCount() {
        // TOOD: this class should throw error if matrix is not a square
        return Math.max(matrix.size(), matrix.get(0).size());
    }
}

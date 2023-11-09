package pl.pwr.structures;

public class TSPSolution {
    public final int minPathLength;
    public final VertexArray minPath;

    public TSPSolution(int minPathLength, VertexArray minPath) {
        this.minPathLength = minPathLength;
        this.minPath = minPath;
    }

    public TSPSolution(int minPathLength) {
        this.minPathLength = minPathLength;
        this.minPath = null;
    }

    public void display() {
        System.out.println("TSPSolution:");
        System.out.println("minPathLength: " + minPathLength);
        if (minPath != null) {
            System.out.print("minPath: ");
            minPath.display();
        } else {
            System.out.println("minPath: null");
        }
    }
}

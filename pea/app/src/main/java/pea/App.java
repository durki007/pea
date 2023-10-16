package pea;

import structures.MatrixGraph;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        MatrixGraph matrixGraph = new MatrixGraph();
        matrixGraph.print();
    }
}

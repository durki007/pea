package pl.pwr.structures;

import java.util.ArrayList;

// This class is a decorator for ArrayList<Integer> and adds the functionality of generating permutations of a list. 
public class VertexList extends ArrayList<Integer> {

    // Generates next mathematical permutation of elements in ArrayList
    public void nextPermutation() {

    }

    public void randomPermutation() {
        // TODO
    }

    // Counts all possible permutations of elements in ArrayList
    public int countPermutations() {
        return 0;
    }

    public static VertexList generateRandom(int vertexCount) {
        VertexList vertexList = new VertexList();
        for (int i = 0; i < vertexCount; i++) {
            vertexList.add(i);
        }
        vertexList.randomPermutation();
        return vertexList;
    }
}

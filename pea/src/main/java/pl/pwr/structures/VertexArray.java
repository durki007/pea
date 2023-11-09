package pl.pwr.structures;

import java.math.BigInteger;

// This class is a decorator for ArrayList<Integer> and adds the functionality of generating permutations of a list.
public class VertexArray {
    private int[] array;
    private BigInteger permutationCount;

    public VertexArray(int vertexCount) {
        array = new int[vertexCount];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        permutationCount = getPermutationCount(vertexCount);
    }

    // Generates next mathematical permutation of elements in ArrayList
    public void nextPermutation() {

    }

    public void randomPermutation() {
        // TODO
    }

    public BigInteger getPermutationCount() {
        return permutationCount;
    }

    public long getPermutationCountExact() throws ArithmeticException {
        return permutationCount.longValueExact();
    }

    private static BigInteger getPermutationCount(int n) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(BigInteger.valueOf(n)) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

    public static VertexArray generateRandom(int vertexCount) {
        VertexArray vertexArray = new VertexArray(vertexCount);
        vertexArray.randomPermutation();
        return vertexArray;
    }
}

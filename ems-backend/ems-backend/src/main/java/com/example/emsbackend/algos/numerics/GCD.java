package com.example.emsbackend.algos.numerics;

public class GCD {

    /**
     * Compute the greatest common divisor of integers using euclid algorithm
     * @param x
     * @param y
     * @return
     */
    public int computeGCD(int x, int y) {
        return x > y ? euclidAlgo(x, y) : euclidAlgo(y, x);
    }


    /**
     * Compute gcd where x > y
     * @param x int
     * @param y int
     * @return
     */
    public int euclidAlgo(int x, int y) {
        if (y == 0) {
            return x;
        }
        return euclidAlgo(y,  x % y);
    }
}

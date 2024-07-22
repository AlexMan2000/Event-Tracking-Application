package com.example.emsbackend.algos.numerics.utils.impl;

import com.example.emsbackend.algos.numerics.utils.PrimeSieve;

import java.util.*;

public class EratoSieve implements PrimeSieve {
    /**
     * Compute the primes within range [1, n], O(nlognlogn)
     * @param n Range indicator
     * @return A set containing all the primes in [1, n]
     */
    @Override
    public List<Integer> sieve(int n) {
        List<Integer> res = new ArrayList<>();
        BitSet isPrime = new BitSet(n + 1);
        isPrime.set(0, false);
        isPrime.set(1, false);
        for (int i = 2; i < n + 1; i++) {
            isPrime.set(i);
        }
        for (int i = 2; i * i < n + 1; i++) {
            if (i % 2 == 0 && i > 2) {
                continue;
            }
            if (isPrime.get(i)) {
                if (i * i > n) {
                    continue;
                }

                for (int j = i * i; j < n + 1; j += i) {
                    isPrime.set(j, false);
                }
            }

        }

        for (int i = 2; i <= n; i++) {
            if (isPrime.get(i)) res.add(i);
        }

        return res;
    }

    /**
     * Compute the primes within range [1, n] using multiple threads
     * @param n
     * @param threadNum
     * @return
     */
    public Set<Integer> sieveMultiThreaded(int n, int threadNum) {
        return null;
    }
}

package com.example.emsbackend.algos.numerics.utils.impl;

import com.example.emsbackend.algos.numerics.utils.PrimeSieve;

import java.util.*;

public class EulerSieve implements PrimeSieve {
    /**
     * Linear sieve, O(n)
     * @param n
     * @return
     */
    @Override
    public List<Integer> sieve(int n) {
        List<Integer> primes = new ArrayList<>();
        BitSet isNotPrime = new BitSet(n + 1);
        for (int i = 2; i < n + 1; i++) {
            if (!isNotPrime.get(i)) {
                primes.add(i);
            }
            for (Integer prime: primes) {
                if (i * prime > n) {
                    break;
                }
                isNotPrime.set(i * prime, true);
                if (i % prime == 0) {
                    break;
                }
            }
        }
        return primes;
    }


    public Set<Integer> sieveMultiThreaded(int start, int end) {
        return null;
    }
}

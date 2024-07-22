package com.example.emsbackend.algos.numerics;

import com.example.emsbackend.algos.numerics.utils.PrimeSieve;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Prime {


    /**
     * Find all the prime numbers within range [start, end]
     * @param start Start number
     * @param end end number
     * @param primeSieve Strategy to sieve the primes
     * @return A set containing all the primes
     */
    public Set<Integer> findPrimesInRange(int start, int end, PrimeSieve primeSieve) {
        Set<Integer> temp = primeSieve.sieve(end).stream().collect(Collectors.toSet());
        temp.removeAll(primeSieve.sieve(start - 1).stream().collect(Collectors.toSet()));
        return temp;
    }





    public static void main(String[] args) {

    }
}

package com.example.emsbackend.algos.numerics.tests;

import com.example.emsbackend.algos.numerics.Prime;
import com.example.emsbackend.algos.numerics.utils.PrimeSieve;
import com.example.emsbackend.algos.numerics.utils.impl.EratoSieve;
import com.example.emsbackend.algos.numerics.utils.impl.EulerSieve;
import org.junit.Test;
import static org.junit.Assert.*;


public class PrimeTest {

    public Prime p = new Prime();
    public static void main(String[] args) {



    }

    @Test
    public void testPrimeCase1() {
        PrimeSieve ps = new EratoSieve();
        PrimeSieve ps2 = new EulerSieve();
        System.out.println(p.findPrimesInRange(2, 5, ps));
        System.out.println(p.findPrimesInRange(10, 500, ps));
        System.out.println(p.findPrimesInRange(2, 5, ps2));}
}

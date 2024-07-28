package com.example.emsbackend.algos.numerics.tests;

import com.example.emsbackend.algos.numerics.GCD;
import org.junit.Test;
import static org.junit.Assert.*;

public class GCDTest {
    public static void main(String[] args) {

    }


    @Test
    public void testGCDCase1() {
        GCD gcd = new GCD();

        assertEquals(2, gcd.computeGCD(2, 4));
        assertEquals(1, gcd.computeGCD(1, 2));
        assertEquals(3, gcd.computeGCD(3, 6));
    }
}

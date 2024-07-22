package com.example.emsbackend.algos.unionfind.tests;

import com.example.emsbackend.algos.unionfind.QuickUnion;
import junit.framework.JUnit4TestAdapter;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuickUnionTest {
    /**
     * JUnit suite target
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(QuickUnionTest.class);
    }


    @Test
    public void testUnionCase1() {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1, 2);
        qu.union(2, 3);
        assertEquals(true, qu.isConnected(1, 2));
        assertEquals(true, qu.isConnected(2, 3));
        assertEquals(true, qu.isConnected(1, 3));
    }


    @Test
    public void testUnionCase2() {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1, 2);
        qu.union(3, 4);
        assertEquals(true, qu.isConnected(1, 2));
        assertEquals(true, qu.isConnected(3, 4));
        assertEquals(false, qu.isConnected(1, 3));
        assertEquals(false, qu.isConnected(1, 4));
    }

    @Test
    public void testNumConnectedCase1() {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1, 2);
        qu.union(3, 4);
        assertEquals(8, qu.numConnectedComponents());
    }

    @Test
    public void testNumConnectedCase2() {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1, 2);
        qu.union(2, 3);
        qu.union(3, 5);
        assertEquals(true, qu.isConnected(1, 5));
        assertEquals(7, qu.numConnectedComponents());
    }

    @Test
    public void testUnionPrint() {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1, 2);
        qu.union(3, 4);

        assertEquals( Arrays.asList(-1,2, -2, 4, -2, -1, -1, -1, -1, -1).toString(), qu.toString());
        assertEquals(2, qu.find(2));
        assertEquals(4, qu.find(3));
        qu.union(2, 3);
        assertEquals(Arrays.asList(-1, 2, 4, 4, -4, -1, -1, -1, -1, -1).toString(), qu.toString());
    }

    @Test
    public void testInitCase1() {
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 3},
                {0, 3},
                {0, 4}
        };
        QuickUnion qu = new QuickUnion(edges);
        assertEquals(5, qu.getNumElem());
        System.out.println(qu);
        assertEquals(1, qu.numConnectedComponents());
    }


}

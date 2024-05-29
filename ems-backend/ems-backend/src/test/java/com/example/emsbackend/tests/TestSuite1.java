package com.example.emsbackend.tests;



import com.example.emsbackend.dto.primary.DagEntityDTO;
import junit.framework.JUnit4TestAdapter;


//
public class TestSuite1 {

    /**
     * Unit test for TupleDesc.combine()
     */
    public static void main(String[] args) {
        new DagEntityDTO();
    }

    /**
     * JUnit suite target
     */
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestSuite1.class);
    }
}
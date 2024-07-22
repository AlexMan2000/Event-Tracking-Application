package com.example.emsbackend.algos.unionfind;

public interface UnionFind {
    boolean isConnected(int u1, int u2);

    int find(int u1);

    boolean union(int u1, int u2);


}

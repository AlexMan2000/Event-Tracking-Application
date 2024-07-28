package com.example.emsbackend.algos.unionfind;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class QuickUnion implements UnionFind{

    public int[] data;
    public int numElem;

    public int numConnected;


    public int biggestComponentNum;

    QuickUnion() {}

    public QuickUnion(int numElem) {
        this.data = new int[numElem];
        this.numElem = numElem;
        for (int i = 0; i < numElem; i++) {
            data[i] = -1;
        }
        this.numConnected = this.numElem;
        this.biggestComponentNum = 1;
    }

    public QuickUnion(int[] data) {
        this.data = data;
        this.numElem = this.data.length;
        this.numConnected = computeConnectedComponents();
        for (int elem: this.data) {
            if (elem < 0) {
                this.biggestComponentNum = Math.max(this.biggestComponentNum, elem);
            }
        }
    }

    public QuickUnion(int[][] edges) {
        Set<Integer> nodes = new HashSet<>();
        for (int[] edge: edges) {
            nodes.add(edge[0]);
            nodes.add(edge[1]);
        }
        this.numElem = nodes.size();
        this.data = new int[this.numElem];
        for (int i = 0; i < this.numElem; i++) {
            this.data[i] = -1;
        }
        this.numConnected = this.numElem;

        for (int[] edge: edges) {
            union(edge[0], edge[1]);
        }

    }


    @Override
    /**
     * Connect to elements
     * @param u1
     * @param u2
     */
    public boolean union(int u1, int u2) {

        int p1 = find(u1), p2 = find(u2);
        if (u1 == u2 || p1 == p2) {
            return false;
        }
        if (p1 >= this.numElem || p2 >= this.numElem) {
            throw new RuntimeException("Node index out of bound");
        }


        // Keeping balance
        int s1 = sizeOf(p1);
        int s2 = sizeOf(p2);
        if (s1 > s2) {
            this.data[p2] = p1;
            this.data[p1] -= s2;
        } else {
            this.data[p1] = p2;
            this.data[p2] -= s1;
        }
        this.numConnected --;
        this.biggestComponentNum = Math.max(this.biggestComponentNum, s1 + s2);
        return true;
    }

    @Override
    /**
     * Return whether two nodes are connected
     * @param u1 node index 1
     * @param u2 node index 2
     * @return If connected, true. If not connected, return false
     * @throws RuntimeException if index not found
     */
    public boolean isConnected(int u1, int u2) {
        int p1 = find(u1), p2 = find(u2);
        if (p1 >= this.numElem || p2 >= this.numElem) {
            throw new RuntimeException("Node index out of bound");
        }
        return find(u1) == find(u2);
    }



    /**
     * Return the parent of the node u1
     * @param u1 node index
     * @return The parent node index of the input node, return itself if it's root node
     */
    public int parent(int u1) {
        int res = this.data[u1];
        if (res < 0) {
            return u1;
        }
        return res;
    }

    /**
     * Compute the size of the connected component that contains node index u1
     * @param u1
     * @return
     */
    public int sizeOf(int u1) {
        int root = find(u1);
        return (-1) * this.data[root];
    }

    @Override
    /**
     * Find the root of a given node with complete path compression
     * @param u1 node index
     * @return root node index of the given node index
     */
    public int find(int u1) {
        if (parent(u1) != u1) {
            // find(parent[u1]) 会返回树的根结点，
            // parent[u1] = find(parent[u1]) 会将沿途经过的结点的父亲结点都指向根结点
            this.data[u1] = find(parent(u1));
        }
        // 输入 u1 没有发生变化，应返回 u1的父亲结点，才表示树根结点
        return parent(u1);
    }

    /**
     * Compute the number of connected components in the disjoint set
     * @return
     */
    private int computeConnectedComponents() {
        int res = 0;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] < 0) {
                res++;
            }
        }
        return res;
    }


    public int numConnectedComponents() {
        return this.numConnected;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int elem: this.data) {
            sb.append(elem);
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

}

package com.example.emsbackend.algos.unionfind;

import java.util.HashSet;
import java.util.Set;

enum WeightCalStrategy {
    ADDITION,
    MULTIPLICATION,
}

public class WeightedQuickUnion extends QuickUnion{
    public int[] weight;

    public WeightCalStrategy weightCal;

    public WeightedQuickUnion(int numElem, int[] weight, WeightCalStrategy weightCal) {
        super(numElem);
        this.weight = weight;
        this.weightCal = weightCal;
    }

    public WeightedQuickUnion(int[] data, int[] weight, WeightCalStrategy weightCal) {
        super(data);
        this.weight = weight;
        this.weightCal = weightCal;
    }

    public WeightedQuickUnion(int[][] edges, int[] weight, WeightCalStrategy weightCal) {
        super();
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
        this.weightCal = weightCal;
        for (int i = 0; i < edges.length; i++) {
            union(edges[i][0], edges[i][1]);
        }

    }

    public int find(int u1) {
        if (parent(u1) != u1) {
            // find(parent[u1]) 会返回树的根结点，
            // parent[u1] = find(parent[u1]) 会将沿途经过的结点的父亲结点都指向根结点
            int origin = parent(u1);
            this.data[u1] = find(origin);
            if (this.weightCal == WeightCalStrategy.MULTIPLICATION) {
                this.weight[u1] *= this.weight[origin];
            } else if (this.weightCal == WeightCalStrategy.ADDITION) {
                this.weight[u1] += this.weight[origin];
            }

        }
        // 输入 u1 没有发生变化，应返回 u1的父亲结点，才表示树根结点
        return parent(u1);
    }

}

package com.example.emsbackend.algos.unionfind.problems;

import com.example.emsbackend.algos.unionfind.QuickUnion;

import java.util.*;

public class UnionProblems {


    /**
     * Leetcode
     * @return
     */
    public static int[] findRedundantConnection(int[][] edges) {

        Set<Integer> nodes = new HashSet<>();
        for (int[] edge: edges) {
            nodes.add(edge[0]);
            nodes.add(edge[1]);
        }
        int numElem = nodes.size();
        QuickUnion qu = new QuickUnion(numElem);
        int[] res = null;
        for (int[] edge: edges) {
            if (!qu.union(edge[0], edge[1])) {
                res = edge;
            }
        }

        return res;
    }


    /**
     *
     * @param n
     * @param connections
     * @return
     */
    public static int makeConnected(int n, int[][] connections) {
        QuickUnion qu = new QuickUnion(n);

        List<int[]> redundantEdges = new ArrayList<>();


        for (int[] edge: connections) {
            if (qu.isConnected(edge[0], edge[1])) {
                redundantEdges.add(new int[] {edge[0], edge[1]});
            } else {
                qu.union(edge[0], edge[1]);
            }
        }
        if (redundantEdges.size() + 1 < qu.numConnectedComponents()) {
            return -1;
        }

        return qu.numConnectedComponents() - 1;
    }

    public static boolean equationsPossible(String[] equations) {
        QuickUnion unionFind = new QuickUnion(26);

        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                unionFind.union(index1, index2);
            }
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                if (unionFind.isConnected(index1, index2)) {
                    // 如果合并失败，表示等式有矛盾，根据题意，返回 false
                    return false;
                }
            }
        }
        // 如果检查了所有不等式，都没有发现矛盾，返回 true
        return true;
    }

    /**
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        QuickUnion qu = new QuickUnion(nums.length);

        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mapping.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (mapping.containsKey(nums[i] + 1)) {
                if (mapping.get(nums[i]) != i) {
                    continue;
                }
                qu.union(i, mapping.get(nums[i] + 1));

            }
            if (mapping.containsKey(nums[i] - 1)) {
                if (mapping.get(nums[i]) != i) {
                    continue;
                }
                qu.union(i, mapping.get(nums[i] - 1));
            }
        }

        System.out.println(qu);
        return qu.getBiggestComponentNum();
    }


    public int largestComponentSize(int[] nums) {
        return 0;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {100, 4, 200, 1, 3, 2};
        int[] nums2 = new int[] {0,3,7,2,5,8,4,6,0,1};
        int res = longestConsecutive(nums2);

        System.out.println(res);
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        return null;
    }


}

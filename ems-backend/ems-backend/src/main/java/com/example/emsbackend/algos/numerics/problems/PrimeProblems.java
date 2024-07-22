package com.example.emsbackend.algos.numerics.problems;

import com.example.emsbackend.algos.unionfind.QuickUnion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrimeProblems {


    public static int largestComponentSize(int[] nums) {
        int m = Arrays.stream(nums).max().getAsInt();
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mapping.put(nums[i], i);
        }
        QuickUnion qu = new QuickUnion(m + 1);
        for (int num: nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0 ) {
                    qu.union(num, i);
                    qu.union(num, num / i);
                }
            }
        }
        int[] counts = new int[m + 1];
        int ans = 0;
        for (int num : nums) {
            int root = qu.find(num);
            counts[root]++;
            ans = Math.max(ans, counts[root]);
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] test = new int[] {4,6,15,35};
        int i = largestComponentSize(test);
        System.out.println(i);
    }
}

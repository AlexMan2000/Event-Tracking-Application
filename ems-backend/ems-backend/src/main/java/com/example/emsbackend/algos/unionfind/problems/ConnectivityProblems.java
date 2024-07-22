package com.example.emsbackend.algos.unionfind.problems;

import com.example.emsbackend.algos.unionfind.QuickUnion;

public class ConnectivityProblems {

    public static int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int height = grid.length;
        int width = grid[0].length;
        QuickUnion qu = new QuickUnion(height * width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i + 1 < height && grid[i + 1][j] == grid[i][j]) {
                    qu.union(width * i + j, width * (i + 1) + j);
                }
                if (j + 1 < width && grid[i][j + 1] == grid[i][j]) {
                    qu.union(width * i + j, width * i + j + 1);
                }
            }
        }

        int res = 0;
        for (int i: qu.data) {
            int y = i / width;
            int x = i % width;
            if (grid[y][x] == '1') {
                res++;
            }
        }
        return res;
    }




    public static void main(String[] args) {
    }
}

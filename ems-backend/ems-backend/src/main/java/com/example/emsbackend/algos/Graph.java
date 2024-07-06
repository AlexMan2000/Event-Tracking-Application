package com.example.emsbackend.algos;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> {

    public static void main(String[] args) {
        Map<Integer, Map<String, Integer>> degreeInfo = new HashMap<>();

        degreeInfo.entrySet()
                .stream()
                .filter(elem -> elem.getValue().get("in") == 0)
                .map(elem -> elem.getKey())
                .collect(Collectors.toList());
    }

//    pri   vate Map<Node, List<Node>> adjList;
//
//    public Graph(Map<T, List<T>> adjListFlat) {
//        graphInit(adjListFlat);
//    }
//
//
//    private void graphInit(Map<T, List<T>> adjListFlat) {
//        for (Map.Entry<T, List<T>> entry: adjListFlat.entrySet()) {
//            T nodeIndex = entry.getKey();
//            List<T> neighbors = entry.getValue();
////            adjList.put(new Node())
//        }
//    }
//
//    private class Node {
//        private List<Node> neighbors;
//        private T index;
//
//        public Node() {
//
//        }
//    }
//
//
//    public List<T> topologicalSort() {
//
//    }
//
//
//    private List<T> topologicalSortDFS() {
//
//
//    }
//
//
//    private List<T> topologicalSortQueue() {
//
//    }
//
//    private boolean dfs() {
//
//    }
//
//    private boolean temp() {
//
//    }

}

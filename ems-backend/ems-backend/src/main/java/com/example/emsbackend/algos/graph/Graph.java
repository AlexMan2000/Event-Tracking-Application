package com.example.emsbackend.algos.graph;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;



public class Graph<T> {

    @Data
    private class GraphNode {
        public T value;
        public List<GraphNode> neighbors;
    }

    @Data
    private class GraphEdge {
        public T weight;
        public GraphNode from;
        public GraphNode to;

    }




}

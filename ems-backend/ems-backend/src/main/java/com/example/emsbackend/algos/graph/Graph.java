package com.example.emsbackend.algos.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

enum GraphType {
    UNDIRECTED,
    DIRECTED
}

@Data
public class Graph<T> {

    public int numEdges;
    public int numNodes;
    public Map<GraphNode, List<GraphNode>> adjList;
    public Set<GraphEdge> edges;
    public GraphType graphType;


    @Data
    private class GraphNode {
        private int weight;
        private T value;

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (other.getClass() != this.getClass()) {
                return false;
            }

            GraphNode oe = (GraphNode) other;
            if (oe.getWeight() == weight
                    && oe.getWeight() == this.getWeight()) {
                return true;
            }
            return false;
        }


        @Override
        public int hashCode() {
            return weight + 31 * value.hashCode();
        }
    }

    @Data
    @AllArgsConstructor
    private class GraphEdge {
        private int weight;
        private GraphNode from;
        private GraphNode to;


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (other.getClass() != this.getClass()) {
                return false;
            }

            GraphEdge oe = (GraphEdge) other;
            if (oe.getTo().equals(to)
                && oe.getTo().equals(to)
                && oe.getWeight() == this.getWeight()) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return weight + 31 * from.hashCode() + 31 * 31 * to.hashCode();
        }
    }


    public void addEdge(GraphNode n1, GraphNode n2,int weight) {
        if (!this.adjList.containsKey(n1)) {
            this.numNodes ++;
        }
        if (!this.adjList.containsKey(n2)) {
            this.numNodes ++;
        }
        this.adjList.putIfAbsent(n1, new ArrayList<>());
        this.adjList.get(n1).add(n2);
        this.adjList.putIfAbsent(n2, new ArrayList<>());
        GraphEdge newEdge = new GraphEdge(weight, n1, n2);
        if (edges.contains(newEdge)) {
            throw new RuntimeException("You have added this edge before, no parallel edges allowed!");
        };
        this.edges.add(newEdge);
    }

    public void removeEdge(GraphNode n1, GraphNode n2, int weight) {
        if (!this.getAdjList().containsKey(n1)
                || !this.getAdjList().containsKey(n2)) {
            throw new RuntimeException("Edge doesn't exist!");
        }

        this.adjList.get(n1).remove(n2);
        GraphEdge graphEdge = new GraphEdge(weight, n1, n2);
        this.edges.remove(graphEdge);
        if (this.graphType == GraphType.UNDIRECTED) {
            this.adjList.get(n2).remove(n1);
            GraphEdge temp = new GraphEdge(weight, n2, n1);
            this.edges.remove(temp);
        }
    }

    public void removeNode() {

    }


    public void removeNodes() {

    }


    public void flipEdges() {

    }

    public void reverseGraph() {

    }

    public Graph<T> getReversedGraph() {
        return null;
    }

    public Map<GraphNode, Map<String, Integer>> getDegreeMap() {
        return null;
    }

    public Map<GraphNode, List<GraphNode>> getAdjList() {
        return null;
    }


}

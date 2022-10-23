package GraphFramework;

import java.util.*;

public class Edge implements Comparable<Edge> {

    public Vertex source_vertix;
    public Vertex destination_vertix;
    public Vertex parent;
    public int weight;

    public Vertex getSource_vertix() {
        return source_vertix;
    }

    public void setSource_vertix(Vertex source_vertix) {
        this.source_vertix = source_vertix;
    }

    public Vertex getDestination_vertix() {
        return destination_vertix;
    }

    public void setDestination_vertix(Vertex destination_vertix) {
        this.destination_vertix = destination_vertix;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static ArrayList<Edge> getEdges() {
        return edges;
    }

    public static void setEdges(ArrayList<Edge> edges) {
        Edge.edges = edges;
    }


    // arraylist of all edges
    public static ArrayList<Edge> edges = new ArrayList<Edge>();

    public Edge(Vertex v, Vertex u, int w) {
        this.source_vertix = v;
        this.destination_vertix = u;
        this.weight = w;
    }

    
    public String displayInfo() {
        return (this.destination_vertix.getLabel() + "(" + this.source_vertix.getLabel() + "," + this.weight + ") ");
    }

    @Override
    public int compareTo(Edge o) {
        return (this.weight - o.weight);
    }

}

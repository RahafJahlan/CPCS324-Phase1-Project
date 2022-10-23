package GraphFramework;

import java.util.ArrayList;
import java.util.LinkedList;


public class Vertex implements Comparable<Vertex>{
    
    public int label;

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public static ArrayList<LinkedList<Edge>> getAdjacencyList() {
        return AdjacencyList;
    }

    public static void setAdjacencyList(ArrayList<LinkedList<Edge>> AdjacencyList) {
        Vertex.AdjacencyList = AdjacencyList;
    }

    //initializeing key value to infinity and parent to -1
    public int parent = -1;
    public int rank = 0;
    public int key = Integer.MAX_VALUE;
    
    static public ArrayList<LinkedList<Edge>> AdjacencyList = new ArrayList<>(); 

    public Vertex(){

    }

    public Vertex(int label){
        this.label = label;
    }

    public String displayInfo(){
        return "Vertex: " + label;
    }

    @Override
    public int compareTo(Vertex o) {
        return (this.key - o.key);
    }
    
}

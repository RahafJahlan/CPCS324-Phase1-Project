package GraphFramework;

import static GraphFramework.MSTAlgorithm.MSTResultList;
import java.io.PrintWriter;
import java.util.*;

public class KruskalAlg extends MSTAlgorithm {

    public KruskalAlg() {
    }

    public KruskalAlg(Graph graph) {
        super(graph);
    }

    // --------------------- find ---------------------
    public int find(Vertex vertex) {
        if (vertex.getParent() == -1) {
            return vertex.getLabel();
        } 

        return vertex.parent = find(graph.vertices.get(vertex.getParent()));
    }// END FIND PARENT METHOD

    // --------------------- checkCycle ---------------------
    public boolean checkCycle(Vertex v, Vertex u) {
        return find(v) == find(u);
    }

    // --------------------- union ---------------------
    public void union(Vertex v, Vertex u) {
        //if they got the same rank
        if (v.getRank() == u.getRank()) {
            int parent = find(v);
            graph.vertices.get(find(u)).setParent(parent);
            v.rank++;
        } // larger rank will be parent of the smaller
        else if (v.getRank() > u.getRank()) {
            int parent = find(u);
            graph.vertices.get(find(v)).setParent(parent);

        } else if (v.getRank() < u.getRank()) {
            int parent = find(v);
            graph.vertices.get(find(u)).setParent(parent);
        }

    }

    public void Kruskal() {
        //timer starts
        start_time = System.currentTimeMillis();

        int counter = 0;
        int i = 0;

        //we use the built in function to sort the edges
        Collections.sort(Edge.edges);
        while (counter < graph.vertices.size() - 1) {

            //break when we reach last edge
            if (i == Edge.edges.size()) {
                break;
            }
            
            //setting the source and destination of the edge
            Vertex source_vertex = Edge.edges.get(i).getSource_vertix();
            Vertex destination_vertex = Edge.edges.get(i).getDestination_vertix();
            //if it creates a cycle skip the edge
            if (checkCycle(source_vertex, destination_vertex)) {
                i++;
                continue;
            }
            //esle --> does not create a cycle
            union(source_vertex, destination_vertex);
            MSTResultList.add(Edge.edges.get(i));
            i++;
            counter++;
        }

        // total time
        finish_time = System.currentTimeMillis();
        total_time = finish_time - start_time;
    }
    public void displayResultingMST(PrintWriter output, int num) {
        int cost = 0;
        if (num == 1) {
            int i = 0;
            output.println("The road map (minimum spanning tree) generated by Kruskal algorithm is as follows: ");
            while (i < MSTResultList.size()) {
                cost += MSTResultList.get(i).weight;
                output.println(MSTResultList.get(i).source_vertix.displayInfo() + " - " + MSTResultList.get(i).destination_vertix.displayInfo() + " : " + MSTResultList.get(i).displayInfo());

                i++;
            }

            output.println("The Minimum Cost Spanning Tree generated by Kruskal algorithm is " + cost
                    + "\nRuntime = " + total_time + " msec");

        } else {
            int i = 0;
            while (i < MSTResultList.size()) {
                cost += MSTResultList.get(i).getWeight();
                i++;
            }
            output.println("The Minimum Cost Spanning Tree generated by Kruskal algorithm is " + cost
                    + "\nRuntime = " + total_time + " msec");

        }
        
       //clear for the other algorithm
       MSTResultList.clear();
    }

}

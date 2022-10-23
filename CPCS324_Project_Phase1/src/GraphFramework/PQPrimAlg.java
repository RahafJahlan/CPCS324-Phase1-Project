package GraphFramework;

import static GraphFramework.MSTAlgorithm.MSTResultList;
import java.io.PrintWriter;
import java.util.*;
import javafx.util.Pair;

public class PQPrimAlg extends MSTAlgorithm {

    public PQPrimAlg() {
    }

    public PQPrimAlg(Graph graph) {
        super(graph);
    }

    public void PQPrim() {
        total_time = 0;
        //initilizing the priority queue PQ 
        PriorityQueue<Map.Entry<Integer, Integer>> PQ = new PriorityQueue<>(
                graph.vertices.size(), (Map.Entry<Integer, Integer> M1, Map.Entry<Integer, Integer> M2) -> {//override compare method
                    /*new Comparator<Map.Entry<Integer, Integer>>() { 
            public int compare(Map.Entry<Integer, Integer> M1, Map.Entry<Integer, Integer> M2) {*/
                    //sorting key values
                    int key1 = M1.getKey();
                    int key2 = M2.getKey();
                    return key1 - key2;
        } //override compare method
        );
        //Timer strats
        start_time = System.currentTimeMillis();
        int VerticesNO = graph.VerticesNo;
        //a boolean flag to mark a vertex as added or not
        boolean[] MSTset = new boolean[VerticesNO];
        //to pick the min each time
        int key[] = new int[VerticesNO];
        //a loop to set key values to infinity and all parent to -1 and mark all vertices as not added
        for (int i = 0; i < VerticesNO; i++) {
            key[i] = Integer.MAX_VALUE;
            graph.vertices.get(i).setParent(-1);
            MSTset[i] = false;
        }
        //initializing the first key as the arbiterary source and giving it the value 0 to add it to the MST list
        //Since 0 < infinity
        key[0] = 0;
        //creating the pair of first key
        AbstractMap.SimpleEntry<Integer, Integer> p0 = new AbstractMap.SimpleEntry<>(key[0], 0);
        //adding it to the priority queue
        PQ.add(p0);
        //a loop that runs as long the priority queue is not empty
        while (!PQ.isEmpty()) {
            //Get the minimum value (vertex) u  
            Map.Entry<Integer, Integer> Min_vert = PQ.poll();
            //Add it to MST set
            MSTset[Min_vert.getValue()] = true;

            //A loop to go through all adjacent vertices of u and update their keys if needed
            for (int i = 0; i < Vertex.AdjacencyList.get(Min_vert.getValue()).size(); i++) {
                //Destination_vert = v
                Vertex Destination_vert = Vertex.AdjacencyList.get(Min_vert.getValue()).get(i).getDestination_vertix(); // v
                int tempWeight = Vertex.AdjacencyList.get(Min_vert.getValue()).get(i).getWeight(); // v - u weight

                //Check if v is not yet added to MST set and if weight will be smaller than current u weight
                if (MSTset[Destination_vert.getLabel()] == false && tempWeight < key[Destination_vert.getLabel()]) {

                    //true --> update key
                    key[Destination_vert.getLabel()] = tempWeight;

                    // CREATE A MAP OBJECT WITH VERTEX V AND KEY = U-V WEIGHT AND INSERT IT INTO
                    //Add it to priority queue
                    AbstractMap.SimpleEntry<Integer, Integer> pq_updated = new AbstractMap.SimpleEntry<>(key[Destination_vert.getLabel()], Destination_vert.getLabel());
                    PQ.add(pq_updated);
                    //set v's parent (adjacent vertex's parent) to be u
                    Destination_vert.setParent(Min_vert.getValue());;
                }
            }
        }

        //timer stops
        finish_time = System.currentTimeMillis();
        //calaculate the time spent on  running the algorithm
        total_time = finish_time - start_time;

        //now we create the MST
        for (int i = 0; i < Vertex.AdjacencyList.size(); i++) {
            Vertex tempVer = graph.vertices.get(i);
            for (int j = 0; j < Vertex.AdjacencyList.get(i).size(); j++) {
                Edge temp = Vertex.AdjacencyList.get(i).get(j);
                if (temp.getSource_vertix().getParent() == tempVer.getParent() && temp.getDestination_vertix().getParent() == tempVer.getLabel()) {
                    MSTResultList.add(temp);
                }
            }
        }
    }

    public void displayResultingMST(PrintWriter output, int num) {
        int cost = 0;
        if (num == 1) {
            int i = 0;
            output.println("The road map (minimum spanning tree) generated by Priority-queue Prim is as follows: ");
            while (i < MSTResultList.size()) {
                cost += MSTResultList.get(i).weight;
                output.println(MSTResultList.get(i).source_vertix.displayInfo() + " - " + MSTResultList.get(i).destination_vertix.displayInfo() + " : " + MSTResultList.get(i).displayInfo());

                i++;
            }

            output.println("The Minimum Cost Spanning Tree generated by Priority-queue Prim is " + cost
                    + "\nRuntime = " + total_time + " msec");

        } else {
            int i = 0;
            while (i < MSTResultList.size()) {
                cost += MSTResultList.get(i).getWeight();
                i++;
            }
            output.println("The Minimum Cost Spanning Tree generated by Priority-queue Prim  is " + cost
                    + "\nRuntime = " + total_time + " msec");

        }
        //clear for the other algorithm
        MSTResultList.clear();
    }

}// END CLASS

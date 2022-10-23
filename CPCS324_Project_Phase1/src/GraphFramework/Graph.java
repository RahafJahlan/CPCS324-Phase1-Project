package GraphFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {

    public int VerticesNo;
    public int EdgeNo;
    public boolean IsDigraph = false;
    public ArrayList<Vertex> vertices = new ArrayList<>();

    public Graph() {

    }

    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.VerticesNo = verticesNo;
        this.EdgeNo = edgeNo;
        this.IsDigraph = isDigraph;
        vertices = new ArrayList<>(verticesNo);
    }

    public Graph readGraphFromFile(File filename) throws FileNotFoundException {

        Graph graph = new Graph();
        Scanner input = new Scanner(filename);
        if (filename.exists()) {

            if (input.nextInt() == 1) {
                IsDigraph = true;
            } else {
                IsDigraph = false;
            }

            VerticesNo = input.nextInt();
            EdgeNo = input.nextInt();

            // creating an adjacency List for all vertices
            for (int i = 0; i < VerticesNo; i++) {
                Vertex vertex = new Vertex(i);
                Vertex.AdjacencyList.add(new LinkedList<>());
                graph.vertices.add(vertex);
            }

            for (int i = 0; i < EdgeNo; i++) {
                char v = input.next().charAt(0);//read first label (vertex)
                char u = input.next().charAt(0);//read second lable (vertex)
                int w = input.nextInt();//read weight

                addEdge(graph.vertices.get(v), graph.vertices.get(u), w);
            }
            return graph;

        } else {
            //if No print msg and return null
            System.out.println("File dose not exist");
            return null;
        }
    }

    public Graph makeGraph(int verticesNo, int edgeNo) {

        //First we create a graph object
        Graph graph = new Graph();

        //now we add the vertices to the graph and create an arraylist for each vertex
        for (int i = 0; i < verticesNo; i++) {
            Vertex vertex = new Vertex(i);
            graph.VerticesNo++;
            Vertex.AdjacencyList.add(new LinkedList<>());
            graph.vertices.add(vertex);
        }
        //now we start connecting vertices together randomly
        for (int i = 0; i < verticesNo - 1; i++) {

            //for each edge we generate a random value ranging from 1 to 20
            int weight = (int) (Math.random() * 50) + 1;

            //Now add an edge between the vertices using the generated weight v and u
            addEdge(graph.vertices.get(i), graph.vertices.get(i + 1), weight);
            //increment 
            graph.EdgeNo++;
            //incase it's an undirected graph
            if (graph.IsDigraph == false) {
                //create another edge between u and v
                addEdge(graph.vertices.get(i + 1), graph.vertices.get(i), weight);
                graph.EdgeNo++;
            }
            if ((i + 1) > verticesNo - 1) {
                 //connect last vertex with the first
                addEdge(graph.vertices.get(i), graph.vertices.get(0), weight);
                graph.EdgeNo++;
            }
        }
        //we get the remaining edges to create the rest of the edges randomly
        int remEdge = edgeNo - (verticesNo - 1);
        for (int i = 0; i < remEdge; i++) {

            //generate random values for the vertices
            int srcVert = (int) (Math.random() * graph.VerticesNo );
            int destVert = (int) (Math.random() * graph.VerticesNo );

            //first check if the edge was ctreated before
            if (destVert == srcVert || EdgeExist(srcVert, destVert)) {
                i--;
                continue;
            }
            int weight = (int) (Math.random() * 50) + 1;
            //create the edge since it's not created before
            addEdge(graph.vertices.get(srcVert), graph.vertices.get(destVert), weight);
            graph.EdgeNo++;

            if (graph.IsDigraph == false) {
                addEdge(graph.vertices.get(destVert), graph.vertices.get(srcVert), weight);
                graph.EdgeNo++;
            }
        }
        return graph;
    }

    // ------------------------------------------- addEdge ---------------------------------------------------
    public void addEdge(Vertex v, Vertex u, int w) {

        Edge edge = new Edge(v, u, w);
        Vertex newVer = new Vertex(-1);
        edge.setParent(newVer);
        Vertex.AdjacencyList.get(v.getLabel()).add(edge);
        Edge.edges.add(edge);

    }

    // ------------------------------------------- EdgeExist ---------------------------------------------------
    public boolean EdgeExist(int source, int Destination) {
        int i = 0;
        while (i < Vertex.AdjacencyList.size()) {
            int j = 0;
            while (j < Vertex.AdjacencyList.get(i).size()) {
                Edge edge = Vertex.AdjacencyList.get(i).get(j);
                if (edge.destination_vertix.getLabel() == Destination && edge.source_vertix.getLabel() == source) { //E(v,u) = E(v,u)they match?
                    return true;
                }

                j++;
            }

            i++;
        }
        return false;
    }

}

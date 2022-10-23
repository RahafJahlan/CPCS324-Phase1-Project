package RoadDesignApplication;
//====================CPCS324-Phase1=================

import GraphFramework.Edge;
import GraphFramework.Graph;
import GraphFramework.KruskalAlg;
import GraphFramework.MSTAlgorithm;
import GraphFramework.PQPrimAlg;
import GraphFramework.Vertex;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class RoadDesignApp extends Graph {

    static Graph graph = new RoadDesignApp();

    public static void main(String[] args) throws FileNotFoundException {

        File Read_file = new File("Read.txt");
        File Write_file = new File("output.txt");
        PrintWriter output = new PrintWriter(Write_file);
        Scanner input = new Scanner(System.in);
        int option = -1, n = 0, m = 0;

        do {
            //a menue for the user to choose displaying output of requirement 1 or 2
            System.out.println("******************WELCOME TO OUR ROAD DESIDGN SYSTEM*****************");
            System.out.print("Please enter your option nuumber: "
                    + "\n1- Requirement 1"
                    + "\n2- Requirement 2"
                    + "\n Your choice is : ");
            option = input.nextInt();

            //incase the user enters a non existing option
            if (option != 1 && option != 2) {
                System.out.print("Please enter option nuumber:"
                        + "\n1- Requirement 1"
                        + "\n2- Requirement 2"
                        + "\n Your choice: ");
                option = input.nextInt();

            } else {
                break;//break of loop and now start displaying output
            }
        } while (true);//to redisplay menue

        //-------------REQUIREMENT 1------------------
        if (option == 1) {
            graph.readGraphFromFile(Read_file);

            MSTAlgorithm cityMap = new KruskalAlg(graph);
            ((KruskalAlg) cityMap).Kruskal();
            ((KruskalAlg) cityMap).displayResultingMST(output, 1);

            cityMap = new PQPrimAlg(graph);
            ((PQPrimAlg) cityMap).PQPrim();
            ((PQPrimAlg) cityMap).displayResultingMST(output, 1);
            output.close();

        }
        //-------------REQUIREMENT 2------------------
        if (option == 2) {
            System.out.print("1- n=1000 , m=10000"
                    + "\n2- n=1000 , m=15000"
                    + "\n3- n=1000 , m=25000"
                    + "\n4- n=5000 , m=15000"
                    + "\n5- n=5000 , m=25000"
                    + "\n6- n=10000 , m=15000"
                    + "\n7- n=10000 , m=25000"
                    + "\nPlease choose a case number: ");
            option = input.nextInt();

            // Requirement 2            
            switch (option) {
                case 1: {
                    n = 1000;
                    m = 10000;
                }
                break;
                case 2: {
                    n = 1000;
                    m = 15000;
                }
                break;
                case 3: {
                    n = 1000;
                    m = 25000;
                }
                break;
                case 4: {
                    n = 5000;
                    m = 15000;
                }
                break;
                case 5: {
                    n = 5000;
                    m = 25000;
                }
                break;
                case 6: {
                    n = 10000;
                    m = 15000;
                }
                break;
                case 7: {
                    n = 10000;
                    m = 25000;
                }
                break;
                default://incase of any other input
                    System.out.print("1- n=1000 , m=10000"
                            + "\n2- n=1000 , m=15000"
                            + "\n3- n=1000 , m=25000"
                            + "\n4- n=5000 , m=15000"
                            + "\n5- n=5000 , m=25000"
                            + "\n6- n=10000 , m=15000"
                            + "\n7- n=10000 , m=25000"
                            + "\nPlease choose a case number: ");
                    option = input.nextInt();

            }

            Graph g = new RoadDesignApp();
            Graph done = g.makeGraph(n, m);

            MSTAlgorithm cityMap = new KruskalAlg(graph);
            ((KruskalAlg) cityMap).Kruskal();
            ((KruskalAlg) cityMap).displayResultingMST(output, 0);

            output.println("\n------------------------------------------------\n");

            cityMap = new PQPrimAlg(graph);
            ((PQPrimAlg) cityMap).PQPrim();
            ((PQPrimAlg) cityMap).displayResultingMST(output, 0);

            output.close();
        }

    }

    @Override
    public Graph makeGraph(int verticesNo, int edgeNo) {
        //now we add the vertices to the graph and create an arraylist for each vertex
        for (int i = 0; i < verticesNo; i++) {
            Vertex vertex = new House(i);
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
            int srcVert = (int) (Math.random() * graph.VerticesNo);
            int destVert = (int) (Math.random() * graph.VerticesNo);

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

    @Override
    public Graph readGraphFromFile(File inputFile) throws FileNotFoundException {

        try (Scanner input = new Scanner(inputFile)) {

            String digraph = input.next();

            if (input.nextInt() == 1) {
                IsDigraph = true;
            } else {
                IsDigraph = false;
            }

            VerticesNo = input.nextInt();
            EdgeNo = input.nextInt();

            for (int i = 0, vertexInTheFile = 0; i < EdgeNo; i++) {
                Vertex source = null;
                Vertex destination = null;

                char v = input.next().charAt(0);
                char u = input.next().charAt(0);
                int w = input.nextInt();

                for (int j = 0; j < vertices.size(); j++) {
                    if (((House) vertices.get(j)).getHouseName() == v) {
                        source = vertices.get(j);
                    }
                }
                // create house object for the new house
                if (source == null) {
                    // source vertexInTheFile
                    source = new House(vertexInTheFile++, v);
                    Vertex.AdjacencyList.add(new LinkedList<>());
                    graph.vertices.add(source);
                }

                for (int j = 0; j < vertices.size(); j++) {
                    if (((House) vertices.get(j)).getHouseName() == u) {
                        destination = vertices.get(j);
                    }
                }

                if (destination == null) {
                    // dest vertexInTheFile
                    destination = new House(vertexInTheFile++, u);
                    Vertex.AdjacencyList.add(new LinkedList<>());
                    graph.vertices.add(destination);
                }

                addEdge(source, destination, w);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        return graph;
    }

    @Override
    public void addEdge(Vertex v, Vertex u, int w) {
        Edge edge = new Road(v, u, w);
        House newHouse = new House(-1);
        edge.setParent(newHouse);
        Vertex.AdjacencyList.get(v.getLabel()).add(edge);
        Edge.edges.add(edge);
    }

}

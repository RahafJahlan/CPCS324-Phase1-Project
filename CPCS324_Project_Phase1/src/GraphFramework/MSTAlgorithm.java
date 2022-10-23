
package GraphFramework;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class MSTAlgorithm {
    
    public static ArrayList<Edge> MSTResultList = new ArrayList<Edge>();
    public static long total_time;
    public static long finish_time;
    public static  long start_time;
    public Graph graph;

    public static ArrayList<Edge> getMSTResultList() {
        return MSTResultList;
    }

    public static void setMSTResultList(ArrayList<Edge> MSTResultList) {
        MSTAlgorithm.MSTResultList = MSTResultList;
    }

    public static long getTotalTime() {
        return total_time;
    }

    public static void setTotalTime(long totalTime) {
        MSTAlgorithm.total_time = totalTime;
    }

    public static long getFinish() {
        return finish_time;
    }

    public static void setFinish(long finish) {
        MSTAlgorithm.finish_time = finish;
    }

    public static long getStart() {
        return start_time;
    }

    public static void setStart(long start) {
        MSTAlgorithm.start_time = start;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
   

    public MSTAlgorithm() {

    }

    public MSTAlgorithm(Graph graph){
        this.graph = graph;
    }
    
 
    // An abstract method that will be overridden in its subclasses
    abstract public void displayResultingMST(PrintWriter output,int num);
    
}

    

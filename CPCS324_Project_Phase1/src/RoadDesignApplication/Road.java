package RoadDesignApplication;

import GraphFramework.Edge;
import GraphFramework.Vertex;

public class Road extends Edge {
    
    private int roadSize;
    

    public Road(Vertex v, Vertex u, int w) {
        super(v, u, w);
        this.roadSize = 3*w;
      
    }

    public int getRoadSize() {
        return roadSize;
    }

    public void setRoadSize(int roadSize) {
        this.roadSize = roadSize;
    }

    @Override
    public String displayInfo(){
        return "Road size: " + roadSize;
   }

}

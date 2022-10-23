package RoadDesignApplication;

import GraphFramework.Vertex;

public class House extends Vertex{
    
  private String houseName;

   
    public House() {

    }
    
    public House(int label) {
      super(label);
    }
    
    public House(int label, char houseName) {
      super(label);
      this.houseName = ""+houseName;
    }
   
    @Override
    public String displayInfo(){
      return "House Name: " + this.houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public char getHouseName(){
      return houseName.charAt(0);
    }

}

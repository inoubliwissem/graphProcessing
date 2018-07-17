import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex {

    private int id;
    private String type;
    private List<Integer> neigbords;
    private Set<Integer> strongNeigbords;

    public Vertex(int id) {
        this.id = id;
        neigbords = new ArrayList<Integer>();
        strongNeigbords=new HashSet<Integer>();
    }

    public Vertex() {
        neigbords = new ArrayList<Integer>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getNeigbords() {
        return neigbords;
    }

    public void setNeigbords(List<Integer> neigbords) {
        this.neigbords = neigbords;
    }

    public void addNeigbor(int v) {
        if (!this.neigbords.contains(v)) {
            this.neigbords.add(v);
        }
    }

    public void printVertex()
    {

      String ng="[";
      for ( Integer neig : this.neigbords){
         ng+=","+neig;
      }
        ng+="]";
        System.out.println("vertice :"+this.id+" neighbors :"+ng);
    }
/*
    public  List<Integer>getStrongNei(double sigma)
    {
        List<Integer> list_strong_neigh=new ArrayList<Integer>();

        for( int vi : this.getNeigbords())
        {
            for(Edge e: edges)
            {
                if(this.id==e.getFromNode() && vi==e.getEndNode() && e.getSimilarity()>=sigma)
                {
                    list_strong_neigh.add(e.getEndNode());
                }
                if(this.id==e.getEndNode() && vi==e.getFromNode() && e.getSimilarity()>=sigma)
                {
                    list_strong_neigh.add(e.getFromNode());
                }

            }
        }

        return list_strong_neigh;
    }*/

    public void addStrongNeighbors(int neighbors)
    {
        this.strongNeigbords.add(neighbors);
    }
    public Set<Integer> getStrongNeigbords()
    {
        return strongNeigbords;
    }

}

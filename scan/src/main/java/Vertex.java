import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private int id;
    private String type;
    private List<Integer> neigbords;

    public Vertex(int id) {
        this.id = id;
        neigbords = new ArrayList<Integer>();
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
}


public class Node {
    private Integer id;
    private Iterable<Integer> neighbor;

    public Node(Integer id) {
        this.id = id;
    }
    public Node(Integer id,Iterable<Integer>neighbor) {
        this.id = id;
        this.neighbor=neighbor;
    }
    public Node(String ids) {
        this.id = Integer.parseInt(ids);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Iterable<Integer> getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(Iterable<Integer> neighbor) {
        this.neighbor = neighbor;
    }
}

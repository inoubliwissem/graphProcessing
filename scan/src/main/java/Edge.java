import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Edge {
    private int fromNode, endNode;
    private Vertex fNode, eNode;
    private double similarity;
    // front or no
    private int type;

    public Edge() {
    }

    public Edge(int fromNode, int endNode) {
        this.fromNode = fromNode;
        this.endNode = endNode;
    }

    public Edge(int fromNode, int endNode, double similarity, int type) {
        this.fromNode = fromNode;
        this.endNode = endNode;
        this.similarity = similarity;
        this.type = type;
    }

    public int getFromNode() {
        return fromNode;
    }

    public void setFromNode(int fromNode) {
        this.fromNode = fromNode;
    }

    public int getEndNode() {
        return endNode;
    }

    public void setEndNode(int endNode) {
        this.endNode = endNode;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void printEdge() {

        System.out.println("from " + this.getFromNode() + " to " + this.endNode + " neighbors 1 :" + this.getfNode().getNeigbords().size() + " neighbors 2: " + this.geteNode().getNeigbords().size() + " with a structural similarity " + this.similarity);

    }

    public Vertex getfNode() {
        return fNode;
    }

    public void setfNode(Vertex fNode) {
        this.fNode = fNode;
    }

    public Vertex geteNode() {
        return eNode;
    }

    public void seteNode(Vertex eNode) {
        this.eNode = eNode;
    }

    public void similarityCalculation() {
        this.similarity = getintersection() / Math.sqrt(this.getfNode().getNeigbords().size() * this.geteNode().getNeigbords().size());
    }

    private int getintersection() {
        int rst = 0;
        if (this.fNode.getNeigbords().size() > this.eNode.getNeigbords().size()) {
            for (Integer i : this.eNode.getNeigbords()) {
                if (this.fNode.getNeigbords().contains(i)) rst++;
            }


        } else {
            for (Integer i : this.fNode.getNeigbords()) {
                if (this.eNode.getNeigbords().contains(i)) rst++;
            }

        }
        return rst;

    }
}

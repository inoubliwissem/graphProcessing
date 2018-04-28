import java.io.Serializable;

public class Edge implements Serializable {
    private Integer from,end;

    public Edge(Integer from, Integer end) {
        this.from = from;
        this.end = end;
    }
    public Edge(String from, String end) {
        this.from = Integer.parseInt(from);
        this.end = Integer.parseInt(end);
    }
    public Edge(String l) {
        String parts[]=l.split("\t");
        this.from = Integer.parseInt(parts[0]);
        this.end = Integer.parseInt(parts[1]);
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}

import java.io.PrintWriter;
import java.util.*;

public class Graph {

    private List<Edge> edges;
    private List<Vertex> vertices;

    public Graph() {
        edges = new ArrayList<Edge>();
        vertices = new ArrayList<Vertex>();
    }

    public boolean isExist(int from, int end)
    {
        for( Edge e: edges)
        {
            if ((e.getFromNode()==from && e.getEndNode()==end) || (e.getEndNode()==from && e.getFromNode()==end))
                return true;
        }
        return false;
    }

    public void graphGenearte(int nb_vertices, int nbMinEdges, int nbMaxEdges) {
        Random generator = new Random();
        for (int i = 0; i < nb_vertices; i++) {

            int nb_neighbor = generator.nextInt((nbMaxEdges - nbMinEdges) + 1) + nbMinEdges;
            Set<Integer> neighbor = new HashSet<Integer>();
            while (neighbor.size() < nb_neighbor) {
                int neig = generator.nextInt(nb_vertices );
                if (!neighbor.contains(neig) && !isExist(i,neig))
                    neighbor.add(neig);
                    edges.add(new Edge(i,neig));
            }
            Vertex v =new Vertex(i);
            v.setNeigbords(new ArrayList<Integer>(neighbor));
            v.addNeigbor(i);
            vertices.add(v);

        }
    }

    public void printGraph() {
        System.out.println("the graph contains :" + vertices.size() + " and " + edges.size());
        System.out.println("Vertices mapping");
        for(Vertex v : vertices)
            System.out.println("V "+v.getId()+" :: "+v.getNeigbords());
        System.out.println("Edges mapping");

        for(Edge e : edges)
            System.out.println("E "+e.getFromNode()+" :=>: "+e.getEndNode());
    }

    public void writeGraph(String path) {
        try {
            PrintWriter file = new PrintWriter(path, "UTF-8");
            String line="";
            for(Vertex v: vertices)
            {  line="";
              line+=v.getId()+";";
              for(Integer i : v.getNeigbords())
              {
                  line+=i+",";
              }
                line = line.substring(0, line.length() - 1);
                line+="\n";
                file.write(line);
            }


            file.close();
        }
        catch(Exception e)
        {

        }

    }

    public double getDensity()
    {
        double dencity=0;
        System.out.println("2*E= "+(double)2*edges.size());
        System.out.println("V ="+(double)vertices.size());
        System.out.println("ratio"+(double)(vertices.size()*(vertices.size()-1)));
        dencity=(double)2*edges.size()/(double)(vertices.size()*(vertices.size()-1));
        return dencity;
    }

}

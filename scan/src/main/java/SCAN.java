import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SCAN {
    private static List<Vertex> vertices;
    private static List<Edge> edges;

    // public function to add a vertex V to our shared list of vertices "vertices".
    public static void addVertex(Edge e) {
        boolean tv = false;
        boolean tv2 = false;
        for (Vertex v : vertices) {
            if (v.getId() == e.getFromNode()) {
                v.addNeigbor(e.getFromNode());
                v.addNeigbor(e.getEndNode());
                tv = true;

            }
            if (v.getId() == e.getEndNode()) {
                v.addNeigbor(e.getFromNode());
                v.addNeigbor(e.getEndNode());
                tv2 = true;

            }
            // if a two nodes of the edge exist into our vertices list we exit block "for"
            if (tv && tv2) {
                break;
            }

        }
        if (tv == false) {
            Vertex vi = new Vertex(e.getFromNode());
            vi.addNeigbor(e.getFromNode());
            vi.addNeigbor(e.getEndNode());
            vertices.add(vi);

        }
        if (tv2 == false) {
            Vertex vi = new Vertex(e.getEndNode());
            vi.addNeigbor(e.getFromNode());
            vi.addNeigbor(e.getEndNode());
            vertices.add(vi);

        }


    }

    // public function to get a specific vertex with his Id from a shared list of vertices "vertices"
    public static Vertex getVertex(int id) {
        for (Vertex v : vertices) {
            if (v.getId() == id) return v;
        }

        return null;
    }
    // public function to determine that a given vertex v is core or not
    public static boolean isCore(Vertex v, int mu,double sigma)
    {
        boolean isC=false;
        int nb_strongConnections=0;
        // get all edges from/to vertex V where their similarity better than sigma
        for(Edge e : edges)
        {
            if((e.getFromNode()==v.getId() || e.getEndNode()==v.getId()) && e.getSimilarity()>=sigma )
            {
                nb_strongConnections+=1;
                if(nb_strongConnections>=mu)
                {
                    return true;
                }
            }
        }


        return isC;
    }

    public static void main(String argc[]) {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        // read an input graph
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("dataset2");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                //
                String parts[] = line.split("\t");
                if (parts.length > 1) {
                    int fn = Integer.parseInt(parts[0]);
                    int en = Integer.parseInt(parts[1]);
                    edges.add(new Edge(fn, en));
                    //edges.add(new Edge(en, fn));
                    addVertex(new Edge(fn, en));
                    //addVertex(new Edge(en, fn));

                }
            }

            // print all vertices
            for (Vertex v : vertices) {
              //  v.printVertex();
            }

            //add a neighbors list to each bord of a edge
            for( Edge e : edges)
            {
                e.setfNode(getVertex(e.getFromNode()));
                e.seteNode(getVertex(e.getEndNode()));
            }

            // print all edges
            for (Edge e : edges) {
                e.similarityCalculation();
                //e.printEdge();
            }
            // liste of cores vertices
            List<Vertex> cores_vertices=new ArrayList<Vertex>();
            for(Vertex v : vertices)
            {
              if(isCore(v,3,0.7))
              {
                  cores_vertices.add(v);
              }
            }

            for(Vertex v : cores_vertices)
            {
                v.printVertex();
            }


        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}

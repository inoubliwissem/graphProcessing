import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SCAN {
    /*
    public static List<Vertex> vertices;
    public static List<Edge> edges;

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

    private static int getListintersection(List<Integer> lst1,Set<Integer> lst2) {
        int rst = 0;
        if (lst1.size() > lst2.size()) {
            for (Integer i : lst2) {
                if (lst1.contains(i)) rst++;
            }


        } else {
            for (Integer i : lst1) {
                if (lst2.contains(i)) rst++;
            }

        }
        return rst;

    }


*/
    public static void main(String argc[]) {
        /*
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
              {   v.setType("core");
                  cores_vertices.add(v);
              }
            }

            for(Vertex v : cores_vertices)
            {
                v.printVertex();
               // System.out.println(v.getStrongNei(0.7).size());
            }

            // start a clustering step
            List<Set<Integer>> clusters=new ArrayList<Set<Integer>>();

            // for each core vertex in the list
       //
               for(Vertex v : cores_vertices)
            {   // if we on first step, when we haven't any cluster
                // add a current core vertex and his strong neighbors
                if(clusters.size()==0) {
                    Set<Integer> cluster=new HashSet<Integer>();
                    cluster.add(v.getId());
                    cluster.addAll(v.getStrongNei(0.7));
                    clusters.add(cluster);
                 }
                else {
                    //variable to memorise when we have merge the current vertex into one existing cluster
                    // when we fid  at least one shared neighbor between a current core vertex and a some cluster
                   // we merge them into same cluster
                    //else
                    //  we must create a new cluster which group  the current vertex ant his strong
                    // neighbors into same cluster
                    boolean added=false;
                    for(Set<Integer> c : clusters)
                    {   // if a difference between current cluster and a neighbors of a v core vertex not null
                        // push a v and their strong neighbors into current cluster

                        if(getListintersection(v.getStrongNei(0.7),c)>0)
                        {
                          c.addAll(v.getStrongNei(0.7));
                          c.add(v.getId());
                          added=true;
                          break;
                        }



                    }
                    // if we parse all cluster and we cannot get added the current  vertex v
                    // we must create a new cluster and push into this a current core and their strong neighbors
                    // the add the new cluster to our set of clusters
                    //
                    if(!added)
                    {
                         Set<Integer> newcluster= new HashSet<Integer>();
                            newcluster.add(v.getId());
                            newcluster.addAll(v.getStrongNei(0.7));
                            clusters.add(newcluster);

                    }



                }


            }
            System.out.println("we fined a "+clusters.size() +" clusters");
               for(Set<Integer> c : clusters)
               {
                   System.out.println(" cluster : ");
                   for(int v : c)
                   {
                       System.out.print(" "+v);
                   }
                   System.out.println(" \n ****************** : ");

               }
            //


        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        */
        Fraction p1=new Fraction();
        p1.readSubGraph("dataset2");
        p1.calculateSimilarity();
        p1.clustering(0.7,3);
    }
}

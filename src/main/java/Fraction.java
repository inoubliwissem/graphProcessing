import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fraction {
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Set<Vertex> cores_vertices;
    private Set<Integer> border_vertices;
    private Set<Integer> outiler_vertices;
    private Set<Integer> bridge_vertices;
    private List<Set<Integer>> clusters;

    public Fraction() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        cores_vertices = new HashSet<Vertex>();
        border_vertices = new HashSet<Integer>();
        outiler_vertices = new HashSet<Integer>();
        bridge_vertices = new HashSet<Integer>();
        clusters = new ArrayList<Set<Integer>>();
    }

    //  function to add a vertex V to list of vertices "vertices".
    public void addVertex(Edge e) {
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
    public Vertex getVertex(int id) {
        for (Vertex v : vertices) {
            if (v.getId() == id) return v;
        }
        return null;
    }

    public boolean isexiste(Edge e) {

        for (Edge ed : edges) {
            if ((ed.getFromNode() == e.getEndNode() && ed.getEndNode() == e.getFromNode()) || (ed.getFromNode() == e.getFromNode() && ed.getEndNode() == e.getEndNode()))
                return true;

        }
        return false;
    }

    // public function to determine that a given vertex v is core or not
    public boolean isCore(Vertex v, int mu, double sigma) {
        boolean isC = false;
        int nb_strongConnections = 0;
        // get all edges from/to vertex V where their similarity better than sigma
        for (Edge e : edges) {
            if ((e.getFromNode() == v.getId() || e.getEndNode() == v.getId()) && e.getSimilarity() >= sigma) {
                nb_strongConnections += 1;
                if (nb_strongConnections >= mu) {
                    return true;
                }
            }
        }
        return isC;
    }

    // function to get an intersection between two lists
    private int getListintersection(List<Integer> lst1, Set<Integer> lst2) {
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

    // read an input sub-graph
    public void readSubGraph(String file) {
        // read an input graph
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split("\t");
                if (parts.length > 1) {
                    int fn = Integer.parseInt(parts[0]);
                    int en = Integer.parseInt(parts[1]);
                    edges.add(new Edge(fn, en));
                    addVertex(new Edge(fn, en));
                }
            }


        } catch (Exception e) {
            System.out.println("Fraction ereur " + e.getMessage());
        }
    }

    // read a graph with simpled file
    public void readGraph(String file) {
        // read an input graph
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(";");
                if (parts.length > 1) {
                    int id_v = Integer.parseInt(parts[0]);
                    Vertex v = new Vertex(id_v);
                    v.addNeigbor(id_v);
                    String neighbors[] = parts[1].split(",");
                    for (int i = 0; i < neighbors.length; i++) {
                        int en = Integer.parseInt(neighbors[i]);
                        v.addNeigbor(en);
                        if (!isexiste(new Edge(id_v, en))) {
                            edges.add(new Edge(id_v, en));
                        }
                    }
                    vertices.add(v);

                }

            }
        } catch (Exception e) {
            System.out.println("Fraction ereur " + e.getMessage());
        }


    }

    // read a graph with simpled file
    public void readPartitionOfGraph(String file) {
        // read an input file partition
        BufferedReader br = null;
        FileReader fr = null;
        // we prepare a variable to memorise all id of vertices in the part of graph
        Set<Integer> vertices_id = new HashSet<Integer>();
        // variable to save all vertices in edges
        Set<Integer> vertices_edges = new HashSet<Integer>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(";");
                if (parts.length > 1) {
                    int id_v = Integer.parseInt(parts[0]);
                    Vertex v = new Vertex(id_v);
                    v.addNeigbor(id_v);
                    String neighbors[] = parts[1].split(",");
                    for (int i = 0; i < neighbors.length; i++) {
                        int en = Integer.parseInt(neighbors[i]);
                        v.addNeigbor(en);
                        if (!isexiste(new Edge(id_v, en))) {
                            edges.add(new Edge(id_v, en));
                            vertices_edges.add(id_v);
                            vertices_edges.add(en);
                        }
                    }
                    vertices.add(v);
                    vertices_id.add(v.getId());


                }

            }

            if (vertices_edges.size() != vertices_id.size()) {
                vertices_edges.removeAll(vertices_id);

                for (Integer i : vertices_edges) {
                    vertices.add(new Vertex(i));

                }


            }
        } catch (Exception e) {
            System.out.println("Fraction ereur " + e.getMessage());
        }


    }

    // function to print a loaded graph
    public void printGraph() {
        for (Vertex v : vertices) {
            System.out.println("V :" + v.getId() + " has a " + v.getNeigbords().size() + " neighbors " + v.getNeigbords());
        }
        calculateSimilarity();
        for (Edge e : edges) {
            System.out.println(e.getFromNode() + " to " + e.getEndNode() + " =" + e.getSimilarity());
        }
        //  computeStrongNeighbors(0.7);
        //   filterCorevertices(3);
        for (Vertex v : cores_vertices) {
            System.out.println("VC " + v.getId() + " strong neighbors" + v.getStrongNeigbords());
        }
    }

    // calulate a similarity for all edges in current sub graph
    public void calculateSimilarity() {
        //add a neighbors list to each bord of a edge
        for (Edge e : edges) {
            e.setfNode(getVertex(e.getFromNode()));
            e.seteNode(getVertex(e.getEndNode()));
        }
        // calculate a similarity for each edge
        for (Edge e : edges) {
            e.similarityCalculation();
        }
    }

    // get a list of a cores vertices in current sub graph
    public void filterCorevertices(int mu) {
        for (Vertex v : vertices) {
            if (v.getStrongNeigbords().size() >= mu) {
                cores_vertices.add(v);
            }
        }
    }

    // function to get foreach vertice a list of his strong neighbors according "sigma" threshold
    public void computeStrongNeighbors(double sigma) {
        for (Vertex v : vertices) {
            // get all edges from/to vertex V where his similarity better than sigma
            for (Edge e : edges) {
                if (e.getFromNode() == v.getId() && e.getSimilarity() >= sigma) {
                    v.addStrongNeighbors(e.getEndNode());
                }
                if (e.getEndNode() == v.getId() && e.getSimilarity() >= sigma) {
                    v.addStrongNeighbors(e.getFromNode());
                }
            }
        }
    }

    // function to add each border vertex to a global list of all border vertices
    public void addBorderVertcies(List<Integer> listTBP) {
        for (Integer idv : listTBP) {
            boolean tv = false;
            for (Vertex v : cores_vertices) {
                if (v.getId() == idv) {
                    tv = true;
                }
            }
            if (!tv) {
                border_vertices.add(idv);
            }
        }
    }

    // clustering function
    public void clustering(double sigma, int mu) {
        if (vertices.size() > 0 && edges.size() > 0) {   // after a loading a given sub graph we calculate all similarity
            this.calculateSimilarity();
            // we find a strong neighbors foreach vertex according a sigma value
            this.computeStrongNeighbors(sigma);
            // filter a cores vertices in the sub graph
            this.filterCorevertices(mu);
            // start SCAN algorithm
            // varibale to save a list of clusters
            //   List<Set<Integer>> clusters=new ArrayList<Set<Integer>>();
            for (Vertex v : cores_vertices) {
                // if we on first step, when we haven't any cluster
                // add a current core vertex and his strong neighbors
                if (clusters.size() == 0) {
                    Set<Integer> cluster = new HashSet<Integer>();
                    cluster.add(v.getId());
                    cluster.addAll(v.getStrongNeigbords());
                    // add border vertices
                    addBorderVertcies(new ArrayList<Integer>(v.getStrongNeigbords()));
                    clusters.add(cluster);
                } else {
                    //variable to memorise when we have merge the current vertex into one existing cluster
                    // when we fid  at least one shared neighbor between a current core vertex and a some cluster
                    // we merge them into same cluster
                    //else
                    //  we must create a new cluster which group  the current vertex ant his strong
                    // neighbors into same cluster
                    boolean added = false;
                    for (Set<Integer> c : clusters) {   // if a difference between current cluster and a neighbors of a v core vertex not null
                        // push a v and their strong neighbors into current cluster

                        if (getListintersection(v.getNeigbords(), c) > 0) {
                            c.addAll(v.getStrongNeigbords());
                            c.add(v.getId());
                            // add border vertices
                            addBorderVertcies(new ArrayList<Integer>(v.getStrongNeigbords()));
                            added = true;
                            break;
                        }


                    }
                    // if we parse all cluster and we cannot get added the current  vertex v
                    // we must create a new cluster and push into this a current core and their strong neighbors
                    // the add the new cluster to our set of clusters
                    //
                    if (!added) {
                        Set<Integer> newcluster = new HashSet<Integer>();
                        newcluster.add(v.getId());
                        newcluster.addAll(v.getStrongNeigbords());
                        //add border vertices
                        addBorderVertcies(new ArrayList<Integer>(v.getStrongNeigbords()));
                        clusters.add(newcluster);

                    }

                }
            }

            // get last vertices (outliers and bridges)
            Set<Integer> all_precessed_vertices = new HashSet<Integer>();
            for (Vertex v : cores_vertices) {
                all_precessed_vertices.add(v.getId());

            }
            all_precessed_vertices.addAll(border_vertices);
            Set<Integer> unprocessed_vertices = new HashSet<Integer>();
            for (Vertex v : vertices) {
                if (!all_precessed_vertices.contains(v.getId()))
                    unprocessed_vertices.add(v.getId());
            }
            // get a stats of each unprocessed vertices, we well annotated each one as an outliers or bridges
            // according their connections
            for (Integer i : unprocessed_vertices) {
                int nb_connections = 0;
                for (Set<Integer> c : clusters) {  // if we have a least one connection between a current unprocessed vertex I and a current cluster C
                    // so we increment a nb_connections value, to be used after that to decide that a I is a bridge or an outlier
                    if (getListintersection(getVertex(i).getNeigbords(), c) > 0) {
                        nb_connections += 1;
                        if (nb_connections >= 2) {
                            bridge_vertices.add(i);
                            break;
                        }
                    }
                }
                if (nb_connections < 2)
                    outiler_vertices.add(i);

            }

        } else {
            System.out.print("we don't have a graph to compute it");
        }

    }

    public void printFractionDetails() {   // print a sub-graph details
        System.out.println("in the graph we have " + vertices.size() + " vertices and " + edges.size() + " edges");
        // print a cores vertices in the sub-graph
        System.out.println("print a cores vertices");
        for (Vertex v : cores_vertices)
            System.out.println(v.getId() + " its neighbors " + v.getNeigbords());
        // print a border vertices
        System.out.println("print a border vertices");
        for (Integer v : border_vertices)
            System.out.println(v);

        System.out.println("we fined a " + clusters.size() + " clusters");

        for (Set<Integer> c : clusters) {
            System.out.println(" cluster : ");
            for (int v : c) {
                System.out.print(" " + v);
            }
            System.out.println(" \n ****************** : ");

        }
        // print a bridges vertices
        System.out.println("print a bridges vertices");
        for (Integer v : this.bridge_vertices)
            System.out.println(v);

        // print a outliers vertices
        System.out.println("print a outliers vertices");
        for (Integer v : this.outiler_vertices)
            System.out.println(v);


    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Set<Vertex> getCores_vertices() {
        return cores_vertices;
    }

    public Set<Integer> getBorder_vertices() {
        return border_vertices;
    }

    public Set<Integer> getOutiler_vertices() {
        return outiler_vertices;
    }

    public List<Vertex> getVerticeOutlier() {
        List<Vertex> outliers_vertices = new ArrayList<Vertex>();
        for (Integer i : outiler_vertices) {
            outliers_vertices.add(getVertex(i));
        }
        return outliers_vertices;

    }

    public Set<Integer> getBridge_vertices() {
        return bridge_vertices;
    }

    public List<Vertex> getBridges_vertices() {
        List<Vertex> bridge_vertices = new ArrayList<Vertex>();
        for (Integer i : getBridge_vertices()) {
            bridge_vertices.add(getVertex(i));
        }
        return bridge_vertices;
    }

    public List<Set<Integer>> getClusters() {
        return clusters;
    }

    public Set<Integer> getIdCores() {
        Set<Integer> liste = new HashSet<Integer>();
        for (Vertex v : cores_vertices) {
            liste.add(v.getId());
        }
        return liste;
    }
}



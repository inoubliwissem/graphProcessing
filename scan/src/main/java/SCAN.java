import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SCAN {

    public static List<Set<Integer>> mergeclusters(List<Set<Integer>> liste_clusters, Integer core)
    {   // final clusters list
        List<Set<Integer>> group=new ArrayList<Set<Integer>>();
        // get all clusters contains a core vertex "core"
        // list of id of cluster which contain a core
        Set<Integer> clusters=new HashSet<Integer>();
        int i=0;
        // find the indices of clusters which contain a core "core
        for(Set<Integer> c : liste_clusters)
        {
            if(c.contains(core))
            {
               clusters.add(i) ;

            }
            i++;
        }
        // prepare a global cluster to group all to merged clusters
        Set<Integer> merged_cluster=new HashSet<Integer>();
        // for each cluster we will push it to global list
        for(Integer j : clusters) {
           merged_cluster.addAll(liste_clusters.get(j));
        }
        // add merged clusters to global list
        group.add(merged_cluster);
        // filter global list
        for(Set<Integer> c : liste_clusters)
        {
            if(!c.contains(core))
            {
                group.add(c);
            }
        }
          return group;
    }

    public static void combiner(Fraction p1, Fraction p2)
    {
    //    try
  //  {
       List<Set<Integer>> clusters=new ArrayList<Set<Integer>>();
       Set<Integer> borders=new HashSet<Integer>();
       Set<Integer> bridges=new HashSet<Integer>();
       Set<Integer> outliers=new HashSet<Integer>();
       // liste of vertices outliers
       List<Vertex> vertices_outliers=new ArrayList<Vertex>();
       // add a local clusters
       clusters.addAll(p1.getClusters());
       clusters.addAll(p2.getClusters());
        // scenario 1 : if we have a shared cores
       Set<Integer> shared_cores=getIntersection(p1.getIdCores(),p2.getIdCores());
       if(shared_cores.size()>0)
       {
           // for each shared core
           for( Integer c : shared_cores) {
               clusters = mergeclusters(clusters, c);
           }
       }
       // end scenario 1 : outlier lists
        // scenario 2
        // for each vertex in return list of locals outlier, we will test if it is part on any cluster
        // we eliminate it from the list.
        // If he has  more than one relationship with less two clusters we add it into bridge list
        // else keep it as an outlier
        // First step: add a list of outliers into global list of outliers
        outliers.addAll(p1.getOutiler_vertices());
        outliers.addAll(p2.getOutiler_vertices());
           //Second step: for ech vertex in outliers global list check if it's already in a cluster
           Set<Integer> listeTBD=new HashSet<Integer>();
           for(Integer i : outliers) {
               for (Set<Integer> c : clusters) {
                   // if a vertex I belongs a cluster c, we will remove it from a list outlier
                   if (c.contains(i)) {
                       listeTBD.add(i);
                      // break;
                   }
               }
           }
           outliers.removeAll(listeTBD);


               //After this, an outlier can be changed to Bridge vertex if he will have another connection
               // with a border of a cluster
               vertices_outliers.addAll(p1.getVerticeOutlier());
               vertices_outliers.addAll(p2.getVerticeOutlier());


               for (Vertex v : vertices_outliers) {
                   // get all neighbors of current outlier and push these into set variable
                   Set<Integer> nei = new HashSet<Integer>();
                   nei.addAll(v.getNeigbords());
                   int nb_classe = 0;
                   for (Set<Integer> c : clusters) {

                       if (getIntersection(nei, c).size() > 0) {
                           nb_classe += 1;
                       }
                       // we test if a cuurent outlier have more one connection with clusters
                       // and change it to a bridge vertex and remove it from outlier list
                      if (nb_classe > 1) {
                           bridges.add(v.getId());
                          // outliers.remove(v.getId());
                           break;
                       }
                   }
                   //remove conflicts with outliers list
                   outliers.removeAll(bridges);
               }

         //in this step we must push a commun bridge, if their not be core and border in other partition
               Set<Integer> all_bridges=new HashSet<Integer>();
               all_bridges.addAll(p1.getBridge_vertices());
               all_bridges.addAll(p2.getBridge_vertices());
               for(Integer i : all_bridges)
               {
                   if(!borders.contains(i))
                   {
                       bridges.add(i);
                       outliers.remove(i);
                   }
               }




          // }



        // end scenario : Outlier
        // print results
        // print all clusters

        System.out.println("*********Print list of clusters******");
        for(Set<Integer> c : clusters)
        {
          System.out.println("**********************");
            for(Integer i : c)
            {
                System.out.print(" "+i);
            }
         System.out.println(" \n ********************** \n");
        }

        System.out.println("*********Print list of bridges******");
        System.out.println("**********************");
        for(Integer br : bridges)
        {
            System.out.print(" "+br);

        }
        System.out.println("********************** \n");

        System.out.println("*********Print list of outliers******");
        System.out.println("**********************");
        for(Integer ot : outliers)
        {
            System.out.print(" "+ot);

        }
        System.out.println("**********************");

 /*   } catch(Exception e )
        {
System.out.println(e.getMessage());
        }*/

    }

    // function to get an intersection between two lists
    private static Set<Integer> getIntersection(Set<Integer> lst1, Set<Integer> lst2) {
        Set<Integer> rst = new HashSet<Integer>();
        if (lst1.size() > lst2.size()) {
            for (Integer i : lst2) {
                if (lst1.contains(i)) rst.add(i);
            }


        }
        else {
            for (Integer i : lst1) {
                if (lst2.contains(i)) rst.add(i);
            }

        }
        return rst;

    }


    public static void main(String argc[]) {

        Fraction p1 = new Fraction();
        Fraction p2 = new Fraction();
        Fraction p3=new Fraction();
        Fraction p4=new Fraction();
        Fraction g100 =new Fraction();

        g100.readGraph("g10000");
        g100.printGraph();
        g100.calculateSimilarity();
        g100.clustering(0.1,2);
        g100.printFractionDetails();

        // lead a sub graph for each fraction
      //  p1.readSubGraph("G111");
      //  p2.readSubGraph("G222");

       // p3.readPartitionOfGraph("data/sub_graph_11");
      //  p3.printGraph();
     //   p4.readPartitionOfGraph("data/sub_graph_22");
      //  p4.printGraph();


      //  p3.printGraph();
        // start a similarition operation for all fractions
       // p1.calculateSimilarity();
       // p2.calculateSimilarity();
     //  p3.calculateSimilarity();
        // start a local clustering for all fractions
       // p1.clustering(0.7, 3);
      //  p1.printFractionDetails();
       // p2.clustering(0.7,3);
      //  p2.printFractionDetails();
        /*
       p3.clustering(0.7,3);
       p4.clustering(0.7,3);
        System.out.println("first partition");
       p3.printFractionDetails();
        System.out.println("second partition");
       p4.printFractionDetails();
       System.out.println("global clustering results");
       combiner(p3,p4);*/
/*
        Graph g=new Graph();
        g.graphGenearte(10000,50,70);

        g.printGraph();
        System.out.println("Graph dencity is "+g.getDensity());
        g.writeGraph("g10000");
        */


    }
}

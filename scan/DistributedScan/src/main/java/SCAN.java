import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SCAN {

    public static void combiner(Fraction p1, Fraction p2)
    {
       List<Set<Integer>> clusters=new ArrayList<Set<Integer>>();
       Set<Integer> borders=new HashSet<Integer>();
       Set<Integer> bridges=new HashSet<Integer>();
       Set<Integer> outliers=new HashSet<Integer>();


       Set<Integer> shared_borders=getIntersection(p1.getBorder_vertices(),p2.getBorder_vertices());
       // we should verifier with a shared cores
        //+++++++++++++++++++++++++++++++++
       // if we don't any shared border we push two clusters to global list of clusters
       if(shared_borders.size()==0)
       {
           // add a local clusters to a global list of cluster
           clusters.addAll(p1.getClusters());
           clusters.addAll(p2.getClusters());

       }
       else
       {   // me should verifier this again
           clusters.addAll(p1.getClusters());
           clusters.addAll(p2.getClusters());
       }






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
        // Fraction p3=new Fraction();
        // lead a sub graph for each fraction
        p1.readSubGraph("G11");
        p2.readSubGraph("G22");
        //  p3.readSubGraph("dataset2");
        // start a similarition operation for all fractions
        p1.calculateSimilarity();
        p2.calculateSimilarity();
        //  p3.calculateSimilarity();
        // start a local clustering for all fractions
        p1.clustering(0.7, 3);
        p1.printFractionDetails();
        p2.clustering(0.7,3);
        p2.printFractionDetails();
        // p3.clustering(0.7,3);


    }
}

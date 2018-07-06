import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SCAN {

    public static void main(String argc[]) {

        Fraction p1 = new Fraction();
        Fraction p2 = new Fraction();
        // Fraction p3=new Fraction();
        // lead a sub graph for each fraction
        p1.readSubGraph("dataset2");
        //   p2.readSubGraph("G2");
        //  p3.readSubGraph("dataset2");
        // start a similarition operation for all fractions
        p1.calculateSimilarity();
        // p2.calculateSimilarity();
        //  p3.calculateSimilarity();
        // start a local clustering for all fractions
        p1.clustering(0.7, 3);
        p1.printFractionDetails();
        //  p2.clustering(0.7,3);
        //  p2.printFractionDetails();
        // p3.clustering(0.7,3);
    }
}

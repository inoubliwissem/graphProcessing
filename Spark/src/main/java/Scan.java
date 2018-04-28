
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Array;
import scala.Tuple1;
import scala.Tuple2;
import java.util.Iterator;

public class Scan {

    public static void main(String args[])
    {

        System.out.print("bonjour");

        JavaSparkContext sc = new JavaSparkContext("local","test");
        // read the dataset
        JavaRDD<String> lines = sc.textFile("dataset2");
         //get all edges from our dataset
        JavaPairRDD<Integer,Integer> edges=lines.mapToPair(new PairFunction<String, Integer, Integer>() {
            @Override
            public Tuple2<Integer, Integer> call(String s) throws Exception {
                String parts[]=s.split("\t");
                return new Tuple2<Integer, Integer>(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
            }
        });
     //   JavaRDD<Edge> edges2=lines.map(l->new Edge(l));
        // get all nodes
        JavaRDD<Node> node=lines.mapToPair(new PairFunction<String, Integer, Integer>() {
            @Override
            public Tuple2<Integer, Integer> call(String s) throws Exception {
                String parts[]=s.split("\t");
                return new Tuple2<Integer, Integer>(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
            }
        }).groupByKey().map(l->new Node(l._1,l._2));
         // get all id nodes
       JavaRDD<Integer> node_id=node.map(l->l.getId());


/*
        JavaPairRDD<Integer,Iterable<Integer>> neighbor=lines.mapToPair(new PairFunction<String, Integer, Integer>() {
        @Override
        public Tuple2<Integer, Integer> call(String s) throws Exception {
            String parts[]=s.split("\t");
            return new Tuple2<Integer, Integer>(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
        }
    }).groupByKey();
        JavaPairRDD<Integer,Iterable<Integer>> neighbor2=lines.mapToPair(new PairFunction<String, Integer, Integer>() {
            @Override
            public Tuple2<Integer, Integer> call(String s) throws Exception {
                String parts[]=s.split("\t");
                return new Tuple2<Integer, Integer>(Integer.parseInt(parts[1]),Integer.parseInt(parts[0]));
            }
        }).groupByKey();
        edges.foreach(new VoidFunction<Tuple2<Integer, Integer>>() {
            @Override
            public void call(Tuple2<Integer, Integer> integerIntegerTuple2) throws Exception {
                System.out.println("edges "+integerIntegerTuple2._1+","+integerIntegerTuple2._2);
            }
        });
        neighbor.foreach(new VoidFunction<Tuple2<Integer, Iterable<Integer>>>() {
            @Override
            public void call(Tuple2<Integer, Iterable<Integer>> integerIterableTuple2) throws Exception {
                System.out.println("Node :"+integerIterableTuple2._1);
                Iterator<Integer> it=integerIterableTuple2._2().iterator();
                System.out.print("[");
                while (it.hasNext())
                {
                    System.out.print(it.next()+",");
                }
                 System.out.print("]");
            }
        });
        neighbor2.foreach(new VoidFunction<Tuple2<Integer, Iterable<Integer>>>() {
            @Override
            public void call(Tuple2<Integer, Iterable<Integer>> integerIterableTuple2) throws Exception {
                System.out.println("2Node :"+integerIterableTuple2._1);
                Iterator<Integer> it=integerIterableTuple2._2().iterator();
                System.out.print("[");
                while (it.hasNext())
                {
                    System.out.print(it.next()+",");
                }
                System.out.print("]");
            }
        });
        JavaPairRDD<Integer,Iterable<Integer>> nei=neighbor.union(neighbor2);
        nei.foreach(new VoidFunction<Tuple2<Integer, Iterable<Integer>>>() {
            @Override
            public void call(Tuple2<Integer, Iterable<Integer>> integerIterableTuple2) throws Exception {
                System.out.println("3Node :"+integerIterableTuple2._1);
                Iterator<Integer> it=integerIterableTuple2._2().iterator();
                System.out.print("[");
                while (it.hasNext())
                {
                    System.out.print(it.next()+",");
                }
                System.out.print("]");
            }
        });
        */

       // JavaStreamingContext stream=new JavaStreamingContext(sc, Durations.seconds(1));

    }
}

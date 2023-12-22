package test3;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TEST {
    public static void main(String[] args) throws IOException {
        File file = new File("C://Users//Administrator//Documents//test3.txt");
        Scanner input = new Scanner(file);
        int V = input.nextInt();
        int E = input.nextInt();
        int[] VV = new int[V];
        int[] XX = new int[V];
        int[] YY = new int[V];
        int[] MM = new int[E];
        int[] NN = new int[E];
        for(int i=0;i<V;i++)
        {
            VV[i] = input.nextInt();
            XX[i] = input.nextInt();
            YY[i] = input.nextInt();
        }
        for(int i=0;i<E;i++)
        {
            MM[i] = input.nextInt();
            NN[i] = input.nextInt();
        }
        EuclideanGraph G = new EuclideanGraph(V,E,VV,XX,YY,MM,NN);
        int s = 1;
        int d = 500;
        Dijkstra dijkstra = new Dijkstra(G);
        Dijkstra1 dijkstra1 = new Dijkstra1(G);
        Dijkstra2 dijkstra2 = new Dijkstra2(G);
        Dijkstra3 dijkstra3 = new Dijkstra3(G);
        System.out.println(dijkstra.distance(s, d));

        long starttime,endtime,realtime;

        starttime =      System.currentTimeMillis();
        dijkstra.showPath(s, d);
        endtime =      System.currentTimeMillis();
        realtime = endtime - starttime;
        System.out.println("普通算法"+realtime+"ms");

        starttime =      System.currentTimeMillis();
        dijkstra1.showPath(s, d);
        endtime =      System.currentTimeMillis();
        realtime = endtime - starttime;
        System.out.println("改进1为"+realtime+"ms");

        starttime =      System.currentTimeMillis();
        dijkstra2.showPath(s, d);
        endtime =      System.currentTimeMillis();
        realtime = endtime - starttime;
        System.out.println("改进2为"+realtime+"ms");

        starttime =      System.currentTimeMillis();
        dijkstra3.showPath(s, d);
        endtime =      System.currentTimeMillis();
        realtime = endtime - starttime;
        System.out.println("改进3为"+realtime+"ms");


    }

}
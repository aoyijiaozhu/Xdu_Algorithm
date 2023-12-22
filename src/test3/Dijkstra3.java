package test3;

import java.awt.Color;

import edu.princeton.cs.algs4.IndexMultiwayMinPQ;

public class Dijkstra3
{
    private static double INFINITY = Double.MAX_VALUE;
    private static double EPSILON = 0.000001;
    private EuclideanGraph G;
    private double[] dist;
    private int[] pred;


    public Dijkstra3(EuclideanGraph G)
    {
        this.G = G;

    }

    // return shortest path distance from s to d
    public double distance(int s, int d)
    {
        dijkstra(s, d);
        return dist[d];
    }

    // print shortest path from s to d  (interchange s and d to print in right order)
    public void showPath(int d, int s)
    {
        dijkstra(s, d);
        if (pred[d] == -1)
        {
            System.out.println(d + " is unreachable from " + s);
            return;
        }
        for (int v = d; v != s; v = pred[v])
            System.out.print(v + "-");
        System.out.println(s);
    }

    // plot shortest path from s to d
    public void drawPath(int s, int d)
    {
        dijkstra(s, d);
        if (pred[d] == -1) return;
        Turtle.setColor(Color.red);
        for (int v = d; v != s; v = pred[v])
            G.point(v).drawTo(G.point(pred[v]));
        Turtle.render();
    }

    // Dijkstra's algorithm to find shortest path from s to d
    public void dijkstra(int s, int d)
    {
        int V = G.V();
        // initialize
        dist = new double[V];
        pred = new int[V];
        for (int v = 0; v < V; v++) dist[v] = INFINITY;
        for (int v = 0; v < V; v++) pred[v] = -1;
        // priority queue
        /*
        * improve3，采用多路堆来代替单路堆（代替indexPQ）
        * */
        IndexMultiwayMinPQ pq = new IndexMultiwayMinPQ(V, 3);
        for (int v = 0; v < V; v++) pq.insert(v, dist[v]);
        // set distance of source
        dist[s] = 0.0;
        pred[s] = s;
        pq.changeKey(s, dist[s]);
        // run Dijkstra's algorithm
        while (!pq.isEmpty())
        {
            int v = pq.delMin();
            if (v == d)
            {
                break;
            }
            // v not reachable from s so stop
            if (pred[v] == -1) break;
            // scan through all nodes w adjacent to v
            IntIterator i = G.neighbors(v);
            while (i.hasNext())
            {
                int w = i.next();
                if (dist[v] - G.distance(v, d) + G.distance(v, w) + G.distance(w, d) < dist[w] - EPSILON)
                {
                    dist[w] = dist[v] - G.distance(v, d) + G.distance(v, w) + G.distance(w, d);
                    pq.changeKey(w, dist[w]);
                    pred[w] = v;
                }
            }
        }
    }
}
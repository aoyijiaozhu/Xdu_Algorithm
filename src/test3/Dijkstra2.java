package test3;

import java.awt.Color;


public class Dijkstra2
{
    private static double INFINITY = Double.MAX_VALUE;
    private static double EPSILON = 0.000001;
    private EuclideanGraph G;
    private double[] dist;
    private int[] pred;

    public Dijkstra2(EuclideanGraph G)
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
    private void dijkstra(int s, int d)
    {
        int V = G.V();
        // initialize
        dist = new double[V];
        pred = new int[V];
        for (int v = 0; v < V; v++) dist[v] = INFINITY;
        for (int v = 0; v < V; v++) pred[v] = -1;
        // priority queue
        IndexPQ pq = new IndexPQ(V);
        for (int v = 0; v < V; v++) pq.insert(v, dist[v]);
        // set distance of source
        dist[s] = 0.0;
        pred[s] = s;
        pq.change(s, dist[s]);
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

                /*
                improve2，计算了从顶点v到顶点w的距离，然后加上了从顶点v到目标点d和从顶点w到目标点d的距离，
                然后与当前已知的到达w的距离进行比较。每次更新的时候都相当于进行了一次三角形判断
                * */
                if (dist[v] - G.distance(v, d) + G.distance(v, w) + G.distance(w, d) < dist[w] - EPSILON)
                {
                    dist[w] = dist[v] - G.distance(v, d) + G.distance(v, w) + G.distance(w, d);

                    pq.change(w, dist[w]);
                    pred[w] = v;
                }
            }
        }
    }
}
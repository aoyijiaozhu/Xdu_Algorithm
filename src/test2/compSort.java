package test2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class compSort
{
    public static void main(String []args)
    {
        String alg1 = "InsertionSort";
        String alg2 = "TopdownMergesort";
        String alg3 = "BottomupMergesort";
        String alg4 = "RandomQuicksort";
        String alg5 = "QuicksortwithDijkstra3wayPartition";
        int N = 10000;
        int T = 10;
        double t1 = timeRandomInput(alg1,N,T);
        double t2 = timeRandomInput(alg2,N,T);
        double t3 = timeRandomInput(alg3,N,T);
        double t4 = timeRandomInput(alg4,N,T);
        double t5 = timeRandomInput(alg5,N,T);
        System.out.println(alg1+" 总花费时间 "+t1);
        System.out.println(alg2+" 总花费时间 "+t2);
        System.out.println(alg3+" 总花费时间 "+t3);
        System.out.println(alg4+" 总花费时间 "+t4);
        System.out.println(alg5+" 总花费时间 "+t5);
    }
    public static double time(String alg, Comparable[] a)
    {
        Stopwatch timer = new Stopwatch();
        if(alg.equals("InsertionSort"))
            InsertionSort.sort(a);
        if(alg.equals("TopdownMergesort"))
            TopdownMergesort.sort(a);
        if(alg.equals("BottomupMergesort"))
            BottomupMergesort.sort(a);
        if(alg.equals("RandomQuicksort"))
            RandomQuicksort.sort(a);
        if(alg.equals("QuicksortwithDijkstra3wayPartition"))
            QuicksortwithDijkstra3wayPartition.sort(a);
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg, int N, int T) //N为数组长度，T为实验次数，返回的是总时间
    {
        double total = 0.0;
        double temp = 0.0;
        Double[] a = new Double[N];
        System.out.print(alg+": ");
        for(int t = 0; t < T; t++)
        {
            for(int i = 0; i < N; i++)
            {
                a[i] = StdRandom.uniform();
            }
            temp = time(alg,a);
            System.out.print("第"+(t+1)+"次："+temp+" ");
            total += temp;
        }
        System.out.println("平均时间："+total/T+" ");
        return total;
    }
    /**
     *    1. Which sort worked best on data in constant or increasing order (i.e., already sorted data)? Why do you think this sort worked best?
     *       插入排序，因为此时，插入排序每次只要比较一次就能确定每一个元素的位置
     *
     *    2. Did the same sort do well on the case of mostly sorted data? Why or why not?
     *       不会的，因为初始数据基本有序的情况对各种排序的影响很大，比如快排的时候，初始有序的时候就变成N2的算法
     *
     *    3. In general, did the ordering of the incoming data affect the performance of the sorting algorithms?
     *    Please answer this question by referencing specific data from your table to support your answer.
     *       影响的，初始状况对于每一种排序的影响是巨大的，比如我使用均匀分布的十个数据，但是总数据量有10000的时候，
     *       三向切分需要6ms，而快排此时就需要9ms，原因是快排处理大量重复数据的时候是非常吃力的
     *
     *    4. Which sort did best on the shorter (i.e., n = 1,000) data sets? Did the same one do better on the longer
     *    (i.e., n = 10,000) data sets? Why or why not? Please use specific data from your table to support your answer.
     *       1000的时候RQ在我的算法中表现的最好，10000的时候QD3P。
     *       比如：总数据量有10000的时候，三向切分需要6ms，而快排此时就需要9ms
     *
     *    5. In general, which sort did better? Give a hypothesis as to why the difference in performance exists.
     *       QD3P，此时他的空间复杂度是lgN，而且也能处理大量重复数据的情况， 时间复杂度介于NlgN与N2之间，综合来看比RQ胜任更多的情况
     *
     *    6. Are there results in your table that seem to be inconsistent?
     *    (e.g., If I get run times for a sort that look like this {1.3, 1.5, 1.6, 7.0, 1.2, 1.6, 1.4, 1.8, 2.0, 1.5]
     *    the 7.0 entry is not consistent with the rest). Why do you think this happened?
     *       对于数据突然出现的偶然性情况，我认为应该是初始化随机数的时候造成了一些偶然情况的发生，使得初始的排序出现了某种比较差的情况
     *
     *
     *
     */
}

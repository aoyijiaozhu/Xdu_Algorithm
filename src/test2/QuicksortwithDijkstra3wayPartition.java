package test2;
//将数组划分为三部分，分别是小于划分元素的部分，等于划分元素的部分和大于划分元素的部分。适用于大量重复元素序列
import edu.princeton.cs.algs4.StdRandom;

public class QuicksortwithDijkstra3wayPartition
{
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a,0,a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if(hi <= lo)
            return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt)
        {
            int cmp = a[i].compareTo(v);
            if(cmp < 0)
                exch(a,lt++,i++);
            else if(cmp > 0)
                exch(a,i,gt--);
            else
                i++;
        }
        sort(a,lo,lt - 1);
        sort(a, gt + 1, hi);
    }
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

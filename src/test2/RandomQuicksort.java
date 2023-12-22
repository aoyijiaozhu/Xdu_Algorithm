package test2;
//选择一个划分元素，然后将数组划分为两部分，使得左边的元素都小于等于划分元素，右边的元素都大于等于划分元素，
//然后对这两部分分别进行排序.初始化随机是为了避免接近有序数列的最坏情况
import edu.princeton.cs.algs4.StdRandom;

public class RandomQuicksort
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
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi + 1;
        Comparable V = a[lo];
        while (true)
        {
            while (less(a[++i],V))
            {
                if(i == hi)
                    break;
            }
            while (less(V,a[--j]))
            {
                if(j == lo)
                    break;
            }
            if(i >= j)
                break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
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

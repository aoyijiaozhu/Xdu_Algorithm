package test2;
//未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
public class InsertionSort
{
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for(int i = 1; i < N; i++)
        {
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--)
            {
                exch(a,j,j-1);
            }
        }
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

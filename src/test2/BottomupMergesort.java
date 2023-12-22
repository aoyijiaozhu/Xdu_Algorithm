package test2;
//循环。它首先将数组中元素一个个归并成两两有序的序列，两两有序的序列归并成四个四个有序的序列，以此类推，直至整个序列有序
public class BottomupMergesort
{
    private static Comparable[] aux;
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];
        for(int sz = 1; sz < N; sz = sz + sz)
        {
            for(int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
        }
    }
    public static void merge(Comparable[] a, int lo, int mid, int hi)
    {	
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for(int k = lo; k <= hi; k++)
        {
            if(i > mid)
                a[k] = aux[j++];
            else if(j > hi)
                a[k] = aux[i++];
            else if(less(aux[j],aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w) 	//返回V是否小于W
    {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j)		//交换
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

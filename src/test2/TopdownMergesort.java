package test2;
//递归。它首先将数组不断二分，直到子数组元素只有一个，然后将两个有序的数组向下合并，再将新的两个有序的数组向下合并，直至整个数组有序
public class TopdownMergesort
{
    private static Comparable[] aux;
    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a,0,a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if(hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
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

package test;

import test.Percolation;

import java.math.BigInteger;

public class test
{
    public static void main(String []args)
    {
        int len = 100;
        int times = 1000;
        int nums[] = new int[times];
        int n;
        int all = 0;
        for(int i = 0; i < times; i++)
        {
            n = myTest(len);
            all += n;
            nums[i] = n;
        }
        for(int i = 0; i < times; i++)
        {
            System.out.print(nums[i] + " ");
            if( i % 5 == 4)
                System.out.println();
        }
        double percent = all * 1.0 / times / (len * len);
        System.out.println("一共进行了 " + times + " 次实验，边长为 " +  len+" , 当发生渗透时网格所占的比例平均为 " + percent+ "  ");
    }
    public static int myTest(int len)
    {
        Percolation percolation = new Percolation(len);
        boolean isPercloate = percolation.percolates();
        while (!isPercloate)
        {
            percolation.randomOpen();
            isPercloate = percolation.percolates();

        }
        return percolation.count;
    }

}

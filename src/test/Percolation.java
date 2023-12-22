package test;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Percolation
{
    int []grid;   //N*N网格
    int []dep;    //深度
    int []block;  //0阻塞，1开放
    int len;      //网格边长
    int all;      //总格子数
    int count = 0;//已经打开的格子数
    boolean head = false;//虚拟头结点
    boolean tail = false;//虚拟尾结点
    public Percolation(int N)           // 创建一个N*N网络，初始化所有都是阻塞的
    {
        len = N;
        all = len * len;
        grid = new int[all + 2];
        for(int i = 0; i < all + 2; i++)
            grid[i] = i;
        dep = new int[all + 2];
        for(int i = 0; i < all + 2; i++)
            dep[i] = 1;
        block = new int[all + 2];
    }
    public int gridToIndex(int i, int j)	//把二维坐标转成一纬索引
    {
        return (i-1) * len + (j-1);
    }
    public void open(int i, int j)         // 打开（i，j）的格子
    {
        block[(i-1) * len + (j-1)] = 1;
    }
    public boolean isOpen(int i, int j)    // 判断是否开放
    {
        return block[(i-1) * len + (j - 1)] == 1;
    }
    public int find(int p)   //找根节点
    {
        while (p != grid[p])
            p = grid[p];
        return p;
    }
    public void union(int p, int q)	//合并P和Q的集合
    {
        int i = find(p);
        int j = find(q);
        if(i == j)
            return;
        if(dep[i] < dep[j])
        {
            grid[i] = j;
            dep[j] += dep[i];
        }
        else
        {
            grid[j] = i;
            dep[i] += dep[j];
        }
    }
    public boolean connected(int p, int q)	//判断P,Q是否在同一集合
    {
        return find(p) == find(q);
    }
    public boolean percolates()          // 判断是否发生渗透
    {
        return connected(all,all+1);
    }
    public void randomOpen()  //随机打开一个网格，并且union
    {
        Random random = new Random(new Date().getTime());
        int i = random.nextInt(len) + 1;
        int k = random.nextInt(len) + 1;
        while (block[gridToIndex(i,k)] == 1)
        {
            i = random.nextInt(len) + 1;
            k = random.nextInt(len) + 1;
        }
        if(!head && i == 1)
        {
            head = true;
            for(int p = 0; p < len; p++)
            {
                if(p == len - 1)
                    union(p, all);
                else
                    union(p,p + 1);
            }
        }
        if(!tail && i == len)
        {
            tail = true;
            for(int p = gridToIndex(len,1); p < all; p++)
            {
                if(p == all - 1)
                    union(p, all + 1);
                else
                    union(p,p + 1);
            }
        }
        open(i,k);
        if(i == 1)        //如果是第一行
        {
            eazyUnionLeftAndRight(i,k);
            eazyUnion(i,k,i+1,k);
        }
        else if(i == len)   //如果是最后一行
        {
            eazyUnionLeftAndRight(i,k);
            eazyUnion(i,k,i-1,k);
        }
        else  //中间行
        {
            eazyUnionLeftAndRight(i,k);
            eazyUnion(i,k,i-1,k);
            eazyUnion(i,k,i+1,k);
        }
        count++;
    }
    public void eazyUnion(int i, int j,int k, int p) //第一个参数是本点，第二个传入可能被union的格子，如果（K,P）打开了，则合并
    {
        if(isOpen(k,p))	
            union(gridToIndex(i,j),gridToIndex(k,p));
    }
    public void eazyUnionLeftAndRight(int i, int k)   //自动判断(i,k)是否在一行的最左或最右，并连接
    {
        if(k == 1)  //如果是第一列
        {
            eazyUnion(i,k,i,k+1);
        }
        else if(k == len) //如果是最后一列
        {
            eazyUnion(i,k,i,k-1);
        }
        else
        {
            eazyUnion(i,k,i,k-1);
            eazyUnion(i,k,i,k+1);
        }
    }
}

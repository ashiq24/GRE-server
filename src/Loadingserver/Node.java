package Loadingserver;

import java.util.Comparator;

/**
 * Created by Ashiq on 5/23/2016.
 */
public class Node implements Comparator<Node>,Comparable<Node>
{
    public String word;
    public  String mean;
    public String senten;
    public Node(String w,String m,String s)
    {
        word=w;
        mean=m;
        senten=s;
    }

    @Override
    public int compare(Node o1, Node o2) {
        return o1.word.compareTo(o2.word);
    }

    @Override
    public int compareTo(Node o) {
        return word.compareTo(o.word);
    }
    public boolean equals(Object n)
    {
        Node m=(Node)n;
        return word.equals((m.word));
    }
    public int hashCode()
    {
        return word.hashCode();
    }
}


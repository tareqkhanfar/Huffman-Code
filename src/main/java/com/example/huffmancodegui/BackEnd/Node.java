package com.example.huffmancodegui.BackEnd;


public class Node implements Comparable<Node>{
    Node left , right ;
    int freq ;
    byte value = -1 ;


    public Node(){

    }

    @Override
    public String toString() {
        return "Node{" +
                "left=" + left.freq +
                ", right=" + right.freq +
                ", freq=" + freq +
                '}';
    }
    public String toString_2 () {
        return  "Value :" +value +"   Freq : " +freq ;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.freq,o.freq);
    }
}

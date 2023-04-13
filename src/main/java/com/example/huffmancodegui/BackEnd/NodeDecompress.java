
package com.example.huffmancodegui.BackEnd;

public class NodeDecompress {
    String value;
    NodeDecompress left;
    NodeDecompress right;

    public NodeDecompress() {

    }
    public NodeDecompress(String value, NodeDecompress left, NodeDecompress right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

package com.example.huffmancodegui.BackEnd.Header;

import com.example.huffmancodegui.Controllers.DecompressController;

import java.io.IOException;
import java.util.*;

public class MainHeader {
    private static Node root;
    public static Map<Byte, String > map1;
    public static byte[] generateHeader(byte []data) throws IOException {
       Map<Byte, Integer > map = createFrequencyTable(data);
       System.out.println(map);
         Node huffmanTree = buildTree(map);
         map1 = new HashMap<>();
          buildTable (map1 , huffmanTree , "");
     //   System.out.println("Map 1 : "+map1);
        List<Byte> header = new ArrayList<>();
        postOrder(huffmanTree, header);
        System.out.println(header);


      ConvertHeader convertHeader = new ConvertHeader() ;
     return convertHeader.writeHeader(header);

       // Node huffmanTreeRebuild =  rebuildTree (header);
      //  printTree(huffmanTreeRebuild);
      //  Map<Byte, String > map2 = new HashMap<>();
      //  buildTable (map2 , huffmanTreeRebuild , "");
      //  System.out.println(map2);



    }



   /* public static void main(String[] args) throws IOException {
        List<Byte> s = generateHeader();

        Object[] arr = s.toArray();
        byte[]arr2 = new byte[arr.length] ;
        for (int i = 0 ; i <arr2.length ; i++) {
            arr2[i] = (byte)arr[i];
        }
      DecodeHeader(arr2) ;


    }

    */




    public static HashMap<String , Byte> DecodeHeader(String  header , byte x) throws IOException {
        ConvertHeader convertHeader = new ConvertHeader();
        List<Byte> list = convertHeader.Read(header , x);
        Node huffmanTreeRebuild =  rebuildTree (list);
        printTree(huffmanTreeRebuild);
        HashMap <String, Byte > map2 = new HashMap<>();

        buildTableDecode (map2 , huffmanTreeRebuild , "");
        StringBuilder builder = new StringBuilder() ;
        for (Map.Entry<String, Byte> set : map2.entrySet()) {
           builder.append(set.getKey() +"    " + set.getValue()+"\n");

        }
        DecompressController.huffmanTable = builder.toString();

        //   System.out.println("Map 2 "+map2);
        return map2 ;
    }
    private static void buildTableDecode(HashMap<String, Byte> table, Node node, String s) {
        if (node.isLeaf()) {
            table.put(s , node.value);
            return;
        }
        buildTableDecode(table, node.left, s + '0');
        buildTableDecode(table, node.right, s + '1');
    }

    private static void postOrder(Node node, List<Byte> header) {
        if (node.left == null && node.right == null) {
            header.add((byte) 1);
            header.add(node.value);
        } else {
            postOrder(node.left, header);
            postOrder(node.right, header);
            header.add((byte) 0);
        }
    }
    public static void printTree(Node root) {
        if (root != null ) {
            printTree(root.left);
            printTree(root.right);
            System.out.print(root.value);
        }
    }

    public static Node rebuildTree(List<Byte> header) {
        Deque<Node> stack = new LinkedList<>();

        for (int i = 0 ; i < header.size() ; i++){
            if (header.get(i)==1) {
                byte symbol =  header.get(i+1);
                //int frequency = (Integer) header.get(header.indexOf(element) + 1);
                Node node = new Node(symbol, -1, null, null);
                stack.push(node);
                i++;
            } else {

                Node right = stack.pop();
                Node left = stack.pop();
                Node node = new Node((byte) 0, 0, left, right);
                stack.push(node);
            }
        }
        return stack.pop() ;
    }

    public static Node buildTree(Map<Byte, Integer> freq) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : freq.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue(), null, null);
            queue.offer(node);

        }
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            int sum = left.freq + right.freq;
            Node parent = new Node((byte) '\0', sum, left, right);
            queue.offer(parent);
        }
        return queue.poll();
    }
    public static Map<Byte, Integer> createFrequencyTable(byte[] data) {
        Map<Byte, Integer> frequencyTable = new HashMap<>();
        for (byte c : data) {
            if (frequencyTable.containsKey(c)) {
                frequencyTable.put(c, frequencyTable.get(c) + 1);
            } else {
                frequencyTable.put(c, 1);
            }
        }
        return frequencyTable;
    }
    private static void buildTable(Map<Byte, String> table, Node node, String s) {
        if (node.isLeaf()) {
            table.put(node.value , s);
            return;
        }
        buildTable(table, node.left, s + '0');
        buildTable(table, node.right, s + '1');
    }

}



class Node implements Comparable<Node>{
    public byte value;
    public int freq;
    public Node left;
    public Node right;

    public Node(byte symbol, int frequency, Node left, Node right) {
        this.value = symbol;
        this.freq = frequency;
        this.left = left;
        this.right = right;
    }
    boolean isLeaf() {
        return left == null && right == null;
    }
    @Override
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}

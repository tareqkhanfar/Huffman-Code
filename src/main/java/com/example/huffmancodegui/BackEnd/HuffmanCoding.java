package com.example.huffmancodegui.BackEnd;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class HuffmanCoding {
	private  final int NUMBER_OF_VALUES = 256 ;
	private   byte header[] = new byte[99999999];
	 int k = 0;
	public  void CreateHuffmanCoding(Node root , HufmanTable[]Huffmancode , String code   ) {
		if (root != null) {
			if (root.left == null && root.right == null) {
				Huffmancode[k++] = new HufmanTable(root.value, code, root.freq, (byte) code.length());
				return;
			}
			CreateHuffmanCoding(root.left, Huffmancode, code + "0");
			CreateHuffmanCoding(root.right, Huffmancode, code + "1");
		}

	}
	private   int index =0 , nextByte = 0;
	private  int COUNTER = 0 ;

	public static StringBuilder lastByteInHeader = new StringBuilder();
public  byte[] preorder(Node root) {
		if (root != null) {
			if (index == 8) {
				nextByte++ ;
				COUNTER++ ;
				index = 0;
				lastByteInHeader.append(" ");
			}
			if (isParent(root)) {
				lastByteInHeader.append("0");
				//header[nextByte] = (byte) (header[nextByte] << 1);
				index++;
			}
			else {
				index++;
				lastByteInHeader.append("1");
			//	header[nextByte] = (byte) ((header[nextByte] << 1) | 1);
				String s2 = String.format("%8s", Integer.toBinaryString(root.value & 0xFF)).replace(' ', '0');
				for (int i = 0 ; i < 8 ; i++) {
					if (index == 8) {
						nextByte++ ;
						COUNTER++ ;
						index = 0;
						lastByteInHeader.append(" ");
					}
					index++;
					lastByteInHeader.append(s2.charAt(i));

				}

				//Take8Bit(root.value);
			}
			if (index == 8) {
				nextByte++ ;
				COUNTER++ ;
				index = 0;

				lastByteInHeader.append(" ");

			}
			preorder(root.left);
			preorder(root.right);
		}
		return header ;
}

public StringBuilder stringBuilder ;
	private  void Take8Bit(byte value) {
	int tempByte = nextByte ;
	byte numberOfBits = 0;
		boolean one = false ;
		if (index == 8) {
			index = 0 ;
			nextByte++ ;
			COUNTER ++;
			lastByteInHeader.append(" ");

		}
		for (byte i = 0 ; i < 8 ; i++) {

			if (tempByte == nextByte){
				 one = checkBitForValue(value , (byte)(8-i-1))==0 ;
			}
			else {
				one = checkBitForValue(value , (byte)(8-numberOfBits-1))==0 ;
			}

			if (one) {
				lastByteInHeader.append("0");
				header[nextByte] = (byte) (header[nextByte] << 1);
				numberOfBits++;
			}
			else {
				lastByteInHeader.append("1");
				header[nextByte] = (byte) ((header[nextByte] << 1) | 1);
				numberOfBits++;
			}
			index++;
			if (index == 8) {
				index = 0 ;
				nextByte++ ;
				COUNTER ++;
				lastByteInHeader.append(" ");
			}
		}
	}

	private   byte checkBitForValue(byte value , byte index) {
		byte  z = (byte)(1<< (index % 8));
		return (byte)(value & z) ;

	}

	private  boolean isParent(Node root) {
		if (root.left != null && root.right != null) {
			return true ;
		}
		return false ;
	}

	public void printNode(Node root) {
		if (root != null){
			System.out.println("BEFORE : " +root.value);
			printNode(root.left);
			printNode(root.right);
		}
	}
	public  Node HuffmanTreeCoding(int []freq , byte []value ) throws IOException {

		for (int i = 0 ; i < value.length ; i++) {
			System.out.print(value[i]+"  ");
		}
		System.out.println();
		for (int i = 0 ; i < freq.length ; i++) {
			System.out.print(freq[i]+"  ");
		}
		PriorityQueue<Node> heap = new PriorityQueue<>() ;
		int k = 0 ;
		for (int i = 0 ; i < freq.length ; i++) {
			if (freq[i]  > 0) {
				Node temp = new Node();
				temp.value = value[i] ;
				temp.freq = freq[i] ;
				temp.left = null ;
				temp.right = null ;
				heap.add(temp);
				k++ ;
			}
		}
		Node root = null ;
		for (int i = 0 ; i < k -1; i++) {
			Node z = new Node() ;
			Node x = heap.poll();
			Node y = heap.poll() ;
			z.freq = x.freq + y.freq ;
			z.left = x;
			z.right= y ;
			root = z ;
			heap.add(z);
		}
		root = heap.poll();
       return  root ;

	}

	public int getCOUNTER() {
	return COUNTER ;
	}
}

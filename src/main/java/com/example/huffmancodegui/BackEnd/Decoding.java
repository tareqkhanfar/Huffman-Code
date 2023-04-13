package com.example.huffmancodegui.BackEnd;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Decoding {
    byte b = 0;
    String FileName = "" ;


  private   int startData =0  , endData = 0 ;

public int EndDataInBufferArray = 0 ;

    public byte[] GenerateDataForDecode(  String  Last,  byte[] data, HashMap<String, Byte> huffmanTable) throws FileNotFoundException {
        //System.out.println("Data : " + data.toString());
        EndDataInBufferArray = 0 ;
        byte []result = new byte[999999999];
        int before = 0 ;
        long EndData = 0 ;
    byte x[] = new byte[1];
        if (data.length == 0 ) {
            if (Last.length() != 0) {
                x[0]=Encoding.readFirst8Byte(Last);
                data = x ;
                EndData = Last.length();
            }
            else {
                EndData = 0 ;
            }
        }
        else {
            if (Last.length() != 0) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(data.length +1);
                byteBuffer.put(data);
                x[0]=Encoding.readFirst8Byte(Last);
                byteBuffer.put(x);
                data = byteBuffer.array();
                EndData = (data.length -1 )*8 ;
                EndData += Last.length() ;
            }
            else {
                EndData = (data.length )*8 ;

            }

        }


        System.out.println("Start Index Data : " + 0);
        System.out.println("Length  Data : " + data.length);
        System.out.println("End  Data : " + EndData);
        System.out.println("Size of Last  Data : " + Last.length());


        for (int i = 0 ; i < EndData ; i++) {
           // System.out.println("Value OF I : " + before  +"    Sub Array : "+(i+1));

            if (huffmanTable.get(subArrayInBits(data , before , i+1)) != null){

                result[EndDataInBufferArray] =huffmanTable.get(subArrayInBits(data , before , i+1));
                EndDataInBufferArray++;

                before = i+1 ;

            }
        }
        System.out.println("ALL BYTES SIZE : " + EndDataInBufferArray);
        data = null ;
        return  result ;
    }

    private String subArrayInBits( byte[]data, int before, int end) {
        String str = "";
        for (int i = before; i < end; i++) {
            if (checkBitForValue(data[(i/8)], (byte) (7-(i % 8)))==0) {
                        str+="0";
            }
            else {
                str+="1";
        }
    }

        return  str ;
    }


    private int index = 0, nextByte = 30;
    private int COUNTER = 0;


    private byte checkBitForValue(byte value, byte index) {
        byte z = (byte) (1 << (index % 8));
        byte x = (byte) (value & z);
        return x;

    }


}



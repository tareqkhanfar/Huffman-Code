package com.example.huffmancodegui.BackEnd;



import com.example.huffmancodegui.BackEnd.Header.MainHeader;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Encoding {


    public int sizeByteBuffer = 0 ;
   public String lastDataByte  ;

    public byte[] returnData (byte data[] , HufmanTable[] hufmanTable ) throws FileNotFoundException {
        index = 0 ;
        COUNTER = 0 ;
        nextByte = 0;
        byte result[] = new byte[999999999];
        for (int i = 0 ; i < data.length ; i++) {
           Map<Byte , String> map1 =  MainHeader.map1;
         String code =  map1.get(data[i]);
           // HufmanTable huffmanObject=searchHuffmanCodeByValue(data[i] , hufmanTable);
            if (code != null) {
                for (int m = 0 ; m < code.length() ;m++) {
                        if (code.charAt(m) == '0') {
                            result[nextByte] = (byte) (result[nextByte] << 1);
                        } else {
                            result[nextByte] = (byte) ((result[nextByte] << 1) | 1);
                        }
                        index++;
                    if (index == 8) {
                        index = 0  ;
                        nextByte++ ;
                        COUNTER++;
                    }
                }
            }

        }
        String sss= "" ;
        for (byte i = 0 ; i < index ; i++) {
            if (checkBitForValue(result[nextByte] , i)==0){
                sss+="0";
            }
            else {
                sss+="1";

            }
        }
        System.out.println("DATA IN BYTE BUFFER IS : " + sss);


        lastDataByte = new StringBuilder(sss).reverse().toString();
        System.out.println("LAST BYTE : " + lastDataByte.length());

        return  result ;
    }
    private byte checkBitForValue(byte value, byte index) {
        byte z = (byte) (1 << (index));
        byte x = (byte) (value & z);
        return x;

    }

    private  int index =0 , nextByte = 0;
    public   int COUNTER = 0 ;







    public static byte  readFirst8Byte(String ss) throws FileNotFoundException {
        //  System.out.println("Length : " + ss.length());
        byte length = (byte) ss.length();
        for (int i = 0 ; i < 8- length; i++) {
               ss+="0" ;
        }
        byte x = 0 ;
        for (int i = 0 ; i < 8 ; i++) {
            if (ss.charAt(i)=='0'){
                x = (byte) (x << 1);

            }
            else {
                x = (byte) ((x << 1) | 1);
            }
        }
        return x;

    }
}

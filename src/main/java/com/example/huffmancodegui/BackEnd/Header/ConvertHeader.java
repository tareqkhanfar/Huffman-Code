package com.example.huffmancodegui.BackEnd.Header;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConvertHeader {
public static byte x = 0 ;

    public byte[] writeHeader(List<Byte> header) throws IOException {
        System.out.println("\nLENGTH HEADER LIST : " +  header.size());

        byte data[] = new byte[6999];
        int nextByte = 0 ;
        for (int i = 0 ; i < header.size() ; i++) {
            if (header.get(i)==1){
                data[nextByte  / 8] = (byte) ((data[nextByte / 8] << 1)| 1);
                nextByte++;
                i++;
                String ss = String.format("%8s", Integer.toBinaryString(header.get(i) & 0xFF)).replace(' ', '0');
              //  System.out.println("SS : " +ss);
                for (int j = 0 ; j <ss.length() ; j++ ){
                  if (ss.charAt(j)=='1'){
                      data[nextByte/ 8] = (byte) ((data[nextByte / 8] << 1)| 1);
                  }
                  else {
                      data[nextByte/ 8] = (byte) ((data[nextByte / 8] << 1));

                  }
                  nextByte++;
                }

            }
            else {
                data[nextByte/8] = (byte) ((data[nextByte / 8] << 1));
                nextByte++;
            }
        }

        data[(nextByte+8)/8] = (byte)(nextByte % 8);
        //x = (byte)(nextByte % 8); ;
        System.out.println("NextByte : " + nextByte/8);
        System.out.println("RemaingValue : " + nextByte % 8);
        String Res = "" ;


        List<Byte> list = new LinkedList<>() ;
        byte arr[] = new byte[(nextByte/8 )+2];

        for (int i = 0 ; i  < (nextByte/8 )+2 ; i++) {
            arr[i] = data[i];
            list.add(data[i]);
        }
        for (int i =0 ; i < (nextByte/8)+2 ; i++) {
             Res+=  String.format("%8s", Integer.toBinaryString(data[i] & 0xFF)).replace(' ', '0');
            //System.out.print(ss+"  ");
        }

return  arr ;
        // stop

    }
    public List<Byte> Read ( String data , byte xx) throws IOException {
        data = data.substring(0 , data.length() - 8);
        //  System.out.println(Arrays.toString(data));
        //String s = new String(data , 0 , (data.length)-2);
       // System.out.println("Size : " + data.length());
        String ss = "" ;
        ss = data;

       // System.out.println("\n Header After Reading : "+ss);
        byte x = xx ;
      //  System.out.println("Number of use bits in last byte : " + x);
        String s2 = "" ;
        s2 = ss.substring(ss.length() - 8 , ss.length());
     //   System.out.println("SS2 : " +s2);
        s2 = s2.substring(8-x , s2.length());
     //   System.out.println("SS2 : " +s2);





       // System.out.println("Result : " + ss.substring(0 , ss.length() - 8)+s2);
        String res = ss.substring(0 , ss.length() - 8) ;
        res += s2 ;
        List<Byte> list = new LinkedList<>();
        for (int i = 0 ; i < res.length() ; i++) {
            if (res.charAt(i)=='1'){
                byte x2 = 0 ;
                String cc = res.substring(i+1 , i+9);
                i+=8;
                for (int j = 0 ; j <cc.length() ; j++) {
                    if (cc.charAt(j)=='1'){
                        x2 = (byte) ((x2 << 1)| 1);
                    }
                    else {
                        x2 = (byte) ((x2 << 1));
                    }

                }
                System.out.println(x2);
                list.add((byte) 1);
                list.add( x2);
            }
            else {
                list.add((byte) 0);
            }
        }
        System.out.println("End");


       // System.out.println(list.toString());

        return list ;
    }
}

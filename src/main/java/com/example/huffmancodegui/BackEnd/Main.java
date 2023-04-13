package com.example.huffmancodegui.BackEnd;


import com.example.huffmancodegui.BackEnd.Header.ConvertHeader;
import com.example.huffmancodegui.Controllers.CompressController;
import com.example.huffmancodegui.Controllers.DecompressController;
import com.example.huffmancodegui.BackEnd.Header.MainHeader;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final int NUMBER_OF_VALUES = 256 ;


    public static void Decode() throws IOException {
        byte[] DATA = Files.readAllBytes(Paths.get(DecompressController.PathCompressedFile));
        String str=new String(DATA);
        System.out.println("Length comprasion : " + DATA.length +"   " + str.length());
        // System.out.println(str);
        String LastData = "" ;
        for (int i = 0 ; i < DATA.length ; i++) {
            if (DATA[i]==48 || DATA[i]==49){
                LastData+=(char)DATA[i];
            }
            else {
                break;
            }
        }
        int FileNameIndexStart = LastData.length()+1; // +1 that mean size of new line
String FileName = "" ;
for (int i = FileNameIndexStart ; i < str.length() ; i++) {
    if (str.charAt(i) != '\n'){
        FileName+=str.charAt(i);
    }
    else {
        break;
    }
}
        System.out.println("Last 8 Byte : " + LastData);
        System.out.println("File Name : " + FileName);
        System.out.println("Str : "+str);
       // int IndexLastHeader = str.indexOf("EndHeader");
int IndexLastHeader =getLastHeader(DATA , LastData.length()+FileName.length() + 2);

        System.out.println("IndexoFEhader :" +IndexLastHeader );


       StringBuilder header =new StringBuilder();
      //  List<Byte> header = new LinkedList<>();

        String ss = "" ;
for (int i  = FileName.length()+LastData.length()+2 ; i < IndexLastHeader ; i++) {
     ss = String.format("%8s", Integer.toBinaryString(DATA[i] & 0xFF)).replace(' ', '0');
     header.append(ss);
          //   header.add((byte) str.charAt(i));
}

        byte x =  DATA[IndexLastHeader-1];
        System.out.println("The X is : " + x);
        System.out.println("Start Header : "+(FileName.length()+LastData.length()+2));
        System.out.println("HEADER  : " + header.toString());

//String [] headerBuf = header.toString().trim().split(" ");

        HashMap<String , Byte> huffmanCode = MainHeader.DecodeHeader(header.toString() , x) ;
   Decoding decoding = new Decoding();


        DATA = Arrays.copyOfRange(DATA, IndexLastHeader+9, DATA.length);

       byte[] result =  decoding.GenerateDataForDecode( LastData, DATA , huffmanCode  );
        System.out.println("PATH : "+DecompressController.PathOrginalFile+FileName.trim());

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(DecompressController.PathOrginalFile+FileName.trim()));
    bufferedOutputStream.write(result , 0 , decoding.EndDataInBufferArray);
    bufferedOutputStream.flush();
    bufferedOutputStream.close();
        System.out.println("DONE");

}

    private static int getLastHeader(byte[] data, int start) {
        for (int i = start ; i < data.length ; i++) {
            if (data[i]=='E' &&data[i+1]=='n' &&data[i+2]=='d' &&data[i+3]=='H' &&data[i+4]=='e' &&data[i+5]=='a' && data[i+6]=='d' &&data[i+7]=='e' &&data[i+8]=='r' ) {
                       return i ;
            }
        }
        return 0 ;
    }


    public static StringBuilder header__ (byte [] data , HufmanTable hufmanTable[]) throws IOException {
        StringBuilder builder = new StringBuilder();
      //  System.out.println("\nTHe data : "+Arrays.toString(data));

        byte[] headerArray  = MainHeader.generateHeader(data);

        builder.append("EndHeader"); //\n
        writeInMemory( headerArray ,builder , data , hufmanTable );
        return builder ;
    }

    private static void writeInMemory(byte []  headerArray, StringBuilder v , byte[] data , HufmanTable[]hufmanTables) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(CompressController.PathCompressedFile)) ;
        Encoding encoding = new Encoding() ;
       byte result [] =  encoding.returnData(data ,hufmanTables );
       int counter = encoding.COUNTER;
       String lastByte = encoding.lastDataByte;
       CompressController.headerToUi.append(lastByte+"\n") ;
       bufferedOutputStream.write((lastByte+"\n").getBytes());
        CompressController.headerToUi.append(CompressController.FileName+"\n") ;
        bufferedOutputStream.write((CompressController.FileName+"\n").getBytes());
        System.out.println("LENGTH OF LAST HEADER : " + headerArray.length);
        bufferedOutputStream.write(headerArray , 0 , headerArray.length);
        for (int i = 0 ; i < headerArray.length ; i++) {
            String s1 = String.format("%8s", Integer.toBinaryString(headerArray[i] & 0xFF)).replace(' ', '0');
            CompressController.headerToUi.append(s1);
        }
        CompressController.headerToUi.append("EndHeader");
        bufferedOutputStream.write("EndHeader".getBytes());
       bufferedOutputStream.write(result ,0 , counter);
       bufferedOutputStream.flush();
       bufferedOutputStream.close();
    }



    public static NodeFromFile CountFrequency(String Path) throws IOException {
        int count [] = new int[NUMBER_OF_VALUES] ;
        byte [] value = new byte[NUMBER_OF_VALUES] ;

        for (int i = 0 ; i < value.length ; i++) {
            value[i] = 0 ;
        }
       byte[]DATA= Files.readAllBytes(Paths.get(Path));

       //System.out.println(Arrays.toString(DATA));
       for (int i = 0 ; i < DATA.length ; i++) {
           if (DATA[i] < 0) {
               count[256+DATA[i]]++;
               value[256+DATA[i]] = DATA[i] ;
           }
           else
           {
               count[DATA[i]]++;
               value[DATA[i]] = DATA[i] ;
           }
       }
        NodeFromFile nodeFromFile = new NodeFromFile() ;
        nodeFromFile.count = count ;
        nodeFromFile.value = value ;
        nodeFromFile.Data = DATA ;
      return nodeFromFile;
    }
    public static class NodeFromFile {
        public int []count ;
        public byte [] value ;

        public byte[] Data;

        String FileName ;
    }





}
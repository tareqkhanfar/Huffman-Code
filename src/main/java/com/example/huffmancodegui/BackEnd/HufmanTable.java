package com.example.huffmancodegui.BackEnd;

public class HufmanTable {
    int freq ;
    byte length ;
    String huffmanCode ;

    byte value ;

    public HufmanTable(byte value , String huffmanCode , int freq, byte length ) {
        this.value = value ;
        this.freq = freq;
        this.length = length;
        this.huffmanCode = huffmanCode;
    }
    public HufmanTable () {

    }

public static String printHuffmanTable (HufmanTable[]hufmanTable) {
        StringBuilder builder = new StringBuilder() ;
    for (int i = 0 ; i < hufmanTable.length ; i++) {
        if (hufmanTable[i] != null)
            builder.append(hufmanTable[i]+"\n");
            //System.out.println(hufmanTable[i]);
    }
    return builder.toString();
}

    @Override
    public String toString() {
        return "HufmanTable{" +
                "freq=" + freq +
                ", length=" + length +
                ", huffmanCode='" + huffmanCode + '\'' +
                ", value=" + value +
                '}';
    }
}

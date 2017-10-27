/**
 * Longest Common Subsequence
 *
 * Algorithms & Data Structures
 * Programming Assignment 3
 *
 * Team Members -
 *  1.  Adhish Thite
 *  2.  Ved Paranjape
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.System;
import java.math.*;

public class LCS {

    public Long startTime;
    public long elapsedTime;

    public static void main(String[] args){

        List<Pair> global_PairList;

        if(args[0] != null && args[0].length() != 0){
            LCS objLCS = new LCS();
            global_PairList = objLCS.readFromFile(args[0]);

            if(!global_PairList.isEmpty()){

            } else {
                System.out.println("\nNo Input Provided !\n");
            }
        } else {
            System.out.println("\nPlease specify an input file !\n");
            return;
        }
    }

    private List<Pair> readFromFile(String fileName){
        File inputFile = new File(fileName);
        List<Pair> pairLists = new ArrayList<>();

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(inputFile));
            String readLine;
            String nextLine;

            while ((readLine = buffReader.readLine()) != null) {
                nextLine = buffReader.readLine();

                if(nextLine != null && readLine.length() != 0) {
                    Pair pair = new Pair(readLine, nextLine);
                    pairLists.add(pair);
                }
            }

            buffReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pairLists;
    }

    private void initiateLCSOperations(List<Pair> pairsList){
        startTime = System.currentTimeMillis();

        for(Pair tempPair : pairsList){
            doLCS(tempPair);
        }

        elapsedTime = System.currentTimeMillis() - startTime;
    }

    private static void doLCS(Pair tempPair){
        String input1 = tempPair.input2;
        String input2 = tempPair.input1;
        int m =input1.length();
        int n = input2.length();
        int[][] L = new int[m+1][n+1];
        for(int i = 0;i<m;i++)
        {
            L[i][0] = 0;
        }
        for(int j=0;j<n;j++)
        {
            L[0][j] = 0;
        }

        for(int i = 0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(input1.charAt(i) == input2.charAt(j))
                {
                    L[i+1][j+1] = L[i][j] + 1;
                }
                else
                {
                    L[i+1][j+1] = Math.max(L[i][j+1], L[i+1][j]);
                }
            }
        }
        for(int i=0;i<m+1;i++)
        {
            for(int j=0;j<n+1;j++)
            {
                System.out.print(L[i][j]+"\t");
            }
            System.out.println();
        }
        int lcs_len = L[m][n];
        int idx = lcs_len;
        char[] lcs = new char[lcs_len];
        System.out.println(lcs_len);
        int i=m;
        int j=n;
        while(i>0 && j>0)
        {
            System.out.println("i:"+i+"\tj:"+j);
            if(input1.charAt(i-1) == input2.charAt(j-1))
            {
                System.out.println(input1.charAt(i-1)+" "+input2.charAt(j-1));
                lcs[idx-1] = input1.charAt(i-1);
                i--;
                j--;
                idx--;

            }
            else {
                if(L[i-1][j] > L[i][j-1])
                {
                    i--;
                }
                else
                {
                    j--;
                }
            }
        }
        tempPair.strLCS = lcs.toString();
        tempPair.lengthLCS = lcs_len;


    }



    private class Pair {
        private String input1;
        private String input2;
        private String strLCS;
        private int lengthLCS;

        public Pair() {
            this.input1 = null;
            this.input2 = null;
            strLCS = null;
            lengthLCS = 0;
        }

        private Pair(String input1, String input2) {
            this.input1 = input1;
            this.input2 = input2;
        }

        @Override
        public String toString() {
            return "\n1. " + input1 + "\n2. " + input2 + "\n";
        }
    }
}

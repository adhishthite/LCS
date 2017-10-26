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

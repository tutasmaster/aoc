package com.tutas.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day7 {
    public int[] crabs = new int[2000];
    public Day7(){
        ReadFile();
        System.out.println(GetMinSum());
    }

    public long GetMinSum(){
        long minSum = 1000000000;
        int f = 0;
        for(int i = 0; i < 2000; i++){
            int v = Sum(i);
            if(v < minSum){
                minSum = v;
                f = i;
            }
        }
        return minSum;
    }

    public int Sum(int val){
        int sum = 0;
        for (int i = 0; i < crabs.length; i++){
            int steps = 0;
            for(int j = 1; j <= Math.abs(val - i); j++){
                steps += j;
            }
            sum += steps * crabs[i];
        }
        return sum;
    }
    BufferedReader br;
    public void ReadFile(){
        try{
            FileReader fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            String[] string = br.readLine().split(",");
            for(int i = 0; i < string.length; i++){
                crabs[Integer.parseInt(string[i])]++;
            }
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}

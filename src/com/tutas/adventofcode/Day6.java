package com.tutas.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    public long[] fish = new long[9];
    public int fLength = 0;
    public Day6(){
        ReadFile();
        for(int j = 0; j < 256; j++){
            long[] new_fish = new long[9];
            for(int i = 0; i < 9; i++){
                if(i == 0){
                    new_fish[8] = fish[0];
                    new_fish[6] = fish[0];
                }else{
                    fish[i-1] = fish[i];
                }
                fish[i] = 0;
            }
            for(int i = 0; i < 9; i++){
                fish[i] += new_fish[i];
            }
            System.out.println("Iteraction: " + (j+1));
        }
        Draw();
    }
    public void Draw(){
        long sum = 0;
        for(Long f: fish){
            System.out.print(f + ",");
            sum += f;
        }
        System.out.println("Total: " + sum);
    }
    BufferedReader br;
    public void ReadFile(){
        try{
            FileReader fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            String[] string = br.readLine().split(",");
            for(int i = 0; i < string.length; i++){
                fish[Integer.parseInt(string[i])]++;
            }
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}

package com.tutas.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day5 {
    public Day5(){
        ReadFile();
        Calculate();
    }
    final static int size = 1000;
    int[] data = new int[size*size];
    private BufferedReader br;

    public void Draw(){
        for(int j = 0; j < size; j++){
            for(int i = 0; i < size; i++){
                System.out.print(data[(j * size) + i] + " ");
            }
            System.out.println();
        }
    }

    public void Calculate(){
        int total = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i] > 1)
                total++;
        }
        System.out.println("Result: " + total);
    }

    public void ReadFile(){
        try{
            FileReader fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                String[] split = line.split(" ");
                String[] valuesLeft = split[0].split(",");
                String[] valuesRight = split[2].split(",");
                int[] values = {
                        Integer.parseInt(valuesLeft[0]),
                        Integer.parseInt(valuesLeft[1]),
                        Integer.parseInt(valuesRight[0]),
                        Integer.parseInt(valuesRight[1])
                };
                System.out.println(values[0] + "," + values[1] + " -> " + values[2] + "," + values[3]);
                if(values[0] == values[2]){
                    for(int i = 0; i <= Math.abs(values[1] - values[3]); i++)
                        data[((Math.min(values[1], values[3]) + i) * size) + values[0]]++;

                }else if(values[1] == values[3]){
                    for(int i = 0; i <= Math.abs(values[0] - values[2]); i++)
                        data[(values[1] * size) + (Math.min(values[0], values[2]) + i)]++;

                }else{
                    //P2 Time
                    /*int x = Integer.signum(values[2] - values[0]);
                    int y = Integer.signum(values[3] - values[1]);*/

                    System.out.println(values[0] + "," + values[1] + " -> " + values[2] + "," + values[3]);
                    if(values[0] > values[2]){
                        int temp = values[0];
                        values[0] = values[2];
                        values[2] = temp;
                        temp = values[1];
                        values[1] = values[3];
                        values[3] = temp;
                    }
                    System.out.println(values[0] + "," + values[1] + " -> " + values[2] + "," + values[3]);
                    int y = Integer.signum(values[3] - values[1]);

                    /*2->4 2->4*/
                    for(int i = 0; i <= Math.abs(values[0] - values[2]); i++){
                        data[(values[0] + i) + ((values[1] + (i*y)) * size)]++;
                    }
                }

            }
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}

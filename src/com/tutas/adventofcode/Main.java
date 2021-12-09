package com.tutas.adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.compare;
import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        new Day9();
    }

    static void D3P1() throws FileNotFoundException, IOException{
        int[] count = new int[12];
        int totalLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++){
                    count[i] += line.charAt(i) == '1' ? 1 : 0;
                }

                totalLines++;
            }
        }
        String buffer = "";
        String buffere = "";
        for(int i = 0; i < count.length; i++){
            int value = (totalLines / 2) > count[i] ? 1 : 0;
            int iValue = value == 1 ? 0 : 1;
            buffer = buffer + value;
            buffere = buffere + iValue;
        }
        int finalGamma = parseInt(buffer,2);
        int finalEpsilon = parseInt(buffere,2);
        System.out.println(finalGamma);
        System.out.println(finalEpsilon);
        System.out.println(finalGamma*finalEpsilon);
    }

    static void D3P2() throws FileNotFoundException, IOException{
        int[] count = new int[12];
        List<String> lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        List<String> oxygen = new ArrayList<String>(lines);
        List<String> carbon = new ArrayList<String>(lines);
        for(int i = 0; i < count.length; i++){
            count[i] = 0;
            for (int j = 0; j < oxygen.size(); j++){
                count[i] += oxygen.get(j).charAt(i) == '1' ? 1 : 0;
            }
            char value = (Math.ceil((double)(oxygen.size()) / 2)) > count[i] ? '1' : '0';
            for(int j = 0; j < oxygen.size(); j++){
                if(oxygen.get(j).charAt(i) != value){
                    oxygen.remove(j);
                    j--;
                }
            }
            if(oxygen.size() == 1)
                break;
        }

        for(int i = 0; i < count.length; i++){
            count[i] = 0;
            for (int j = 0; j < carbon.size(); j++){
                count[i] += carbon.get(j).charAt(i) == '1' ? 1 : 0;
            }
            char value = (Math.ceil((double)(carbon.size()) / 2)) > count[i] ? '1' : '0';
            for(int j = 0; j < carbon.size(); j++){
                if(carbon.get(j).charAt(i) == value){
                    carbon.remove(j);
                    j--;
                }
            }
            if(carbon.size() == 1)
                break;
        }
        System.out.println(oxygen.size());
        System.out.println(oxygen.get(0));
        System.out.println(carbon.size());
        System.out.println(carbon.get(0));
        System.out.println(parseInt(oxygen.get(0),2));
        System.out.println(parseInt(carbon.get(0),2));
    }

    static void D2P1() throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            Integer horizontal = 0, depth = 0;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("u")){
                    depth -= parseInt(line.split(" ")[1]);
                }
                if(line.startsWith("d")){
                    depth += parseInt(line.split(" ")[1]);
                }
                if(line.startsWith("f")){
                    horizontal += parseInt(line.split(" ")[1]);
                }
            }
            System.out.println(horizontal);
            System.out.println(depth);
            System.out.println(horizontal*depth);
        }
    }

    static void D2P2() throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            Integer horizontal = 0, depth = 0;
            Integer aim = 0;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("u")){
                    aim += parseInt(line.split(" ")[1]);
                }
                if(line.startsWith("d")){
                    aim -= parseInt(line.split(" ")[1]);
                }
                if(line.startsWith("f")){
                    horizontal += parseInt(line.split(" ")[1]);
                    depth -= aim * parseInt(line.split(" ")[1]);
                }
            }
            System.out.println(horizontal);
            System.out.println(depth);
            System.out.println(horizontal*depth);
        }
    }

    void D1P1() throws FileNotFoundException, IOException{
        Integer i = 0;
        Integer count = -1;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(i < parseInt(line)){
                    count++;
                }
                i = parseInt(line);
            }
        }
        System.out.println(count);
    }

    static Integer D1Wrap(Integer a){
        return Math.abs(a % 3);
    }

    static void D1P2() throws FileNotFoundException, IOException{
        Integer[] i = {0,0,0};
        Integer i_buff = 2;
        Integer count = -1;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            i[0] = parseInt(br.readLine());
            i[1] = parseInt(br.readLine());
            while ((line = br.readLine()) != null) {
                Integer sum1 = i[0] + i[1] + i[2];
                i[i_buff] = parseInt(line);
                Integer sum2 = i[0] + i[1] + i[2];
                if(sum1 < sum2){
                    count++;
                }
                i_buff = D1Wrap(i_buff + 1);
            }
        }
        System.out.println(count);
    }
}

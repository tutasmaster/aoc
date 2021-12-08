package com.tutas.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;

public class Day8 {
    /*
    *  0000
    * 1    2
    * 1    2
    *  3333
    * 4    5
    * 4    5
    *  6666
    * */
    public byte[][] digits = {
            {1,1,1,0,1,1,1},
            {0,0,1,0,0,1,0}, //1
            {1,0,1,1,1,0,1}, //2
            {1,0,1,1,0,1,1}, //3 -> 3
            {0,1,1,1,0,1,0},
            {1,1,0,1,0,1,1}, //5 -> 1
            {1,1,0,1,1,1,1},
            {1,0,1,0,0,1,0}, //7 -> 0
            {1,1,1,1,1,1,1},
            {1,1,1,1,0,1,1}
    }; //7 -> 3 -> 5
    class Confirmed{
        int val = 0;
        char c = 'a';
        Confirmed(int val, char c){
            this.val = val;
            this.c = c;
        }
    }
    public char[] size = {5,2,5,5,4,5,6,3,7,6};
    public char[] conf = {' ', ' ', ' ', ' ', ' ', ' ', ' '};

    BufferedReader br;

    public Day8(){
        ReadFile();
    }
    String []data;
    String matching = "";
    public String FindByLength(int x){
        for(String s: data){
            if(s.length() == x)
                return s;
        }
        return "";
    }
    public String FindByLengthMatchingTrue(int l, int m, int t){
        for(String s: data){
            if(s.length() == l){
                int match = 0;
                int total = 0;
                for(char c1: s.toCharArray()){
                    for(char c2: matching.toCharArray()){
                        if(c1 == c2){
                            match++;
                            continue;
                        }
                    }
                    for(char c2: conf){
                        if(c1 == c2){
                            total++;
                            continue;
                        }
                    }
                }
                if(match == m && total == t){
                    return s;
                }
            }
        }
        return "";
    }
    public String Diff(String s){
        String diff = "";
        for (char c1 : s.toCharArray()){
            boolean found = false;
            for (char c2 : matching.toCharArray()){
                if(c1 == c2)
                    found = true;
            }
            if(!found)
                diff += c1;
        }
        return diff;
    }
    public String GetNonMatching(String s){
        String str = "";
        for(char c1 : s.toCharArray()){
            boolean match = false;
            for(char c2: matching.toCharArray()){
                if(c1 == c2){
                    match = true;
                    break;
                }
            }
            if(!match)
                str += c1;
        }
        return str;
    }

    public char GetMatchingNonTrue(String s){
        for(char c1 : s.toCharArray()){
            for(char c2: matching.toCharArray()){
                if(c1 == c2){
                    boolean c = false;
                    for(char c3: conf){
                        if(c1 == c3){
                            c = true;
                            continue;
                        }
                    }
                    if(!c)
                        return c1;
                }
            }
        }
        return ' ';
    }

    public String GetMatching(String s1, String s2){
        String str = "";
        for(char c1 : s1.toCharArray()){
            boolean match = false;
            for(char c2: s2.toCharArray()){
                if(c1 == c2){
                    match = true;
                    break;
                }
            }
            if(match)
                str += c1;
        }
        return str;
    }

    public int DecodeDigit(String s){
        byte[] digit = {0,0,0,0,0,0,0};
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < conf.length; j++){
                if(s.charAt(i) == conf[j]){
                    digit[j] = 1;
                    break;
                }
            }
        }
        for(int i = 0; i < digits.length; i++){
            boolean check = true;
            for(int j = 0; j < digit.length; j++){
                if(digits[i][j] != digit[j]){
                    check = false;
                }
            }
            if(check == true){
                return i;
            }
        }
        return -1;
    }

    public void ReadFile(){
        int countP1 = 0;
        int countP2 = 0;
        try{
            FileReader fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null){
                matching = "";
                conf[0] = ' ';
                conf[1] = ' ';
                conf[2] = ' ';
                conf[3] = ' ';
                conf[4] = ' ';
                conf[5] = ' ';
                conf[6] = ' ';
                String[] string = line.split("\\|");
                data = string[0].split(" ");
                matching += FindByLength(2);
                conf[0] = Diff(FindByLength(3)).charAt(0);
                matching += conf[0];
                String midSection = GetNonMatching(FindByLengthMatchingTrue(5,3,1));
                String four = FindByLength(4);
                conf[3] = GetMatching(four, midSection).charAt(0);
                matching += conf[3];
                conf[6] = midSection.charAt(0) == conf[3] ? midSection.charAt(1) : midSection.charAt(0);
                conf[1] = GetNonMatching(four).charAt(0);
                matching += conf[6];
                matching+= conf[1];
                String two = FindByLengthMatchingTrue(5,4,3);
                conf[4] = GetNonMatching(two).charAt(0);
                conf[2] = GetMatchingNonTrue(two);
                for(char c1 : matching.toCharArray()){
                    boolean match = false;
                    for(char c2: conf){
                        if(c1 == c2){
                            match = true;
                            break;
                        }
                    }
                    if(!match){
                        conf[5] = c1;
                        break;
                    }
                }
                System.out.println("CODE: " + conf[0]+conf[1]+conf[2]+conf[3]+conf[4]+conf[5]+conf[6]);
                String fullnum = "";
                for(String s: string[1].split(" ")){
                    if(s == "")
                        continue;
                    System.out.println("DECODED: " + DecodeDigit(s));
                    int d = DecodeDigit(s);
                    if(d == 1 || d == 4 || d == 7 || d == 8){
                        countP1++;
                    }
                    fullnum += d;
                }
                countP2 += Integer.parseInt(fullnum);
                System.out.println(countP1);

            }
            System.out.println(countP2);
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}

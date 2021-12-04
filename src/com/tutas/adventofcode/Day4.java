package com.tutas.adventofcode;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Day4 {
    class Board{
        public final static int size = 5;
        public boolean winner = false;
        public int[] values;
        public boolean[] confirmed;
        public Board(int[] v){
            values = v;
            confirmed = new boolean[values.length];
        }

        public int getScore(){
            int sum = 0;
            for(int i = 0; i < values.length; i++){
                if(!confirmed[i])
                    sum += values[i];
            }
            return sum;
        }

        public void clearBoard(){
            for(int i = 0; i < confirmed.length; i++)
                confirmed[i] = false;
        }
        public void checkValue(int k){
            for (int i = 0; i < values.length; i++) {
                if(values[i] == k)
                    confirmed[i] = true;
            }
        }
        public boolean checkBingo(){
            for(int j = 0; j < Board.size; j++){
                int confX = 0;
                int confY = 0;
                for(int i = 0; i < Board.size; i++){
                    confX += confirmed[(j * size) + i] ? 1 : 0;
                    confY += confirmed[(i * size) + j] ? 1 : 0;
                }
                if(confX == Board.size || confY == Board.size){
                    return true;
                }
            }
            return false;
        }

        public void drawBoard(){
            for(int j = 0; j < Board.size; j++){
                for(int i = 0; i < Board.size; i++){
                    if(confirmed[(j * size) + i])
                        System.out.print("!");
                    else
                        System.out.print("#");
                    System.out.print(values[(j*size) + i] + " ");
                }
                System.out.println();
            }
        }
    }
    private int[] key;
    private List<Board> boards = new ArrayList<Board>();
    private BufferedReader br;

    public Day4(){
        ReadFile();

        boolean bingo = false;
        int lastWinner = -1;
        int lastValue = -1;
        for (int k: key) {
            int i = -1;
            for (Board b: boards){
                i++;
                if(b.winner)
                    continue;
                b.checkValue(k);
                bingo = b.checkBingo();
                if(bingo && lastWinner == -1){
                    b.winner = true;
                    lastWinner = i;
                    lastValue = k;
                    b.drawBoard();
                    System.out.println("FIRST WINNER: " + k);
                    System.out.println("FINAL SCORE: " + k * b.getScore());
                }else if(bingo){
                    b.winner = true;
                    lastWinner = i;
                    lastValue = k;
                }
            }
        }
        for(Board b : boards)
            b.clearBoard();
        for(int k: key){
            for(Board b: boards)
                b.checkValue(k);
            if(k == lastValue)
                break;
        }
        System.out.println();
        boards.get(lastWinner).drawBoard();
        System.out.println("LAST WINNER: " + lastValue);
        System.out.println("FINAL SCORE: " + lastValue * boards.get(lastWinner).getScore());
    }

    public void ReadFile(){
        try{
            FileReader fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            ReadKey();
            while(ReadBoard() == true);
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }

    public void ReadKey() throws IOException {
        String line = br.readLine();
        String[] keys = line.split(",");
        key = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            key[i] = parseInt(keys[i]);
        }
    }

    public boolean ReadBoard() throws IOException {
        String line = br.readLine();
        if(line == null){
            return false;
        }
        int[] values = new int[Board.size * Board.size];
        int h = 0;
        for(int i = 0; i < Board.size; i++){
            line = br.readLine();
            String[] split = line.split(" ");
            for (String s : split) {
                if(s == "")
                    continue;
                values[h] = parseInt(s);
                h++;
            }
        }
        boards.add(new Board(values));
        return true;
    }
}

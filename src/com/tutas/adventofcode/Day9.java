package com.tutas.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public int[] map = new int[1000000];
    public boolean[] visited = new boolean[1000000];
    public int width = 10;
    public int height = 10;
    class Pos{
        int x, y;
        public Pos(int x, int y){
            this.x = x; this.y = y;
        }
    }
    int[] basins = {-1,-1,-1};
    public List<Pos> currentBasin = new ArrayList<Pos>();
    public Day9(){
        ReadFile();
        int sum = 0;
        for(int j = 0; j < height; j++){
            for(int i = 0; i < width; i++){
                /*int match = GetSpot(i,j);
                int m1 = GetSpot(i-1,j) > match ? 1 : 0;
                int m2 = GetSpot(i+1,j) > match ? 1 : 0;
                int m3 = GetSpot(i,j-1) > match ? 1 : 0;
                int m4 = GetSpot(i,j+1) > match ? 1 : 0;
                if(m1 + m2 + m3 + m4 == 4){
                    System.out.println("x: " + i + " y:" + j + " val: " + match);
                    sum += match + 1;
                }*/

                currentBasin = new ArrayList<Pos>();
                if(GetVisited(i,j))
                    continue;
                if(GetSpot(i,j) == 9 || GetSpot(i,j) < 8)
                    continue;
                FloodFill(i,j, GetSpot(i,j));
                System.out.println("BASIN: " + currentBasin.size() + " X" + i + " Y" + j);
                int min = 10000;
                int b = 0;
                for(int k = 0; k < basins.length; k++){
                    if(basins[k] == -1){
                        b = k;
                        min = 0;
                        break;
                    }else{
                        if(basins[k] < min && currentBasin.size() > basins[k]){
                            min = basins[k];
                            b = k;
                        }
                    }
                }
                if(min == 10000)
                    continue;

                if(currentBasin.size() == basins[0] || currentBasin.size() == basins[1] || currentBasin.size() == basins[2])
                    continue;

                basins[b] = currentBasin.size();
                currentBasin = new ArrayList<Pos>();
            }
        }
        System.out.println(sum);
        for(int i : basins){
            System.out.println(i);
        }
    }
    BufferedReader br;

    public boolean FindPos(int x, int y){
        for(Pos p: currentBasin){
            if(p.x == x && p.y == y){
                return true;
            }
        }
        return false;
    }

    public boolean GetVisited(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height)
            return true;
        else
            return visited[(y * width) + x];
    }

    public void SetVisited(int x, int y, boolean value){
        if(x < 0 || x >= width || y < 0 || y >= height)
            return;
        else
            visited[(y * width) + x] = value;
    }

    public void FloodFill(int x, int y, int val){
        SetVisited(x,y,true);
        currentBasin.add(new Pos(x,y));
        if(!FindPos(x-1, y) && GetSpot(x-1, y) <= val)
            FloodFill(x-1,y,val);
        if(!FindPos(x+1, y) && GetSpot(x+1, y) <= val)
            FloodFill(x+1,y, val);
        if(!FindPos(x, y-1) && GetSpot(x, y-1) <= val)
            FloodFill(x,y-1, val);
        if(!FindPos(x, y+1) && GetSpot(x, y+1) <= val)
            FloodFill(x,y+1, val);
    }

    public int GetSpot(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height)
            return 99;
        else
            return map[(y * width) + x];
    }

    public void ReadFile() {
        try {
            FileReader fr = new FileReader("input.txt");
            br = new BufferedReader(fr);
            String line = "";
            int i = 0;
            height = 0;
            while ((line = br.readLine()) != null) {
                width = 0;
                for (char c : line.toCharArray()) {
                    map[i] = Integer.parseInt("" + c);
                    i++;
                    width++;
                }
                height++;
            }
            System.out.println(width);
            System.out.println(height);
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
    }
}

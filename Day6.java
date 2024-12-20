import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Day6 {
    public static void main(String[] args) {
        String filename = "input6.txt";
        Scanner file = getFileScanner(filename);
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        int val = 0;
        int count = 1;
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] temp = line.split("");
            ArrayList<String> lineList = new ArrayList<>(Arrays.asList(temp));
            map.put(val,lineList);
            val++;
        }
        int direction = 0;
        int row = initialPosition(map).get(0);
        int column = initialPosition(map).get(1);
        try {
            while (count>=0) {
                if (direction%4==0) {
                    int prerow = row;
                    ArrayList<Integer> pos = up(map,row,column);
                    direction++;
                    row = pos.get(0);
                    column = pos.get(1);
                    for (int i = prerow; i>=row; i--) {
                        map.get(i).set(column,"X");
                    }
                    count+=pos.get(2);
                }
                else if (direction%4==1) {
                    int precol = column;
                    ArrayList<Integer> pos = right(map,row,column);
                    direction++;
                    row = pos.get(0);
                    column = pos.get(1);
                    for (int i = precol; i<=column; i++) {
                        map.get(row).set(i,"X");
                    }
                    count+=pos.get(2);
                }
                else if (direction%4==2) {
                    int prerow = row;
                    ArrayList<Integer> pos = down(map,row,column);
                    direction++;
                    row = pos.get(0);
                    column = pos.get(1);
                    for (int i = prerow; i<=row;i++) {
                        map.get(i).set(column,"X");
                    }
                    count+=pos.get(2);
                }
                else if (direction%4==3) {
                    int precol = column;
                    ArrayList<Integer> pos = left(map,row,column);
                    direction++;
                    row = pos.get(0);
                    column = pos.get(1);
                    for (int i = precol; i>=column; i--) {
                        map.get(row).set(i,"X");
                    }
                    count+=pos.get(2);
                }
            }
        }
        catch  (Exception e) {
            if (direction%4==0) {
                count+=finalup(map,row,column);
            }
            else if (direction%4==1) {
                count+=finalright(map,row,column);
            }
            else if (direction%4==2) {
                count+=finaldown(map,row,column);
            }
            else if (direction%4==3) {
                count+=finalleft(map,row,column);
            }
            System.out.println(count);
        }
    }

    public static ArrayList<Integer> initialPosition(HashMap<Integer,ArrayList<String>> map) {
        ArrayList<Integer> pos = new ArrayList<>();
        for (Integer key : map.keySet()) {
            ArrayList<String> list = map.get(key);
            int index = list.indexOf("^");
            if (index != -1) {
                pos.add(key);
                pos.add(index);
                return pos;
            }
        }
        return pos;
    }

    public static ArrayList<Integer> up(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        ArrayList<Integer> newpos = new ArrayList<>();
        int count = 0;
        while (!map.get(row).get(column).equals("#")) {
            if (map.get(row).get(column).equals(".")) {
                count++;
            }
            row--;
        }
        newpos.add(row+1);
        newpos.add(column);
        newpos.add(count);
        return newpos;
    }

    public static ArrayList<Integer> down(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        ArrayList<Integer> newpos = new ArrayList<>();
        int count=0;
        while (!map.get(row).get(column).equals("#")) {
            if (map.get(row).get(column).equals(".")) {
                count++;
            }
            row++;
        }
        newpos.add(row-1);
        newpos.add(column);
        newpos.add(count);
        return newpos;
    }

    public static ArrayList<Integer> left(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        ArrayList<Integer> newpos = new ArrayList<>();
        int count=0;
        while (!map.get(row).get(column).equals("#")) {
            if (map.get(row).get(column).equals(".")) {
                count++;
            }
            column--;
        }
        newpos.add(row);
        newpos.add(column+1);
        newpos.add(count);
        return newpos;
    }

    public static ArrayList<Integer> right(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        ArrayList<Integer> newpos = new ArrayList<>();
        int count=0;
        while (!map.get(row).get(column).equals("#")) {
            if (map.get(row).get(column).equals(".")) {
                count++;
            }
            column++;
        }
        newpos.add(row);
        newpos.add(column-1);
        newpos.add(count);
        return newpos;
    }

    public static Integer finalup(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        int count = 0;
        for (int i = row; i >= 0; i--) {
            if (map.get(i).get(column).equals(".")) {
                count++;
            }
        }
        return count;
    }

    public static Integer finaldown(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        int count=0;
        for (int i = row; i< map.size(); i++) {
            if (map.get(i).get(column).equals(".")) {
                count++;
            }
        }
        return count;
    }

    public static Integer finalright(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        int count=0;
        for (int i = column; i<map.get(row).size(); i++) {
            if (map.get(row).get(i).equals(".")) {
                count++;
            }
        }
        return count;
    }

    public static Integer finalleft(HashMap<Integer,ArrayList<String>> map, int row, int column) {
        int count=0;
        for (int i = column; i>=0; i--) {
            if (map.get(row).get(i).equals(".")) {
                count++;
            }
        }
        return count;
    }

    public static Scanner getFileScanner (String fileName) {
        try {
            FileInputStream textFileStream = new FileInputStream(fileName);
            Scanner inputFile = new Scanner(textFileStream);
            return inputFile;
        } catch (IOException ex) {
            System.out.println("Warning: could not open " + fileName);
            return null;
        }
    }
}


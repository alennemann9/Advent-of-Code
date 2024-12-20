import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Day8 {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<String>> graph = initialize();
        HashMap<Integer, HashSet<Integer>> antinodes = new HashMap<>();
        int height = graph.size();
        int length = graph.get(0).size();
        for (Integer y1 : graph.keySet()) {
            for (int x1 = 0; x1 < length; x1++) {
                if (!graph.get(y1).get(x1).equals(".")) {
                    String ant = graph.get(y1).get(x1);
                    for (Integer y2 : graph.keySet()) {
                        for (int x2 = 0; x2 < length; x2++) {
                            if ((y2 > y1) || (y2.equals(y1) && x2 > x1)) {
                                if (graph.get(y2).get(x2).equals(ant)) {
                                    ArrayList<Integer> antis = anti(x1, x2, y1, y2,height,length);
                                    for (int i = 0; i <antis.size(); i +=2) {
                                        int x = antis.get(i);
                                        int y = antis.get(i+1);
                                        if (antinodes.containsKey(x)) {
                                            antinodes.get(x).add(y);
                                        }
                                        else {
                                            HashSet<Integer> hup = new HashSet<>();
                                            hup.add(y);
                                            antinodes.put(x,hup);
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        int total = 0;
        for (int key: antinodes.keySet()) {
            total += antinodes.get(key).size();
        }
        System.out.println(total);
    }

    public static HashMap<Integer, ArrayList<String>> initialize() {
        Scanner scnr = getFileScanner("input8.txt");
        HashMap<Integer, ArrayList<String>> temp = new HashMap<>();
        int count = 1;
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            ArrayList<String> linelist = new ArrayList<>(Arrays.asList(line.split("")));
            temp.put(count,linelist);
            count++;
        }
        HashMap<Integer, ArrayList<String>> graph = new HashMap<>();
        for (Integer key: temp.keySet()) {
            graph.put(temp.size()-key, temp.get(key));
        }
        return graph;
    }

    public static ArrayList<Integer> anti(int x1, int x2, int y1, int y2, int height, int length) {
        ArrayList<Integer> antis = new ArrayList<>();
        int heightdiff = Math.abs(y1-y2);
        int lengthdiff = Math.abs(x1-x2);
        int upx = x2;
        int upy = y2;
        int downx = x1;
        int downy = y1;
        antis.add(upx);
        antis.add(upy);
        antis.add(downx);
        antis.add(downy);
        if (x1>x2) {
            while (upx>=lengthdiff && upy<height-heightdiff) {
                upx-=lengthdiff;
                upy+=heightdiff;
                antis.add(upx);
                antis.add(upy);
            }
            while (downx<length-lengthdiff && downy>=heightdiff) {
                downx+=lengthdiff;
                downy-=heightdiff;
                antis.add(downx);
                antis.add(downy);
            }
        }
        else {
            while (upx<length-lengthdiff && upy<height-heightdiff) {
                upx+=lengthdiff;
                upy+=heightdiff;
                antis.add(upx);
                antis.add(upy);
            }
            while (downx>=lengthdiff && downy>=heightdiff) {
                downx-=lengthdiff;
                downy-=heightdiff;
                antis.add(downx);
                antis.add(downy);
            }
        }
        return antis;
    }


    public static Scanner getFileScanner(String fileName) {
        try {
            FileInputStream textFileStream = new FileInputStream(fileName);
            return new Scanner(textFileStream);
        } catch (IOException ex) {
            System.out.println("Warning: could not open " + fileName);
            return null;
        }
    }
}


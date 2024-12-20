import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Day5 {
    public static void main(String[] args) {
        // read in text file
        String filename = "input5.txt";
        Scanner file = getFileScanner(filename);
        HashMap<Integer,ArrayList<Integer>> rules = new HashMap<>();
        // read through the file line-by-line
        String line = file.nextLine();
        while ((line.length()==5)) {
            String[] temp = line.split("");
            ruleMaker(temp, rules);
            line = file.nextLine();
        }
        int middle = 0;
        String liner = file.nextLine();
        while (file.hasNextLine()) {
            String[] blah = liner.split(",");
            ArrayList<Integer> pages = new ArrayList<>();
            for (String s: blah) {
                pages.add(Integer.parseInt(s));
            }
            middle+=pageCheck(pages,rules);
            liner=file.nextLine();
        }
        String[] blah = liner.split(",");
        ArrayList<Integer> pages = new ArrayList<>();
        for (String s: blah) {
            pages.add(Integer.parseInt(s));
        }
        middle+=pageCheck(pages,rules);
        System.out.println(middle);
    }

    public static void ruleMaker(String[] temp, HashMap<Integer,ArrayList<Integer>> rules) {
        int first = Integer.parseInt(temp[3]+temp[4]);
        int last = Integer.parseInt(temp[0]+temp[1]);
        if (!rules.containsKey(first)) {
            ArrayList<Integer> name = new ArrayList<>();
            name.add(last);
            rules.put(first, name);
        }
        else {
            rules.get(first).add(last);
        }
    }

    public static Integer pageCheck(ArrayList<Integer> pages, HashMap<Integer,ArrayList<Integer>> rules) {
        boolean safe = true;
        for (int i=0; i<pages.size(); i++) {
            if (rules.containsKey(pages.get(i))) {
                for (int n = i+1; n<pages.size(); n++) {
                    if (rules.get(pages.get(i)).contains(pages.get(n))) {
                        safe = false;
                    }
                }
            }
        }
        if (safe) {
            return 0;
        }
        return reorder(pages, rules);
    }

    public static Integer reorder(ArrayList<Integer> pages, HashMap<Integer,ArrayList<Integer>> rules) {
        ArrayList<Integer> order = new ArrayList<>();
        for (Integer p: pages) {
            boolean added = false;
            for (int i = 0; i<order.size(); i++) {
                if (!order.contains(p)) {
                    if (rules.containsKey(order.get(i))) {
                        if (rules.get(order.get(i)).contains(p)) {
                            order.add(i, p);
                            added = true;
                        }
                    }
                }
            }
            if (!added) {
                order.add(p);
            }
        }
        return order.get(order.size()/2);
    }

    // This method returns a Scanner object for reading from a file.
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
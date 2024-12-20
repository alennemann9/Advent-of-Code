import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Iterator;


class Day3 {
    public static void main(String[] args) {
        int multiplier = 0;
        // read in text file
        String filename = "input3.txt";
        Scanner file = getFileScanner(filename);
        LinkedList<String> mylist = new LinkedList<>();
        // read through the file line-by-line
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] temp = line.split("");
            for (String s : temp) {
                mylist.add(s);
            }
        }

        Iterator<String> iter = mylist.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals("m")) {
                if (iter.next().equals("u")) {
                    if (iter.next().equals("l")) {
                        if (iter.next().equals("(")) {
                            String fnum = iter.next();
                            int count = 0;
                            int tempor = 0;
                            while (numCheck(fnum) && count < 3) {
                                tempor *= 10;
                                tempor += Integer.parseInt(fnum);
                                fnum = iter.next();
                                count++;
                            }
                            if (tempor != 0 && fnum.equals(",")) {
                                String lnum = iter.next();
                                int lcount = 0;
                                int ltempor = 0;
                                while (numCheck(lnum) && lcount < 3) {
                                    ltempor *= 10;
                                    ltempor += Integer.parseInt(lnum);
                                    lnum = iter.next();
                                    lcount++;
                                }
                                if (ltempor != 0 && lnum.equals(")")) {
                                    multiplier += (tempor * ltempor);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(multiplier);
    }

    public static Boolean numCheck(String possible) {
        HashMap<String,String> numbers = new HashMap<>();
        numbers.put("0","");
        numbers.put("1","");
        numbers.put("2","");
        numbers.put("3","");
        numbers.put("4","");
        numbers.put("5","");
        numbers.put("6","");
        numbers.put("7","");
        numbers.put("8","");
        numbers.put("9","");
        if (numbers.containsKey(possible)) {
            return true;
        }
        return false;
    }


    // This method returns a Scanner object for reading from a file.
    public static Scanner getFileScanner(String fileName) {
        try {
            FileInputStream textFileStream = new FileInputStream(fileName);
            Scanner inputFile = new Scanner(textFileStream);
            return inputFile;
        }
        catch (IOException ex) {
            System.out.println("Warning: could not open " + fileName);
            return null;
        }
    }

}
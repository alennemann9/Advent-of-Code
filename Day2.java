
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Day2 {
    public static void main(String[] args) {
        ArrayList<String> lineArray = new ArrayList<>();
        String filename = "input2.txt";
        Scanner file = getFileScanner(filename);
        int safeCount = 0;

        // read through the file line-by-line
        while (file.hasNextLine()) {
            String line = file.nextLine();

            // TODO: implement the solution here
            lineArray.add(line);
        }
        for (String list : lineArray) {
            String[] temp = list.split(" ");
            ArrayList<Integer> code = new ArrayList<>();
            for (String item : temp) {
                code.add(Integer.parseInt(item));
            }
            if (saferCode(code)) {
                safeCount++;
            }
            else {
                int counter = 0;
                for (int i = 0; i < code.size(); i++) {
                    if (safeCode(code, i)) {
                        counter++;
                    }
                }
                if (counter>0) {
                    safeCount++;
                }
            }
        }
        System.out.println(safeCount);
    }

    // This method returns a Scanner object for reading from a file.
    public static Scanner getFileScanner(String fileName) {
        try {
            FileInputStream textFileStream = new FileInputStream(fileName);
            Scanner inputFile = new Scanner(textFileStream);
            return inputFile;
        } catch (IOException ex) {
            System.out.println("Warning: could not open " + fileName);
            return null;
        }
    }

    public static Boolean ascending(ArrayList<Integer> code) {
        boolean asc = true;
        for (int i = 1; i < code.size(); i++) {
            if (code.get(i) <= code.get(i - 1)) {
                asc = false;
            }
        }
        return asc;
    }

    public static Boolean descending(ArrayList<Integer> code) {
        boolean desc = true;
        for (int i = 1; i < code.size(); i++) {
            if (code.get(i) >= code.get(i - 1)) {
                desc = false;
            }
        }
        return desc;
    }

    public static Boolean slowasc(ArrayList<Integer> code) {
        boolean small = true;
        for (int i = 1; i < code.size(); i++) {
            if ((code.get(i) - code.get(i - 1)) > 3) {
                small = false;
            }
        }
        return small;
    }

    public static Boolean slowdesc(ArrayList<Integer> code) {
        boolean small = true;
        for (int i = 1; i < code.size(); i++) {
            if ((code.get(i - 1) - code.get(i)) > 3) {
                small = false;
            }
        }
        return small;
    }

    public static Boolean safeCode(ArrayList<Integer> Newcode, Integer missing) {
        boolean safe = false;
        ArrayList<Integer> code = new ArrayList<>();
        for (int i = 0; i<Newcode.size(); i++) {
            if (i!=missing) {
                code.add(Newcode.get(i));
            }
        }
        if (ascending(code) || descending(code)) {
            if (ascending(code)) {
                if (slowasc(code)) {
                    safe = true;
                }
            }
            else {
                if (slowdesc(code)) {
                    safe = true;
                }
            }
        }
        return safe;
    }

    public static Boolean saferCode(ArrayList<Integer> code) {
        boolean safe = false;
        if (ascending(code) || descending(code)) {
            if (ascending(code)) {
                if (slowasc(code)) {
                    safe = true;
                }
            }
            else {
                if (slowdesc(code)) {
                    safe = true;
                }
            }
        }
        return safe;
    }
}
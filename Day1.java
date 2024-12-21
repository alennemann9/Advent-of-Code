import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        Scanner scnr = getFileScanner("input1.txt");
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        int total = 0;
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            String[] temp = line.split("   ");
            leftList.add(Integer.parseInt(temp[0]));
            rightList.add(Integer.parseInt(temp[1]));
        }
        for (Integer l: leftList) {
            int occurs = 0;
            for (Integer r: rightList) {
                if (l.equals(r)) {
                    occurs++;
                }
            }
            total += l*occurs;
        }
        System.out.println(total);
    }

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

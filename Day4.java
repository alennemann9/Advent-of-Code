import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;

public class Day4 {
    public static void main(String[] args) {
        String filename = "input4.txt";
        Scanner scnr = getFileScanner(filename);
        HashMap<Integer, ArrayList<String>> puzzle = new HashMap<>();
        int count = 0;
        int val = 0;
        // Reading the grid from file
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            String[] temp = line.split("");
            ArrayList<String> lineList = new ArrayList<>(Arrays.asList(temp));
            puzzle.put(val, lineList);
            val++;
        }

        // Checking all directions for "XMAS"
        for (int row = 0; row < puzzle.size(); row++) {
            for (int column = 0; column < puzzle.get(row).size(); column++) {
                if (puzzle.get(row).get(column).equals("X")) {
                    if (RightCheck(puzzle.get(row), column)) {
                        count++;
                    }
                    if (LeftCheck(puzzle.get(row), column)) {
                        count++;
                    }
                    if (UpCheck(puzzle, column, row)) {
                        count++;
                    }
                    if (DownCheck(puzzle, column, row)) {
                        count++;
                    }
                    if (UpLeftCheck(puzzle, column, row)) {
                        count++;
                    }
                    if (UpRightCheck(puzzle, column, row)){
                        count++;
                    }
                    if (DownRightCheck(puzzle, column, row)) {
                        count++;
                    }
                    if (DownLeftCheck(puzzle, column, row)) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    public static Boolean RightCheck(ArrayList<String> line, int column) {
        if (column <= line.size() - 4) {
            String mas = line.get(column + 1) + line.get(column + 2) + line.get(column + 3);
            return "MAS".equals(mas);
        }
        return false;
    }

    public static Boolean LeftCheck(ArrayList<String> line, int column) {
        if (column >= 3) {
            String mas = line.get(column - 1) + line.get(column - 2) + line.get(column - 3);
            return "MAS".equals(mas);
        }
        return false;
    }

    public static Boolean UpCheck(HashMap<Integer, ArrayList<String>> puzzle, int column, int row) {
        if (row >= 3) {
            String mas = puzzle.get(row - 1).get(column) + puzzle.get(row - 2).get(column) + puzzle.get(row - 3).get(column);
            return "MAS".equals(mas);
        }
        return false;
    }

    public static Boolean DownCheck(HashMap<Integer, ArrayList<String>> puzzle, int column, int row) {
        if (row <= puzzle.size() - 4) {
            String mas = puzzle.get(row + 1).get(column) + puzzle.get(row + 2).get(column) + puzzle.get(row + 3).get(column);
            return "MAS".equals(mas);
        }
        return false;
    }

    public static Boolean UpRightCheck(HashMap<Integer, ArrayList<String>> puzzle, int column, int row) {
        if (row >= 3 && column <= puzzle.get(row).size() - 4) {
            String mas = puzzle.get(row - 1).get(column + 1) + puzzle.get(row - 2).get(column + 2) + puzzle.get(row - 3).get(column + 3);
            return "MAS".equals(mas);
        }
        return false;
    }

    public static Boolean UpLeftCheck(HashMap<Integer, ArrayList<String>> puzzle, int column, int row) {
        if (row >= 3 && column >= 3) {
            String mas = puzzle.get(row - 1).get(column - 1) + puzzle.get(row - 2).get(column - 2) + puzzle.get(row - 3).get(column - 3);
            return "MAS".equals(mas);
        }
        return false;
    }

    public static Boolean DownRightCheck(HashMap<Integer, ArrayList<String>> puzzle, int column, int row) {
        if (row <= puzzle.size() - 4 && column <= puzzle.get(row).size() - 4) {
            String mas = puzzle.get(row + 1).get(column + 1) + puzzle.get(row + 2).get(column + 2) + puzzle.get(row + 3).get(column + 3);
            return "MAS".equals(mas);
        }
        return false;
    }

    public static Boolean DownLeftCheck(HashMap<Integer, ArrayList<String>> puzzle, int column, int row) {
        if (row <= puzzle.size() - 4 && column >= 3) {
            String mas = puzzle.get(row + 1).get(column - 1) + puzzle.get(row + 2).get(column - 2) + puzzle.get(row + 3).get(column - 3);
            return "MAS".equals(mas);
        }
        return false;
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

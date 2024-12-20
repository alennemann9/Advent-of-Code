import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        Scanner scnr = getFileScanner("input7.txt");
        BigInteger total = BigInteger.ZERO;  // Use BigInteger for total
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine();
            String[] temp = line.split(": ");
            BigInteger answer = new BigInteger(temp[0]); // Convert to BigInteger
            ArrayList<BigInteger> numbers = getanswer(temp);
            ArrayList<String> process = doprocess(numbers.size()-1);
            if (works(answer, numbers, process)) {
                total = total.add(answer); // Use add for BigInteger addition
            }
        }
        System.out.println(total);
    }

    public static ArrayList<BigInteger> getanswer(String[] temp) {
        String[] templine = temp[1].split(" ");
        ArrayList<BigInteger> numbers = new ArrayList<>();
        for (String s : templine) {
            numbers.add(new BigInteger(s)); // Convert each number to BigInteger
        }
        return numbers;
    }

    public static ArrayList<String> doprocess(int numlength) {
        ArrayList<String> process = new ArrayList<>();
        int max = (int)Math.pow(3,numlength);
        for (int i = 0; i<max;i++) {
            process.add(getprocess(i,numlength));
        }
        return process;
    }

    public static String getprocess(int number, int numlength) {
        StringBuilder ternary = new StringBuilder();
        while (number > 0) {
            ternary.insert(0, number % 3);
            number /= 3;
            }
        while (ternary.length() < numlength) {
            ternary.insert(0, "0");
        }
        return ternary.toString();
    }

    public static Boolean works(BigInteger answer, ArrayList<BigInteger> numbers, ArrayList<String> process) {
        for (String p : process) {
            BigInteger total = BigInteger.ZERO; // Initialize total as BigInteger
            for (int i = 0; i < numbers.size() - 2; i++) {
                BigInteger a = (total.equals(BigInteger.ZERO)) ? numbers.get(i) : total;
                BigInteger b = numbers.get(i + 1);
                int x = Integer.parseInt(p.substring(i, i + 1));
                switch (x) {
                    case 0:
                        total = a.multiply(b); // Multiply using BigInteger
                        break;
                    case 1:
                        total = a.add(b); // Add using BigInteger
                        break;
                    case 2:
                        total = new BigInteger(a.toString()+b.toString());
                }
            }
            BigInteger a = (total.equals(BigInteger.ZERO)) ? numbers.get(numbers.size() - 2) : total;
            BigInteger b = numbers.get(numbers.size() - 1);
            int x = Integer.parseInt(p.substring(numbers.size() - 2));
            switch (x) {
                case 0:
                    total = a.multiply(b); // Multiply using BigInteger
                    break;
                case 1:
                    total = a.add(b); // Add using BigInteger
                    break;
                case 2:
                    total = new BigInteger(a.toString()+b.toString());
            }
            if (answer.equals(total)) { // Use equals for BigInteger comparison
                return true;
            }
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
package SCal.util;
import java.io.*;
import java.util.*;

public class CSVWriter {

    public static boolean writeToCSV(String fileName, List<String> lines) {

        if (lines == null || lines.isEmpty()) {
            System.out.println("No history to save.");
            return false;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {

            pw.println("S.No,Record");

            int i = 1;
            for (String line : lines) {
                // Replace comma in line to avoid CSV break
                String safeLine = line.replace(",", ";");
                pw.println(i + "," + safeLine);
                i++;
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}

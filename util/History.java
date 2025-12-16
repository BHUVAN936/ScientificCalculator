package SCal.util;
import java.util.*;

public class History {

    private static List<String> historyList = new ArrayList<>();

    public static void add(String record) {
        historyList.add(record);
    }

    public static void printHistory() {
        if (historyList.isEmpty()) {
            System.out.println("No history yet.");
            return;
        }

        System.out.println("\n------ HISTORY ------");
        int i = 1;
        for (String s : historyList) {
            System.out.println(i + ". " + s);
            i++;
        }
    }

    public static List<String> getHistoryList() {
        return historyList;
    }
}

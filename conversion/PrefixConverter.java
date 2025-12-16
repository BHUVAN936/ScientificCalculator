package SCal.conversion;
import java.util.*;

public class PrefixConverter {

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") ||
               s.equals("/") || s.equals("^") ||
               s.equals("sin") || s.equals("cos") || s.equals("tan") ||
               s.equals("log") || s.equals("sqrt");
    }

    // PREFIX → INFIX
    public static String prefixToInfix(String expr) {

        String[] tokens = expr.trim().split("\\s+");
        Stack<String> stack = new Stack<>();

        // Scan from right to left
        for (int i = tokens.length - 1; i >= 0; i--) {
            String t = tokens[i];

            if (!isOperator(t)) {
                stack.push(t);
            } else {

                if (t.equals("sin") || t.equals("cos") || t.equals("tan") ||
                    t.equals("log") || t.equals("sqrt")) {

                    if (stack.isEmpty()) return "Invalid Expression";
                    String a = stack.pop();
                    String res = t + "(" + a + ")";
                    stack.push(res);
                } else {
                    if (stack.size() < 2) return "Invalid Expression";
                    String a = stack.pop();
                    String b = stack.pop();
                    String res = "(" + a + " " + t + " " + b + ")";
                    stack.push(res);
                }
            }
        }

        if (stack.size() != 1) return "Invalid Expression";

        return stack.pop();
    }

    // PREFIX → POSTFIX
    public static String prefixToPostfix(String expr) {

        String[] tokens = expr.trim().split("\\s+");
        Stack<String> stack = new Stack<>();

        // Scan from right to left
        for (int i = tokens.length - 1; i >= 0; i--) {
            String t = tokens[i];

            if (!isOperator(t)) {
                stack.push(t);
            } else {

                if (t.equals("sin") || t.equals("cos") || t.equals("tan") ||
                    t.equals("log") || t.equals("sqrt")) {

                    if (stack.isEmpty()) return "Invalid Expression";
                    String a = stack.pop();
                    String res = a + " " + t;
                    stack.push(res);
                } else {
                    if (stack.size() < 2) return "Invalid Expression";
                    String a = stack.pop();
                    String b = stack.pop();
                    String res = a + " " + b + " " + t;
                    stack.push(res);
                }
            }
        }

        if (stack.size() != 1) return "Invalid Expression";

        return stack.pop();
    }
}

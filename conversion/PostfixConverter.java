package SCal.conversion;
import java.util.*;

public class PostfixConverter {

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") ||
               s.equals("/") || s.equals("^") ||
               s.equals("sin") || s.equals("cos") || s.equals("tan") ||
               s.equals("log") || s.equals("sqrt");
    }

    public static String postfixToInfix(String expr) {

        String[] tokens = expr.trim().split("\\s+");
        Stack<String> stack = new Stack<>();

        for (String t : tokens) {

            if (!isOperator(t)) {
                // operand
                stack.push(t);
            } else {

                // function (unary)
                if (t.equals("sin") || t.equals("cos") || t.equals("tan") ||
                    t.equals("log") || t.equals("sqrt")) {

                    if (stack.isEmpty()) return "Invalid Expression";
                    String a = stack.pop();
                    String res = t + "(" + a + ")";
                    stack.push(res);
                }
                // normal operator (binary)
                else {
                    if (stack.size() < 2) return "Invalid Expression";
                    String b = stack.pop();
                    String a = stack.pop();
                    String res = "(" + a + " " + t + " " + b + ")";
                    stack.push(res);
                }
            }
        }

        if (stack.size() != 1) return "Invalid Expression";

        return stack.pop();
    }
}

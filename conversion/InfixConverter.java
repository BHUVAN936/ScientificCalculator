package SCal.conversion;
import java.util.*;

public class InfixConverter {

    // Precedence of operators
    private static int precedence(String op) {
        if (op.equals("sin") || op.equals("cos") || op.equals("tan") ||
            op.equals("log") || op.equals("sqrt"))
            return 4;
        if (op.equals("^"))
            return 3;
        if (op.equals("*") || op.equals("/"))
            return 2;
        if (op.equals("+") || op.equals("-"))
            return 1;
        return 0;
    }

    // Check if operator or function
    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") ||
               s.equals("/") || s.equals("^") ||
               s.equals("sin") || s.equals("cos") || s.equals("tan") ||
               s.equals("log") || s.equals("sqrt");
    }

    // INFIX → POSTFIX
    public static List<String> infixToPostfix(String expr) {

        Stack<String> stack = new Stack<>();
        List<String> output = new ArrayList<>();

        int i = 0;

        while (i < expr.length()) {

            char ch = expr.charAt(i);

            // Skip space
            if (ch == ' ') {
                i++;
                continue;
            }

            // Number
            if (Character.isDigit(ch) || ch == '.') {
                String number = "";

                while (i < expr.length() &&
                      (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    number += expr.charAt(i);
                    i++;
                }

                output.add(number);
                continue;
            }

            // Function name
            if (Character.isLetter(ch)) {
                String func = "";

                while (i < expr.length() && Character.isLetter(expr.charAt(i))) {
                    func += expr.charAt(i);
                    i++;
                }

                stack.push(func);
                continue;
            }

            // Opening bracket
            if (ch == '(') {
                stack.push("(");
                i++;
                continue;
            }

            // Closing bracket
            if (ch == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // remove '('
                }
                i++;
                continue;
            }

            // Operator
            String op = Character.toString(ch);

            if (isOperator(op)) {
                while (!stack.isEmpty() &&
                       isOperator(stack.peek()) &&
                       precedence(stack.peek()) >= precedence(op)) {
                    output.add(stack.pop());
                }
                stack.push(op);
            }

            i++;
        }

        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }

        return output;
    }
}

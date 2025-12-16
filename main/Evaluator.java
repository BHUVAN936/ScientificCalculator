package SCal.main;
import java.util.*;

public class Evaluator {

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") ||
               s.equals("/") || s.equals("^") ||
               s.equals("sin") || s.equals("cos") || s.equals("tan") ||
               s.equals("log") || s.equals("sqrt");
    }

    public static double evaluatePostfix(List<String> postfix) {

        Stack<Double> stack = new Stack<>();

        for (String t : postfix) {

            // Number
            if (t.matches("[0-9.]+")) {
                stack.push(Double.parseDouble(t));
            }

            // Operator or function
            else if (isOperator(t)) {

                if (t.equals("sin")) stack.push(Math.sin(stack.pop()));
                else if (t.equals("cos")) stack.push(Math.cos(stack.pop()));
                else if (t.equals("tan")) stack.push(Math.tan(stack.pop()));
                else if (t.equals("log")) stack.push(Math.log(stack.pop()));
                else if (t.equals("sqrt")) stack.push(Math.sqrt(stack.pop()));
                else {
                    double b = stack.pop();
                    double a = stack.pop();

                    if (t.equals("+")) stack.push(a + b);
                    else if (t.equals("-")) stack.push(a - b);
                    else if (t.equals("*")) stack.push(a * b);
                    else if (t.equals("/")) stack.push(a / b);
                    else if (t.equals("^")) stack.push(Math.pow(a, b));
                }
            }
        }

        return stack.pop();
    }
}

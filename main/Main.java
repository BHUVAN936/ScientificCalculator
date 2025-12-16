package SCal.main;
import java.util.*;

import SCal.conversion.InfixConverter;
import SCal.conversion.PostfixConverter;
import SCal.conversion.PrefixConverter;
import SCal.util.CSVWriter;
import SCal.util.History;

public class Main extends CalculatorBase implements ExpressionOperation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main app = new Main();

        System.out.println("===== SCIENTIFIC CALCULATOR PROJECT =====");

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n----------------- MENU -----------------");
            System.out.println("1. Set / Update Variables");
            System.out.println("2. Evaluate Infix Expression");
            System.out.println("3. Convert Postfix → Infix");
            System.out.println("4. Convert Prefix → Infix");
            System.out.println("5. Convert Prefix → Postfix");
            System.out.println("6. Show History");
            System.out.println("7. Save History to CSV");
            System.out.println("Press any key for Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {

                case 1:
                    app.setVariables(sc);
                    break;

                case 2:
                    app.execute(sc); // Interface method
                    break;

                case 3:
                    app.convertPostfixToInfix(sc);
                    break;

                case 4:
                    app.convertPrefixToInfix(sc);
                    break;

                case 5:
                    app.convertPrefixToPostfix(sc);
                    break;

                case 6:
                    History.printHistory();
                    break;

                case 7:
                    app.saveHistoryToCSV(sc);
                    break;

                default:
                    System.out.println("Exiting... Thank you!");
            }
        }

        sc.close();
    }

    // Interface method
    @Override
    public void execute(Scanner sc) {
        evaluateInfixExpression(sc);
    }

    private void setVariables(Scanner sc) {
        System.out.print("How many variables do you want to enter? : ");
        int n;

        try {
            n = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter variable name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter value for " + name + ": ");
            try {
                double value = Double.parseDouble(sc.nextLine());
                variables.put(name, value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid value. Skipping.");
            }
        }

        System.out.println("Variables updated: " + variables);
    }

    private void evaluateInfixExpression(Scanner sc) {
        System.out.print("Enter Infix Expression: ");
        String expr = sc.nextLine();

        String replacedExpr = replaceVariables(expr);

        List<String> postfix = InfixConverter.infixToPostfix(replacedExpr);
        double result = Evaluator.evaluatePostfix(postfix);

        System.out.println("Result = " + result);
        History.add("Infix: " + expr + " | Result: " + result);
    }

    private void convertPostfixToInfix(Scanner sc) {
        System.out.print("Enter Postfix Expression: ");
        String postfixExpr = sc.nextLine();

        String infix = PostfixConverter.postfixToInfix(postfixExpr);
        System.out.println("Infix = " + infix);
        History.add("Postfix: " + postfixExpr + " | Infix: " + infix);
    }

    private void convertPrefixToInfix(Scanner sc) {
        System.out.print("Enter Prefix Expression: ");
        String prefixExpr = sc.nextLine();

        String infix = PrefixConverter.prefixToInfix(prefixExpr);
        System.out.println("Infix = " + infix);
        History.add("Prefix: " + prefixExpr + " | Infix: " + infix);
    }

    private void convertPrefixToPostfix(Scanner sc) {
        System.out.print("Enter Prefix Expression: ");
        String prefixExpr = sc.nextLine();

        String postfix = PrefixConverter.prefixToPostfix(prefixExpr);
        System.out.println("Postfix = " + postfix);
        History.add("Prefix: " + prefixExpr + " | Postfix: " + postfix);
    }

    private void saveHistoryToCSV(Scanner sc) {
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();

        if (CSVWriter.writeToCSV(fileName, History.getHistoryList())) {
            System.out.println("History saved.");
        } else {
            System.out.println("Error saving history.");
        }
    }
}

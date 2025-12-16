package SCal.main;
import java.util.HashMap;
import java.util.Map;

public abstract class CalculatorBase {

    protected static Map<String, Double> variables = new HashMap<>();

    protected String replaceVariables(String expr) {
        String result = expr;
        for (String key : variables.keySet()) {
            result = result.replace(key, variables.get(key).toString());
        }
        return result;
    }
}

/**
 * Evaluate arithmetic expression by Dijiksta's two-stack algorithm.
 * @author NGUYEN HOANG NAM ANH
 */
public class Evaluator {
    private FixedCapacityStack<Integer> valueStack;
    private FixedCapacityStack<Character> operatorStack;
    private FixedCapacityStack<Character> openingParenStack;
    private final static String OPERATORS = "+-*/";
    private final static String CLOSING_PAREN = ")]}";
    private final static String OPENING_PAREN = "([{";

    public Evaluator(){
        valueStack = new FixedCapacityStack<Integer>();
        operatorStack = new FixedCapacityStack<Character>();
        openingParenStack = new FixedCapacityStack<Character>();
    }

    public int evaluate(String expr){
        if (expr == null) throw new IllegalArgumentException("expression cannot be empty");
        if (isValid(expr)){
            for (int i = 0; i < expr.length(); i++){
                char c = expr.charAt(i);
                if (Character.isDigit(c)) valueStack.push(Character.getNumericValue(c));
                else if (OPERATORS.indexOf(c) != -1) operatorStack.push(c);
                else if (CLOSING_PAREN.indexOf(c) != -1){
                    int a = valueStack.pop();
                    int b = valueStack.pop();
                    char opr = operatorStack.pop();
                    valueStack.push(evaluateHelper(a, b, opr));
                }
            }
        return valueStack.pop();
        }
        throw new IllegalArgumentException("Invalid expression");

    }

    private boolean isValid (String expr){
        if (expr == null) throw new IllegalArgumentException("expression cannot be empty");
        for (int i = 0; i < expr.length(); i++){
            char c = expr.charAt(i);
            if (CLOSING_PAREN.indexOf(c) != -1){
                if (openingParenStack.isEmpty()
                || OPENING_PAREN.indexOf(openingParenStack.pop()) != CLOSING_PAREN.indexOf(c)){
                    return false;
                }
            }
            else if (OPENING_PAREN.indexOf(c) != -1){
                openingParenStack.push(c);
            }
            else if (OPERATORS.indexOf(c) != -1){
                char b = expr.charAt(i-1);
                char a = expr.charAt(i+1);
                if (!((Character.isDigit(b) || CLOSING_PAREN.indexOf(b) != -1)
                    && (Character.isDigit(a) || OPENING_PAREN.indexOf(a) != -1))){
                    return false;
                }
            }


        }
        return true;
    }

    public int evaluateHelper(int a, int b, char opr){
        switch (opr){
            case '+':
                 return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            default:
                return a/b;
        }
    }

}

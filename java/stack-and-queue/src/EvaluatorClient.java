import edu.princeton.cs.algs4.StdIn;

/**
 * Client of Evaluator
 */
public class EvaluatorClient {
    public static void main(String[] args){
        Evaluator evaluator = new Evaluator();
        while (!StdIn.isEmpty()){
            String expr = StdIn.readLine().strip();
            System.out.println(evaluator.evaluate(expr));
        }
    }
}

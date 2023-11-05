import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InferenceEngine inferenceEngine = new InferenceEngine();
        Scanner in = new Scanner(System.in);

        inferenceEngine.addRule(new ModusPonens());
        inferenceEngine.addRule(new ModusTollens());
        inferenceEngine.addRule(new HypotheticalSyllogism());
        inferenceEngine.addRule(new DisjunctiveSyllogism());
        inferenceEngine.addRule(new Resolution());

        while (true){
            System.out.println("Enter first Expression (Empty to stop the program)");
            String exp1 = in.nextLine();
            if(exp1.isEmpty()) break;
            inferenceEngine.addExpression(new Expression(exp1));

            System.out.println("Enter Second Expression");
            String exp2 = in.nextLine();
            inferenceEngine.addExpression(new Expression(exp2));

            Expression result = inferenceEngine.applyRules();
            if(!result.getRepresentation().isEmpty())
                System.out.println(result.getRepresentation());
            else
                System.out.println("The input expression cannot be inferred");
        }
    }
}
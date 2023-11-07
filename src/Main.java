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

        do {
            Expression e1, e2;
            do {
                System.out.println("Enter first Expression");
                String exp1 = in.nextLine();
                e1 = new Expression(exp1);
                if(!e1.isValid()) System.out.println("invalid Expression");
            } while (!e1.isValid());

            inferenceEngine.addExpression(e1);

            do {
                System.out.println("Enter Second Expression");
                String exp2 = in.nextLine();
                e2 = new Expression(exp2);
                if(!e2.isValid()) System.out.println("invalid Expression");
            } while (!e2.isValid());

            inferenceEngine.addExpression(e2);

            Expression result = inferenceEngine.applyRules();
            if (!result.getRepresentation().isEmpty())
                System.out.println(result.getRepresentation());
            else
                System.out.println("The input expression cannot be inferred");

            System.out.println("do you Want to Continue (y/n)");

        } while (in.nextLine().charAt(0) == 'y');
    }
}

import java.util.*;

public class InferenceEngine implements IInferenceEngine {
    private List<InferenceRule> rules;
    private List<Expression> expressions;
    public InferenceEngine(){
        this.rules = new ArrayList<>();
        this.expressions = new ArrayList<>();
    }
    public void  addRule(InferenceRule rule){
        this.rules.add(rule);
    }
    public void addExpression(Expression expression){
        this.expressions.add(expression);
    }
    public Expression applyRules(){

        Expression exp1 = expressions.get(expressions.size()-1);
        Expression exp2 = expressions.get(expressions.size()-2);
        Expression res = new Expression();
        for(InferenceRule rule : rules){
            if(rule.matches(exp1,exp2)){
                res =  rule.apply(exp1,exp2);
                System.out.print("Solved by (" + rule.getRuleName()+ ") - " );
                break;
            }
        }
        return res;
    }
}

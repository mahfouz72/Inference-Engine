public class HypotheticalSyllogism implements InferenceRule {
    private String ruleName;
    public HypotheticalSyllogism(){
        this.ruleName = "hypothetical syllogism";
    }
    private boolean matchesWithOrder = true; // the rule matches with the same order of the parameters
    private boolean matchesWithReverse = true; // the rule matches with the reverse order of the parameters
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String ex1 = exp1.getRepresentation(),ex2 = exp2.getRepresentation();
        if(!ex1.contains(">") || !ex2.contains(">")) return false;

        int operatorIndex1 = ex1.indexOf(">"),operatorIndex2 = ex2.indexOf(">"),j = 0;

        for(int i = operatorIndex1+1;i < ex1.length();i++){
            if(ex1.charAt(i) != ex2.charAt(j++))
                matchesWithOrder = false;
        }
        j = 0;
        for(int i = operatorIndex2+1;i < ex2.length();i++){
            if(ex2.charAt(i) != ex1.charAt(j++)) matchesWithReverse = false;
        }
        return matchesWithOrder || matchesWithReverse;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        StringBuilder result = new StringBuilder();
        String ex1 = exp1.getRepresentation(),ex2 = exp2.getRepresentation();
        if(matchesWithOrder){
            for(int i = 0; i < ex1.length();i++){
                result.append(ex1.charAt(i));
                if(ex1.charAt(i) == '>'){
                    break;
                }
            }
            for(int i = ex2.indexOf(">") + 1; i < ex2.length();i++){
                result.append(ex2.charAt(i));
            }
        }
        else if(matchesWithReverse){
            for(int i = 0; i < ex2.length();i++){
                result.append(ex2.charAt(i));
                if(ex2.charAt(i) == '>'){
                    break;
                }
            }
            for(int i = ex1.indexOf(">") + 1; i < ex1.length();i++){
                result.append(ex1.charAt(i));
            }
        }
        return new Expression(result.toString());

    }

    public String getRuleName() {
        return ruleName;
    }
}

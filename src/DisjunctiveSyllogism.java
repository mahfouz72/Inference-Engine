public class DisjunctiveSyllogism implements InferenceRule{
    private String ruleName;
    private String ans = " "; // answer is in which term
    public DisjunctiveSyllogism(){
        this.ruleName = "Disjunctive Syllogism";
    }
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        int l1 = exp1.getRepresentation().length();
        int l2 = exp2.getRepresentation().length();
        String longerExp , shorterExp;

        if(l1 > l2){
            longerExp = exp1.getRepresentation();
            shorterExp = exp2.getRepresentation();
        }else if(l1 < l2){
            longerExp = exp2.getRepresentation();
            shorterExp = exp1.getRepresentation();
        }
        else return false;

        if(!longerExp.contains("v") || shorterExp.length() > 2) return false;

        if(shorterExp.charAt(0) == '~'){
            if(shorterExp.charAt(1) == longerExp.charAt(0)){
                ans = "second";
                return true;
            }else if(shorterExp.charAt(1) == longerExp.charAt(longerExp.length()-1) && longerExp.charAt(longerExp.length()-2) == 'v'){
                ans = "first";
                return true;
            }
        }
        if(longerExp.charAt(0) == '~') {
            if (shorterExp.charAt(0) == longerExp.charAt(1)) {
                ans = "second";
                return true;
            }
        }
        if(longerExp.charAt(2) == '~'){
            if(shorterExp.charAt(0) == longerExp.charAt(3)){
                ans = "first";
                return true;
            }
        }
        if(longerExp.length() > 3 && longerExp.charAt(3) == '~'){
            if(shorterExp.charAt(0) == longerExp.charAt(4)){
                ans = "first";
                return true;
            }
        }
        return false;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        int l1 = exp1.getRepresentation().length();
        int l2 = exp2.getRepresentation().length();
        String longerExp = l1 > l2 ? exp1.getRepresentation() : exp2.getRepresentation();
        int operatorIndex= longerExp.indexOf("v");
        return ans.equals("first") ?
                new Expression(longerExp.substring(0,operatorIndex)):new Expression(longerExp.substring(operatorIndex+1));
    }
    public String getRuleName() {
        return ruleName;
    }
}

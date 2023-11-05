public class ModusPonens implements InferenceRule {
    private String ruleName;
    public ModusPonens(){
        this.ruleName = "Modus Ponens";
    }
    // P > Q   P --> Q
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String longer, shorter;
        int l1 = exp1.getRepresentation().length(),l2 = exp2.getRepresentation().length(),i;
        if(l1 > l2){
            longer = exp1.getRepresentation();
            shorter = exp2.getRepresentation();
        }else if(l2 > l1){
            longer = exp2.getRepresentation();
            shorter = exp1.getRepresentation();
        }else{
            return  false;
        }
        for(i = 0; i < shorter.length();i++){
            if(longer.charAt(i) != shorter.charAt(i)) return false;
        }
        return longer.charAt(i) == '>';
    }
    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String taller,result;
        int l1 = exp1.getRepresentation().length(),l2 = exp2.getRepresentation().length(),i;
        taller = l1 > l2 ?  exp1.getRepresentation():exp2.getRepresentation();

        result = taller.substring(taller.indexOf('>')+1);
        return new Expression(result);
    }

    public String getRuleName() {
        return ruleName;
    }
}

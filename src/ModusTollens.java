public class ModusTollens implements InferenceRule{

        private String ruleName;
        public ModusTollens(){
            this.ruleName = "Modus Tollens";
        }
        @Override
        public boolean matches(Expression exp1,Expression exp2){
            String tallerExpression,shorterExpression;
            if(exp1.getRepresentation().length()>exp2.getRepresentation().length()){
                tallerExpression=exp1.getRepresentation();
                shorterExpression=exp2.getRepresentation();
            }
            else if(exp1.getRepresentation().length()<exp2.getRepresentation().length()){
                shorterExpression=exp1.getRepresentation();
                tallerExpression=exp2.getRepresentation();
            }
            else{
                return false;
            }
            boolean conclusionInShort=true;
            if(shorterExpression.charAt(0)=='~'){
                conclusionInShort=false;
            }
            int i=tallerExpression.indexOf('>');
            if(i!=-1){
                i++;
                if(tallerExpression.charAt(i)=='~' ^ conclusionInShort) {
                    return false;
                }
                int c=conclusionInShort?0:1;
                i+=conclusionInShort?1:0;
                for(;i<tallerExpression.length();i++) {
                    if(c==shorterExpression.length() || tallerExpression.charAt(i)!=shorterExpression.charAt(c)){
                        return false;
                    }
                    c++;
                }
            }
            return i!=-1;
        }
        @Override
        public Expression apply(Expression exp1, Expression exp2) {
            String taller,result="";
            int l1 = exp1.getRepresentation().length(),l2 = exp2.getRepresentation().length(),i;
            if(l1 > l2){
                taller = exp1.getRepresentation();
            }else{
                taller = exp2.getRepresentation();
            }
            if(taller.charAt(0)!='~'){
                result="~";
                result += taller.substring(0,taller.indexOf('>'));
            }
            else{
                result += taller.substring(1,taller.indexOf('>'));
            }

            return new Expression(result);
        }

    public String getRuleName() {
        return ruleName;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Resolution implements InferenceRule {

    private String ruleName;
    public Resolution(){
        this.ruleName = "Resolution";
    }
    private int indexOfRepeatedVarExp1=-1,indexOfRepeatedVarExp2=-1;
    private String expression1,expression2;
   private boolean isOperator(char operator){
        if(operator=='~'  || operator=='v'){
            return true;
        }
        return false;
    }
    @Override
    public boolean matches(Expression exp1,Expression exp2){
        expression1=exp1.getRepresentation();
        expression2=exp2.getRepresentation();
        //Counting number of distinct variables in the two expressions
        List<Character>uniqueVariables=new ArrayList<>();
        int numberOfDistinctVariables=0;
        for(int i=0;i<expression1.length();i++){
            if(!isOperator(expression1.charAt(i)) && !uniqueVariables.contains(expression1.charAt(i))){
                uniqueVariables.add(expression1.charAt(i));
            }
        }
        for(int i=0;i<expression2.length();i++){
            if(!isOperator(expression2.charAt(i))&&!uniqueVariables.contains(expression2.charAt(i))){
                uniqueVariables.add(expression2.charAt(i));
            }
            else if (!isOperator(expression2.charAt(i)) && uniqueVariables.contains(expression2.charAt(i))){
                indexOfRepeatedVarExp2=i;
            }
        }
        if(uniqueVariables.size()!=3){
            return false;
        }
        indexOfRepeatedVarExp1=expression1.indexOf(expression2.charAt(indexOfRepeatedVarExp2));
        //PvQ
        //PvR
        if(indexOfRepeatedVarExp1==0 && indexOfRepeatedVarExp2==0){
            return false;
        }
        //PvQ
        //RvP
        else if(indexOfRepeatedVarExp1==0 &&(indexOfRepeatedVarExp2>0 && expression2.charAt(indexOfRepeatedVarExp2-1)!='~')){
            return false;
        }
        else if(indexOfRepeatedVarExp2==0 && (indexOfRepeatedVarExp1>0 && expression1.charAt(indexOfRepeatedVarExp1-1)!='~')){
            return false;
        }
        boolean not1=true,not2=true;
        if(indexOfRepeatedVarExp1>0){
            not1=!(expression1.charAt(indexOfRepeatedVarExp1-1)=='~');
        }
        if(indexOfRepeatedVarExp2>0){
            not2=!(expression2.charAt(indexOfRepeatedVarExp2-1)=='~');
        }
        if(!(not1^not2)){
            return false;
        }
        return true;
    }
    @Override
    public Expression apply(Expression exp1,Expression exp2){
        //for expression1
        String result="";
        for(int i=0;i<expression1.length();i++){
            if(i!=indexOfRepeatedVarExp1 && expression1.charAt(i)!='v'){
                if(!(i+1==indexOfRepeatedVarExp1 && expression1.charAt(i)=='~')){
                    result += expression1.charAt(i);
                }
            }
        }
        result+='v';
        //for expression2
        for(int i=0;i<expression2.length();i++){
            if(i!=indexOfRepeatedVarExp2 && expression2.charAt(i)!='v'){
                if(!(i+1==indexOfRepeatedVarExp2 && expression2.charAt(i)=='~')){
                    result += expression2.charAt(i);
                }
            }
        }
        return new Expression(result);
    }

    public String getRuleName() {
        return ruleName;
    }
}
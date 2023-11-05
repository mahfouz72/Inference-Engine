import java.util.Scanner;
import java.util.Stack;

public class Expression {
    private String representation;
    private Scanner in;
    public Expression(){
        this(" ");
    }
    public Expression(String representation){
        this.representation = normalize(representation);
        in = new Scanner(System.in);
    }
    private String normalize(String representation) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < representation.length(); i++) {
            char currentChar = representation.charAt(i);
            if(isLetter(currentChar)) res.append(Character.toUpperCase(currentChar));
            else res.append(currentChar);
        }
        return res.toString().replace(" ","");
    }
    public void setRepresentation(){

        if(!isValid()) throw new IllegalArgumentException("wrong Expression");
        for(int i = 0; i < representation.length();i++){
            char c = representation.charAt(i);
            if(isLetter(c)){
                System.out.print(c + " = ");
                char value = in.nextLine().equals("true") ? '1':'0';
                representation = representation.replace(c,value);
            }
        }
    }
    public String getRepresentation() {
        return representation;
    }
    private boolean isValid(){

        if(!validParenthesis())  return false;

        for(int i = 0; i < representation.length()-1;i++){

            char a = representation.charAt(i),b = representation.charAt(i+1);

            if(i == 0 && isOperator(a) && a != '~' || (i == representation.length()-2 && isOperator(b)))
                return false;

            if(b == '~' && (a == ')' || isLetter(a))) return false;
            if(isLetter(b) && (a== ')')) return false;
            if(isOperator(a) && isOperator(b) && b != '~' || (isLetter(a) && isLetter(b))) return false;
        }
        return true;
    }
    private boolean isOperator(char a) {
        return a == '~' || a == '^' || a == 'v' || a == '>';
    }
    private boolean isLetter(char c){
        return  Character.isLetter(c) && c != 'v';
    }

    private boolean validParenthesis() {

        Stack<Character> parenthesis=new Stack<>();
        for(int i = 0; i < representation.length();i++){
            char c = representation.charAt(i);
            if(c == '(') parenthesis.push(c);
            else if(c == ')' && !parenthesis.empty()){
                parenthesis.pop();
            }
            else if(c == ')' && parenthesis.empty()){
                return false;
            }
        }
        return parenthesis.empty();
    }
}

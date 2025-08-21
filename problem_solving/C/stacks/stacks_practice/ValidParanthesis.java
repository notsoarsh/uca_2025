import java.util.Stack;

public class ValidParanthesis {
    /**
     * Given a s string of paranthesis , return true if its balanced
     * Sample input- str = "()[]{}"
     * Sample output - true
     */

     public static void main(String[] args) {
        String str = "(()[][]{})";
        System.out.println(isValid(str));
     }
     /**
      * Function that checks if the string is valid
      * @param s - String containing the paranthesis
      * @return boolean based on result
      */
     public static boolean isValid(String s){
        //creating a stack to store the order of paranthesis
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            char curr = ch;
            //checking if the curr bracket is an opening, simply push in the stack
            if(curr == '(' || curr == '{' || curr == '['){
                stack.push(curr);
            }else if( !stack.isEmpty() && (curr == ')' && stack.peek() == '(' //if the current bracket is a closing bracket , and the top is a matching opening bracket , pop
                    || curr == ']' && stack.peek() == '['
                    || curr == '}' && stack.peek() == '{') ){
                stack.pop();
            }else{
                stack.push(curr); 
            }
        }

        //checking if stack is empty then valid
        return stack.isEmpty();
     }
     /**
      * Time Complexity - O(2N)
      * Space Complexity - O(2N)
      */

}

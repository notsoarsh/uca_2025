import java.util.*;

class InfixToPostFix{
    //Question is to convert the infix expression to a post fix exp
    /**
     * Sample input 
     * str = "(a+b)*(c^d-e)"
     * Sample Ouput
     * str = "abcd^e-*+"
     */
public static void main(String[] args){
    Stack<Character> stack = new Stack<>();
    String str = "(a+b)*(c^d-e)";
    String ans = "";
    char[] arr = str.toCharArray(); // Move this outside the loop
    int i = 0 ;
    while(i < str.length()){
      if(Character.isLetter(arr[i])){ // Changed from isDigit to isLetter
           ans += arr[i];
        }else if(arr[i] == '('){
           stack.push(arr[i]);
        }else if(arr[i] == ')'){
           while(!stack.isEmpty() && stack.peek() != '('){
             ans += stack.pop(); // Add the popped operator to answer
           }
            stack.pop(); // Remove the opening parenthesis
        }else{
                //case of operator
                 while (!stack.isEmpty() && stack.peek() != '(' && priority(arr[i]) <= priority(stack.peek())) {
                    ans += stack.pop();
                  }
                 stack.push(arr[i]);
            }
            i++;
        }
        while(!stack.isEmpty()){
            ans += stack.pop();
        }
        System.out.println(ans);
    }

    /**
     * Function to check the priority of the operator
     * @param op operator that is checked for priority
     * @return the priority of the operator
     */
    static int priority(char operator){
        if(operator == '^'){
            return 3;
        }else if(operator == '*' || operator == '/'){
            return 2;
        }else if(operator == '+' || operator == '-'){
            return 1;
        }
        return -1;
    }

    /***
     * Time Complexity - O(2N) (approx)
     * Space Complexity - O(3N)
     */
}

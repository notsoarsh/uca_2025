import java.lang.reflect.Array;
import java.util.ArrayList;

public class ValidParenthesis {
  /**
   * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
   * determine if the input string is valid.
   *
   * An input string is valid if:
   * 1. Open brackets must be closed by the same type of brackets.
   * 2. Open brackets must be closed in the correct order.
   * 3. Every close bracket has a corresponding open bracket of the same type.
   * 
   * @param s - Input string containing parentheses. 1 <= s.length <= 10^4
   * @returns boolean - True if the string is valid, false otherwise.
   */
  public boolean isValid(String s) {
    if (s.length() == 0 || s.length() % 2 != 0) return false;
    ArrayList<Character> ds = new ArrayList<Character>();
    boolean result = helper(0, s, ds);
    System.out.println(ds);
    return result && ds.size() == 0;
  }

  private boolean helper(int index, String s, ArrayList<Character> ds) {
    if (index == s.length()) return true;
    char ch = s.charAt(index);

    if (ch == '[' || ch == '(' || ch == '{') {
      ds.add(ch);
      return helper(index + 1, s, ds);
    } else {
      if (ds.size() > 0 && 
          ((ch == ')' && ds.get(ds.size() - 1) == '(') ||
           (ch == '}' && ds.get(ds.size() - 1) == '{') ||
           (ch == ']' && ds.get(ds.size() - 1) == '['))) {
        ds.remove(ds.size() - 1);
        return helper(index + 1, s, ds);
      } else {
        // Invalid: either stack is empty or brackets don't match
        return false;
      }
    }
  }

  /**
   * Main method for testing the ValidParenthesis class.
   */
  public static void main(String[] args) {
    ValidParenthesis vp = new ValidParenthesis();
    String test1 = "()";
    String test2 = "()[]{}";
    String test3 = "(]";
    String test4 = "([)]";
    String test5 = "{[]}";
    String test6 = ")[";
    System.out.println(vp.isValid(test6));;
    // assert vp.isValid(test1) == true : "Test case 1 failed";
    // assert vp.isValid(test2) == true : "Test case 2 failed";
    // assert vp.isValid(test3) == false : "Test case 3 failed";
    // assert vp.isValid(test4) == false : "Test case 4 failed";
    // assert vp.isValid(test5) == true : "Test case 5 failed";
  }
}
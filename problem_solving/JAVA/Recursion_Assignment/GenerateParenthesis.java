import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GenerateParenthesis {
    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * 
     * Constraints:
     * 1. 1 <= n <= 8
     * 2. The solution set must not contain duplicate combinations.
     * 
     * Example:
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     * 
     * @param n - Number of pairs of parentheses.
     * @returns List<String> - A list of all combinations of well-formed parentheses.
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper(n, n , ans, new StringBuilder());
        return ans;
    }

    private void helper(int open, int close, List<String> ans, StringBuilder str) {
        if (open == 0 && close == 0) {
          ans.add(str.toString());
          return;
        }
        if (open < 0 || close < 0) return;
        int len = str.length();
        //We can open everytime open > 0
        str.append("(");
        helper(open - 1, close, ans, str);
        str.setLength(len);

        //we can only close when close > open
        if (close > open) {
            str.append(")");
            helper(open, close - 1, ans, str);
            str.setLength(len);
        } 
    }

    /**
     * Main method for testing the GenerateParenthesis class.
     */
    public static void main(String[] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        int n = 3;
        List<String> result = gp.generateParenthesis(n);
        // System.out.println(result);
        List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        
        assert result.size() == expected.size() && result.containsAll(expected) : "Test case failed";
    }
}
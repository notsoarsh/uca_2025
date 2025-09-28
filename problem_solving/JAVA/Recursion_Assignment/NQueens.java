public class NQueens {
  /**
   * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
   * such that no two queens attack each other.
   * Given an integer n, return the number of distinct solutions to the n-queens
   * puzzle.
   * 
   * Example:
   * Input n = 4
   * Output: 2
   * Explanation: There are two distinct solutions to the 4-queens puzzle as shown
   * below.
   *
   * [
   * [".Q..", // Solution 1
   * "...Q",
   * "Q...",
   * "..Q."],
   * ["..Q.", // Solution 2
   * "Q...",
   * "...Q",
   * ".Q.."
   * ]
   * ]
   * 
   * Constraints:
   * 1. 1 <= n <= 9
   * 2. You may assume that n is a positive integer.
   *
   * @param n - The size of the chessboard and the number of queens to place.
   * @returns int - The number of distinct solutions to the n-queens puzzle.
   **/
  public int totalNQueens(int n) {
    if (n == 1)
      return 1;
    if (n == 2)
      return 0;
    int[] ans = new int[1];
    boolean[] rows = new boolean[n]; // lookup for row
    boolean[] leftDiag = new boolean[2 * n - 1]; //constant sums i + j for elements on diagnols
    boolean[] rightDiag = new boolean[2 * n - 1]; // constant differences (n - 1) + (j - i) for elements 
    // Creating the board initially
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = '.';
      }
    }

    helper(0, n, board, rows, leftDiag, rightDiag, ans);

    return ans[0];
  }

  private void helper(int c, int n, char[][] board, boolean[] rows, boolean[] leftDiag, boolean[] rightDiag,
      int[] ans) {
    // base case - if we reach the end we have placed all the queens and we can
    // update the ans
    if (c == board.length) {
      ans[0]++;
      return;
    }

    for (int r = 0; r < n; r++) {
      if (board[r][c] == '.') {
        if (canPlace(r, c, n, rows, leftDiag, rightDiag)) {
          board[r][c] = 'Q';
          rows[r] = true;
          leftDiag[r + c] = true;
          rightDiag[(board.length - 1) + (c - r)] = true;
          // call for next row
          helper(c + 1, n, board, rows, leftDiag, rightDiag, ans);
          // backtrack
          board[r][c] = '.';
          rows[r] = false;
          leftDiag[r + c] = false;
          rightDiag[(board.length - 1) + (c - r)] = false;
        }
      }
    }

  }

  private boolean canPlace(int i, int j, int n, boolean[] rows, boolean[] leftDiag, boolean[] rightDiag) {
    if (rows[i] || leftDiag[i + j] || rightDiag[(n - 1) + (j - i)]) {
      return false;
    }
    return true;
  }

  /**
   * Main method for testing the NQueens class.
   */
  public static void main(String[] args) {
    NQueens nQueens = new NQueens();
    System.out.println(nQueens.totalNQueens(4));
    // assert nQueens.totalNQueens(4) == 2 : "Test case 1 failed";
    // assert nQueens.totalNQueens(1) == 1 : "Test case 2 failed";
    // assert nQueens.totalNQueens(5) == 10 : "Test case 3 failed";
  }
}
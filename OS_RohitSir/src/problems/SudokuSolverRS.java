public class SodukoSolverRS {
  public void solveSudoku(char[][] board) {
    // Data structures to track number presence
    Set<Integer>[] rows = new HashSet[9];
    Set<Integer>[] cols = new HashSet[9];
    Set<Integer>[] boxes = new HashSet[9];
    for (int i = 0; i < 9; i++) {
      rows[i] = new HashSet<>();
      cols[i] = new HashSet<>();
      boxes[i] = new HashSet<>();
    }

    // A list to store the coordinates of empty cells
    List<int[]> unsolvedCells = new ArrayList<>();

    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (board[r][c] == '.') {
          // If the cell is empty, add its coordinates to our to-do list
          unsolvedCells.add(new int[] {r, c});
        } else {
          // If the cell has a number, record it in our sets
          int num = board[r][c] - '0';
          rows[r].add(num);
          cols[c].add(num);
          int boxIdx = (r / 3) * 3 + (c / 3);
          boxes[boxIdx].add(num);
        }
      }
    }
    

    solve(board, 0, unsolvedCells, rows, cols, boxes);
  }


  private boolean solve(char[][] board, int cell, List<int[]> unsolvedCells, Set<Integer>[] rows, Set<Integer>[] cols, Set<Integer>[] boxes) {
    if (cell == unsolvedCells.size()) {
        return true;
    }

    int[] curr = unsolvedCells.get(cell);
    int i = curr[0];
    int j = curr[1];
    int boxIdx = (i / 3) * 3 + (j / 3);
    
    for (int num = 1; num <= 9; num++) {
      if (!rows[i].contains(num) && !cols[j].contains(num) && !boxes[boxIdx].contains(num)) {
        rows[i].add(num);
        cols[j].add(num);
        boxes[boxIdx].add(num);
        board[i][j] = (char)(num + '0');
      
        if (solve(board, cell + 1, unsolvedCells, rows, cols, boxes)) {
          return true;
        } 
      
        rows[i].remove(num);
        cols[j].remove(num);
        boxes[boxIdx].remove(num);
        board[i][j] = '.';
      }
    }
    return false;
  }
}

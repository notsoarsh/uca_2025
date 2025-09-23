public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        //recursive helper
        helper(board);
    }

    static boolean helper(char[][] board){
        //iterate in the matrix to find the missing index
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                if(board[i][j] == '.'){
                    //iterate for possible ans
                    for(char c = '1' ; c <= '9' ; c++){
                        if(isValid(c,i,j,board)){
                            board[i][j]=c;
                            if(helper(board)){
                                return true;
                            }else{
                                board[i][j]='.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValid(char c, int row, int col, char[][] board){
        for(int i =0; i < 9 ;i++){
            if(board[row][i] == c){
                return false;
            }
            if(board[i][col] == c){
                return false;
            }
            if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c){
                return false;
            }
        }
        return true;
    }
}

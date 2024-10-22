public class AI {
    public int move(char[] board) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == '\u0000') {
                board[i] = 'O';
                int score = minimax(board, 0, false);
                board[i] = '\u0000';
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    private int minimax(char[] board, int depth, boolean isMaximizing) {
        int result = checkWinner(board);
        if (result != 0 || isBoardFull(board)) {
            return result;
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == '\u0000') {
                    board[i] = 'O';
                    int score = minimax(board, depth + 1, false);
                    board[i] = '\u0000';
                    bestScore = Math.max(score, bestScore);
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == '\u0000') {
                    board[i] = 'X';
                    int score = minimax(board, depth + 1, true);
                    board[i] = '\u0000';
                    bestScore = Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

    private int checkWinner(char[] board) {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] == board[condition[1]] && board[condition[1]] == board[condition[2]]) {
                if (board[condition[0]] == 'O') {
                    return 10; // AI wins
                } else if (board[condition[0]] == 'X') {
                    return -10; // Player wins
                }
            }
        }

        return 0; // No winner yet
    }

    private boolean isBoardFull(char[] board) {
        for (char c : board) {
            if (c == '\u0000') {
                return false;
            }
        }
        return true;
    }
}
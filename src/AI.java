public class AI
{
    public int move(char[] state)
    {
        int bestVal = -1000;
        int index = 0;
        for(int i=0; i<9; i++){
            if(state[i] == '\u0000'){
                state[i] = 'X';
                int moveVal = MiniMax(state, false);
                state[i] = '\u0000';
                if(moveVal>bestVal){
                    index = i;
                    bestVal = moveVal;
                }
            }
        }
        return index;
    }

    public int MiniMax(char[] state, Boolean isMax)
    {
        int score = eval(state);
        if (score == 10)//if X won
            return score;
        if (score == -10)//if O won
            return score;
        if (BoardFull(state))//board is full and no one won
            return 0;

        if(isMax){//Maximizer
            int best = -1000;
            for(int i=0; i<9; i++){
                if(state[i]=='\u0000'){
                    state[i] = 'X';
                    best = Math.max(best, MiniMax(state, false));
                    state[i]='\u0000';
                }
            }
            return best;
        }
        else{//Minimizer
            int best = 1000;
            for(int i=0; i<9; i++){
                if(state[i]=='\u0000'){
                    state[i] = 'O';
                    best = Math.min(best, MiniMax(state, true));
                    state[i]='\u0000';
                }
            }
            return best;
        }

    }

    public int eval(char[] state)
    {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = state[i + j * 3];
            }
        }
        // Checking for Rows for X or O victory. 
        for (int row = 0; row < 3; row++)
        {
            if (board[row][0] == board[row][1] &&
                    board[row][1] == board[row][2])
            {
                if (board[row][0] == 'X')
                    return +10;
                else if (board[row][0] == 'O')
                    return -10;
            }
        }

        // Checking for Columns for X or O victory. 
        for (int col = 0; col < 3; col++)
        {
            if (board[0][col] == board[1][col] &&
                    board[1][col] == board[2][col])
            {
                if (board[0][col] == 'X')
                    return +10;

                else if (board[0][col] == 'O')
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory. 
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            if (board[0][0] == 'X')
                return +10;
            else if (board[0][0] == 'O')
                return -10;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            if (board[0][2] == 'X')
                return +10;
            else if (board[0][2] == 'O')
                return -10;
        }

        // Else if none of them have won then return 0 
        return 0;
    }

    public boolean BoardFull(char[] state){
        for(int i=0; i<9; i++){
            if(state[i]=='\u0000')
                return false;
        }
        return true;
    }
}


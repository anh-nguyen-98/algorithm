#include<iostream>
bool inBoard(int n, int row, int col) {
    return row >= 0 && row < n && col >= 0 && col < n;
}

bool isValid(int n, int **board, int r, int c) {

    for (int row = 0; row < r; row++) {
        if (board[row][c] == 1) {
            return false;
        }
    }

    for (int col = 0; col < c; col++) {
        int offset = c - col;
        if (inBoard(n, r - offset, col) && board[r - offset][col] == 1) {
            return false;
        }
    }

    return true;


}
bool solve(int n, int **board, int r, int c) {

    board[r][c] = 1;
   
    if (r == n -1) {
        return true;
    }

    for (int col = 0; col < n; col++) {
        if (inBoard(n, r+1, col) && isValid(n, board, r+1, col)) {
            if (solve(n, board, r+1, col)) {
                        return true;
                    }
        }
        
    }
    board[r][c] = 0;
    return false;


}

int printBoard(int n, int **board) {
    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            std::cout<<board[row][col];
        }
        std::cout<<std::endl;
    }
    return 0;
}

int main() {
    // std::cout<<"Hello world"<<std::endl;
    int n;
    std::cout<<"Enter n: ";
    std::cin>>n;

    int **board = new int *[n];

    for(int i = 0; i < n; i++) {
        board[i] = new int[n];
    }

    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            board[row][col] = 0;
        }
    }

    for (int col = 0; col < n; col ++) {
        if (solve(n, board, 0, col)) {
            printBoard(n, board);
            return 0;
        }

    }
  
    return 0;
}
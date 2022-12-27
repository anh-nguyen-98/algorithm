#include <iostream>
#include <math.h>

bool inBoard(int n, int row, int col) {
    return row >= 0 && row < n && col >= 0 && col < n;
}

bool explore (int **board, int n, int r, int c, int next) {
    board[r][c] = next;
    if (next == pow(n, 2) -1) {
        return true;
    }
    int rowOffset[8] = {-2,-2,-1,-1, 1, 1, 2, 2};
    int colOffset[8] = {-1, 1,-2, 2,-2, 2,-1, 1};

    for (int i = 0; i < 8; i++) {
        int nextRow = r + rowOffset[i];
        int nextCol =  c + colOffset[i];

        if (inBoard(n, nextRow, nextCol) && board[nextRow][nextCol] == -1 && 
            explore (board, n, nextRow, nextCol, next +1)) {
            return true;
        }
    }
    board[r][c] = -1;
    return false;
}

int printBoard(int n, int **board) {
    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            std::cout<<board[row][col] << "|";
        }
        std::cout<<std::endl;
    }
    return 0;
}
int main() {
    int n;
    std::cout << "Enter n: ";
    std::cin>>n;
    
    int **board = new int *[n];

    for(int i = 0; i < n; i++) {
        board[i] = new int[n];
    }

    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            board[row][col] = -1;
        }
    }

    explore(board, n, 0, 0, 0);
    printBoard(n, board);
    return 0;
}
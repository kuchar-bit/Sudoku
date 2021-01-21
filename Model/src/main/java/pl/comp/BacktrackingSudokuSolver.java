package pl.comp;

import java.io.Serializable;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {
    private boolean flag = true;

    public boolean solve(SudokuBoard board) {

        if (flag) {
            flag = false;
            for (int i = 0; i < board.size; i++) {
                int r = (int) (Math.random() * (8 - 1 + 1) + 1);
                int c = (int) (Math.random() * (8 - 1 + 1) + 1);
                if (board.get(r,c) == 0) {
                    board.set(r, c, i);
                } else {
                    i--;
                }
            }
        }

        for (int row = 0; row < board.size; row++) {
            for (int col = 0; col < board.size; col++) {
                if (board.get(row, col) == board.empty) {
                    for (int number = 1; number <= board.size; number++) {
                        board.set(row, col, number);
                        if (board.getBox(row, col).verify() && board.getColumn(col).verify()
                                && board.getRow(row).verify()) {
                            if (solve(board)) {
                                return true;
                            }
                        } else {
                            board.set(row, col, board.empty);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
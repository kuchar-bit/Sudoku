package pl.comp;

public class MediumLevel extends Level {

    @Override
    public void eraseFieldSudoku(SudokuBoard sudokuBoard) {
        for (int i = 0; i < sudokuBoard.size; i++) {
            for (int j = 0; j < sudokuBoard.size; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    sudokuBoard.set(i,j,0);
                }
            }
        }
    }
}

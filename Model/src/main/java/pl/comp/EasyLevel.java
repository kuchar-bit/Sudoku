package pl.comp;

public class EasyLevel extends Level {
    @Override
    public void eraseFieldSudoku(SudokuBoard sudokuBoard) {
        for (int i = 0; i < sudokuBoard.size; i++) {
            for (int j = 0; j < sudokuBoard.size; j++) {
                if (i % 3 == 0 && j % 3 == 0) {
                    sudokuBoard.set(i,j,0);
                }
            }
        }
    }
}

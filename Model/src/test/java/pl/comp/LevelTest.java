package pl.comp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LevelTest {

    @Test
    public void EasyLevelTest() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        Level easeLevel = new EasyLevel();
        easeLevel.eraseFieldSudoku(sudokuBoard);

        for (int i = 0; i < sudokuBoard.size; i++) {
            for (int j = 0; j < sudokuBoard.size; j++) {
                if (i % 3 == 0 && j % 3 == 0) {
                    assertEquals(sudokuBoard.get(i,j),0);
                }
            }
        }
    }

    @Test
    public void MediumLevelTest() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        Level mediumLevel = new MediumLevel();
        mediumLevel.eraseFieldSudoku(sudokuBoard);

        for (int i = 0; i < sudokuBoard.size; i++) {
            for (int j = 0; j < sudokuBoard.size; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    assertEquals(sudokuBoard.get(i,j),0);
                }
            }
        }
    }

    @Test
    public void HardLevelTest() {
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();
        Level hardLevel = new HardLevel();
        hardLevel.eraseFieldSudoku(sudokuBoard);

        for (int i = 0; i < sudokuBoard.size; i++) {
            for (int j = 0; j < sudokuBoard.size; j++) {
                if ((i + 1) % 2 == 0 || j % 2 == 0) {
                    assertEquals(sudokuBoard.get(i,j),0);
                }
            }
        }
    }
}

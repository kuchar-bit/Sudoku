package pl.comp;

import com.google.common.base.MoreObjects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VerifyTest {

    @Test
    public void GettersAndSetters() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        SudokuRow row1 = testSudoku.getRow(1);
        SudokuRow row2 = testSudoku.getRow(1);

        row1.setLine(1,new SudokuField(1));
        assertNotEquals(row1.getLine(), row2.getLine());
    }

    @Test
    public void ToStringTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        assertEquals(testSudoku.getColumn(1).toString(),
                MoreObjects.toStringHelper(testSudoku.getColumn(1))
                        .add("line", testSudoku.getColumn(1).getLine())
                        .toString());
    }

    @Test
    public void EqualsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        SudokuRow row1 = testSudoku.getRow(1);
        SudokuRow row2 = testSudoku.getRow(1);
        SudokuColumn col1 = testSudoku.getColumn(1);

        assertEquals(row1, row1);
        assertEquals(row2, row1);
        assertNotEquals(row2, null);
        assertNotEquals(row2, col1);
        row2.setLine(1, new SudokuField(0));
        assertNotEquals(row1,row2);
    }

    @Test
    public void HashCodeTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        SudokuRow row1 = testSudoku.getRow(1);
        SudokuRow row2 = testSudoku.getRow(1);
        SudokuColumn col1 = testSudoku.getColumn(1);

        assertEquals(row2.hashCode(), row1.hashCode());
    }
}

package pl.comp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FixedListTest {
    @Test
    public void setTest() {
        SudokuBoard testSudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        assertNull(testSudoku.getSudokuFields().get(1).set(12, new SudokuField(9)));
    }

    @Test
    public void removeTest() {
        SudokuBoard testSudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        assertEquals(testSudoku.getSudokuFields().get(1).get(2), testSudoku.getSudokuFields().get(1).remove(2));
        assertNull(testSudoku.getSudokuFields().get(1).remove(13));
    }
}

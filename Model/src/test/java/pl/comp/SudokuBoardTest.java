package pl.comp;

import com.google.common.base.MoreObjects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    public void testFillBoardTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        int sum = 0;
        for (int i = 0; i < testSudoku.size; i++) {
            for (int j = 0; j < testSudoku.size; j++) {
                sum += testSudoku.get(i, j);
            }
        }

        testSudoku.print();

        assertEquals(sum, 405);
    }

    @Test
    public void testIsNotEqualBoards() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard testSudoku2 = new SudokuBoard(backtrackingSudokuSolver);

        int[][] boardTest1 = new int[testSudoku1.size][testSudoku1.size];
        int[][] boardTest2 = new int[testSudoku2.size][testSudoku2.size];

        testSudoku1.solveGame();

        for (int i = 0; i < testSudoku1.size; i++) {
            for (int j = 0; j < testSudoku1.size; j++) {
                boardTest1[i][j] = testSudoku1.get(i, j);
            }
        }

        testSudoku2.solveGame();
        for (int i = 0; i < testSudoku2.size; i++) {
            for (int j = 0; j < testSudoku2.size; j++) {
                boardTest2[i][j] = testSudoku2.get(i, j);
            }
        }

        assertNotEquals(boardTest1, boardTest2);
    }

    @Test
    public void NegativeTestColumn() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        testSudoku.set(0, 0, testSudoku.get(0, 0)+1);
        assertFalse(testSudoku.isCheckTrue());
    }

    @Test
    public void NegativeTestRow() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        testSudoku.set(0, 2, testSudoku.get(0, 2)+1);
        assertFalse(testSudoku.isCheckTrue());
    }

    @Test
    public void NegativeTestBox() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        testSudoku.set(1, 1, testSudoku.get(1, 1)+1);
        assertFalse(testSudoku.isCheckTrue());
    }

    @Test
    public void ToStringTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        assertEquals(testSudoku.toString(), MoreObjects.toStringHelper(testSudoku)
                        .add("size", testSudoku.getSize())
                        .add("empty", testSudoku.getEmpty())
                        .add("sudokuFields", testSudoku.getSudokuFields())
                        .add("sudokuSolver", testSudoku.getSudokuSolver())
                        .toString());
    }

    @Test
    public void EqualsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        SudokuBoard testSudoku1 = testSudoku;
        SudokuBoard testSudoku2 = new SudokuBoard(backtrackingSudokuSolver);

        BacktrackingSudokuSolver backtrackingSudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku3 = new SudokuBoard(backtrackingSudokuSolver1);

        SudokuField sudokuField = new SudokuField(0);

        assertEquals(testSudoku, testSudoku);
        assertEquals(testSudoku1, testSudoku);
        assertNotEquals(testSudoku, null);
        testSudoku.equals(sudokuField);
        testSudoku1.set(0,0,testSudoku.get(0,0) + 1);
        testSudoku.equals(testSudoku2);
        testSudoku.equals(testSudoku3);
    }

    @Test
    public void ToHashTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard testSudoku = new SudokuBoard(backtrackingSudokuSolver);
        testSudoku.solveGame();

        assertEquals(testSudoku.hashCode(), testSudoku.hashCode());
    }

}


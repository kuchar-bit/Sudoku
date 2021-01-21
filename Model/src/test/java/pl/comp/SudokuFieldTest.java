package pl.comp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SudokuFieldTest {

    @Test
    public void ToStringTest() {
        SudokuField sudokuField = new SudokuField(0);
        assertEquals(sudokuField.toString(), "SudokuField{value=0}");
    }

    @Test
    public void EqualsTest() {
        SudokuField sudokuField = new SudokuField(0);
        SudokuField sudokuField1 = new SudokuField(0);
        FixedList<SudokuField> sudokurow = new FixedList<>(9);
        for (SudokuField value : sudokurow) {
            value.setValue(0);
        }

        assertEquals(sudokuField, sudokuField);
        assertEquals(sudokuField, sudokuField1);
        assertNotEquals(sudokuField1, null);
        assertNotEquals(sudokuField1, sudokurow);
        sudokuField1.setValue(1);
        assertNotEquals(sudokuField,sudokuField1);
    }

    @Test
    void compareToTest() {
        SudokuField sudokuField = new SudokuField(5);
        SudokuField sudokuField0 = new SudokuField(5);
        assertEquals(0, sudokuField.compareTo(sudokuField0));
    }
}

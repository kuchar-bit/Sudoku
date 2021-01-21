package pl.comp;

import java.io.Serializable;

public class
SudokuRow extends Verify implements Cloneable, Serializable {
    public SudokuRow(FixedList<SudokuField> values) {
        super(values);
    }
}

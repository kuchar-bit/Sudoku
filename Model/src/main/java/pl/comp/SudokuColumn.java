package pl.comp;

import java.io.Serializable;

public class SudokuColumn extends Verify implements Cloneable, Serializable {
    public SudokuColumn(FixedList<SudokuField> values) {
        super(values);
    }
}

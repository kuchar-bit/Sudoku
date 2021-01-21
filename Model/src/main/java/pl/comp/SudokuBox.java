package pl.comp;

import java.io.Serializable;

public class SudokuBox extends Verify implements Cloneable, Serializable {
    public SudokuBox(FixedList<SudokuField> values) {
        super(values);
    }
}

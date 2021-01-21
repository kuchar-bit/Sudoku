package pl.comp;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SudokuBoard implements Cloneable, Serializable {
    final int size = 9;
    final int empty = 0;

    private static final Logger log = LogManager.getLogger(SudokuBoard.class.getName());

    private final ArrayList<FixedList<SudokuField>> sudokuFields = new ArrayList<>(9);

    public int getSize() {
        return size;
    }

    public int getEmpty() {
        return empty;
    }

    public ArrayList<FixedList<SudokuField>> getSudokuFields() {
        return sudokuFields;
    }

    public SudokuSolver getSudokuSolver() {
        return sudokuSolver;
    }

    private final SudokuSolver sudokuSolver;

    public boolean isCheckTrue() {
        return checkBoard();
    }

    SudokuBoard(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
        for (int i = 0; i < size; i++) {
            sudokuFields.add(new FixedList<>(9));
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudokuFields.get(i).add(new SudokuField(empty));
            }
        }
    }

    public int get(int row, int column) {
        return sudokuFields.get(row).get(column).getValue();
    }

    public void set(int row, int column, int value) {
        sudokuFields.get(row).get(column).setValue(value);
    }

    public SudokuColumn getColumn(int column) {
        FixedList<SudokuField> columnField = new FixedList<>(9);
        for (int i = 0; i < size; i++) {
            columnField.add(new SudokuField(empty));
            columnField.set(i, sudokuFields.get(i).get(column));
        }
        return new SudokuColumn(columnField);
    }

    public SudokuRow getRow(int row) {
        FixedList<SudokuField> rowField = new FixedList<>(9);
        for (int i = 0; i < size; i++) {
            rowField.add(new SudokuField(empty));
            rowField.set(i, sudokuFields.get(row).get(i));
        }
        return new SudokuRow(rowField);
    }

    public SudokuBox getBox(int row, int column) {
        int r = row - row % 3;
        int c = column - column % 3;
        FixedList<SudokuField> boxField = new FixedList<>(9);
        int index = 0;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                boxField.add(new SudokuField(empty));
                boxField.add(index++, sudokuFields.get(i).get(j));
            }
        }
        return new SudokuBox(boxField);
    }

    public void print() {
        StringBuilder value = new StringBuilder();
        value.append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                value.append(sudokuFields.get(i).get(j).getValue()).append(" ");
            }
            value.append("\n");
        }
        value.append("\n");
        log.info(value.toString());
    }

    public void solveGame() {
        sudokuSolver.solve(this);
        checkBoard();
    }

    private boolean checkBoard() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (!getColumn(column).verify()
                        || !getRow(row).verify()
                        || !getBox(row, column).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("size", size)
                .add("empty", empty)
                .add("sudokuFields", sudokuFields)
                .add("sudokuSolver", sudokuSolver)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null
                || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(sudokuFields, that.sudokuFields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(size, empty, sudokuFields);
    }

}

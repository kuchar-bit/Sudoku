package pl.comp;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.HashSet;
import java.util.Set;

public abstract class Verify {
    private final FixedList<SudokuField> line;

    public Verify(FixedList<SudokuField> values) {
        this.line = values;
    }

    public boolean verify() {
        Set<Integer> usedNumbers = new HashSet<>();
        for (SudokuField value : line) {
            if (value.getValue() != 0) {
                if (!usedNumbers.add(value.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public FixedList<SudokuField> getLine() {
        return line;
    }

    public void setLine(int l, SudokuField val) {
        line.set(l, val);
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
        Verify verify = (Verify) o;
        return Objects.equal(line, verify.line);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(line);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("line", line)
                .toString();
    }

}

package pl.comp;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;

public class SudokuField implements Cloneable, Comparable<SudokuField>, Serializable {
    private int value;

    public SudokuField(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", getValue())
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
        SudokuField that = (SudokuField) o;
        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public int compareTo(SudokuField o) {
        return Integer.compare(this.getValue(), o.getValue());
    }
}

package pl.comp;

import java.util.ArrayList;

public class FixedList<SudokuT> extends ArrayList<SudokuT> {
    private final int size;

    FixedList(int size) {
        this.size = size;
    }

    @Override
    public boolean add(SudokuT sudoku) {
        if (this.size() < size) {
            return super.add(sudoku);
        }
        return false;
    }

    @Override
    public SudokuT set(int index, SudokuT element) {
        if (index < size) {
            return super.set(index, element);
        }
        return null;
    }

    @Override
    public SudokuT remove(int index) {
        if (index < size) {
            return super.remove(index);
        }
        return null;
    }
}
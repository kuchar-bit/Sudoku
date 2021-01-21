package pl.comp;

import java.io.Serializable;

public interface Dao<T> extends AutoCloseable, Serializable {
    T read() throws FileSudokuBoardDaoExceptions;

    void write(T obj) throws FileSudokuBoardDaoExceptions;
}
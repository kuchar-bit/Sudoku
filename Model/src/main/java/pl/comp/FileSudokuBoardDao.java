package pl.comp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private final String path;

    public FileSudokuBoardDao(String path) {
        this.path = path;
    }

    @Override
    public SudokuBoard read() throws FileSudokuBoardDaoExceptions {
        SudokuBoard sudokuBoard;
        try (FileInputStream fileReader = new FileInputStream(this.path)) {
            ObjectInputStream reader = new ObjectInputStream(fileReader);
            sudokuBoard = (SudokuBoard) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new FileSudokuBoardDaoExceptions("Runtime read exception", e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws FileSudokuBoardDaoExceptions {
        try (FileOutputStream fileWriter = new FileOutputStream(this.path)) {
            ObjectOutputStream writer = new ObjectOutputStream(fileWriter);
            writer.writeObject(obj);
        } catch (IOException e) {
            throw new FileSudokuBoardDaoExceptions("Runtime write exception");
        }
    }

    @Override
    public void close() {

    }
}
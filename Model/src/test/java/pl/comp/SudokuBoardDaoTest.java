package pl.comp;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoTest {

    @Test
    void read() {
        try (Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao("Pogotowie")) {
            SudokuSolver back = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(back);
            fileDao.write(sudokuBoard);
            assertEquals(fileDao.read(),sudokuBoard);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void negativeRead() {
        try (Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao("e")) {
            SudokuSolver back = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(back);
            assertNotEquals(fileDao.read(),sudokuBoard);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void write() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao("Pogotowie")) {
            SudokuSolver back = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(back);
            back.solve(sudokuBoard);
            fileDao.write(sudokuBoard);
            assertTrue(new File("Pogotowie").length() != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void negativeWrite() {
        try (Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao("e")) {
            SudokuSolver back = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(back);
            try (Dao<SudokuBoard> fileDao1 = SudokuBoardDaoFactory.getFileDao("")){
                SudokuSolver back1 = new BacktrackingSudokuSolver();
                SudokuBoard sudokuBoard1 = new SudokuBoard(back1);
                assertThrows(FileSudokuBoardDaoExceptions.class, () -> fileDao1.write(sudokuBoard1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            assertNotEquals(fileDao.read(),sudokuBoard);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

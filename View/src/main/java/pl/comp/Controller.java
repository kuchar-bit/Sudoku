package pl.comp;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger log = LogManager.getLogger(Controller.class.getName());

    @FXML
    public AnchorPane playSudokuPane;
    public AnchorPane madeByPane;
    public AnchorPane levelChooser;
    @FXML
    public Label madeBy;
    public Label author1;
    public Label author2;
    public Label levelChooserLabel;
    @FXML
    public Button playSudoku;
    public Button levelEasy;
    public Button levelMedium;
    public Button levelHard;
    public Button pl;
    public Button eng;
    public Button save;
    public Button load;
    public Button check;
    @FXML
    GridPane playGrid;

    private boolean flagLang = false;

    private SudokuBoard sudokuBoard = null;
    private SudokuBoard original = null;
    private final List<JavaBeanIntegerProperty> integerProperties = new ArrayList<>();

    private void enablePane(AnchorPane pane) {
        pane.setDisable(false);
        pane.setOpacity(1);
    }

    private void disablePane(AnchorPane pane) {
        pane.setDisable(true);
        pane.setOpacity(0);
    }

    private void clearSudoku() {
        for (int i = 0; i < 81; i++) {
            TextField current = (TextField) playGrid.getChildren().get(i);
            current.setText("");
            current.setStyle(null);
            current.setEditable(true);
        }
    }

    public void playSudokuOnButtonClick() {
        disablePane(madeByPane);
        disablePane(playSudokuPane);
        enablePane(levelChooser);
        clearSudoku();
    }

    public void levelEasyOnButtonClick() throws controllerExceptions {
        disablePane(levelChooser);
        disablePane(madeByPane);
        enablePane(playSudokuPane);
        setLevelButtonClick(new EasyLevel());
    }

    public void levelMediumOnButtonClick() throws controllerExceptions {
        disablePane(levelChooser);
        disablePane(madeByPane);
        enablePane(playSudokuPane);
        setLevelButtonClick(new MediumLevel());
    }

    public void levelHardOnButtonClick() throws controllerExceptions {
        disablePane(levelChooser);
        disablePane(madeByPane);
        enablePane(playSudokuPane);
        setLevelButtonClick(new HardLevel());
    }

    private void setLevelButtonClick(Level level) throws controllerExceptions {
        SudokuSolver back = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(back);
        sudokuBoard.solveGame();
        level.eraseFieldSudoku(sudokuBoard);
        original = sudokuBoard;
        setValues(sudokuBoard);
    }

    private void setValues(SudokuBoard board) throws controllerExceptions{
        int counter = 0;
        integerProperties.clear();
        JavaBeanIntegerProperty integerProperty = null;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField current = (TextField) playGrid.getChildren().get(counter);

                JavaBeanIntegerPropertyBuilder builder = JavaBeanIntegerPropertyBuilder.create();
                StringConverter<Number> converter = new NumberStringConverter();
                try {
                    integerProperty = builder.bean(board.getSudokuFields().get(j).get(i))
                            .name("value").build();
                    current.textProperty().bindBidirectional(integerProperty, converter);
                } catch (NoSuchMethodException e) {
                    log.debug(e.getLocalizedMessage(), e);
                    throw new controllerExceptions("No Such Method Exception",e);
                }
                integerProperties.add(integerProperty);

                if (current.getText().equals("0")) {
                    current.setText("");
                    current.setStyle("-fx-background-color: #c1c1a2");
                } else {
                    current.setEditable(false);
                }

                current.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d?")) {
                        current.setText(oldValue);
                    }
                });

                counter++;
            }
        }
    }

    private Path getPath(Button daoButton) {
        Main main = new Main();
        FileChooser fileChooser = new FileChooser();
        Path path = null;
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(main.stage);
        if (String.valueOf(file).equals("null")) {
            if (flagLang) {
                daoButton.setText("Sprobuj ponownie");
            } else {
                daoButton.setText("Try again");
            }
        } else {
            path = Path.of(String.valueOf(file));
        }
        return path;
    }

    private void wait5s(Button button, String text) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() throws controllerExceptions {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new controllerExceptions("Interrupted exception",e);
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> button.setText(text));
        new Thread(sleeper).start();
    }

    public void save() throws FileSudokuBoardDaoExceptions {
        Path path = getPath(save);
        if (path != null) {
            FileSudokuBoardDao dao = new FileSudokuBoardDao(String.valueOf(path));
            dao.write(sudokuBoard);
            save.setText("Saved");
        }
        if (flagLang) {
            wait5s(save,"Zapisz");
        } else {
            wait5s(save,"Save");
        }
    }

    public void load() throws FileSudokuBoardDaoExceptions, controllerExceptions {
        Path path = getPath(load);
        if (path != null) {
            FileSudokuBoardDao dao = new FileSudokuBoardDao(String.valueOf(path));
            sudokuBoard = dao.read();
            load.setText("Loaded");
            setValues(sudokuBoard);
        }
        if (flagLang) {
            wait5s(load,"Odczytaj");
        } else {
            wait5s(load,"Load");
        }
    }

    public void changeLanguage(ResourceBundle bundle) {
        playSudoku.setText(bundle.getString("playButton"));
        levelEasy.setText(bundle.getString("easyButton"));
        levelMedium.setText(bundle.getString("mediumButton"));
        levelHard.setText(bundle.getString("hardButton"));
        pl.setText(bundle.getString("changeToPolish"));
        eng.setText(bundle.getString("changeToEnglish"));
        save.setText(bundle.getString("saveButton"));
        load.setText(bundle.getString("loadButton"));
        check.setText(bundle.getString("checkButton"));
    }

    public void changeToPolish() {
        ResourceBundle polishBundle = ResourceBundle.getBundle("bundles.mess_pl");
        ResourceBundle polishList = ResourceBundle.getBundle("pl.comp.polishBundle",
                new Locale("pl"));

        changeLanguage(polishBundle);
        levelChooserLabel.setText(polishBundle.getString("chooseLevel"));
        madeBy.setText(polishList.getString("madeBy"));
        author1.setText(polishList.getString("author1"));
        author2.setText(polishList.getString("author2"));
        flagLang = true;
    }

    public void changeToEnglish() {
        ResourceBundle englishBundle = ResourceBundle.getBundle("bundles.mess_en");
        ResourceBundle englishList = ResourceBundle.getBundle("pl.comp.englishBundle",
                new Locale("en"));

        changeLanguage(englishBundle);
        madeBy.setText(englishList.getString("madeBy"));
        author1.setText(englishList.getString("author1"));
        author2.setText(englishList.getString("author2"));
        flagLang = false;

    }

    public void check() {
        sudokuBoard.print();
    }
}

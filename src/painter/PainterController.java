package painter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.paint.Paint.valueOf;

public class PainterController {

    @FXML
    private Button buttonChooseColor;

    @FXML
    private Button buttonDisplayCurrentColor;

    @FXML
    private RadioButton radioButtonThickness5;

    @FXML
    private RadioButton radioButtonThickness10;

    @FXML
    private RadioButton radioButtonThickness15;

    @FXML
    private RadioButton radioButtonThickness20;

    @FXML
    private Button buttonEraser;

    private static PainterController instance = null;

    private int oldX;
    private int oldY;

    private List<ArrayList<ColorPoint>> colorPointList;

    private java.awt.Color color;
    private int thickness;


    private final String RESOURCES_PATH = "file:///C://Katador//projects//javafx//resources";

    private final ToggleGroup thicknessesButtonGroup = new ToggleGroup();
    private Image image;

    private static Map<String, Button> buttonsMap = new HashMap<>();

    @FXML
    private void initialize() {

        colorPointList = new ArrayList<>();

        color = new java.awt.Color(0, 0, 0);
        thickness = 5;

        image = new Image(RESOURCES_PATH + "//pencil.png");
        buttonChooseColor.setGraphic(new ImageView(image));

        initializeButtonsTooltip();
        initializeRadioButtonsWithToggleGroup();

        image = new Image(RESOURCES_PATH + "//eraser.png");
        buttonEraser.setGraphic(new ImageView(image));

        buttonDisplayCurrentColor.setBackground(new Background(new BackgroundFill(valueOf("#000000"),
                CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));

        buttonsMap.put("buttonDisplayCurrentColor", buttonDisplayCurrentColor);

    }

    private void initializeRadioButtonsWithToggleGroup() {
        radioButtonThickness5.setSelected(false);
        radioButtonThickness10.setSelected(true);
        radioButtonThickness15.setSelected(false);
        radioButtonThickness20.setSelected(false);

        radioButtonThickness5.setToggleGroup(thicknessesButtonGroup);
        radioButtonThickness10.setToggleGroup(thicknessesButtonGroup);
        radioButtonThickness15.setToggleGroup(thicknessesButtonGroup);
        radioButtonThickness20.setToggleGroup(thicknessesButtonGroup);
    }

    private void initializeButtonsTooltip() {
        buttonChooseColor.setTooltip(new Tooltip("Click here to choose brash color"));
        buttonEraser.setTooltip(new Tooltip("Choose eraser to clear fragment of the image"));
    }

    private void changeThickness(int newThickness) {
        System.out.println("Selected thickness = " + newThickness);
        this.thickness = newThickness;
    }


    public void changeButtonColor(java.awt.Color newColor) {
        System.out.println("Selected color = " + newColor);

        buttonsMap.get("buttonDisplayCurrentColor").setStyle("-fx-base: rgb(" + newColor.getRed() + ", "
                + newColor.getGreen() + ", " + newColor.getBlue() + ");");
    }

    public void onChooseColor(ActionEvent actionEvent) {
        System.out.println("Color chooser run");
        Platform.runLater(()->ColorChooser.createAndShowGUI());

    }

    public void onThicknessChangeTo5(ActionEvent actionEvent) {
        changeThickness(5);
    }

    public void onThicknessChangeTo10(ActionEvent actionEvent) {
        changeThickness(10);
    }

    public void onThicknessChangeTo15(ActionEvent actionEvent) {
        changeThickness(15);
    }

    public void onThicknessChangeTo20(ActionEvent actionEvent) {
        changeThickness(20);
    }

    public void onErase(ActionEvent actionEvent) {

    }

    public static synchronized PainterController getInstance() {
        if (instance == null) {
            instance = new PainterController();
        }
        return instance;
    }

    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }
}
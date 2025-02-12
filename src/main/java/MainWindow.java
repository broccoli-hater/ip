import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Ui;

import java.util.Objects;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private SirTalksALot sirTalksALot;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/user.jpg")));
    private final Image knightImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/knight.jpg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greeting = Ui.sayHello();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, knightImage, "")
        );
    }

    /** Injects the Duke instance */
    public void setSirTalksALot(SirTalksALot s) {
        sirTalksALot = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sirTalksALot.getResponse(input);
        String commandType = sirTalksALot.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, knightImage, commandType)
        );
        userInput.clear();
    }
}

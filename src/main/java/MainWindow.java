import java.io.InputStream;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main Duke GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = loadImage("/images/DaUser.png");
    private final Image dukeImage = loadImage("/images/DaDuke.png");

    @FXML
    public void initialize() {
        userInput.setPromptText("Type here...");

        // Keep the newest message visible whenever the conversation grows.
        dialogContainer.heightProperty().addListener((observable, oldHeight, newHeight) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    /** Injects the Duke instance used to generate replies. */
    public void setDuke(Duke duke) {
        this.duke = Objects.requireNonNull(duke, "duke");
    }

    /**
     * Adds the user's message and Duke's response to the conversation.
     */
    @FXML
    private void handleUserInput() {
        if (duke == null) {
            return;
        }

        String input = userInput.getText();
        String response = duke.getResponse(input);
        String commandType = duke.getCommandType();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, commandType)
        );
        userInput.clear();
    }

    private static Image loadImage(String path) {
        InputStream imageStream = MainWindow.class.getResourceAsStream(path);
        if (imageStream == null) {
            throw new IllegalStateException("Missing image resource: " + path);
        }
        return new Image(imageStream);
    }
}

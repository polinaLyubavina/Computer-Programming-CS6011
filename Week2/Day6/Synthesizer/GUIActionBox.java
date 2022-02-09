package Synthesizer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GUIActionBox {
    VBox actionBox;

    GUIActionBox(BorderPane borderpane) {
        actionBox = new VBox();
        actionBox.setStyle("-fx-background-color: #c999ff");
        actionBox.setMinSize(100, 100);
        borderpane.setLeft(actionBox);

        actionBox.setSpacing(50);
        actionBox.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play");
        playButton.setMinWidth(100);
        playButton.setOnAction(e -> handleButtonPress(playButton));
        actionBox.getChildren().add(playButton);

        Button stopButton = new Button("Stop");
        stopButton.setMinWidth(100);
        stopButton.setOnAction(e -> handleButtonPress(stopButton));
        actionBox.getChildren().add(stopButton);
    }

    private void handleButtonPress(Button button) {
        GUIApplication.mainSpeaker.Play();
    }
}

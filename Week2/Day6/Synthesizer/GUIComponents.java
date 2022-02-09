package Synthesizer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class GUIComponents {
    VBox components;
        
    GUIComponents (BorderPane borderpane) {
        components = new VBox();
        components.setStyle("-fx-background-color: #a38aff");
        components.setMinSize(100, 100);
        borderpane.setRight(components);
        components.setSpacing(50);
        components.setAlignment(Pos.CENTER);

        //buttons

        Button volumeButton = new Button("GUIVolume");
        volumeButton.setMinWidth(100);
        volumeButton.setPadding(new Insets(20, 10, 20, 10));
        volumeButton.setOnAction( e -> handleButtonPress(volumeButton));
        components.getChildren().add(volumeButton);

        Button sinewaveButton = new Button("Sine Wave");
        sinewaveButton.setMinWidth(100);
        sinewaveButton.setPadding(new Insets(0, 0, 0, 0));
        sinewaveButton.setOnAction( e -> handleButtonPress(sinewaveButton));
        components.getChildren().add(sinewaveButton);

        Button GUIVFSineWaveButton = new Button("VF Sine Wave");
        GUIVFSineWaveButton.setMinWidth(100);
        GUIVFSineWaveButton.setPadding(new Insets(0, 0, 0, 0));
        GUIVFSineWaveButton.setOnAction(e -> handleButtonPress(GUIVFSineWaveButton));
        components.getChildren().add(GUIVFSineWaveButton);

        components.setFillWidth(true); 
    }

    //handlers

    private void handleButtonPress(Button button) {
        System.out.println("Press " + button.getText());
        String text = button.getText();

        if(text.equals("Sine Wave")) {
            GUISineWave sinewave = new GUISineWave(200);
        }

        else if(text.equals("GUIVolume")) {
            GUIVolume volume = new GUIVolume();
        }

        else if(text.equals("Mixer")) {
            Mixer mixer = new Mixer();
        }

        else if(text.equals("VF Sine Wave")) {
            GUIVFSineWave vf = new GUIVFSineWave();
        }
    }
}

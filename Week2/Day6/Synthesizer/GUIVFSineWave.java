package Synthesizer;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class GUIVFSineWave extends Base {
    Label frequencyS, frequencyE;
    Slider lilslider, lilslider2;

    GUIVFSineWave() {
        super("VFSineWave");

        lilslider = new Slider(50, 10000, 100);
        frequencyS = new Label(Double.toString(100));
        shape.getChildren().add(lilslider);
        frequencyS.textProperty().bind(Bindings.format("%1f", lilslider.valueProperty()));
        HBox lilhbox = new HBox();
        lilhbox.setAlignment(Pos.CENTER);
        lilhbox.getChildren().add(frequencyS);
        shape.getChildren().add(lilhbox);

        lilslider2 = new Slider(50, 10000, 1000);
        frequencyE = new Label(Double.toString(1000));
        shape.getChildren().add(lilslider2);
        frequencyE.textProperty().bind(Bindings.format("%1f", lilslider2.valueProperty()));
        HBox hboxe = new HBox();
        hboxe.setAlignment(Pos.CENTER);
        hboxe.getChildren().add(frequencyE);
        shape.getChildren().add(hboxe);

        addBottom(false);
    }

    WaveBase generatedWave() {
        return new VFSineWave(100, 200);
    }
}

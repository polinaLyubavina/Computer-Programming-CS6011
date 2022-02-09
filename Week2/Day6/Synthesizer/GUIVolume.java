package Synthesizer;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

import Synthesizer.AudioClip;

public class GUIVolume extends Base {
    Volume volume;
    Label number;
    double num = 50;
    Slider lilslider;

    GUIVolume() {
        super("Volume");
        volume = new Volume();
        lilslider = new Slider(0, 100, num);
        shape.getChildren().add(lilslider);
        HBox lilbox = new HBox();
        number = new Label(Double.toString(num));
        number.textProperty().bind(Bindings.format("%.1f", lilslider.valueProperty()));
        lilbox.setAlignment(Pos.CENTER);
        lilbox.getChildren().add(number);
        shape.getChildren().add(lilbox);
        addBottom(true);
    }

    private void handleSlider() {
        number.setText(Double.toString(lilslider.getValue()));
    }

    public AudioClip getWaveClip() {
        ArrayList<WaveBase> basewaves = getWaves();
        if(basewaves.size() < 1) return null;
        AudioClip ac = basewaves.get(0).getClip();
        double scale = Double.parseDouble(number.getText()) * 2 / 100;
        System.out.println("GUIVolume is scaling by " + scale);
        
        return Volume.modifyClip(ac, scale);
    }
}

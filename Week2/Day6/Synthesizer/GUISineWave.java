package Synthesizer;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;


public class GUISineWave extends Base {
    Label freq_;
    Slider sld;

    GUISineWave(int freq) {
        super ("SineWave" );
        sld = new Slider(50, 10000, freq );
        //sld.setOnDragOver(e -> handleSlider(e));
        shape.getChildren().add( sld );
        freq_ = new Label( Double.toString( freq ) );
        freq_.textProperty().bind( Bindings.format("%.1f", sld.valueProperty() ) );

        HBox hb = new HBox();
        hb.setAlignment( Pos.CENTER );
        hb.getChildren().add( freq_ );
        shape.getChildren().add( hb );
        System.out.println( "adding bottom" );

        addBottom(false );
    }

	WaveBase generatedWave() {
		return new SineWave(200);
	}
}

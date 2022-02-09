package Synthesizer;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainWindow {
    AnchorPane mainWindow; 

    MainWindow( BorderPane borderpane) {
        mainWindow = new AnchorPane();
        mainWindow.setStyle(" -fx-background-color: #dfcfff");
        mainWindow.setMinSize(300, 300);
        borderpane.setCenter(mainWindow);
    }

    void addSpeaker(){
        Speaker wispeaker = new Speaker();
        GUIApplication.mainSpeaker = wispeaker;
        mainWindow.getChildren().add(wispeaker);
            Bounds bs = wispeaker.getBoundsInLocal();
            Bounds bm = mainWindow.getBoundsInLocal();
            double prefw = wispeaker.getMinWidth();
        System.out.printf("Speaker b %.0f %.0f\n", bs.getWidth(), bs.getHeight());
		System.out.printf("Speaker m %.0f %.0f\n", bm.getWidth(), bm.getHeight());
        System.out.println(prefw);
        AnchorPane.setBottomAnchor(wispeaker, 0.);
        AnchorPane.setLeftAnchor(wispeaker, (bm.getWidth() - prefw)/2);
    }

    AnchorPane getWindow() {
        return mainWindow;
    }
}

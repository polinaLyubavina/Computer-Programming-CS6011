package Synthesizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIApplication extends Application {
    public static MainWindow guiMain; 
    public static GUIActionBox guiActionBox;
    public static GUIComponents guiComponents;
    public static Speaker mainSpeaker;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Hello from Start ");
        primaryStage.setTitle("Synthesizer");
        BorderPane borderpane = new BorderPane();

        guiMain = new MainWindow(borderpane);
        guiActionBox = new GUIActionBox(borderpane);
        guiComponents = new GUIComponents(borderpane);

        Scene scene = new Scene(borderpane, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        guiMain.addSpeaker();
    }
}

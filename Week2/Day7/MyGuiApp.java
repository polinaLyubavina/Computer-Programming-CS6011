import javafx.application.Application;

public class MyGuiApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Hello from My Gui App");
        // primaryStage.setTitle("Hello World!");
        // public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

        btn.setOnAction( e -> handleButtonPress(btn) );



        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, width:300, height:250));
        primaryStage.show();
        // }
    }

    private Object handleButtonPress(Button btn) {
        System.out.println("button was pressed");
        btn.setText("I was pressed");
    }
}

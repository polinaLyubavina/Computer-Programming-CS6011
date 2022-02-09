public class ACWidget extends Application {

    private AnchorPane pane_;
    private double sliderValue_ = 0.0;
    private Text sliderText_ = new Text();

    @Override
    public void start(Stage primateStage) throws Exception {
            System.out.println("start()");
            System.out.println("javafx version:" + System.getProperty("javafx.runtime.version"));

            primaryStage.setTitle("My GUI App");

            pane_ = new AnchorPane();
            pane_.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, radii:null, inserts:null)));

            Button btn = new Button(text:"Press Me");

            ButtonListener b1 = new ButtonListener();
            btn.setOnAction(e -> handleButtonPress(e));
            btn.setOnAction(b1);

            Slider slider = new Slider();
            slider.setMin(0.0);
            slider.setMax(1.0);

            slider.valueProperty().addListener(e -> handleSlider(slider));

            sliderText_.setText(Double.toString(sliderValue_));
            sliderText_.setFill(Color.WHITE);

            HBox hbox = new HBox();
            hbox.setPrefSize(prefWidth:400, prefHeight:50);
            hbox.setBackground(new Background(new BackgroundFill(Color.BLUE, radii:null, inserts:null)));
            hbox.setAlignment(Pos.CENTER);

            hbox.setOnMouseDragged(e -> handleDrag(e));

            hbox.getChildren().add(btn);
            hbox.getChildren().add(slider);
            hbox.getChildren().add(sliderText_);
            hbox.setSpacing(50);

            HBox.setHgrow(sliderText_, Priority.NEVER);

            pane_.getChildren().add(hbox);

            Scene scene = new Scene(pane_, width:1000, height:500);

            primateStage.setScene(scene);
            primaryStage.show();
        }

    private void handleSlider(Slider slider) {
        double value = slider.getValue();
        System.out.println("handle slider: " + value);
        sliderText_.setText(String.format("%3.2f", value));
    }

    private void handleDrag(MouseEvent e) {
        System.out.println("Drag: " + e);
        HBOX hbox = (HBox) e.getSource();

        hbox.setLayoutX(e.getSceneX());
        hbox.setLayoutY(e.getSceneY());
    }

    private void handleButtonPress(ActionEvent e) { System.out.println("click: " + e.toString());}

}

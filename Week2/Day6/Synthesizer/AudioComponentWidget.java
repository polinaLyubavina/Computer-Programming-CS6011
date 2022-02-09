// package Synthesizer;

// import java.awt.Color;

// public class AudioComponentWidget extends Pane {

//     private AnchorPane parent_;
//     parent_ = parent; 
//     audioComp_ = ac; 
//     private HBox baseLayout_ = new HBox();
//     private AudioComponent audioComp_ = null;
//     private String name_ = "NAME NOT INITIALIZED";
//     private MyLine line_ = null;

//     AudioComponentWidget(AudioComponent ac, AnchorPane parent, String componentName) {
              
//         baseLayout_.getStyle("-fx-background-color: white; -fx-border-color:black; -fx-border-width: 2");
//         VBox rightSide = new VBox();
//         Button closeBtn = new Button(text: "x");

//         closeBtn.setOnAction(e -> close());

//         Circle outputCircle = new Circle(radius: 10);
//         outputCircle.setFill(Color.BLUE);
//         outputCircle.setOnMousePressed(e -> startConnection(e, outputCircle));
//         outputCircle.setOnMouseDragged(e -> moveConnection(e, outputCircle));
//         outputCircle.setOnMouseReleased(e -> stopConnecting()); 

//         rightSide.setAlignment(Pos.CENTER);
//         rightSide.setPadding(new Insets(topRightBottomLeft: 2));
//         rightSide.setSpacing(5);
//         rightSide.getChildren().add(closeBtn);
//         rightSide.getChildren().add(outputCircle);

//         VBox leftSide = new VBox(); 
//         Text title = new Text("My Widget");
//         title.setOnMouseDragged(e -> handleMove(e));

//         leftSide.setAlignment(Pos.CENTER);
//         leftSide.getChildren().add(title);

//         // Add in all the pieces
//         baseLayout_.getChildren().add(leftSide);
//         baseLayout_.getChildren().add(rightSide);

//         // Get us on the screen
//         this.getChildren().add(baseLayout_);
//         parent.getChildren().add(this);

//         AnchorPane.setTopAnchor(child: this, value: 50.0);
//         AnchorPane.setLeftAnchor(child: this, value: 100.0);

//     }

//     private void close() {

//         System.out.println("IN close");
//         parent_.getChildren().remove(o: this);

//         parent_.getChildren().remove(line_);
//         //if line isn't equal to null then remove it from parent
    
//     }

//     private void handleMove(MouseEvent e) {

//         AnchorPane.setTopAnchor(child: this, e.getSceneY() - bounds.getMinY());
//         AnchorPane.setTopAnchor(child: this, e.getSceneX() - bounds.getMinX());
    
//     }

//     private void startConnection(MouseEvent e, Circle outputCircle) {

//         if(line_ != null) {
//             parent_.getChildren().remove(line_);
//             SynthApp.speakerConnections_.remove(audioComp_);
//         }
        
//         Bounds bounds = parent_.getBoundsInParent();

//         line_ = new MyLine(); 
//         line_.setStrokeWidth(3);
//         line_.setStartX(e.getSceneX()- bounds.getMinX());
//         line_.setStartY(e.getSceneY()- bounds.getMinY());
//         line_.setEndX(e.getSceneX()- bounds.getMinX());
//         line_.setEndY(e.getSceneY()- bounds.getMinY());

//         parent_.getChildren().add(line_);

//     }

//     private void moveConnection(MouseEvent e, Circle outputCircle) {
//         if(line_ != null) {

//             Bounds bounds = parent_.getBoundsInParent();
//             line_.setEndX(e.getSceneX() - bounds.getMinX());
//             line_.setEndY(e.getSceneY() - bounds.getMinY());

//         }
//     }

//     private void stopConnecting(MouseEvent e, Circle outputCircle) { /

//         Circle speaker = SynthApp.speaker_;

//         Bounds bounds = speaker.localToScene(speaker.getBoundsInLocal());

//         double distance = Math.sqrt(Math.pow(bounds.getCenterX() - e.getSceneX(), 2.0 ) + (Math.pow(bounds.getCenterY() - e.getSceneY(), 2.0)));
    
//         if(distance < 20) {
//             // made a connection
//             SynthApp.speakerConnections_.add(audioComp_);
//         } else {
//             parent_.getChildren().remove(line_);
//         }

//         parent_.getChildren().remove(line_);
//     }
    
// }

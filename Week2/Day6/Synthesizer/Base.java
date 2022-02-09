package Synthesizer;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class Base extends VBox {
    
    Base shape;
	Connector connectorIn = null;
	Connector connectorOut = null;
	Text title;
	static int width = 275, height = 50;
	static double offset = 0;
	Point2D p_start;

	Base( String Label ) {
		this( Label, width, height, true,true) ;
	}

	Base( String label, double sw, double sh, boolean addToMainW, boolean addClose ) {
		shape = this;
			System.out.printf( "WB w,h %.0f %.0f\n",sw,sh );
		shape.setMinSize( sw, sh );
		shape.setAlignment( Pos.CENTER );
		shape.setStyle(
					"-fx-border-color: black;" +
					"-fx-border-width: 3;" +
					"-fx-background-color: #c6ffc6;"
		);

		if( addClose ) {
			HBox top = new HBox();
			top.setMaxHeight( 0.5 );

			Button closeBtn = new Button("x" );
			top.getChildren().add( closeBtn );
			top.setAlignment( Pos.TOP_RIGHT );
			shape.getChildren().add( top );
			closeBtn.setOnAction( e -> handleClose( shape ) );
		}

		shape.setOnMousePressed( e-> handlePress ( e ));
		shape.setOnMouseDragged( e-> handleMove ( e ));

		title = new Text( label );
		shape.getChildren().add( title );
		shape.setId( "connector:" + label );

		if( addToMainW ) {
			AnchorPane mw = ( AnchorPane ) GUIApplication.guiMain.getWindow();
			Bounds bm = mw.getBoundsInLocal();
				System.out.printf( "--- %.0f %.0f\n", bm.getWidth(),bm.getHeight() );
			offset += 100.;
			if( offset > ( mw.getBoundsInLocal().getWidth() ) * .75 ) offset = 0;
			AnchorPane.setTopAnchor(shape, 0.);
			AnchorPane.setLeftAnchor(shape, offset);
			mw.getChildren().add(shape);
			// Bounds b = mw.localToScene(shape.getBoundsInParent());
			//Bounds b = shape.getBoundsInParent();
			Bounds b = shape.localToParent( shape.getBoundsInLocal() );
			// System.out.println(b.getMinX() + " " + b.getMinY());
			//posCur = new Point2D(b.getMinX(), b.getMinY());
			//System.out.println(b.getMinX() + " " + b.getMinY());
		}
	}
    WaveBase generatedWave() { return null;}

	boolean hasConIn()				{ return ( connectorIn != null );}
	boolean hasConOut()				{return ( connectorOut != null );}
	Connector getConIn()		{ return connectorIn ;}
	Connector getConOut()	    { return connectorOut; }

	void addBottom( Boolean hasConIn ) {
		HBox bottom = new HBox();
		bottom.setMaxHeight( 100. );
		bottom.setAlignment( Pos.TOP_RIGHT );
		if ( hasConIn ) {
			connectorIn = new Connector( bottom, Connector.t_in, this );
			bottom.setAlignment( Pos.CENTER );
			bottom.setSpacing( 250 );
		}
		connectorOut = new Connector( bottom, Connector.t_out, this );
		shape.getChildren().add( bottom );
		System.out.println( "adding bottom here" );
	}

	//------------------------------------------------------------
	private void handleClose( Base v ) {
		AnchorPane ap = ( AnchorPane ) GUIApplication.guiMain.getWindow();
		if( v.connectorIn != null ) connectorIn.removeLines();
		if( v.connectorOut != null ) connectorOut.removeLines();
		ap.getChildren().remove(v);
	}

	private void handlePress( MouseEvent e ) {
		p_start	= new Point2D(e.getSceneX(), e.getSceneY());
		System.out.printf( "pressed %.0f %.0f\n", p_start.getX(),p_start.getY() );
	}

	private void handleMove( MouseEvent e ) {
		Point2D p_end = new Point2D(e.getSceneX(), e.getSceneY());
		Point2D p_delta	= p_end.subtract( p_start );
		System.out.printf( "move %.0f %.0f\n", p_end.getX(),p_end.getY() );
		System.out.printf( "delta %.0f %.0f\n", p_delta.getX(),p_delta.getY() );

		this.setTranslateX(p_delta.getX() + this.getTranslateX());
		this.setTranslateY(p_delta.getY() + this.getTranslateY());
		p_start = p_end;

		if( connectorIn != null	) connectorIn.moveLines( p_delta );
		if( connectorOut != null	) connectorOut.moveLines( p_delta );
	}

//-----------------------------------------------

	public ArrayList<WaveBase> getWaves() {
		ArrayList<WaveBase> waves = new ArrayList<>();
		WaveBase wave = generatedWave();
		if( wave != null) {
			waves.add(wave);
		}
		else {
            System.out.println(connectorIn);
			for( MyLine wl : connectorIn.lines ) {
				Base par = wl.getConOut().parent;
				waves.addAll( par.getWaves() );
			}
		}
		return waves;
	}

	public AudioClip getWaveClip() {
		ArrayList<WaveBase> waves = getWaves();
		if( waves.size() > 0 ) return waves.get(0).getClip();
		else return null;
	}
}

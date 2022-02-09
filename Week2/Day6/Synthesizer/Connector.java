package Synthesizer;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class Connector extends Circle {
    public static int t_in = 0x01;
	public static int t_out = 0x02;

    Base parent;
	ArrayList<MyLine> lines	= new ArrayList<>();
	MyLine lineCur = null;
	Point2D p_start;
	int typ;

	Connector( Pane h, int type, Base p ) {
		super(10 );
		parent = p;
		typ = type;
		setFill( Color.BLACK );
		h.getChildren().add( this) ;
		this.setOnMouseDragged( e -> handleDrag(e) );
		this.setOnMousePressed( e -> handlePressed(e) );
		this.setOnMouseReleased( e -> handleReleased(e) );
	}

	boolean isConnector( String s ) { return ( ( s != null ) && s.startsWith( "connector:" ) ); }
	String idConnector( String s ) { return s.substring( 11 ); }
	boolean isConIn() { return ( ( typ & t_in ) != 0 ); }
	void removeLine( MyLine wl )	{ lines.remove( wl ); }

	void removeLines() {
		for( int i = lines.size() -1 ; i >= 0 ; i -- ) {
			lines.get(i).removeLine();
		}
	}

	void moveLines( Point2D delta ) {
		for ( MyLine wl : lines ) {
			wl.moveEnd(this,delta );
		}
	}

	private void handleReleased( MouseEvent e ) {
		Point2D p_end = new Point2D( e.getSceneX(), e.getSceneY() );
		Point2D p_delta	= p_end.subtract( p_start );

		Connector wcEnd = null;
		AnchorPane ap = ( AnchorPane ) GUIApplication.guiMain.getWindow();

		for( Node node : ap.getChildren() ) { // 0 - node search main win
			//if (node instanceof Pane && isConnector(node.getId())) {
			if( isConnector (node.getId() ) ) {
				String id = idConnector( node.getId() );
				System.out.println( "Checking node.get id " + node.getId() );
				Base nod = ( Base ) node;
				if ( !nod.contains( nod.sceneToLocal(p_end) ) ) continue;

				if ( isConIn() ) {
					if ( nod.hasConOut() ) { // 4 - dest has out
						if ( nod.hasConIn() && nod.getConIn() == this ) continue;
							//System.out.println("node has conout");

							Connector conOut = nod.getConOut();
							if ( conOut.contains( conOut.sceneToLocal(p_end) ) ) {
								System.out.println( "on conOut" );
								wcEnd = conOut;
								if ( conOut.lines.size() > 0 ) {
									MyLine ln = conOut.lines.get( 0 );
									ln.removeLine();
								}
								e.consume(); // 6 - close mouse Event
								break;
							}
						}
					}
					else {
						if ( nod.hasConIn() ) {
							if ( nod.hasConOut() && nod.getConOut() == this ) continue;
							System.out.println( "check conIn " );

							Connector conIn = nod.getConIn();
							if ( conIn.contains(conIn.sceneToLocal(p_end ) ) ) {
								System.out.println( "We're in connector " + node.getId() );
								wcEnd = conIn;
								if ( !conIn.parent.getId().contains( "Mixer" ) ) {
									if ( conIn.lines.size() > 0 ) {
										MyLine ln = conIn.lines.get(0);
										ln.removeLine();
									}
								}
								e.consume(); //  - close mouse Event
								break;
							}
						}
					}
				}//node test
			} //for

			if ( wcEnd != null ) {
				Bounds b = ap.getBoundsInParent();
				lineCur.setEndX(p_end.getX() - b.getMinX());
				lineCur.setEndY(p_end.getY() - b.getMinY());

				lineCur.setConnectors(this,wcEnd );
				lines.add( lineCur );
				wcEnd.lines.add( lineCur) ;
			}
			else {
				ap.getChildren().remove( lineCur );
			}
		}

	private void handlePressed ( MouseEvent e ){
		p_start = new Point2D( e.getSceneX(), e.getSceneY() );
		System.out.println( "pressed line" );
		if ( isConIn() ) {
			if ( !parent.getId().contains( "Mixer" ) ) {
				if ( lines.size() > 0 ) {
					MyLine ln = lines.get( 0 );
					ln.removeLine();
				}
			}
		}
		else {
			if ( lines.size() > 0 ) {
				MyLine ln = lines.get( 0 );
				ln.removeLine();
			}
		}

		AnchorPane ap = ( AnchorPane ) GUIApplication.guiMain.getWindow();
		Bounds bounds = ap.getBoundsInParent();
		MyLine line	= new MyLine();
		lineCur	= line;
		line.setStrokeWidth( 3 );
		Point2D lp = new Point2D( p_start.getX() - bounds.getMinX(),
								  p_start.getY() - bounds.getMinY() );
		line.setStartX( lp.getX() );	line.setStartY( lp.getY() );
		line.setEndX( lp.getX() );	line.setEndY( lp.getY() );

		ap.getChildren().add( lineCur );
		e.consume();
	}

	private void handleDrag ( MouseEvent e ){
		if ( lineCur == null ) {
			System.out.println( "Drag without creating" );
			return;
		}
		//Point2D p_end	= new Point2D(e.getSceneX(), e.getSceneY());
		//Point2D p_delta = p_end.subtract(p_start);

		AnchorPane ap = ( AnchorPane ) GUIApplication.guiMain.getWindow();
		Bounds bounds = ap.getBoundsInParent();
		lineCur.setEndX( e.getSceneX() - bounds.getMinX() );
		lineCur.setEndY( e.getSceneY() - bounds.getMinY() );

		e.consume();
	}
}

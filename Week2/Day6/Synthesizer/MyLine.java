package Synthesizer;

import apple.laf.JRSUIConstants.WindowClipCorners;
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class MyLine extends Line {
    Connector wc_start = null;
    Connector wc_end = null;

    public void setConnectors(Connector wcs, Connector wce) {
        wc_start = wcs;
        wc_end = wce;
    }

    void removeLine() {
        AnchorPane lilAP = (AnchorPane)GUIApplication.guiMain.getWindow();
        if(wc_start != null) wc_start.removeLine(this);
        if(wc_end != null) wc_end.removeLine(this);
        lilAP.getChildren().remove(this);
    }

    void moveEnd(Connector wc, Point2D delta ) {
        if ( wc == wc_start ) {
            setStartX( getStartX() + delta.getX() );
            setStartY( getStartY() + delta.getY() );
        }
        else {
            setEndX(getEndX() + delta.getX());
            setEndY(getEndY() + delta.getY());
        }
    }

    Connector getConStart() {
        return wc_start;
    }
    Connector getConEnd() {
        return wc_end;
    }

    Connector getConIn() {
        if( wc_start.isConIn() ) return wc_start;
        else return wc_end;
    }
    Connector getConOut() {
        if ( wc_start.isConIn() ) return wc_end;
        else return wc_start;
    }
}

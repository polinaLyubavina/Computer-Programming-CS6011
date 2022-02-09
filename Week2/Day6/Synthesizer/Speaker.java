package Synthesizer;

import javafx.scene.paint.Color;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Speaker extends Base {
    Speaker() {
        super("Speaker", 100, 100, false, false);
        Connector speaker = new Connector(this, Connector.t_in, this);
        speaker.setFill(Color.BLACK);
        connectorIn = speaker;
    }

    void Play() {
        System.out.println("Play");
        MyLine widgetline = connectorIn.lines.get(0);
        Connector wcIncoming;
        if(widgetline.getConStart() == connectorIn) wcIncoming = widgetline.getConEnd();
        else wcIncoming = widgetline.getConStart();
        Base pwd = wcIncoming.parent;
        System.out.println(pwd.getId());
        AudioClip ac = pwd.getWaveClip();
        if(ac == null) {
            System.out.println("No sound clip");
            return;
        }

        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
            c.open(format, ac.getData(), 0, ac.getData().length);
            System.out.println("Playing... " + 88200);
            c.start();
        }
        catch(Exception e) {
            System.out.println("Excetion " + e);
            System.exit(-1);
        }
    }

    void Stop() throws LineUnavailableException {
        System.out.println("Stop");
        try {
            Clip lilclip = AudioSystem.getClip();
            lilclip.close();
        }
        catch(Exception e) {
            System.out.println("Exception " + e);
            System.exit(-1);
        }
    }
}

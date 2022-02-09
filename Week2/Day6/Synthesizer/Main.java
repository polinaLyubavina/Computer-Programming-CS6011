package Synthesizer;

import javafx.application.Application;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Main {
    public static void oldmain(String[] args) throws LineUnavailableException {
        System.out.println(java.nio.ByteOrder.nativeOrder()); // We don't need to do anything special

        // Get properties from the system about samples rates, etc.
        // AudioSystem is a class from the Java standard library.
        Clip c = AudioSystem.getClip(); //terrible name, different from our AudioClip class

        // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

        AudioComponent sine1 = new SineWave(100);
        AudioComponent sine2 = new SineWave(102);

        AudioComponent volF1 = new FilterVolume(0.5);
        volF1.connectInput(sine1);
        AudioComponent volF2 = new FilterVolume(0.75);
        volF2.connectInput(sine2);

        AudioComponent mix = new Mixer();
        mix.connectInput(volF1);
        mix.connectInput(volF2);

        AudioClip clip = mix.getClip();

        c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

        System.out.println( "About to play..." );
        c.start(); // Plays it.

        // Makes sure the program doesn't quit before the sound plays.
        while( c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning() ){
        // Do nothing.
        }

        System.out.println( "Done." );
        c.close();
    }

    public static void main(String[] args) throws LineUnavailableException {
		Application.launch( GUIApplication.class );
	}
}

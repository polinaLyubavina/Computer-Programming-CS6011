package Synthesizer;

import javafx.application.Application;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class Synthesizer {

	void main1() throws LineUnavailableException {

	Clip c = AudioSystem.getClip();

// This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
    AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

    //_________\\\\\\\\\_____________________________________________________________
    //AudioComponent gen = new SineWave(220);
    AudioComponent gen = new VFSine(70, 2000);
    AudioClip clip = new GUIVolume(gen, 2).getClip();
    //___________________________________________________________________________

    c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

    System.out.println( "About to play..." + AudioClip.length );
    c.start(); // Plays it.
    c.loop( 2 ); // Plays it 2 more times if desired, so 6 seconds total

    // Makes sure the program doesn't quit before the sound plays.
    while( c.getFramePosition() < AudioClip.length || c.isActive() || c.isRunning() ){
        // Do nothing.
    }

    System.out.println( "Done." );
    c.close();
}

//---------------------------------
    void main2() throws LineUnavailableException {

        Clip c = AudioSystem.getClip();
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

        //______________________________________________________________________
        Mixer mx = new Mixer();
        mx.connectInput(new GUIVolume(new SineWave(2220) , .2 ));
        mx.connectInput(new GUIVolume(new SineWave(130) , 1.5 ));

        //mx.connectInput(new GUIVolume(new GUIVFSineWave(220) ,  2));
        AudioClip clip = mx.getClip();
        //___________________________________________________________________________

        c.open( format16, clip.getData(), 0, clip.getData().length );

        System.out.println( "About to play..." + AudioClip.length );
        c.start(); // Plays it.
        c.loop( 2 ); // Plays it 2 more times if desired, so 6 seconds total

// Makes sure the program doesn't quit before the sound plays.
        while( c.getFramePosition() < AudioClip.length || c.isActive() || c.isRunning() ){
            // Do nothing.
        }

        System.out.println( "Done." );
        c.close();
    }

public static void main(String[] args) throws LineUnavailableException {
        Synthesizer s = new Synthesizer();
        s.main2();
        Application.launch( GUIApplication.class );
    }
}



package Synthesizer;

import com.apple.laf.AquaInternalFrameDockIconUI;

import Synthesizer.AudioClip;

public class Filter implements AudioComponent {
    AudioComponent audioComp;

    public Filter() {   
        
    }

    public static AudioClip CreateNewAudioClip() {
        short[] s = new short[88200];
        AudioClip result = new AudioClip();
        return result;
    }

    @Override
    public AudioClip getClip() {
        return(AudioClip) null;
    }

    @Override
    public void connectInput(AudioComponent ac) {

    }

    @Override
    public boolean hasInput() {
        // TODO Auto-generated method stub
        return false;
    }
}

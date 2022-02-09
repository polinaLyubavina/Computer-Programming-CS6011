package Synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent {

    ArrayList<AudioComponent> inputs = new ArrayList<>();

    @Override
    public AudioClip getClip() {
        ArrayList<AudioClip> clips = new ArrayList<>();
        for ( AudioComponent input : inputs ) {
            AudioClip t = input.getClip();
            clips.add( t );
        }
        return modifyClip( clips );
    }

    @Override
    public boolean hasInput() {
        return inputs.size() > 0;
    }

    @Override
    public void connectInput(AudioComponent inputComponent) {
        inputs.add(inputComponent);
    }

    AudioClip modifyClip(ArrayList<AudioClip> clips) {
        AudioClip mix = new AudioClip();

        for ( int clipIndex = 0; clipIndex < clips.size(); clipIndex++ ) {
            AudioClip clip = clips.get( clipIndex );

            for ( int j = 0; j < 88200; j++ ) {
                short clipValue = clip.getSample(j);
                short mixValue = mix.getSample(j);

                int outputValue = clipValue + mixValue;

                if ( outputValue > Short.MAX_VALUE )
                    outputValue = Short.MAX_VALUE;
                else if ( outputValue < Short.MIN_VALUE )
                    outputValue = Short.MIN_VALUE;
                
                mix.setSample( j, ( short ) outputValue );
            }
        }

        return mix;
    }

}

package Synthesizer;

public class FilterVolume implements AudioComponent {

    double volume = 1;
    AudioComponent input;

    FilterVolume(double vol) {
        volume = vol;
    }

    public AudioClip getClip() {
        AudioClip original = input.getClip();
        AudioClip result = modifyClip(original); // Some modification of the original clip.
        return result;
    }

    public boolean hasInput() {
        return true;
    }

    public void connectInput(AudioComponent inputComponent) {
        input = inputComponent;
    }

    AudioClip modifyClip(AudioClip original) {
        AudioClip output = new AudioClip();

        for (int i = 0; i < 88200; i++) {
            int originalValue = original.getSample(i);
            int newValue = (int) (originalValue * volume);

            if ( newValue > 32767 )
                newValue = 32767;
			else if ( newValue < -32767 )
                newValue = -32767;

            output.setSample(i, (short) newValue);
        }

        return output;
    }
    
}

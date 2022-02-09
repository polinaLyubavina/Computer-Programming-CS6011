package Synthesizer;

public class WaveBase implements AudioComponent {
    AudioClip ac;

    @Override
    public AudioClip getClip() {
        return ac;
    }

    @Override
    public void connectInput(AudioComponent input) {
        assert(false);
    }

    @Override
    public short getSample(int i) {
        return ac.getSample(i);
    }

    @Override
    public boolean hasInput() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void connectInput(AudioComponent input, int index) {
        // TODO Auto-generated method stub
        
    }

}

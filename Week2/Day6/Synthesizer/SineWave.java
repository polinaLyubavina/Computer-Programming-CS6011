package Synthesizer;


public class SineWave extends WaveBase {

    private double frequency_;

    // constructor
    public SineWave(int freq) {
        frequency_ = freq;
    }

    // returns current sound produced by component
    public AudioClip getClip() {
        AudioClip temp = new AudioClip();
        short maxValue = Short.MAX_VALUE; 

        for(int i = 0; i < 88200; i++) {
            temp.setSample(i, (short) (maxValue * Math.sin(2 * Math.PI * frequency_ * i / 44100)));
        }

        return temp;
    }

    // checks for input
    public boolean hasInput() {
        return false;
    }

    // connects another device
    public void connectInput(AudioComponent input){
        System.out.println("I'm a sine wave and I don't want any inputs.");
        assert (false); 
    }
}

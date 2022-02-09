package Synthesizer;


public class VFSineWave extends WaveBase {

    private double start_;
    private double stop_;

    // constructor
    public VFSineWave(int start, int stop) {
        start_ = start;
        stop_ = stop;
    }

    // returns current sound produced by component
    public AudioClip getClip() {
        AudioClip temp = new AudioClip();
        short maxValue = Short.MAX_VALUE; 

		double freq[] = new double[88200];
        for(int i = 0; i < 88200; i++) {
            freq[i] = ( i * (stop_ - start_) + start_ * 88200 ) / 88200;
				if ( i % 1000 == 0 ) System.out.println(freq[i]);
        }
        double phase = 0;
        for(int i = 0; i < 88200; i++) {
            phase += 2 * Math.PI * freq[i] / 44100;
            temp.setSample(i, (short) ((maxValue / 2.) * Math.sin( phase )));
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

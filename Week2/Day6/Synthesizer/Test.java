package Synthesizer;


import java.util.Random;


public class Test {
    short[] data;
    int nsamples;

    Test(int len) {
    nsamples = len;
    }

    void Values() {
        AudioClip aud = new AudioClip(new short[ samples ] );
        for( int i = Short.MIN_VALUE; i <= Short.MAX_VALUE; i++) {
            aud.setSample( 0, (short) i );
            short s = aud.getSample(0);
            if(s != i ) {
                System.out.printf("Error set  %d != get %d\n", i, s);
                System.exit (-1);
            }
        }
        System.out.println("success");
    }

    void createRandom() {
        Random rand = new Random(37);
        data = new short[ nsamples ];
        for( int i =0; i < ( nsamples ); i++) {
        data [i] = (byte) (rand.nextInt(256) -128);

        }
}
    public static void main(String[] args) {
        Test test = new Test(AudioClip.length);
        // t.createRandom();
        t.Values();

    }
}




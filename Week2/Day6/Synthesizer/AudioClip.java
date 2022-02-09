package Synthesizer;

import java.util.Arrays;
import org.junit.Test;

public class AudioClip {

    public static final int TOTAL_SAMPLES = 88200;
    public static double duration = 2.0;
    public static int sample_rate = 44100; 
    private byte[] byte_array = new byte[176400];
    // public byte[] actualByte = new byte[(int)(sampleRate * Duration) * 2]; 
    // public final static int totalSamples = (int)(duration_ * sample_rate_); 



    // gets sample
    short getSample(int index) {
        byte byte2 = byte_array[2 * index];
        byte byte1 = byte_array[(2 * index) + 1];

        //int b1 = Byte.toUnsignedInt(actualByte[2 * i]); // build it function to turn byte into integer but not do sign extension

        int r = byte1 & 0xFF;
        r = (r << 8) | (byte2 & 0xFF);
        short s = (short)r;

        //b1 = mask | (b2 << 8);    // up top instead of masking it (values should be 32767|-32767)
        // int mask = b1 & 0xFF;

        return s;
    }

    // sets sample
    void setSample(int index, short value) {
        byte byte1 = (byte)(value & 0x00FF); // Gets right most byte (bigger numbers by endianness)
        byte byte2 = (byte)((value >> 8) & 0x00FF); // Gets left most byte (smaller numbers by endianness)

        byte_array[2 * index] = byte1;
        byte_array[(2 * index) + 1] = byte2;
    }

    // makes a copy of byte array
    byte[] getData() {
        byte[] copy = Arrays.copyOf(byte_array, 176400);
        return copy;
    }
    
    @Test
    public void test() {
        AudioClip test = new AudioClip();
        test.setSample(0, (short) 30000);
        test.setSample(1, (short) 5000);
        test.setSample(2, (short) -5000);

        System.out.println(test.getSample(0));
        assert(test.getSample(0) == 30000);
        assert(test.getSample(1) == 5000);
        assert(test.getSample(2) == -5000);
    }

}

package Synthesizer;

public class Volume extends Filter {

	double scale;

	Volume() {}

	Volume(AudioComponent comp, double sca) {
		scale = sca;
		audComp = comp;
	}

	Volume(AudioClip ac) {}

	public AudioClip getClip() {
		AudioClip original = audComp.getClip();
		return Volume.modifyClip( original, scale);
	}

	public static AudioClip modifyClip( AudioClip ac, double scale ) {
		System.out.println( "Modifying volume by " + scale );
		AudioClip result = CreateNewAudioClip();
		int adjustedSample;

		for( int i = 0; i < 88200; i++ ) {
			adjustedSample = (int) ( scale * ac.getSample( i ) );
			if ( adjustedSample > 32767 )
				adjustedSample = 32767;
			else if ( adjustedSample < -32767 )
				adjustedSample = -32767;

			result.setSample( i, ( short ) adjustedSample );
		}
		return result;
	}
}



package Synthesizer;

// Some piece of code that generates an audio clip for us,
// has to implement interface, get data, and connect to input

// some piece of code that generates an audio clip for us, has
// to implement interface, get data, and connect to input
interface AudioComponent { 

    // returns the current sound produced by this component
    abstract AudioClip getClip();

    // checks for input
    abstract boolean hasInput();

    // connects another device
    abstract void connectInput(AudioComponent input);

}

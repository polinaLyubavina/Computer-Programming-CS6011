import java.util.Random;

public class RandomNumber {


    public static void main(String[] args) {

        int [] numbers = new int[10];

        for( int i = 0; i < numbers.length; i++ ) {
            Random rand = new Random();
            numbers[i] = rand.nextInt( 20);
            System.out.println( i + ": " + numbers[i]);
        }


    }
}
package Fraction;

public class Fraction implements Comparable<Fraction> {

    private long numerator_;
    private long denominator_;

    // default Constructor
    Fraction() {
        numerator_ = 0;
        denominator_ = 1; 
    }

    // A constructor which sets the value of the fraction to a specific 
    // numerator (n) and denominator (d). 
    Fraction(long n, long d) {
        if( n < 0 && d < 0 ) {      // if both negative, makes positive
            n *= -1;
            d *= -1;
        }
        else if( d < 0 ) {          // if denominator negative, sets the nominator to positive
            n = -n;
            d *= -1;
        }
        
        numerator_ = n;
        denominator_ = d;
        
        reduce();                   // reduces the fraction 
    }

    // Returns a new fraction that is the result of the right hand side (rhs) fraction added to this fraction.
    Fraction plus(Fraction rhs) {
        long new_numerator = (rhs.numerator_ * denominator_) + (rhs.denominator_ * numerator_);
        long new_denominator = (rhs.denominator_ * denominator_);
        return new Fraction(new_numerator, new_denominator);
    }

    // Returns a new fraction that is the result of the right hand side (rhs) fraction subtracted from this fraction.
    Fraction minus(Fraction rhs) {
        long new_numerator =  (rhs.denominator_ * numerator_) - (rhs.numerator_ * denominator_);
        long new_denominator = (rhs.denominator_ * denominator_);
        return new Fraction(new_numerator, new_denominator);
    }

    // Returns a new fraction that is the result of this fraction multiplied by the right hand side (rhs) fraction.
    Fraction times(Fraction rhs) {
        long new_numerator = (rhs.numerator_ * numerator_);
        long new_denominator = (rhs.denominator_ * denominator_);
        return new Fraction(new_numerator, new_denominator);
    }

    // Returns a new fraction that is the result of this fraction divided by the right hand side (rhs) fraction.
    Fraction dividedBy(Fraction rhs) {
        long new_numerator = (numerator_ * rhs.denominator_);
        long new_denominator = (denominator_ * rhs.numerator_);
        return new Fraction(new_numerator, new_denominator);
    }

    // Returns a new fraction that is the reciprocal of this fraction.
    Fraction reciprocal() {
        long new_numerator = denominator_;
        long new_denominator = numerator_;
        return new Fraction(new_numerator, new_denominator);
    }

    // Returns a string representing this fraction.
    //The string should have the format: "N/D", where N is the numerator, and D is the denominator. 
    String to_string() {
        String N = Long.toString(numerator_);
        String D = Long.toString(denominator_);
        return N + "/" + D;
    }

    // Returns a (double precision) floating point number that is the approximate value of this fraction, printed as a real number.
    double toDouble() {
        return ( numerator_ / (double) denominator_);
    }

    // Changes this fraction to its reduced form.
    void reduce() {
        long gcd = GCD();
        
        numerator_ = numerator_ / gcd;
        denominator_ = denominator_ / gcd;
    }

    // Returns the greatest common divisor of this fraction's numerator and denominator. This is a helper method for the reduce method, below.
    long GCD() {
        long gcd = numerator_;
        long remainder = denominator_;
        while(remainder != 0) {
        long temp = remainder;
        remainder = gcd % remainder;
        gcd = temp;
        }
        
        return gcd; 
    }

    //-------------------------------------------//

    @Override
    public int compareTo(Fraction o) {
        if(this.denominator_ == o.denominator_ && this.numerator_ == o.numerator_) {
            return 0;
        } else if(this.toDouble() > o.toDouble()) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        new Fraction(); 
    }
}

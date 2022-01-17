package Fraction;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class Tests {
    @Test
    public void test() {
        Fraction test = new Fraction(5, 25);

        assert(test.to_string().equals("1/5"));
        assert(test.plus(new Fraction(1, 5)).to_string().equals("2/5"));
        assert(test.minus(new Fraction(1, 5)).to_string().equals("0/1"));
        assert(test.times(new Fraction(1, 5)).to_string().equals("1/25"));
        assert(test.dividedBy(new Fraction(1, 5)).to_string().equals("1/1"));

        ArrayList<Fraction> toSort = new ArrayList<Fraction>();
        toSort.add(test);
        toSort.add(new Fraction(3, 5));
        toSort.add(new Fraction(2, 5));
        toSort.add(new Fraction(4, 5));
        Collections.sort(toSort);

        assert(toSort.get(0).to_string().equals("1/5"));
        assert(toSort.get(1).to_string().equals("2/5"));
        assert(toSort.get(2).to_string().equals("3/5"));
        assert(toSort.get(3).to_string().equals("4/5"));

    }
}

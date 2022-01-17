public class Triplet<A, B, C> {
    A first;
    B second;
    C third;

    Triplet(A f, B s, C t) {
        first = f;
        second = s;
        third = t;
    }

    C getThird() {
        return third;
    }

    A getFirst() {
        return first;
    }
    
}

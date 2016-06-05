public class Pair {

    Polyomino first;
    Polyomino second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(Polyomino first, Polyomino second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        return (first.hashCode() * 7) ^ (second.hashCode() * 11);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Pair) {
            Pair tmp = (Pair) obj;

            return (first.equals(tmp.first) && second.equals(tmp.second)
                    || first.equals(tmp.second) && second.equals(tmp.first));
        }

        return false;
    }

    public void setFirst(Polyomino first) {
        this.first = first;
    }

    public void setSecond(Polyomino second) {
        this.second = second;
    }

    public Polyomino getFirst() {
        return first;
    }

    public Polyomino getSecond() {
        return second;
    }
}

public class Configuration {
    private Polyomino first;
    private Polyomino second;

    public Polyomino getFirst() {
        return first;
    }

    public void setFirst(Polyomino first) {
        this.first = first;
    }

    public Polyomino getSecond() {
        return second;
    }

    public void setSecond(Polyomino second) {
        this.second = second;
    }

    public Configuration(Polyomino first, Polyomino second) {
        this.first = first;
        this.second = second;
    }
}

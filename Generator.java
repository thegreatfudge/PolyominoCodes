import java.util.*;

public class Generator {
    private List<String> alphabet;
    private int min, max, howMany;
    private List<Point> available;
    private List<Monomino> monominos;
    private Random rand;

//    public List<Polyomino> generateSid(){
//        List<Polyomino> polyominoList = new LinkedList<>();
//        Set<Point> availablePoints = new HashSet<>();
//
//        monominos.add(new Monomino(new Point(0,0),alphabet.get(rand.nextInt(alphabet.size())),true,false));
//        availablePoints.add(new Point(0,1));
//        availablePoints.add(new Point(0,-1));
//        availablePoints.add(new Point(1,0));
//        availablePoints.add(new Point(-1,0));
//        for(int i=0; i<howMany; i++){
//            monominos.add(new Monomino(availablePoints.get(rand.nextInt())));
//        }
//        availablePoints.
//        return polyominoList;
//    }

    public Generator(List<String> alphabet, int min, int max, int howMany) {
        available = new LinkedList<>();
        monominos = new LinkedList<>();
        this.alphabet = alphabet;
        this.min = min;
        this.max = max;
        this.howMany = howMany;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public List<Point> getAvailable() {
        return available;
    }

    public void setAvailable(List<Point> available) {
        this.available = available;
    }

    public List<Monomino> getMonominos() {
        return monominos;
    }

    public void setMonominos(List<Monomino> monominos) {
        this.monominos = monominos;
    }
}

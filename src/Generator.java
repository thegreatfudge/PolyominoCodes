import java.util.*;

public class Generator {
    private List<String> alphabet;
    private int min, max, howMany;
    private List<Point> available;
    private Set<Monomino> monominos;
    private Random rand;


    public List<Polyomino> generateSid(){
        List<Polyomino> polyominoList = new LinkedList<>();
        Set<Point> usedPoints = new HashSet<>();
        int size;
        for(int i = 0; i < howMany; i++){
            Set<Monomino> monominos = new HashSet<>();
            available.clear();
            monominos.clear();
            usedPoints.clear();
            monominos.add(new Monomino(new Point(0,0),alphabet.get(rand.nextInt(alphabet.size())), true, false));
            available.add(new Point(1,0));
            available.add(new Point(0,1));
            available.add(new Point(-1,0));
            available.add(new Point(0,-1));
            usedPoints.add(new Point(0,0));
            size = rand.nextInt((max - min) + 1) + min;
            for(int j = 0; j < size; j++){


                int choosenPoint = rand.nextInt(available.size());
                monominos.add(new Monomino(available.get(choosenPoint), alphabet.get(rand.nextInt(alphabet.size())), false, false));
                available.forEach(System.out::print);
                System.out.println();
                System.out.println("WYLOSOWANY: " + available.get(choosenPoint));
                Point tmp = available.get(choosenPoint).movePointX(1);
                if(!usedPoints.contains(tmp)){
                    System.out.println("MOVEPOINTX1: " + tmp);
                    available.add(tmp);
                }
                Point tmp2 = available.get(choosenPoint).movePointX1(1);
                if(!usedPoints.contains(tmp2)){
                    System.out.println("MOVEPOINTX-1: " + tmp2);
                    available.add(tmp2);
                }
                Point tmp3 = available.get(choosenPoint).movePointY(1);
                if(!usedPoints.contains(tmp3)){
                    System.out.println("MOVEPOINTY1: " + tmp3);
                    available.add(tmp3);
                }
                Point tmp4 = available.get(choosenPoint).movePointY1(1);
                if(!usedPoints.contains(tmp4)){
                    System.out.println("MOVEPOINTY-1: " + tmp4);
                    available.add(tmp4);
                }

                usedPoints.add(available.get(choosenPoint));
                available.remove(choosenPoint);
            }
            int choosenPoint = rand.nextInt(available.size());
            while(!(available.get(choosenPoint).getCoordinateX() > 0)){
                choosenPoint = rand.nextInt(available.size());
            }
            monominos.add(new Monomino(available.get(choosenPoint), "", false, true));
            polyominoList.add(new Polyomino(monominos));
        }
        return polyominoList;
    }

    public Generator(List<String> alphabet, int min, int max, int howMany) {
        available = new LinkedList<>();
        monominos = new HashSet<>();
        rand = new Random();
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

}

import javax.sound.midi.SysexMessage;
import java.util.*;

public class Main {
    static Polyomino polyomino1;
    static Polyomino polyomino2;
    static Polyomino polyomino3;
    static Polyomino polyomino4;
    static Polyomino polyomino5;
    static Polyomino polyomino7;

    public static void main(String[] args) {
        Set<Monomino> listOfMonominos = new HashSet<>();
        listOfMonominos.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos.add(new Monomino(new Point(1,0),"",false,true));
        polyomino1= new Polyomino(listOfMonominos);

        Set<Monomino> listOfMonominos2 = new HashSet<>();
        listOfMonominos2.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos2.add(new Monomino(new Point(0,1), "", false, true));
        polyomino2 = new Polyomino(listOfMonominos2);

//        Set<Monomino> listOfMonominos4 = new HashSet<>();
//        listOfMonominos4.add(new Monomino(new Point(0,0),"a",true,false));
//        listOfMonominos4.add(new Monomino(new Point(0,1), "a", false, false));
//        listOfMonominos4.add(new Monomino(new Point(1,1), "", false, true));
//        polyomino4 = new Polyomino(listOfMonominos4);
//
//        Set<Monomino> listOfMonominos3 = new HashSet<>();
//        listOfMonominos3.add(new Monomino(new Point(0,0),"a",true,false));
//        listOfMonominos3.add(new Monomino(new Point(0,-1),"a",false,false));
//        listOfMonominos3.add(new Monomino(new Point(1,0),"",false,true));
//        polyomino3 = new Polyomino(listOfMonominos3);

        List<Polyomino> test = new ArrayList<>();
        test.add(polyomino1);
        test.add(polyomino2);
//        test.add(polyomino4);
//        test.add(polyomino3);

        System.out.println(PolyominoCode.checkIfSetIsCode(test));

        Map<Point, String> mapTest1 = new HashMap<>();
        Map<Point, String> mapTest2 = new HashMap<>();

        mapTest1.put(new Point(0,0),"a");
        mapTest2.put(new Point(0,0),"a");

        //System.out.println(mapTest1.equals(mapTest2));
    }
}

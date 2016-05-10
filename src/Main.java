import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static Polyomino polyomino1;
    static Polyomino polyomino2;
    static Polyomino polyomino3;
    static Polyomino polyomino4;

    public static void main(String[] args) {
        Set<Monomino> listOfMonominos = new HashSet<>();
        listOfMonominos.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos.add(new Monomino(new Point(1,0),"a",false,false));
        listOfMonominos.add(new Monomino(new Point(0,1),"",false,true));
        listOfMonominos.add(new Monomino(new Point(-10,0),"a",false,false));
        polyomino1 = new Polyomino(listOfMonominos);

        Set<Monomino> listOfMonominos2 = new HashSet<>();
        listOfMonominos2.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos2.add(new Monomino(new Point(1,0),"a",false,false));
        listOfMonominos2.add(new Monomino(new Point(-1,0),"a",false,false));
        listOfMonominos2.add(new Monomino(new Point(-2,0),"a",false,false));
        listOfMonominos2.add(new Monomino(new Point(-10,0),"b",false,false));
        listOfMonominos2.add(new Monomino(new Point(2,0),"",false,true));
        polyomino2 = new Polyomino(listOfMonominos2);

        Set<Monomino> listOfMonominos3 = new HashSet<>();
        listOfMonominos3.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos3.add(new Monomino(new Point(0,1),"a",false,false));
        listOfMonominos3.add(new Monomino(new Point(1,1),"",false,true));
        polyomino3 = new Polyomino(listOfMonominos3);

        Set<Monomino> listOfMonominos4 = new HashSet<>();
        listOfMonominos4.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos4.add(new Monomino(new Point(0,-1),"a",false,false));
        listOfMonominos4.add(new Monomino(new Point(0,-2),"a",false,false));
        listOfMonominos4.add(new Monomino(new Point(1,0),"",false,true));
        polyomino4 = new Polyomino(listOfMonominos4);

        List<Polyomino> test = new ArrayList<>();
        test.add(polyomino1);
        test.add(polyomino2);
        test.add(polyomino3);
        test.add(polyomino4);
//        Point tests = PolyominoCode.findEastVector(test);
//        System.out.println("(" + tests.getCoordinateX() + " " + tests.getCoordinateY() + ")");
//        PolyominoCode.checkIfHalfPlaneContainsAllMonominos(new Point(-1,-1),test);
//        System.out.println(PolyominoCode.calculateAngleBetweenVectors(polyomino1,new Point(-1,0)));
//        List<Polyomino> tmp = PolyominoCode.findMinMaxAngleBetweenPolyominos(test, new Point(1,1));
//        System.out.println("NV: " + tmp.get(0).getEndOfPolyomino().getCoordinates().toString());
//        System.out.println("SV: " + tmp.get(1).getEndOfPolyomino().getCoordinates().toString());
        PolyominoCode.checkIfSetIsCode(test);
        //System.out.println(PolyominoCode.checkIfLabelEqualInCEMinus(polyomino1,polyomino2));
    }
}

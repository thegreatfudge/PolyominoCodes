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
        listOfMonominos.add(new Monomino(new Point(1,0),"a",false,false));
        listOfMonominos.add(new Monomino(new Point(0,1),"",false,true));

        polyomino1= new Polyomino(listOfMonominos);
        Set<Monomino> listOfMonominos7 = new HashSet<>();
        listOfMonominos7.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos7.add(new Monomino(new Point(1,0),"a",false,false));
        listOfMonominos7.add(new Monomino(new Point(2,0),"",false,true));
   //     listOfMonominos7.add(new Monomino(new Point(-10,0),"a",false,false));

        polyomino7 = new Polyomino(listOfMonominos7);


        Set<Monomino> listOfMonominos2 = new HashSet<>();
        listOfMonominos2.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos2.add(new Monomino(new Point(0,1),"a",false,false));
//        listOfMonominos2.add(new Monomino(new Point(-1,0),"a",false,false));
       // listOfMonominos2.add(new Monomino(new Point(1,0),"a",false,false));
     //   listOfMonominos2.add(new Monomino(new Point(10,0),"a",false,false));

        listOfMonominos2.add(new Monomino(new Point(1,1),"",false,true));
        polyomino2 = new Polyomino(listOfMonominos2);

        Set<Monomino> listOfMonominos3 = new HashSet<>();
        listOfMonominos3.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos3.add(new Monomino(new Point(0,-1),"a",false,false));
      //  listOfMonominos3.add(new Monomino(new Point(0,-2),"a",false,false));
        listOfMonominos3.add(new Monomino(new Point(1,0),"",false,true));
        polyomino3 = new Polyomino(listOfMonominos3);

        Set<Monomino> listOfMonominos4 = new HashSet<>();
        listOfMonominos4.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos4.add(new Monomino(new Point(1,0),"",false,true));
        polyomino4 = new Polyomino(listOfMonominos4);

        Set<Monomino> listOfMonominos5 = new HashSet<>();
        listOfMonominos5.add(new Monomino(new Point(0,0),"a",true,false));
        listOfMonominos5.add(new Monomino(new Point(0,1),"",false,true));

        polyomino5 = new Polyomino(listOfMonominos5);

        //listOfMonominos6.add(new Monomino(new Point()));

        List<Polyomino> test = new ArrayList<>();
        test.add(polyomino1);
        test.add(polyomino7);
        test.add(polyomino2);
        test.add(polyomino3);
//        test.add(polyomino4);
//        test.add(polyomino5);
        PolyominoCode.calculateVectors(test);
 //       System.out.println(PolyominoCode.findEastVector(test));
//        PolyominoCode.checkIfHalfPlaneContainsAllMonominos(new Point(-1,-1),test);
//        System.out.println(PolyominoCode.calculateAngleBetweenVectors(polyomino1,new Point(-1,0)));
//        List<Polyomino> tmp = PolyominoCode.findMinMaxAngleBetweenPolyominos(test, new Point(1,1));
//        System.out.println("NV: " + tmp.get(0).getEndOfPolyomino().getCoordinates());
//        System.out.println("SV: " + tmp.get(1).getEndOfPolyomino().getCoordinates());
//        System.out.println(PolyominoCode.rescaleVector(new Point(1, 1), test, VectorDirection.EAST));

//        System.out.println(PolyominoCode.checkIfEndsOutsideCWEPlus(polyomino3,polyomino4));
//        System.out.println(PolyominoCode.checkIfHalfPlaneContainsAllMonominos(new Point(-3,0), Arrays.asList(polyomino5), VectorDirection.SOUTH));
     //    System.out.println(PolyominoCode.checkIfLabelEqualInCEMinus(polyomino1, polyomino2, new Pair()));
      //  System.out.println(PolyominoCode.checkIfPlaneContainsMonomino(VectorDirection.WEST,new Point(-1,-1),new Monomino(new Point(10,0),"a",false,false)));
//        System.out.println(polyomino1.equals(polyomino7));

       // test.stream().forEach(e -> System.out.println(e.hashCode()));
       // System.out.println(PolyominoCode.concatenatePolyominos(polyomino1,polyomino2));

        System.out.println(PolyominoCode.checkIfSetIsCode(test));







    }
}

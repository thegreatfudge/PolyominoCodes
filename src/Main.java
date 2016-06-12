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
        List<String> alphabet = new LinkedList<>();
        List<Polyomino> polyominos = new LinkedList<>();
        alphabet.add("a");
        alphabet.add("b");
        Generator gen = new Generator(alphabet,0,6,3);
        polyominos = gen.generateSid();
        PolyominoCode polyominoCode = new PolyominoCode();
        while(!polyominoCode.checkIfSetIsCode(polyominos)){
            polyominos = gen.generateSid();
        }
        polyominos.forEach(System.out::println);

//        Set<Monomino> listOfMonominos = new HashSet<>();
//        listOfMonominos.add(new Monomino(new Point(0,0),"a",true,false));
//        listOfMonominos.add(new Monomino(new Point(1,0),"",false,true));
//        polyomino1= new Polyomino(listOfMonominos);
//
//        Set<Monomino> listOfMonominos2 = new HashSet<>();
//        listOfMonominos2.add(new Monomino(new Point(0,0),"a",true,false));
//        listOfMonominos2.add(new Monomino(new Point(0,1), "", false, true));
//        polyomino2 = new Polyomino(listOfMonominos2);
//
//
//        Set<Monomino> listOfMonominos4 = new HashSet<>();
//        listOfMonominos4.add(new Monomino(new Point(0,0),"a",true,false));
//        listOfMonominos4.add(new Monomino(new Point(1,0), "a", false, false));
//        listOfMonominos4.add(new Monomino(new Point(2,0), "", false, true));
//        polyomino4 = new Polyomino(listOfMonominos4);
//
////        Set<Monomino> listOfMonominos3 = new HashSet<>();
////        listOfMonominos3.add(new Monomino(new Point(0,0),"a",true,false));
////        listOfMonominos3.add(new Monomino(new Point(0,-1),"a",false,false));
////        listOfMonominos3.add(new Monomino(new Point(1,0),"",false,true));
////        polyomino3 = new Polyomino(listOfMonominos3);
//
//        List<Polyomino> test = new LinkedList<>();
//        test.add(polyomino1);
//        test.add(polyomino2);
////        test.add(polyomino4);
////        test.add(polyomino3);
//
//        System.out.println(PolyominoCode.checkIfSetIsCode(test));
//
//        List<Polyomino> toEncode = new ArrayList<>();
//        toEncode.add(polyomino1);
//        toEncode.add(polyomino2);
//        toEncode.add(polyomino1);
//        toEncode.add(polyomino1);
//        System.out.println(PolyominoCode.encodeCode(toEncode));
//       // System.out.println(PolyominoCode.decodeCode(PolyominoCode.encodeCode(toEncode), test));
//        //System.out.println(mapTest1.equals(mapTest2));

    }
}

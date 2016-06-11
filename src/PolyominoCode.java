import java.util.*;
import java.lang.Math;

public class PolyominoCode {

//    public static Polyomino concatenatePolyominos(Polyomino first, Polyomino second){
//
//        Set<Monomino> tmp = new HashSet<>();
//
//        Polyomino firstCopy = first.copy();
//        Polyomino secondCopy = second.copy();
//
//        for(Monomino monomino : secondCopy.getMonominos()){
//            monomino.setCoordinates(new Point(
//                    monomino.getCoordinates().getCoordinateX()+firstCopy.getEndOfPolyomino().getCoordinates().getCoordinateX(),
//                    monomino.getCoordinates().getCoordinateY()+firstCopy.getEndOfPolyomino().getCoordinates().getCoordinateY()
//                    ));
//        }
//
//        Monomino m = null;
//
//        for (Monomino monomino : firstCopy.getMonominos()) {
//            if (monomino.isEnd()) {
//                m = monomino;
//            }
//        }
//
//        firstCopy.getMonominos().remove(m);
//
//        firstCopy.setEndOfPolyomino(null);
//
//        secondCopy.getBeginningOfPolyomino().setBeginning(false);
//
//        boolean flag;
//
//        for (Monomino x : secondCopy.getMonominos()) {
//
//            flag = true;
//
//            for (Monomino y : firstCopy.getMonominos()) {
//                if (x.getCoordinates().equals(y.getCoordinates())) {
//                    flag = false;
//                    break;
//                }
//            }
//
//            if (flag) {
//                tmp.add(x);
//            }
//        }
//
//        tmp.addAll(firstCopy.getMonominos());
//
//
//        Polyomino result = new Polyomino(tmp);
//
//        return result;
//    }

        public static Polyomino concatenatePolyominos(Polyomino first, Polyomino second) {

            Set<Monomino> tmp = new HashSet<>();

            Polyomino firstCopy = first.copy();
            Polyomino secondCopy = second.copy();

            for (Monomino monomino : secondCopy.getMonominos()) {
                monomino.setCoordinates(new Point(
                        monomino.getCoordinates().getCoordinateX() + firstCopy.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                        monomino.getCoordinates().getCoordinateY() + firstCopy.getEndOfPolyomino().getCoordinates().getCoordinateY()
                ));
            }



            firstCopy.getMonominos().remove(firstCopy.getEndOfPolyomino());
            secondCopy.getBeginningOfPolyomino().setBeginning(false);

            Set<Monomino> listOfMonominos = new HashSet<>();
            for(Monomino monomino : firstCopy.getMonominos()){
                listOfMonominos.add(monomino);
            }

            for(Monomino monomino : secondCopy.getMonominos()){
                if(!listOfMonominos.contains(monomino))
                    listOfMonominos.add(monomino);
            }

            Polyomino result = new Polyomino(listOfMonominos);
            return result;
        }

    public static boolean checkIfEndsOutsideCWEPlus(Polyomino first, Polyomino second,
                                                    Map<VectorDirection,Point> vectors){
        //TODO ASK IF DRAWING IS WRONG
        // Prep 6.

        Map<VectorDirection,Point> copyVectors = new HashMap<>();
        for(Map.Entry<VectorDirection, Point> entry : vectors.entrySet()){
            copyVectors.put(entry.getKey(),entry.getValue().copy());
        }

        Map<VectorDirection, Point> vectorsForCE = new HashMap<>();
        Map<VectorDirection, Point> vectorsForCW = new HashMap<>() ;

        for(Map.Entry<VectorDirection,Point> entry : copyVectors.entrySet()){
            if(entry.getKey() != VectorDirection.EAST){
                vectorsForCE.put(entry.getKey(),entry.getValue().copy());
            }
            if(entry.getKey() != VectorDirection.WEST){
                vectorsForCW.put(entry.getKey(),entry.getValue().copy());
            }
        }


        vectorsForCW.get(VectorDirection.NORTH).translate(-1);
        vectorsForCW.get(VectorDirection.SOUTH).translate(-1);


        Point tmp = vectorsForCW.get(VectorDirection.NORTH);
        vectorsForCW.put(VectorDirection.NORTH,vectorsForCW.get(VectorDirection.SOUTH));
        vectorsForCW.put(VectorDirection.SOUTH, tmp);


        if ((checkIfMonominoIsWithinVector(second.getEndOfPolyomino(), first.getEndOfPolyomino(), vectorsForCW)
                || checkIfMonominoIsWithinVector(second.getEndOfPolyomino(), first.getEndOfPolyomino(),vectorsForCE))
                && (checkIfMonominoIsWithinVector(first.getEndOfPolyomino(), second.getEndOfPolyomino(),vectorsForCW)
                || checkIfMonominoIsWithinVector(first.getEndOfPolyomino(), second.getEndOfPolyomino(),vectorsForCE)))
        {
            return true;
        }

        return false;
    }

    public static boolean checkIfMonominoIsWithinVector(Monomino monomino, Monomino end, Map<VectorDirection, Point> vectors) {
        Monomino tmp = new Monomino(new Point(0,0), "a", false, false);
        for(Map.Entry<VectorDirection, Point> mapEntry : vectors.entrySet() ){
 //           System.out.println(mapEntry.getKey() + " " + mapEntry.getValue());
            tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - end.getCoordinates().getCoordinateX(),
                    monomino.getCoordinates().getCoordinateY() - end.getCoordinates().getCoordinateY()));
   //         System.out.println(monomino + "  tmp: " +tmp);

            if(!checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
    //            System.out.println("RETURNING FALSE");
                return false;
            }
        }
    //    System.out.println("RETURNING TRUE");
        return true;
    }

    public static boolean checkIfLabelEqualInCEMinus (Polyomino first, Polyomino second, Pair configuration,
                                                      Map<VectorDirection, Point> vectors){
        boolean contains = true;
        Set<Monomino> outcastsFromFirst = new HashSet<>();
        Set<Monomino> outcastsFromSecond = new HashSet<>();

//        Map<VectorDirection, Point> vectorOfFirst = findAllVectorsInPolyomino(first);
//        Map<VectorDirection, Point> vectorOfSecond = findAllVectorsInPolyomino(second);

//
//        vectorOfFirst.entrySet().stream().forEach(System.out::println);
//        vectorOfSecond.entrySet().stream().forEach(System.out::println);
        Monomino tmp = new Monomino(new Point(0,0), "a", false, false);

        for(Monomino monomino : first.getMonominos()) {

            for (Map.Entry<VectorDirection, Point> mapEntry : vectors.entrySet()) {
                if (mapEntry.getKey() != VectorDirection.EAST) {

                    tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - first.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            monomino.getCoordinates().getCoordinateY() - first.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                    if (!checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
                        contains = false;
                        break;
                    }
             //       System.out.println(tmp +" TMP " + monomino.getCoordinates() + " MONO COORD"+
        //            " vector" + mapEntry.getKey() + " "+mapEntry.getValue());
                }
            }
            if (!contains) {
                for (Map.Entry<VectorDirection, Point> mapEntry : vectors.entrySet()) {

                    if (mapEntry.getKey() != VectorDirection.EAST) {

                        tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - second.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                                monomino.getCoordinates().getCoordinateY() - second.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                        if (!checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
                //            System.out.println(monomino + " yhtyjgukh");
                            outcastsFromFirst.add(monomino);
                        }
                //        System.out.println(tmp +" TMP " + monomino.getCoordinates() + " MONO COORD"+
                 //               " vector" + mapEntry.getKey() + " "+mapEntry.getValue());
                    }
                }
            }
        }
        contains = true;

        for(Monomino monomino : second.getMonominos()){
            for (Map.Entry<VectorDirection, Point> mapEntry : vectors.entrySet()) {

                if (mapEntry.getKey() != VectorDirection.EAST) {

                    tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - first.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            monomino.getCoordinates().getCoordinateY() - first.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                    if (!checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
                        contains = false;
                        break;
                    }
             //       System.out.println(tmp +" TMP " + monomino.getCoordinates() + " MONO COORD"+
              //              " vector" + mapEntry.getKey() + " "+mapEntry.getValue());
                }
            }
            if (!contains) {
                for (Map.Entry<VectorDirection, Point> mapEntry : vectors.entrySet()) {

                    if (mapEntry.getKey() != VectorDirection.EAST) {

                        tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - second.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                                monomino.getCoordinates().getCoordinateY() - second.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                        if (!checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
                    //        System.out.println(monomino + " yhtyjgukh");
                            outcastsFromSecond.add(monomino);
                        }
                    //    System.out.println(tmp +" TMP " + monomino.getCoordinates() + " MONO COORD"+
                     //           " vector" + mapEntry.getKey() + " "+mapEntry.getValue());
                    }
                }
            }
        }

//        System.out.println("Outcasts from first");
//        outcastsFromFirst.forEach(System.out::println);
//        System.out.println("Outcasts from second");
//        outcastsFromSecond.forEach(System.out::println);


        boolean result = true;

        if (outcastsFromFirst.size() != outcastsFromSecond.size()) {
            result = false;
        }
        else {
            for(Monomino monomino : outcastsFromFirst){
                if(!outcastsFromSecond.contains(monomino)){
                    result = false;
                    break;
                }
                for(Monomino monomino1 : outcastsFromSecond) {
                    if(monomino.equals(monomino1) && !monomino.getLabel().equals(monomino1)){
                        result = false;
                        break;
                    }
                }
            }
        }

        Set<Monomino> setForFirst = new HashSet<>();
        Set<Monomino> setForSecond = new HashSet<>();

        for (Monomino monomino : first.getMonominos()) {
            if (!outcastsFromFirst.contains(monomino)) {
                setForFirst.add(monomino.copy());
            }
        }
        for (Monomino monomino : second.getMonominos()) {
            if (!outcastsFromSecond.contains(monomino)) {
                setForSecond.add(monomino.copy());
            }
        }

//        System.out.println("Set for first");
//        setForFirst.forEach(System.out::println);
//        System.out.println("Set for second");
//        setForSecond.forEach(System.out::println);

        configuration.setFirst(new Polyomino(setForFirst));
        configuration.setSecond(new Polyomino(setForSecond));

        return result;
    }

    public static void findOutcast(Polyomino polyomino, Set<Monomino> outcasts, Map<VectorDirection, Point> vectors, Monomino monomino) {

        Monomino tmp = new Monomino(new Point(0,0), "a", false, false);

        for (Map.Entry<VectorDirection,Point> mapEntry : vectors.entrySet()) {

            if (mapEntry.getKey() != VectorDirection.EAST) {

                tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - polyomino.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                                             monomino.getCoordinates().getCoordinateY() - polyomino.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                if(checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
             //       System.out.println(monomino+ " yhtyjgukh");
                    outcasts.add(monomino);
                }
            }
        }
    }

//    public static Map<VectorDirection, Point> findAllVectorsInPolyomino(Polyomino polyomino){
//
//        Map<VectorDirection, Point> vectors = new HashMap<>();
//
//        Point east  = new Point(polyomino.getEndOfPolyomino().getCoordinates().getCoordinateX(), polyomino.getEndOfPolyomino().getCoordinates().getCoordinateY());
//        Point west  = new Point(-east.getCoordinateX(), -east.getCoordinateY());
//        Point north = new Point(-east.getCoordinateY(),  east.getCoordinateX());
//        Point south = new Point( east.getCoordinateY(), -east.getCoordinateX());
//
//        List<Polyomino> singlePolyominoList = Collections.singletonList(polyomino);
//
//        east    = rescaleVector(east,  singlePolyominoList, VectorDirection.EAST);
//        west    = rescaleVector(west,  singlePolyominoList, VectorDirection.WEST);
//        north   = rescaleVector(north, singlePolyominoList, VectorDirection.NORTH);
//        south   = rescaleVector(south, singlePolyominoList, VectorDirection.SOUTH);
//
//        vectors.put(VectorDirection.EAST,  east);
//        vectors.put(VectorDirection.WEST,  west);
//        vectors.put(VectorDirection.NORTH, north);
//        vectors.put(VectorDirection.SOUTH, south);
//
//        return vectors;
//    }

    public static boolean checkIfHalfPlaneContainsAllMonominos(Point vector, List<Polyomino> polyominos, VectorDirection vd) {

        for(Polyomino polyomino : polyominos){
            for(Monomino monomino : polyomino.getMonominos()){
                if (!(checkIfPlaneContainsMonomino(vd, vector, monomino))) return false;
            }
        }

        return true;
    }

    public static boolean checkIfPlaneContainsMonomino(VectorDirection vd, Point vector, Monomino monomino) {

        int C = (-1) * (vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());

        if (vector.getCoordinateX() == 0) {

            double y1 = ((-1) * vector.getCoordinateX() * monomino.getCoordinates().getCoordinateX() - C) / (double) vector.getCoordinateY();

            if (vector.getCoordinateY() > 0) {
                if ((monomino.getCoordinates().getCoordinateY() <= y1))
                    return true;
            } else {
                if((monomino.getCoordinates().getCoordinateY() >= y1))
                    return true;
            }

        } else if (vector.getCoordinateY() == 0) {

            double x1 = ((-1) * vector.getCoordinateY() * monomino.getCoordinates().getCoordinateY() - C) / (double) vector.getCoordinateX();

            if (vector.getCoordinateX() > 0) {
                if ((monomino.getCoordinates().getCoordinateX() <= x1))
                    return true;
            } else {
                if((monomino.getCoordinates().getCoordinateX() >= x1))
                    return true;
            }

        } else {

            double x1 = ((-1) * vector.getCoordinateY() * monomino.getCoordinates().getCoordinateY() - C) / (double) vector.getCoordinateX();
            double y1 = ((-1) * vector.getCoordinateX() * monomino.getCoordinates().getCoordinateX() - C) / (double) vector.getCoordinateY();

            if (vd == VectorDirection.EAST) {
                if ((monomino.getCoordinates().getCoordinateX() <= x1))
                    return true;

            } else if (vd == VectorDirection.WEST) {
                if ((monomino.getCoordinates().getCoordinateX() >= x1))
                    return true;

            } else if (vd == VectorDirection.NORTH) {
                if((monomino.getCoordinates().getCoordinateY() <= y1))
                    return true;

            } else if (vd == VectorDirection.SOUTH) {
                if((monomino.getCoordinates().getCoordinateY() >= y1))
                    return true;
            }
        }

        return false;
    }

    public static double calculateAngleBetweenVectors(Point vector, Point endOfPolyomino){

        double x0 = (endOfPolyomino.getCoordinateX());
        double y0 = (endOfPolyomino.getCoordinateY());
        double x1 = (vector.getCoordinateX());
        double y1 = (vector.getCoordinateY());

        double cosA = (x0 * x1 + y0 * y1) / (Math.sqrt(x0 * x0 + y0 * y0) * Math.sqrt(x1 * x1 + y1 * y1));

        return Math.toDegrees(Math.acos(cosA));
    }

    // TODO nie wyznacza maxX, niech szuka az znajdzie
    public static Point findEastVector(List<Polyomino> polyominos){

        int maxX=0, maxY=0, minY=0;

        for(Polyomino poly : polyominos){
            for(Monomino mono : poly.getMonominos()) {

                if (mono.getCoordinates().getCoordinateX() > maxX)
                    maxX = mono.getCoordinates().getCoordinateX();

                if (mono.getCoordinates().getCoordinateY() > maxY)
                    maxY = mono.getCoordinates().getCoordinateY();

                if (mono.getCoordinates().getCoordinateY() < minY)
                    minY = mono.getCoordinates().getCoordinateY();
            }
        }

        maxX *= 2;

        Point eastVector = new Point(0, 0);

        outer:
        for(int i = 0; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++){

                eastVector.setCoordinateX(i);
                eastVector.setCoordinateY(j);

                for (int k = 0; k < polyominos.size(); k++) {
                    if (((eastVector.getCoordinateX() * polyominos.get(k).getEndOfPolyomino().getCoordinates().getCoordinateX()
                            + eastVector.getCoordinateY() * polyominos.get(k).getEndOfPolyomino().getCoordinates().getCoordinateY()) <= 0))
                        break;

                    if (k == (polyominos.size() - 1))
                        break outer;

                }
            }
        }

        return eastVector;
    }

    public static List<Polyomino> findMinMaxAngleBetweenPolyominos(List<Polyomino> polyominos, Point eastVector){

        Polyomino northVector = null;
        Polyomino southVector = null;

        Point rotatedEastVector = new Point(eastVector.getCoordinateY(), (-1) * eastVector.getCoordinateX());
        double tmp, minAngle = 360, maxAngle = 0;

        for (Polyomino poly : polyominos) {

            tmp = calculateAngleBetweenVectors(rotatedEastVector, poly.getEndOfPolyomino().getCoordinates());

            if (tmp > maxAngle) {
                maxAngle = tmp;
                northVector = poly;
            }
            if (tmp < minAngle) {
                minAngle = tmp;
                southVector = poly;
            }
        }

        List<Polyomino> northSouth = new LinkedList<>();
        northSouth.add(northVector);
        northSouth.add(southVector);

        return northSouth;
    }

    public static Map<VectorDirection, Point> calculateVectors(List<Polyomino> polyominos) {

        Map<VectorDirection, Point> vectors = new HashMap<>();

        Point east = PolyominoCode.findEastVector(polyominos);
        vectors.put(VectorDirection.EAST, east);

//        System.out.println(east+"kurwa");

        List<Polyomino> tmp = PolyominoCode.findMinMaxAngleBetweenPolyominos(polyominos, east);

        vectors.put(VectorDirection.NORTH, new Point((-1) * tmp.get(0).getEndOfPolyomino().getCoordinates().getCoordinateY(),
                tmp.get(0).getEndOfPolyomino().getCoordinates().getCoordinateX()));

//        System.out.println(tmp.get(1).getEndOfPolyomino() + " SOUTH");
//        System.out.println(tmp.get(0).getEndOfPolyomino() + " NORTH");

        vectors.put(VectorDirection.SOUTH, new Point(tmp.get(1).getEndOfPolyomino().getCoordinates().getCoordinateY(),
                (-1) * tmp.get(1).getEndOfPolyomino().getCoordinates().getCoordinateX()));

        vectors.put(VectorDirection.WEST, new Point((-1) * east.getCoordinateX(), (-1) * east.getCoordinateY()));

        return vectors;
    }

    public static Point rescaleVector(Point vector, List<Polyomino> polyominos, VectorDirection vd) {

        int scale = 1;
        Point vectorToReturn = new Point(0, 0);

        do {
            vectorToReturn.setCoordinateX(vector.getCoordinateX());
            vectorToReturn.setCoordinateY(vector.getCoordinateY());
            vectorToReturn.translate(scale);
            scale++;
        } while(!(checkIfHalfPlaneContainsAllMonominos(vectorToReturn, polyominos, vd)));

        return vectorToReturn;
    }

    public static boolean checkIfNazwaDoWymyslenia(Polyomino first, Polyomino second){
        Map<Point, String> firstMap = new HashMap<>();
        Map<Point, String> secondMap = new HashMap<>();

        for (Monomino m : first.getMonominos()) {
            firstMap.put(m.getCoordinates(), m.getLabel());
        }
        for (Monomino m : second.getMonominos()) {
            secondMap.put(m.getCoordinates(), m.getLabel());
        }

        return firstMap.equals(secondMap);
    }

    public static boolean checkIfTwoPairsEqual(Pair first, Pair second){
        return ((checkIfNazwaDoWymyslenia(first.getFirst(), second.getFirst()) && checkIfNazwaDoWymyslenia(first.getSecond(), second.getSecond()))
         || (checkIfNazwaDoWymyslenia(first.getFirst(), second.getSecond()) && checkIfNazwaDoWymyslenia(first.getSecond(), second.getFirst())));
//        for(Monomino monomino : first.getFirst().getMonominos()){
//                for (Monomino monomino1 : second.getFirst().getMonominos()) {
//                    if (monomino.equals(monomino1)) {
//                        if (monomino.getLabel().equals(monomino1.getLabel())) {
//                            return false;
//                        }
//                    }
//            }
//        }
//        for(Monomino monomino : first.getSecond().getMonominos()){
//                for (Monomino monomino1 : second.getSecond().getMonominos()) {
//                    if (monomino.equals(monomino1)) {
//                        if (monomino.getLabel().equals(monomino1.getLabel())) {
//                            return false;
//                        }
//                    }
//                }
//
//        }
//        for(Monomino monomino : first.getFirst().getMonominos()){
//                for (Monomino monomino1 : second.getSecond().getMonominos()) {
//                    if (monomino.equals(monomino1)) {
//                        if (monomino.getLabel().equals(monomino1.getLabel())) {
//                            return false;
//                        }
//                    }
//                }
//
//        }
//        for(Monomino monomino : first.getSecond().getMonominos()){
//                for (Monomino monomino1 : second.getFirst().getMonominos()) {
//                    if (monomino.equals(monomino1)) {
//                        if (monomino.getLabel().equals(monomino1.getLabel())) {
//                            return false;
//                        }
//                    }
//            }
//
//        }

//        return true;
    }


    public static boolean checkIfSetIsCode(List<Polyomino> polyominos){

        // pkt 3
        Map<VectorDirection, Point> vectors = calculateVectors(polyominos);


        //skalowanie wektorów
        for (Map.Entry<VectorDirection, Point> e : vectors.entrySet()) {
            vectors.put(e.getKey(),rescaleVector(e.getValue(), polyominos, e.getKey()));
        }

//        vectors.put(VectorDirection.EAST, new Point(2,0));
//        vectors.put(VectorDirection.NORTH, new Point(0,1));
//        vectors.put(VectorDirection.SOUTH, new Point(0,-1));
//        vectors.put(VectorDirection.WEST, new Point(-1,0));

        vectors.entrySet().stream().forEach((e)->System.out.println(e.getKey()+" "+e.getValue()));


        // pkt 4
        Set<Pair> checkedConfigurations = new HashSet<>();
        Set<Pair> reducedConfigurations = new HashSet<>();

        // pkt 5

        Pair configuration;


        for (int i = 0; i < polyominos.size(); i++) {
            for (int j = i+1; j < polyominos.size(); j++) {
                configuration = new Pair();
                boolean flaga = false;

                if (checkIfLabelEqualInCEMinus(polyominos.get(i), polyominos.get(j), configuration,vectors)
                        && checkIfEndsOutsideCWEPlus(polyominos.get(i), polyominos.get(j),vectors))
                {
                    for (Pair pair : reducedConfigurations) {
                        if (checkIfTwoPairsEqual(pair, configuration))
                            flaga = true;
                    }
                    if(!flaga){
                        checkedConfigurations.add(new Pair(polyominos.get(i), polyominos.get(j)));
                        reducedConfigurations.add(configuration);
                        System.out.println(configuration.getFirst() +" "+configuration.getSecond());

                    }
                }
            }
        }
        System.out.println("==================+FIRST+===================");
        checkedConfigurations.stream().forEach(e -> System.out.println(e.getFirst() + " PAIRS " + e.getSecond()));

        // pkt 6

        Set<Pair> checkedConfigurationsTMP = new HashSet<>();
        Set<Pair> reducedConfigurationsTMP = new HashSet<>();

        // pkt 7
        while (!(reducedConfigurations.size() == reducedConfigurationsTMP.size()
                && reducedConfigurations.containsAll(reducedConfigurationsTMP)) ) {
            // a)

            checkedConfigurationsTMP = new HashSet<>(checkedConfigurations);
            reducedConfigurationsTMP = new HashSet<>(reducedConfigurations);
            checkedConfigurations = new HashSet<>();

            // b)


            Polyomino catenationXZ, catenationYZ;

            for (Pair pair : checkedConfigurationsTMP) {

                for (Polyomino polyomino : polyominos) {

                    catenationXZ = concatenatePolyominos(pair.getFirst(), polyomino);
                    catenationYZ = concatenatePolyominos(pair.getSecond(), polyomino);

                    Map<Point, String> map1 = new HashMap<>();
                    Map<Point, String> map2 = new HashMap<>();
                    Map<Point, String> map3 = new HashMap<>();
                    Map<Point, String> map4 = new HashMap<>();



                    for(Monomino monomino : catenationXZ.getMonominos()){
                        map1.put(monomino.getCoordinates(), monomino.getLabel());
                    }

                    for(Monomino monomino : catenationYZ.getMonominos()){
                        map2.put(monomino.getCoordinates(), monomino.getLabel());
                    }

                    for(Monomino monomino : pair.getFirst().getMonominos()){
                        map3.put(monomino.getCoordinates(), monomino.getLabel());
                    }

                    for(Monomino monomino : pair.getSecond().getMonominos()){
                        map4.put(monomino.getCoordinates(), monomino.getLabel());
                    }

                    if (map1.equals(map4)) {
                        map1.entrySet().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
                        map4.entrySet().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
                        System.out.println("Katenacja1");
                        return false;

                    }
                    if(map2.equals(map3)) {
                        map2.entrySet().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
                        map3.entrySet().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

                        System.out.println("Katenacja2");
                        return false;
                    }

                    // II
                    //C copy
                    Point eastVector = vectors.get(VectorDirection.EAST);

                    double eastLenght = Math.pow(eastVector.getCoordinateY(), 2)
                            + Math.pow(eastVector.getCoordinateX(), 2);
                    Point endXZ = new Point(catenationXZ.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            catenationXZ.getEndOfPolyomino().getCoordinates().getCoordinateY());
                    Point endY = new Point(pair.getSecond().getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            pair.getSecond().getEndOfPolyomino().getCoordinates().getCoordinateY());
                    Point endDifference = new Point(endXZ.getCoordinateX() - endY.getCoordinateX(),
                            endXZ.getCoordinateY() - endY.getCoordinateY());

                    double scalar = (eastVector.getCoordinateX() * endDifference.getCoordinateX()) +
                            (eastVector.getCoordinateY() * endDifference.getCoordinateY());
                    configuration = new Pair();
                    boolean flaga = false;
                    if (checkIfLabelEqualInCEMinus(catenationXZ, pair.getSecond(), configuration,vectors)
                            && checkIfEndsOutsideCWEPlus(catenationXZ, pair.getSecond(),vectors)
                            && (Math.abs(scalar) <= eastLenght)) {
                        for (Pair pair2 : reducedConfigurations) {
                            if (checkIfTwoPairsEqual(pair2, configuration))
                                flaga = true;
                        }

                        if(!flaga) {
                            checkedConfigurations.add(new Pair(catenationXZ, pair.getSecond()));
                            reducedConfigurations.add(configuration);
                        }
                    }
                    // III
                    Point endYZ = new Point(catenationYZ.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            catenationYZ.getEndOfPolyomino().getCoordinates().getCoordinateY());
                    Point endX = new Point(pair.getFirst().getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            pair.getFirst().getEndOfPolyomino().getCoordinates().getCoordinateY());
                    Point endDifference2 = new Point(endX.getCoordinateX() - endYZ.getCoordinateX(),
                            endX.getCoordinateY() - endYZ.getCoordinateY());
                    double scalar2 = (eastVector.getCoordinateX() * endDifference2.getCoordinateX()) +
                            (eastVector.getCoordinateY() * endDifference2.getCoordinateY());
                    configuration = new Pair();
                    flaga = false;
                    if (checkIfLabelEqualInCEMinus(catenationYZ, pair.getFirst(), configuration, vectors)
                            && checkIfEndsOutsideCWEPlus(catenationYZ, pair.getFirst(), vectors)
                            && (Math.abs(scalar2) <= eastLenght)) {
                        for (Pair pair2 : reducedConfigurations) {
                            if (checkIfTwoPairsEqual(pair2, configuration))
                                flaga = true;
                        }
                        if(!flaga) {
                            checkedConfigurations.add(new Pair(catenationYZ, pair.getFirst()));
                            reducedConfigurations.add(configuration);
                        }
                    }
                }
            }

        }
        // pkt 8

        return true;
    }
}


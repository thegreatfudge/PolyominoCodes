import java.util.*;
import java.lang.Math;

public class PolyominoCode {

    public static boolean checkIfEndsOutsideCWEPlus (Polyomino first, Polyomino second){
        //TODO ASK IF DRAWING IS WRONG

        Map<VectorDirection, Point> vectorsOfFirstForCE = findAllVectorsInPolyomino(first);
        Map<VectorDirection, Point> vectorsOfSecondForCE = findAllVectorsInPolyomino(second);

        Map<VectorDirection, Point> vectorsOfFirstForCW = findAllVectorsInPolyomino(first);
        Map<VectorDirection, Point> vectorsOfSecondForCW = findAllVectorsInPolyomino(second);

        vectorsOfFirstForCW.get(VectorDirection.NORTH).translate(-1);
        vectorsOfFirstForCW.get(VectorDirection.SOUTH).translate(-1);
        vectorsOfSecondForCW.get(VectorDirection.NORTH).translate(-1);
        vectorsOfSecondForCW.get(VectorDirection.SOUTH).translate(-1);

        Point tmp = vectorsOfFirstForCW.get(VectorDirection.NORTH);
        vectorsOfFirstForCW.put(VectorDirection.NORTH,vectorsOfFirstForCW.get(VectorDirection.SOUTH));
        vectorsOfFirstForCW.put(VectorDirection.SOUTH, tmp);

        Point tmp2 = vectorsOfSecondForCW.get(VectorDirection.NORTH);
        vectorsOfSecondForCW.put(VectorDirection.NORTH,vectorsOfSecondForCW.get(VectorDirection.SOUTH));
        vectorsOfSecondForCW.put(VectorDirection.SOUTH, tmp2);


        vectorsOfFirstForCE.remove(VectorDirection.EAST);
        vectorsOfSecondForCE.remove(VectorDirection.EAST);
        vectorsOfFirstForCW.remove(VectorDirection.WEST);
        vectorsOfSecondForCW.remove(VectorDirection.WEST);

        if ((checkIfMonominoIsWithinVector(second.getEndOfPolyomino(), first.getEndOfPolyomino(), vectorsOfFirstForCW)
                || checkIfMonominoIsWithinVector(second.getEndOfPolyomino(), first.getEndOfPolyomino(),vectorsOfFirstForCE))
                && (checkIfMonominoIsWithinVector(first.getEndOfPolyomino(), second.getEndOfPolyomino(),vectorsOfSecondForCW)
                || checkIfMonominoIsWithinVector(first.getEndOfPolyomino(), second.getEndOfPolyomino(),vectorsOfSecondForCE))) return true;

        return false;
    }

    public static boolean checkIfMonominoIsWithinVector(Monomino monomino, Monomino end, Map<VectorDirection, Point> vectors) {
        Monomino tmp = new Monomino(new Point(0,0), "a", false, false);
        for(Map.Entry<VectorDirection, Point> mapEntry : vectors.entrySet() ){
            System.out.println(mapEntry.getKey() + " " + mapEntry.getValue());
            tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - end.getCoordinates().getCoordinateX(),
                    monomino.getCoordinates().getCoordinateY() - end.getCoordinates().getCoordinateY()));
            System.out.println(monomino + "  tmp: " +tmp);

            if(!checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
                System.out.println("RETURNING FALSE");
                return false;
            }
        }
        System.out.println("RETURNING TRUE");
        return true;
    }

    public static boolean checkIfLabelEqualInCEMinus (Polyomino first, Polyomino second){
        Set<Monomino> outcastsFromFirst = new HashSet<>();
        Set<Monomino> outcastsFromSecond = new HashSet<>();

        Map<VectorDirection, Point> vectorOfFirst = findAllVectorsInPolyomino(first);
        Map<VectorDirection, Point> vectorOfSecond = findAllVectorsInPolyomino(second);

        vectorOfFirst.entrySet().stream().forEach(System.out::println);
        vectorOfSecond.entrySet().stream().forEach(System.out::println);


        for(Monomino monomino : first.getMonominos()){
            findOutcast(first, outcastsFromFirst, vectorOfFirst, monomino);
            findOutcast(second, outcastsFromFirst, vectorOfSecond, monomino);
        }


        for(Monomino monomino : second.getMonominos()){
            findOutcast(first, outcastsFromSecond,  vectorOfFirst, monomino);
            findOutcast(second, outcastsFromSecond, vectorOfSecond, monomino);
        }

        System.out.println("Outcasts from first");
        outcastsFromFirst.forEach(System.out::println);
        System.out.println("Outcasts from second");
        outcastsFromSecond.forEach(System.out::println);

        if(outcastsFromFirst.size() != outcastsFromSecond.size())
            return false;
        return outcastsFromFirst.containsAll(outcastsFromSecond);
    }

    public static void findOutcast(Polyomino polyomino, Set<Monomino> outcasts, Map<VectorDirection, Point> vectors, Monomino monomino) {

        Monomino tmp = new Monomino(new Point(0,0), "a", false, false);

        for (Map.Entry<VectorDirection,Point> mapEntry : vectors.entrySet()) {

            if (mapEntry.getKey() != VectorDirection.EAST) {

                Point vector = mapEntry.getValue();

                int C = (-1) * (vector.getCoordinateX() * vector.getCoordinateX() +
                                vector.getCoordinateY() * vector.getCoordinateY());

                tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - polyomino.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                                             monomino.getCoordinates().getCoordinateY() - polyomino.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                if(!checkIfPlaneContainsMonomino(mapEntry.getKey(), mapEntry.getValue(), tmp)) {
                    outcasts.add(monomino);
                }
            }
        }
    }

    public static Map<VectorDirection, Point> findAllVectorsInPolyomino(Polyomino polyomino){

        Map<VectorDirection, Point> vectors = new HashMap<>();

        Point east  = new Point(polyomino.getEndOfPolyomino().getCoordinates().getCoordinateX(), polyomino.getEndOfPolyomino().getCoordinates().getCoordinateY());
        Point west  = new Point(-east.getCoordinateX(), -east.getCoordinateY());
        Point north = new Point(-east.getCoordinateY(),  east.getCoordinateX());
        Point south = new Point( east.getCoordinateY(), -east.getCoordinateX());

        List<Polyomino> singlePolyominoList = Collections.singletonList(polyomino);

        east    = rescaleVector(east,  singlePolyominoList, VectorDirection.EAST);
        west    = rescaleVector(west,  singlePolyominoList, VectorDirection.WEST);
        north   = rescaleVector(north, singlePolyominoList, VectorDirection.NORTH);
        south   = rescaleVector(south, singlePolyominoList, VectorDirection.SOUTH);

        vectors.put(VectorDirection.EAST,  east);
        vectors.put(VectorDirection.WEST,  west);
        vectors.put(VectorDirection.NORTH, north);
        vectors.put(VectorDirection.SOUTH, south);

        return vectors;
    }

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
            } else if (tmp < minAngle) {
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

        List<Polyomino> tmp = PolyominoCode.findMinMaxAngleBetweenPolyominos(polyominos, east);

        vectors.put(VectorDirection.NORTH, new Point((-1) * tmp.get(0).getEndOfPolyomino().getCoordinates().getCoordinateY(),
                tmp.get(0).getEndOfPolyomino().getCoordinates().getCoordinateX()));

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

    public static boolean checkIfSetIsCode(List<Polyomino> polyominos){

        Map<VectorDirection, Point> vectors = calculateVectors(polyominos);

        for (Map.Entry<VectorDirection, Point> e : vectors.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        return false;
    }
}


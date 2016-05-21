import java.util.*;
import java.lang.Math;

public class PolyominoCode {

    public static boolean checkIfLabelEqualInCEMinus (Polyomino first, Polyomino second){
        Set<Monomino> outcastsFromFirst = new HashSet<>();
        Set<Monomino> outcastsFromSecond = new HashSet<>();
        Monomino tmp = new Monomino(new Point(0,0), "a", false, false);

        Map<VectorDirection, Point> vectorOfFirst = findAllVectorsInPolyomino(first);
        Map<VectorDirection, Point> vectorOfSecond = findAllVectorsInPolyomino(second);

        vectorOfFirst.entrySet().stream().forEach(System.out::println);
        vectorOfSecond.entrySet().stream().forEach(System.out::println);

//        for (Map.Entry<VectorDirection, Point> vector : vectorOfFirst.entrySet()) {
//            vector.getValue().setCoordinateX(vector.getValue().getCoordinateX() +
//                                             first.getEndOfPolyomino().getCoordinates().getCoordinateX());
//            vector.getValue().setCoordinateY(vector.getValue().getCoordinateY() +
//                                             first.getEndOfPolyomino().getCoordinates().getCoordinateY());
//        }
//
//        for (Map.Entry<VectorDirection, Point> vector : vectorOfSecond.entrySet()) {
//            vector.getValue().setCoordinateX(vector.getValue().getCoordinateX() +
//                                             second.getEndOfPolyomino().getCoordinates().getCoordinateX());
//            vector.getValue().setCoordinateY(vector.getValue().getCoordinateY() +
//                                             second.getEndOfPolyomino().getCoordinates().getCoordinateY());
//        }


        for(Monomino monomino : first.getMonominos()){
            for(Map.Entry<VectorDirection,Point> mapEntry : vectorOfFirst.entrySet()){
                if(mapEntry.getKey() != VectorDirection.EAST){
                    Point vector = mapEntry.getValue();
                    int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
                    tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - first.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                                                 monomino.getCoordinates().getCoordinateY() - first.getEndOfPolyomino().getCoordinates().getCoordinateY()));
                    if(!checkIfPlaneIncludes(mapEntry.getKey(), mapEntry.getValue(), C, tmp)){
                        outcastsFromFirst.add(monomino);
                    }
                }
            }
            for(Map.Entry<VectorDirection,Point> mapEntry : vectorOfSecond.entrySet()){
                if(mapEntry.getKey() != VectorDirection.EAST){
                    Point vector = mapEntry.getValue();
                    int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
                    tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - second.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            monomino.getCoordinates().getCoordinateY() - second.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                    if(!checkIfPlaneIncludes(mapEntry.getKey(), mapEntry.getValue(), C, tmp)){
                        outcastsFromFirst.add(monomino);
                    }
                }
            }
        }


        for(Monomino monomino : second.getMonominos()){
            for(Map.Entry<VectorDirection,Point> mapEntry : vectorOfFirst.entrySet()){
                if(mapEntry.getKey() != VectorDirection.EAST){
                    Point vector = mapEntry.getValue();
                    int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
                    tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - first.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            monomino.getCoordinates().getCoordinateY() - first.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                    if(!checkIfPlaneIncludes(mapEntry.getKey(), mapEntry.getValue(), C, tmp)){
                        outcastsFromSecond.add(monomino);
                    }
                }
            }
            for(Map.Entry<VectorDirection,Point> mapEntry : vectorOfSecond.entrySet()){
                if(mapEntry.getKey() != VectorDirection.EAST){
                    Point vector = mapEntry.getValue();
                    int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
                    tmp.setCoordinates(new Point(monomino.getCoordinates().getCoordinateX() - second.getEndOfPolyomino().getCoordinates().getCoordinateX(),
                            monomino.getCoordinates().getCoordinateY() - second.getEndOfPolyomino().getCoordinates().getCoordinateY()));

                    if(!checkIfPlaneIncludes(mapEntry.getKey(), mapEntry.getValue(), C, tmp)){
                        outcastsFromSecond.add(monomino);
                    }
                }
            }
        }

        System.out.println("Outcasts from first");
        outcastsFromFirst.forEach(System.out::println);
        System.out.println("Outcasts from second");
        outcastsFromSecond.forEach(System.out::println);

        for(Monomino monomino : outcastsFromFirst){
            if(outcastsFromSecond.contains(monomino)){
                return true;
            }
        }
        return false;
    }

    public static Map<VectorDirection, Point> findAllVectorsInPolyomino(Polyomino polyomino){
        Map<VectorDirection, Point> vectors = new HashMap<>();
        Point east = new Point(polyomino.getEndOfPolyomino().getCoordinates().getCoordinateX(), polyomino.getEndOfPolyomino().getCoordinates().getCoordinateY());
        Point west = new Point(-east.getCoordinateX(),-east.getCoordinateY());
        Point north = new Point(-east.getCoordinateY(), east.getCoordinateX());
        Point south = new Point(east.getCoordinateY(), -east.getCoordinateX());
        System.out.println(east);
        System.out.println(west);
        System.out.println(north);
        System.out.println(south);
        east = rescaleVector(east,Arrays.asList(polyomino),VectorDirection.EAST);
        west = rescaleVector(west,Arrays.asList(polyomino),VectorDirection.WEST);
        north = rescaleVector(north,Arrays.asList(polyomino),VectorDirection.NORTH);
        south = rescaleVector(south,Arrays.asList(polyomino),VectorDirection.SOUTH);

        vectors.put(VectorDirection.EAST, east);
        vectors.put(VectorDirection.WEST, west);
        vectors.put(VectorDirection.NORTH, north);
        vectors.put(VectorDirection.SOUTH, south);
        return vectors;
    }

//    public static boolean checkIfHalfPlaneContainsMonomino(Point vector, Monomino mono, VectorDirection vd){
//        int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
//        //EAST
//        if(vd == VectorDirection.EAST || vd == VectorDirection.WEST || vector.getCoordinateY() == 0) {
//            int x0 = mono.getCoordinates().getCoordinateX();
//            double x1 = ((-1) * vector.getCoordinateY() * mono.getCoordinates().getCoordinateY() - C) / (double) vector.getCoordinateX();
//            if (x0 <= x1 && (vd == VectorDirection.EAST || vector.getCoordinateX() > 0) ) {
////                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
//                return true;
//            } else if (x0 >= x1 && (vd == VectorDirection.WEST ||  vector.getCoordinateX() < 0)) {
//                return true;
//            }
//
//
//        }
//        else {
//            //North&South
//
//            int y0 = mono.getCoordinates().getCoordinateY();
//            double y1 = ((-1) * vector.getCoordinateX() * mono.getCoordinates().getCoordinateX() - C) / (double) vector.getCoordinateY();
//            if (y0 <= y1 && vd == VectorDirection.NORTH) {
//                return true;
////LYL                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
//            } else if (y0 >= y1 && vd == VectorDirection.SOUTH) {
//                return true;
//            }
//
//
//        }
//        return false;
//    }


//    public static boolean checkIfHalfPlaneContainsAllMonominos(Point vector, List<Polyomino> polyominos, VectorDirection vd){
//        int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
//        //EAST/
//        if(vector.getCoordinateY() == 0) {
//            for (Polyomino poly : polyominos) {
//                for (Monomino mono : poly.getMonominos()) {
//                    int x0 = mono.getCoordinates().getCoordinateX();
//                    double x1 = ((-1) * vector.getCoordinateY() * mono.getCoordinates().getCoordinateY() - C) / (double) vector.getCoordinateX();
//                    if (x0 <= x1 && vector.getCoordinateX() > 0) {
////                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
//
//                        continue;
//                    } else if (x0 >= x1 && vector.getCoordinateX() < 0) {
//                        continue;
//                    } else {
//                        //System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
//                        return false;
//                    }
//                }
//            }
//        }
//        else {
//            //North&South
//            for (Polyomino poly : polyominos) {
//                for (Monomino mono : poly.getMonominos()) {
//                    int y0 = mono.getCoordinates().getCoordinateY();
//                    double y1 = ((-1) * vector.getCoordinateX() * mono.getCoordinates().getCoordinateX() - C) / (double) vector.getCoordinateY();
//                    if (y0 <= y1 && vd == VectorDirection.NORTH) {
////                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
//                        continue;
//                    } else if (y0 >= y1 && vd == VectorDirection.SOUTH) {
//                        continue;
//                    } else {
//                        //System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
    public static boolean checkIfHalfPlaneContainsAllMonominos(Point vector, List<Polyomino> polyominos, VectorDirection vd) {
        int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
        for(Polyomino polyomino : polyominos){
            for(Monomino monomino : polyomino.getMonominos()){
                if (!(checkIfPlaneIncludes(vd, vector, C, monomino))) return false;
            }
        }
        return true;
    }

    public static boolean checkIfPlaneIncludes(VectorDirection vd, Point vector, int c, Monomino monomino) {
        if(vector.getCoordinateX() == 0) {
            double y1 = ((-1) * vector.getCoordinateX() * monomino.getCoordinates().getCoordinateX() - c) / (double) vector.getCoordinateY();
            if (vector.getCoordinateY() > 0){
                if ((monomino.getCoordinates().getCoordinateY() <= y1))
                    return true;
            }
            else{
                if((monomino.getCoordinates().getCoordinateY() >= y1))
                    return true;
            }
        }
        else if(vector.getCoordinateY() == 0) {
            double x1 = ((-1) * vector.getCoordinateY() * monomino.getCoordinates().getCoordinateY() - c) / (double) vector.getCoordinateX();
            if(vector.getCoordinateX() > 0) {
                if ((monomino.getCoordinates().getCoordinateX() <= x1))
                    return true;
            }
            else{
                if((monomino.getCoordinates().getCoordinateX() >= x1))
                    return true;
            }
        }
        else{
            double x1 = ((-1) * vector.getCoordinateY() * monomino.getCoordinates().getCoordinateY() - c) / (double) vector.getCoordinateX();
            double y1 = ((-1) * vector.getCoordinateX() * monomino.getCoordinates().getCoordinateX() - c) / (double) vector.getCoordinateY();
            if(vd == VectorDirection.EAST){
                if((monomino.getCoordinates().getCoordinateX() <= x1))
                    return true;
            }
            else if(vd == VectorDirection.WEST){
                if((monomino.getCoordinates().getCoordinateX() >= x1))
                    return true;
            }
            else if(vd == VectorDirection.NORTH){
                if((monomino.getCoordinates().getCoordinateY() <= y1))
                    return true;
            }
            else if(vd == VectorDirection.SOUTH){
                if((monomino.getCoordinates().getCoordinateY() >= y1))
                    return true;
            }
        }
        return false;
    }

    public static double calculateAngleBetweenVectors(Polyomino polyomino, Point vector){
        double x0 = (polyomino.getEndOfPolyomino().getCoordinates().getCoordinateX());
        double y0 = (polyomino.getEndOfPolyomino().getCoordinates().getCoordinateY());
        double x1 = (vector.getCoordinateX());
        double y1 = (vector.getCoordinateY());
        double cosA = (x0 * x1 + y0 * y1) / (Math.sqrt(x0*x0 + y0*y0) * Math.sqrt(x1*x1 + y1*y1));
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
                for(int k=0; k<polyominos.size(); k++){
                    if(((eastVector.getCoordinateX() * polyominos.get(k).getEndOfPolyomino().getCoordinates().getCoordinateX()
                            + eastVector.getCoordinateY() * polyominos.get(k).getEndOfPolyomino().getCoordinates().getCoordinateY()) <= 0))
                        break;

                    if(k==(polyominos.size()-1))
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

        for(Polyomino poly : polyominos){
            tmp = calculateAngleBetweenVectors(poly, rotatedEastVector);
            if(tmp > maxAngle){
                maxAngle = tmp;
                northVector = poly;
            }
            else if(tmp < minAngle){
                minAngle = tmp;
                southVector = poly;
            }
        }
        List<Polyomino> northSouth = new LinkedList<>();
        northSouth.add(northVector);
        northSouth.add(southVector);
        return northSouth;
    }
    public static boolean checkIfSetIsCode(List<Polyomino> polyominos){
        Map<VectorDirection, Point> vectors = calculateVectors(polyominos);



        for (Map.Entry<VectorDirection, Point> e : vectors.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        return false;
    }

    public static Map<VectorDirection, Point> calculateVectors(List<Polyomino> polyominos) {
        Map<VectorDirection, Point> vectors = new HashMap<>();
        Point east = PolyominoCode.findEastVector(polyominos);
        vectors.put(VectorDirection.EAST, east);
        List<Polyomino> tmp = PolyominoCode.findMinMaxAngleBetweenPolyominos(polyominos, east);
        //TO DO obrocic wektory
        vectors.put(VectorDirection.NORTH, new Point((-1) * tmp.get(0).getEndOfPolyomino().getCoordinates().getCoordinateY(),
                tmp.get(0).getEndOfPolyomino().getCoordinates().getCoordinateX()));
        vectors.put(VectorDirection.SOUTH, new Point(tmp.get(1).getEndOfPolyomino().getCoordinates().getCoordinateY(),
                (-1) * tmp.get(1).getEndOfPolyomino().getCoordinates().getCoordinateX()));
        vectors.put(VectorDirection.WEST, new Point((-1) * east.getCoordinateX(), (-1) * east.getCoordinateY()));
        return vectors;
    }

    public static Point rescaleVector(Point vector, List<Polyomino> polyominos, VectorDirection vd) {
        int scale = 1;
        Point vectorToReturn = new Point(vector.getCoordinateX(), vector.getCoordinateY());
        do {
            vectorToReturn.setCoordinateX(vector.getCoordinateX() * scale);
            vectorToReturn.setCoordinateY(vector.getCoordinateY() * scale);
            scale++;
        } while(!(checkIfHalfPlaneContainsAllMonominos(vectorToReturn, polyominos, vd)));
        return vectorToReturn;
    }

    public static void main(String[] args) {
    }
}


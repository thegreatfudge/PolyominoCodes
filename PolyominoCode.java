import java.util.*;
import java.lang.Math;

public class PolyominoCode {

    public static boolean checkIfLabelEqualInCEMinus (Polyomino first, Polyomino second){
        List<Monomino> outcastsFromFirst;
        List<Monomino> outcastsFromSecond;

        Map<VectorDirection, Point> vectorOfFirst = calculateVectors(Arrays.asList(first));
        Map<VectorDirection, Point> vectorOfSecond = calculateVectors(Arrays.asList(second));

        for (Map.Entry<VectorDirection, Point> vector : vectorOfFirst.entrySet()) {
            vector.getValue().setCoordinateX(vector.getValue().getCoordinateX() +
                                             first.getEndOfPolyomino().getCoordinates().getCoordinateX());
            vector.getValue().setCoordinateY(vector.getValue().getCoordinateY() +
                                             first.getEndOfPolyomino().getCoordinates().getCoordinateY());
        }

        for (Map.Entry<VectorDirection, Point> vector : vectorOfSecond.entrySet()) {
            vector.getValue().setCoordinateX(vector.getValue().getCoordinateX() +
                                             first.getEndOfPolyomino().getCoordinates().getCoordinateX());
            vector.getValue().setCoordinateY(vector.getValue().getCoordinateY() +
                                             first.getEndOfPolyomino().getCoordinates().getCoordinateY());
        }

        for(Monomino monomino : first.getMonominos()){

        }

        return true;
    }

    public static boolean checkIfHalfPlaneContainsMonomino(Point vector, Monomino mono, VectorDirection vd){
        int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
        //EAST
        if(vd == VectorDirection.EAST || vd == VectorDirection.WEST || vector.getCoordinateY() == 0) {
                    int x0 = mono.getCoordinates().getCoordinateX();
                    double x1 = ((-1) * vector.getCoordinateY() * mono.getCoordinates().getCoordinateY() - C) / (double) vector.getCoordinateX();
                    if (x0 <= x1 && vd == VectorDirection.EAST) {
//                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
                        return true;
                    } else if (x0 >= x1 && vd == VectorDirection.WEST || vd == VectorDirection.SOUTH || vd == VectorDirection.NORTH) {
                        return true;
                    }


        }
        else {
            //North&South

                    int y0 = mono.getCoordinates().getCoordinateY();
                    double y1 = ((-1) * vector.getCoordinateX() * mono.getCoordinates().getCoordinateX() - C) / (double) vector.getCoordinateY();
                    if (y0 <= y1 && vd == VectorDirection.NORTH) {
                        return true;
//                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
                    } else if (y0 >= y1 && vd == VectorDirection.SOUTH) {
                        return true;
                    }


        }
        return false;
    }


    public static boolean checkIfHalfPlaneContainsAllMonominos(Point vector, List<Polyomino> polyominos, VectorDirection vd){
        int C = (-1)*(vector.getCoordinateX() * vector.getCoordinateX() + vector.getCoordinateY() * vector.getCoordinateY());
        //EAST
        if(vd == VectorDirection.EAST || vd == VectorDirection.WEST || vector.getCoordinateY() == 0) {
            for (Polyomino poly : polyominos) {
                for (Monomino mono : poly.getMonominos()) {
                    int x0 = mono.getCoordinates().getCoordinateX();
                    double x1 = ((-1) * vector.getCoordinateY() * mono.getCoordinates().getCoordinateY() - C) / (double) vector.getCoordinateX();
                    if (x0 <= x1 && vd == VectorDirection.EAST) {
//                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
                        continue;
                    } else if (x0 >= x1 && vd == VectorDirection.WEST || vd == VectorDirection.SOUTH || vd == VectorDirection.NORTH) {
                        continue;
                    } else {
                        //System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
                        return false;
                    }
                }
            }
        }
        else {
            //North&South
            for (Polyomino poly : polyominos) {
                for (Monomino mono : poly.getMonominos()) {
                    int y0 = mono.getCoordinates().getCoordinateY();
                    double y1 = ((-1) * vector.getCoordinateX() * mono.getCoordinates().getCoordinateX() - C) / (double) vector.getCoordinateY();
                    if (y0 <= y1 && vd == VectorDirection.NORTH) {
//                        System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
                        continue;
                    } else if (y0 >= y1 && vd == VectorDirection.SOUTH) {
                        continue;
                    } else {
                        //System.out.println(mono.getCoordinates().getCoordinateX() + " " + mono.getCoordinates().getCoordinateY());
                        return false;
                    }
                }
            }
        }
        return true;
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
        do {
            vector.setCoordinateX(vector.getCoordinateX() * scale);
            vector.setCoordinateY(vector.getCoordinateY() * scale);
            scale++;
        } while (!(checkIfHalfPlaneContainsAllMonominos(vector, polyominos, vd)));
        return vector;
    }

    public static void main(String[] args) {
    }
}


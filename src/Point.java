public class Point {
    private int coordinateX;
    private int coordinateY;

    public Point(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Point copy() {
        return new Point(coordinateX, coordinateY);
    }

    public void translate(int scale){
        coordinateX *= scale;
        coordinateY *= scale;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        else if(obj instanceof Point){
            Point tmp = (Point) obj;
            return (coordinateX == tmp.coordinateX && coordinateY == tmp.coordinateY);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + coordinateX + "," + coordinateY + "]";
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
}

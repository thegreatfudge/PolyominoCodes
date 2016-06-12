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
    public int hashCode() {
        return ((coordinateX * 839) ^ (coordinateY * 907));
    }

    public Point movePointX(int x){
        return new Point(coordinateX + x, coordinateY);
    }
    public Point movePointY(int y){
        return new Point(coordinateX,coordinateY + y);
    }
    public Point movePointX1(int x){
        return new Point(coordinateX - x, coordinateY);
    }
    public Point movePointY1(int y){
        return new Point(coordinateX,coordinateY - y);
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

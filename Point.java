public class Point {
    private int coordinateX;
    private int coordinateY;

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

    public Point(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        else if(obj instanceof Point){
            Point tmp = (Point) obj;
            return (tmp.getCoordinateX() == this.coordinateX && tmp.getCoordinateY() == this.coordinateY);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.coordinateX + "," + this.coordinateY + "]";
    }
}
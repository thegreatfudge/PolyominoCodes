public class MonominoNoLabel {

    private Point coordinates;
    private boolean isBeginning;
    private boolean isEnd;


    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isBeginning() {
        return isBeginning;
    }

    public void setBeginning(boolean beginning) {
        isBeginning = beginning;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public MonominoNoLabel(Point coordinates, boolean isBeginning, boolean isEnd) {
        this.coordinates = coordinates;
        this.isBeginning = isBeginning;
        this.isEnd = isEnd;
    }

    public MonominoNoLabel() {
        this (new Point(0,0),false,false);
    }

    //TODO BOOLEAN SHIT TO HASHCODE FUNCTION
    @Override
    public int hashCode() {
        return (((this.coordinates.getCoordinateX() * 11) ^ (this.coordinates.getCoordinateY() * 7)));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        else if(obj instanceof Monomino){
            Monomino tmp = (Monomino) obj;
            return (this.coordinates.equals(tmp.getCoordinates()) && this.isEnd == tmp.isEnd());
        }
        return false;
    }
}

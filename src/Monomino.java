public class Monomino {

    private Point coordinates;
    private boolean isBeginning;
    private boolean isEnd;
    private String label;

    public Monomino(Point coordinates, String label, boolean isBeginning, boolean isEnd) {
        this.coordinates = coordinates;
        this.isBeginning = isBeginning;
        this.isEnd = isEnd;
        this.label = label;
    }

    //TODO BOOLEAN SHIT TO HASHCODE FUNCTION
    @Override
    public int hashCode() {
        return (((coordinates.getCoordinateX() * 11) ^ (coordinates.getCoordinateY() * 7)));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        else if(obj instanceof Monomino){
            Monomino tmp = (Monomino) obj;
            return (coordinates.equals(tmp.getCoordinates()) && isEnd == tmp.isEnd() && label.equals(tmp.label));
        }
        return false;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

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
}

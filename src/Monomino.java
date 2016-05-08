public class Monomino extends MonominoNoLabel {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Monomino(Point coordinates, String label, boolean isBeginning, boolean isEnd) {
        super(coordinates, isBeginning, isEnd);
        this.label = label;
    }

    public Monomino() {
        this (new Point(0,0),"",false,false);
    }

    //TODO BOOLEAN SHIT TO HASHCODE FUNCTION
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        else if(obj instanceof Monomino){
            Monomino tmp = (Monomino) obj;
            return (this.getCoordinates().equals(tmp.getCoordinates()) && this.label.equals(tmp.getLabel()));
        }
        return false;
    }

    @Override
    public String toString() {
        return getCoordinates() + " " +getLabel();
    }
}



public enum VectorDirection {

    EAST ("E"),
    WEST ("W"),
    NORTH ("N"),
    SOUTH ("S");

    private String name;

    VectorDirection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

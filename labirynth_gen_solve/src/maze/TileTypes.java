package maze;

/**
 * Sets integer values of tile types
 */
public enum TileTypes {
    WALL(1),
    PATH(0),
    ENTRY(2),
    EXIT(3),
    VISITED(9),
    FOUNDPATH(6);

    private final int intValue;
    TileTypes(final int i) {
        intValue = i;
    }

    /**
     * @return integer value of tile type
     */
    public int getIntValue() {
        return intValue;
    }
}

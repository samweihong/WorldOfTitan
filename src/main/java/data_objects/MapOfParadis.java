package data_objects;

import java.util.Arrays;
import java.util.List;

public class MapOfParadis {
    public static final List<List<Integer>> MAP = Arrays.asList(
        Arrays.asList(1, 5, 7),
        Arrays.asList(0, 2, 4, 6),
        Arrays.asList(1, 3, 11, 13),
        Arrays.asList(2, 10),
        Arrays.asList(1, 6, 10),
        Arrays.asList(0, 6, 7, 12),
        Arrays.asList(1, 4, 5, 8, 15),
        Arrays.asList(0, 5, 9),
        Arrays.asList(6, 10),
        Arrays.asList(7, 12, 15),
        Arrays.asList(3, 4, 8, 14),
        Arrays.asList(2, 13),
        Arrays.asList(5, 9),
        Arrays.asList(2, 11, 14),
        Arrays.asList(10, 13, 15),
        Arrays.asList(6, 9, 14)
    );

    private enum NodeType {
        NULL,
        BUILDING,
        TITAN,
        TREE
    }

    public static NodeType getNodeType(int index) {
        return switch (index) {
            case 0 -> NodeType.NULL;
            case 1, 3, 6, 8, 10, 11, 13 -> NodeType.BUILDING;
            case 2, 12, 14 -> NodeType.TITAN;
            case 4, 5, 7, 9, 15 -> NodeType.TREE;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public static int getTime(GameCharacter gameCharacter, int nodeIndex) {
        if (getNodeType(nodeIndex) == NodeType.NULL) return 0;
        
        int attribute = switch (getNodeType(nodeIndex)) {
            case NULL -> 0;
            case BUILDING -> gameCharacter.coordination();
            case TITAN -> gameCharacter.intelligence();
            case TREE -> gameCharacter.agility();
        };
        if (attribute < 5) return 3;
        if (attribute < 8) return 2;
        else return 1;
    }
}

package logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class StoreLoadSortingBinarySearch {
    @Deprecated
    public static GameCharacter getGameCharacter(String name) {
        LinkedList<GameCharacter> characterList = GameCharacterList.getGameCharacterList();
        for (GameCharacter gameCharacter : characterList)
            if (gameCharacter.name().equalsIgnoreCase(name))
                return gameCharacter;
        return null;
    }

    public static GameCharacter storeGameCharacterInformation(String name, int[] intArray) {
        LinkedList<GameCharacter> characters = GameCharacterList.getGameCharacterList();
        GameCharacter character = new GameCharacter(name, intArray[0], intArray[1], intArray[2], intArray[3], intArray[4], intArray[5], intArray[6]);
        for(int i = 0 ; i < characters.getSize(); i++) {
            if (characters.get(i).name().equalsIgnoreCase(name)) {
                characters.set(characters.indexOf(characters.get(i)), character);
                GameCharacterList.writeToFile("myjson.json");
                return character;
            }
        }
        characters.add(character);
        GameCharacterList.writeToFile("myjson.json");
        return character;
    }

    public static String sortingAttribute(String attribute) {
        LinkedList<GameCharacter> gameCharacters = GameCharacterList.getGameCharacterList();
        GameCharacterList.sort(attribute);
        StringBuilder str = new StringBuilder();
        try {
            Method attributeGetter = GameCharacter.class.getMethod(attribute.toLowerCase());
            for (GameCharacter gameCharacter : gameCharacters) {
                str.append(gameCharacter.name())
                   .append(" ")
                   .append(attributeGetter.invoke(gameCharacter)).append("\n");
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return str.toString();
    }

    public static String binarySearch(String ability, int value) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        LinkedList<GameCharacter> characters = GameCharacterList.getGameCharacterList();
        LinkedList<GameCharacter> outputList;
        String str = "";
//        characters.sortList(SortBy.valueOf(ability));
        outputList = characters.binarySearchList(ability, value);
        for (int i = 0; i < outputList.getSize(); i++) {
            str += outputList.get(i).name() + ", ";
        }
        return str.substring(0, str.length() - 2);
    }

    public static int[] readCharacteristicsInput() {
        Scanner scanner = new Scanner(System.in);
        return Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}

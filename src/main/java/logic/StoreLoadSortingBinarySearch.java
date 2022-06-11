package logic;

import collections.LinkedList;

import java.util.Arrays;
import java.util.Scanner;

public class StoreLoadSortingBinarySearch {
    public static GameCharacter storeGameCharacterInformation(String name, int[] intArray) {
        LinkedList<GameCharacter> characters = GameCharacterList.getGameCharacterList();
        GameCharacter character = new GameCharacter(name, intArray[0], intArray[1], intArray[2], intArray[3], intArray[4], intArray[5], intArray[6]);
        for(int i = 0; i < characters.getSize(); i++) {
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

    public static int[] readCharacteristicsInput() {
        Scanner scanner = new Scanner(System.in);
        return Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}

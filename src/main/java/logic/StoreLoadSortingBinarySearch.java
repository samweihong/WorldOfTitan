package logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class StoreLoadSortingBinarySearch {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        System.out.println(storeGameCharacterInformation());
//        System.out.println(loadGameCharacterInformation());
//        System.out.println(sortingAttribute());
//        System.out.println(binarySearch());
    }

    public static GameCharacter loadGameCharacterInformation(String name) {
        LinkedList<GameCharacter> characters = FileHandling.getCharacterList();
        for(int i = 0 ; i < characters.getSize(); i++) {
            if (characters.get(i).getName().equalsIgnoreCase(name)) return characters.get(i);
        }
        return null;
    }

    public static GameCharacter storeGameCharacterInformation(String name, int[] intArray) {
        LinkedList<GameCharacter> characters = FileHandling.getCharacterList();
        GameCharacter character = new GameCharacter(name, intArray[0], intArray[1], intArray[2], intArray[3], intArray[4], intArray[5], intArray[6]);
        for(int i = 0 ; i < characters.getSize(); i++) {
            if (characters.get(i).getName().equalsIgnoreCase(name)) {
                characters.set(characters.indexOf(characters.get(i)), character);
                FileHandling.writeInFile(characters, "myjson.json");
                return character;
            }
        }
        characters.add(character);
        FileHandling.writeInFile(characters, "myjson.json");
        return character;
    }

    public static String sortingAttribute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LinkedList<GameCharacter> characters = FileHandling.getCharacterList();
        System.out.print("Sorting attribute: ");
        String input = readInput();
        Method n = GameCharacter.class.getDeclaredMethod("getName");
        Method m = GameCharacter.class.getDeclaredMethod("get" + input);
        m.setAccessible(true);
        n.setAccessible(true);
        String str = "";
        System.out.println();
        characters.sortList(SortBy.valueOf(input));
        for (int i = 0; i < characters.getSize(); i++) {
            str += n.invoke(characters.get(i)) + " " + m.invoke(characters.get(i)) + "\n";
        }
        return str;
    }

    public static String binarySearch() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        LinkedList<GameCharacter> characters = FileHandling.getCharacterList();
        LinkedList<GameCharacter> outputList;
        Method n = GameCharacter.class.getDeclaredMethod("getName");
        String str = "";
        System.out.print("Finding ability : ");
        String ability = readInput();
        System.out.println();
        System.out.print("value: ");
        int value = Integer.parseInt(readInput());
        System.out.println();
        characters.sortList(SortBy.valueOf(ability));
        outputList = characters.binarySearchList(ability, value);
        for (int i = 0; i < outputList.getSize(); i++) {
            str += n.invoke(outputList.get(i)) + ", ";
        }
        return str.substring(0, str.length() - 2);
    }

    public static String readInput() {
        return scanner.nextLine();
    }

    public static int[] readCharacteristicsInput() {return Arrays.stream(readInput().split(" ")).mapToInt(Integer::parseInt).toArray();}
}

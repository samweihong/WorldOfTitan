import java.util.Scanner;
import java.util.Arrays;
import java.lang.reflect.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        System.out.println(storeGameCharacterInformation());
//        System.out.println(loadGameCharacterInformation());
//        System.out.println(sortingAttribute());
        System.out.println(binarySearch());
    }

    public static GameCharacter loadGameCharacterInformation() {
        LinkedList<GameCharacter> characters = FileHandling.getCharacterList();
        System.out.print("Enter name: " );
        String name = readInput();
        for(int i = 0 ; i < characters.getSize(); i++) {
            if (characters.get(i).getName().equalsIgnoreCase(name)) return characters.get(i);
        }
        return null;
    }

    public static GameCharacter storeGameCharacterInformation() {
        LinkedList<GameCharacter> characters = FileHandling.getCharacterList();
        System.out.print("Enter name: " );
        String name = readInput();
        System.out.print("Enter characteristics: ");
        int[] intArray = Arrays.stream(readInput().split(" ")).mapToInt(Integer::parseInt).toArray();
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

}

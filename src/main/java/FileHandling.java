import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.*;

public class FileHandling {

//    public void writeInFile(Character character,String path) {
//
//        File file = new File(path);
//
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        try ( final FileWriter fileWriter = new FileWriter(file) ) {
//
//            new Gson().toJson(character, fileWriter);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void writeInFile(Character[] character,String path) {

        File file = new File(path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try ( final FileWriter fileWriter = new FileWriter(file) ) {

            new Gson().toJson(character, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Character[] readFromFile(String path) {
        final File file = new File(path);

        try ( final FileReader fileReader = new FileReader(file) ) {
            final Character[] character = new Gson().fromJson(fileReader, Character[].class);
            return character;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

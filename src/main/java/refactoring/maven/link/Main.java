package refactoring.maven.link;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: refactoring <filename> <options>");
            System.exit(1);
        }

        String fileName = args[0];

        if (fileName.equals("--help") || fileName.equals("-h")) {
            System.out.println("Usage: refactoring <filename> <options>");
            System.exit(1);
        }

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Usage: refactoring <filename> <options>");
            System.exit(1);
        }

        StringBuilder builder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Usage: refactoring <filename> <options>");
            System.exit(1);
        }

        Gson gson = new Gson();
        Map<String, Integer[]> map = gson.fromJson(builder.toString(), new TypeToken<Map<String, Integer[]>>(){}.getType());

    }
}

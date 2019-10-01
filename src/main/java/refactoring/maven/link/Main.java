package refactoring.maven.link;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static boolean shouldReverse = false;

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

        if (args.length > 1 && fileName.equals("--reverse") || fileName.equals("-r")) {
            shouldReverse = true;
            fileName = args[1];
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
        Map<String, int[]> otherMap = new TreeMap<>();

        for (Map.Entry<String, Integer[]> e : map.entrySet()) {
            int[] ints = Arrays.stream(e.getValue()).mapToInt(i->i).toArray();
            Sorter sorter =  new Sorter(ints, shouldReverse);
            int[] newInts = sorter.sort();
            otherMap.put(e.getKey(), newInts);
        }

        System.out.println("***********CONTENTS***********");
        for(Map.Entry<String, int[]> e : otherMap.entrySet()) {
            System.out.print(e.getKey());

            StringBuilder builder1 = new StringBuilder();
            builder1.append("   ");

            for (int i : e.getValue()) {
                builder1.append(i);
                builder1.append(", ");
            }

            String str = builder1.toString();
            str = str.substring(0, str.length() - 1);
            System.out.println(str);
        }
    }
}

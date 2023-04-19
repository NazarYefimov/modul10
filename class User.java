import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Main {
    public static void main(String[] args) {
        String inputFile = "src/main/resources/file.txt";
        String outputFile = "user.json";

        List<User> userList = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;

            while ((line = br.readLine()) != null) {

                if (line.matches("\\(\\d{3}\\) \\d{3}-\\d{4}") || line.matches("\\d{3}-\\d{3}-\\d{4}")) {
                    System.out.println(line);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<String, Integer> wordFrequency = new HashMap<>();
        for (User user : userList) {
            String[] words = user.name.split(" ");
            for (String word : words) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        userList.sort((user1, user2) -> {
            int freq1 = 0;
            int freq2 = 0;
            String[] words1 = user1.name.split(" ");
            String[] words2 = user2.name.split(" ");
            for (String word : words1) {
                freq1 += wordFrequency.get(word);
            }
            for (String word : words2) {
                freq2 += wordFrequency.get(word);
            }
            return freq2 - freq1;
        });


        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fw = new FileWriter(outputFile);
            gson.toJson(userList, fw);
            fw.close();
            System.out.println("Файл user.json створено успішно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
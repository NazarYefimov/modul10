import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

class PhoneNumberValidator {

    public static void main(String[] args) {
        String fileName = "src/main/java/org/example/file.txt";
        List<String> validPhoneNumbers = readAndValidatePhoneNumbers(fileName);
        printPhoneNumbersByFrequency(validPhoneNumbers);
    }

    public static List<String> readAndValidatePhoneNumbers(String fileName) {
        List<String> validPhoneNumbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isValidPhoneNumber(line)) {
                    validPhoneNumbers.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка при читанні файлу: " + e.getMessage());
        }
        return validPhoneNumbers;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {

        Pattern pattern = Pattern.compile("^\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$");
        return pattern.matcher(phoneNumber).matches();
    }

    public static void printPhoneNumbersByFrequency(List<String> phoneNumbers) {
        Map<String, Integer> phoneNumberFrequency = new HashMap<>();
        for (String phoneNumber : phoneNumbers) {
            phoneNumberFrequency.put(phoneNumber, phoneNumberFrequency.getOrDefault(phoneNumber, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(phoneNumberFrequency.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey());
        }
    }
}
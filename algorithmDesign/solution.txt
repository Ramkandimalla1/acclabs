package algorithmDesign;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {

    // Function to generate a random string of a specified length
    private static String generateRandomString(int length) {
        Random random = new Random();

        // Generate a random string by collecting characters within the ASCII range of 'a' to 'z'
        return random.ints('a', 'z' + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // Function to load words from a file and return them as a list
    private static List<String> loadWordsFromFile(String filePath) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            // Read lines from the file, split them into words, and collect into a list
            return fileReader.lines()
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .collect(Collectors.toList());
        }
    }

    // Function to calculate the average time for generating random string pairs and calculating edit distance
    private static long calculateAverageEditDistanceTime(int numberOfPairs, int stringLength) {
        return (long) LongStream.range(0, numberOfPairs)
                .mapToObj(i -> {
                    // Generate two random strings
                    String randomString1 = generateRandomString(stringLength);
                    String randomString2 = generateRandomString(stringLength);

                    // Measure the time taken to calculate edit distance between the two strings
                    long startTime = System.nanoTime();
                    int editDistance = Sequences.editDistance(randomString1, randomString2);
                    long endTime = System.nanoTime();

                    return endTime - startTime;
                })
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }

    // Function to perform the word search and display similar words
    private static void searchAndDisplaySimilarWords(String filePath, String searchKey) {
        System.out.println("Finding similar words from the file [10]");
        System.out.println();

        try {
            // Load all words from the specified file
            List<String> allWords = loadWordsFromFile(filePath);

            // Group words by their edit distance from the search key
            TreeMap<Integer, List<String>> wordEditDistanceMap = allWords.stream()
                    .collect(Collectors.groupingBy(
                            word -> Sequences.editDistance(searchKey, word),
                            TreeMap::new,
                            Collectors.toList()
                    ));

            int displayCount = 0;

            // Display similar words for each edit distance
            for (Map.Entry<Integer, List<String>> entry : wordEditDistanceMap.entrySet()) {
                for (String similarWord : entry.getValue()) {
                    System.out.println(similarWord + " (Edit Distance: " + entry.getKey() + ")");
                    System.out.println();
                    displayCount++;

                    // Break the loop when 10 similar words have been displayed
                    if (displayCount == 10) {
                        break;
                    }
                }

                // Break the outer loop when 10 similar words have been displayed
                if (displayCount == 10) {
                    break;
                }
            }
        } catch (IOException e) {
            // Print the stack trace in case of an exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Constants for generating random string pairs
        int numberOfPairs = 1000;
        int[] stringLengths = {10, 20, 50, 100};

        // Loop over different string lengths
        for (int currentStringLength : stringLengths) {
            // Calculate and display the average time for generating random string pairs and calculating edit distance
            long averageEditDistanceTime = calculateAverageEditDistanceTime(numberOfPairs, currentStringLength);
            System.out.printf("Avg time for generating %d random pairs of strings of length %d: %d ns%n",
                    numberOfPairs, currentStringLength, averageEditDistanceTime);
        }

        // Get user input for file name and search key
        Scanner userInputScanner = new Scanner(System.in);

        System.out.println("Enter the file name: ");
        String chosenFileName = userInputScanner.nextLine();

        // Path of the chosen file
        String filePath = "C:\\acc labs\\Advanced Design and Analysis\\Advanced Design and Analysis\\Algorithm Design\\w3c web pages\\Text\\" + chosenFileName + ".txt";

        System.out.println("Enter the search key: ");
        String userSearchKey = userInputScanner.nextLine();

        // Close the scanner
        userInputScanner.close();

        // Perform word search and display similar words
        searchAndDisplaySimilarWords(filePath, userSearchKey);
    }
}

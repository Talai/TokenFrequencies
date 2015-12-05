package de.up.ling.stud.aline;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aline Castendiek on 05.12.15.
 */

// Class for counting token frequencies in a text.
public class WordCounter {

    // Reads in the tokens from a string.
    public Map<String,Integer> count(String text) {
        List<String> tokens = tokenize(text);
        // Map is only an interface and must be further specified
        Map<String,Integer> tokenToFrequency = new HashMap<>();

        // For following notation an object has to have an iterable interface
        for (String token : tokens) {
            if (tokenToFrequency.containsKey(token)) {
                // Have to read out old value first
                Integer oldCount = tokenToFrequency.get(token);
                // Use put function for maps
                tokenToFrequency.put(token,oldCount+1);
            }
            else {
                tokenToFrequency.put(token,1);
            }
        }
        return tokenToFrequency;
    }

    // Reads in the tokens from a file.
    public Map<String,Integer> count(File file) {
        Map<String,Integer> tokenToFrequency = new HashMap<>();
        // Use try/catch to handle exceptions
        try {
            // Needs two readers to read in a file for some reason
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            // Read in line after line until there is none left anymore:
            while ((line = bufferedReader.readLine()) != null) {
                Map<String,Integer> tokenFrequencyForLine = count(line);
                // forEach takes key and value at the same time.
                // Lambda expression (since Java 8): After "->" specify what to do with key-value pair
                tokenFrequencyForLine.forEach((token, frequency) -> {
                    // getOrDefault: Specify 0 as default value. If token is not in map yet, use default value.
                    // Otherwise add frequency to current value.
                    tokenToFrequency.put(token,tokenToFrequency.getOrDefault(token, 0)+frequency);
                });
            }

        } catch (FileNotFoundException e) {
            System.err.println("ERROR:" + file.getAbsolutePath() + " not found.");
        } catch (IOException e) {
            System.err.println("ERROR while reading in file.");
        }
        return tokenToFrequency;
    }

    // Tokenizes a string and returns a list of all the tokens.
    private List<String> tokenize(String text) {
        // Remember: List<String> is an abstract list (interface), therefore we cannot create a concrete object from it.
        // But we can create an ArrayList.
        // Since Java 7: Diamond-Operator for template arguments (only declare them once)
        List<String> tokens = new ArrayList<>();
        String[] words = text.split(" ");
        // Remember: length is not a function!
        for (int i = 0; i < words.length; i++) {
            // Append to list with add function:
            tokens.add(words[i]);
        }
        return tokens;
    }
}

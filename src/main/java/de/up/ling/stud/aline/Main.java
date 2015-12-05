package de.up.ling.stud.aline;

import java.io.File;

/**
 * Created by Aline Castendiek on 05.12.15.
 */
public class Main {
/*
    // This version reads in file directly
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        System.out.println(wordCounter.count("Just some random test sentence"));
        System.out.println(wordCounter.count(new File("zeit.txt")));
    }
*/

    // This version reads in file from command line
    // This function is required for every executable class:
    public static void main(String[] args) {
        if (args.length == 1) {
            WordCounter wordCounter = new WordCounter();
            System.out.println(wordCounter.count(new File(args[0])));
        } else {
            System.err.println("ERROR: Please specify exactly one file.");
        }
    }
}


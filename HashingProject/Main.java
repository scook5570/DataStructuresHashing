package HashingProject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import HashingProject.HashingAlgs.*;
public class Main {
    // load factor 0.5 = 466551/933102
    // load factor 0.7 = 466551/666502 - 666511 (prime version)

    public static void main(String args[]) throws IOException {
        SeparateChaining sepMap = new SeparateChaining(933102);
        SeparateChaining sepMapPrime = new SeparateChaining(466551);

        File words = new File("Resources/words.txt");
        File output = new File("HashingProject/Results/maps.txt");
        Scanner in = new Scanner(words);

        while (in.hasNextLine()) {
            String input = in.nextLine();
            sepMapPrime.addNode(input);
        }

        sepMapPrime.printMapToFile(output);

        in.close();
    }
}

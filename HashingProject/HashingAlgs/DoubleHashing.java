package HashingProject.HashingAlgs;

import java.io.FileWriter;
import java.io.IOException;

public class DoubleHashing {
    int size; // Size of the hash table
    int prime; // Prime number used for double hashing
    String[] map; // Hash table to store strings
    public int tracker[]; // Array to track collisions
    public int searchTracker; // Counter for search operations
    public int[] wordProbes = new int[466551]; // Array to track probes for each word
    String value; // Temporary variable for holding string values

    // Constructor
    public DoubleHashing(int size) {
        this.size = size;
        map = new String[size];
        tracker = new int[size];
        prime = size - 1;
        // Find the largest prime less than size
        while (!isPrime(prime)) {
            prime--;
        }
    }

    // Method to add a node to the hash table
    public void addNode(String value, int n) {
        int key = Math.abs(value.hashCode()) % size; // Calculate hash key
        int offset = (prime - (value.hashCode() % prime)); // Calculate offset for double hashing
        int probes = 0; // Counter for probes

        // Probe until an empty slot is found
        while (map[key] != null) {
            wordProbes[n]++; // Increment probe count for this word
            tracker[key]++; // Increment collision tracker
            probes++;
            key = (key + offset) % size; // Double hashing
        }

        tracker[key]++; // Increment collision tracker for the final position
        map[key] = value; // Insert the value into the hash table
    }

    // Method to search for strings in the hash table
    public int search(String[] values) {
        searchTracker = 0; // Reset search tracker
        for (String value : values) {
            int key = Math.abs(value.hashCode()) % size; // Calculate hash key
            int offset = (prime - (value.hashCode() % prime)); // Calculate offset for double hashing
            int probes = 0; // Counter for probes

            // Probe until the value is found
            while (!map[key].equals(value)) {
                searchTracker++; // Increment search operation count
                probes++;
                key = (key + offset) % size; // Double hashing
            }
            searchTracker++; // Increment search operation count for the final comparison
        }
        return searchTracker; // Return the total number of search operations
    }

    // Method to check if a number is prime
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Method to calculate the sum of collision tracker array
    public int getSum() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += tracker[i];
        }
        return sum;
    }

    // Method to write words vs probes to a file
    public void getHashTo() {
        try {
            FileWriter probeWriter = new FileWriter("words_vs_probes_doublehashing.txt");

            // Find the maximum number of probes
            int maxProbes = 0;
            for (int i = 0; i < wordProbes.length; i++) {
                if (wordProbes[i] > 0) {
                    maxProbes = i;
                }
            }

            // Print the distribution up to the maximum number of probes found
            for (int i = 0; i <= 2000; i++) {
                int count = 0;
                for (int j = 0; j < wordProbes.length; j++) {
                    if (wordProbes[j] == i) {
                        count++;
                    }
                }

                // Write words vs probes to probeWriter
                probeWriter.write(i + "->" + count + "\n");

                // Stop updating file if the maximum number of probes is reached
                if (i == maxProbes) {
                    break;
                }
            }

            probeWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

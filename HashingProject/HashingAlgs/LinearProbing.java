package HashingProject.HashingAlgs;

import java.io.FileWriter;
import java.io.IOException;

public class LinearProbing {
    int size; // Size of the hash table
    String[] map; // Hash table to store strings
    public int[] tracker; // Array to track collisions
    public int searchTracker; // Counter for search operations
    public int[] wordProbes = new int[466551]; // Array to track probes for each word
    String value; // Temporary variable for holding string values

    // Constructor
    public LinearProbing(int size) {
        this.size = size;
        map = new String[size];
        tracker = new int[size];
    }

    // Method to add a node to the hash table
    public void addNode(String value, int n) {
        int key = Math.abs(value.hashCode()) % size; // Calculate hash key
        int probes = 0; // Counter for probes

        // Probe until an empty slot is found
        while (map[key] != null) {
            probes++;
            tracker[key]++; // Increment collision tracker
            key = (key + 1) % size; // Linear probing
        }

        tracker[key]++; // Increment collision tracker for the final position
        map[key] = value; // Insert the value into the hash table

        // Update wordProbes array with the number of probes
        wordProbes[probes]++;
    }

    // Method to search for strings in the hash table
    public int search(String[] values) {
        searchTracker = 0; // Reset search tracker

        // Iterate over input values
        for (String value : values) {
            int key = Math.abs(value.hashCode()) % size; // Calculate hash key
            int probes = 0; // Counter for probes

            // Probe until the value is found
            while (!map[key].equals(value)) {
                searchTracker++; // Increment search operation count
                probes++;
                key = (key + 1) % size; // Linear probing
            }
            searchTracker++; // Increment search operation count for the final comparison

            // Update wordProbes array with the number of probes
            wordProbes[probes]++;
        }
        return searchTracker; // Return the total number of search operations
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
            FileWriter probeWriter = new FileWriter("words_vs_probes_linearprobing.txt");

            // Find the maximum number of probes
            int maxProbes = 0;
            for (int i = 0; i < wordProbes.length; i++) {
                if (wordProbes[i] > 0) {
                    maxProbes = i;
                }
            }

            // Print the distribution up to the maximum number of probes found
            for (int i = 0; i <= maxProbes; i++) {
                int count = 0;
                for (int j = 0; j < wordProbes.length; j++) {
                    if (wordProbes[j] == i) {
                        count++;
                    }
                }

                // Write words vs probes to probeWriter
                probeWriter.write(i + "->" + count + "\n");
            }

            probeWriter.close(); // Close the FileWriter
        } catch (IOException e) {
            e.printStackTrace(); // Print any IOExceptions that occur
        }
    }
}

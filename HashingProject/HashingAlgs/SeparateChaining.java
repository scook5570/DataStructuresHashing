package HashingProject.HashingAlgs;

import java.io.FileWriter;
import java.io.IOException;

public class SeparateChaining {
    HashNode[] map; // Array of HashNode objects to represent separate chains
    public int[] tracker; // Array to track the number of elements in each chain
    public int searchTracker; // Counter for search operations
    public int[] wordProbes = new int[466551]; // Array to track probes for each word
    int size; // Size of the hash table

    // Constructor
    public SeparateChaining(int size) {
        this.size = size;
        map = new HashNode[size];
        tracker = new int[size];
    }

    // Method to add a node to the hash table
    public void addNode(String value, int n) {
        int key = Math.abs(value.hashCode()) % size; // Calculate hash key
        HashNode node = new HashNode(value); // Create a new HashNode

        tracker[key]++; // Increment the counter for this chain

        // If the chain at this index already has elements, add the node to the end
        if (map[key] != null) {
            HashNode current = map[key];
            // Iterate to the end of the chain
            while (current.next != null) {
                wordProbes[tracker[key]]++; // Update wordProbes array with the number of probes
                tracker[key]++;
                current = current.next;
            }
            // Update wordProbes array for the last node
            wordProbes[tracker[key]]++;
            tracker[key]++;
            current.next = node; // Add the node to the end of the chain
        } else {
            map[key] = node; // If the chain is empty, add the node as the head
        }
    }

    // Method to search for strings in the hash table
    public int search(String[] values) {
        searchTracker = 0; // Reset search tracker

        // Iterate over input values
        for (String item : values) {
            int key = Math.abs(item.hashCode()) % size; // Calculate hash key
            HashNode current = map[key]; // Get the head of the chain
            // Traverse the chain until the value is found
            while (!current.value.equals(item)) {
                searchTracker++; // Increment search operation count
                current = current.next;
            }
            searchTracker++; // Increment search operation count for the final comparison
        }
        return searchTracker; // Return the total number of search operations
    }

    // Method to calculate the sum of elements in each chain
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
            FileWriter probeWriter = new FileWriter("words_vs_probes_separatechaining.txt");

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

// Class to represent a node in the separate chaining implementation
class HashNode {
    public String value; // Value stored in the node
    public HashNode next; // Reference to the next node in the chain

    // Constructor to initialize the node with a value and a null reference to the
    // next node
    HashNode(String value) {
        this.next = null;
        this.value = value;
    }
}

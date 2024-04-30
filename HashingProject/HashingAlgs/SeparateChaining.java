package HashingProject.HashingAlgs;

import java.io.FileWriter;
import java.io.IOException;

public class SeparateChaining {
    HashNode[] map;
    public int[] tracker;
    public int searchTracker;
    int size;

    public SeparateChaining(int size) {
        this.size = size;
        map = new HashNode[size];
        tracker = new int[size];
    }

    public void addNode(String value) {
        int key = Math.abs(value.hashCode()) % size;
        HashNode node = new HashNode(value);

        tracker[key]++;

        if (map[key] != null) {
            HashNode current = map[key];
            while (current.next != null) {
                tracker[key]++;
                current = current.next;
            }
            tracker[key]++;
            current.next = node;
        } else {
            map[key] = node;
        }

        // Write value and probe count to file
        // writeToFile(key, tracker[key]);
    }

    public int search(String[] values) {
        searchTracker = 0;
        for (String item : values) {
            int key = Math.abs(item.hashCode()) % size;
            HashNode current = map[key];
            while (!current.value.equals(item)) {
                searchTracker++;
                current = current.next;
            }
            searchTracker++;

            // Write value and probe count to file
            // writeToFile(key, searchTracker);
        }
        return searchTracker;
    }

    public int getSum() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += tracker[i];
        }
        return sum;
    }

    public void getHashTo() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (tracker[i] > max) {
                max = tracker[i];
            }
        }

        try {
            FileWriter writer = new FileWriter("hashes_per_index_separatechaining.txt", true);
            for (int i = 0; i < max + 1; i++) {
                int sum = 0;
                for (int j = 0; j < size; j++) {
                    if (i == tracker[j]) {
                        sum++;
                    }
                }
                if (sum != 0) {
                    writer.write(i + "->" + sum + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(int key, int probes) {
        try {
            FileWriter writer = new FileWriter("probe_counts_separatechaining.txt", true);
            writer.write(key + "," + probes + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class HashNode {
    public String value;
    public HashNode next;

    HashNode(String value) {
        this.next = null;
        this.value = value;
    }
}

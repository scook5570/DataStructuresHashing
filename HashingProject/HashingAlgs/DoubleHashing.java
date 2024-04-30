package HashingProject.HashingAlgs;

import java.io.FileWriter;
import java.io.IOException;

public class DoubleHashing {
    int size;
    int prime;
    String[] map;
    public int tracker[];
    public int searchTracker;
    String value;

    public DoubleHashing(int size) {
        this.size = size;
        map = new String[size];
        tracker = new int[size];
        prime = size - 1;
        while (!isPrime(prime)) {
            prime--;
        }
    }

    public void addNode(String value) {
        int key = Math.abs(value.hashCode()) % size;
        int offset = (prime - (value.hashCode() % prime));
        int probes = 0;

        while (map[key] != null) {
            tracker[key]++;
            probes++;
            key = (key + offset) % size;
        }

        tracker[key]++;
        map[key] = value;

        // Write value and probe count to file
        // writeToFile(key, probes);
    }

    public int search(String[] values) {
        searchTracker = 0;
        for (String value : values) {
            int key = Math.abs(value.hashCode()) % size;
            int offset = (prime - (value.hashCode() % prime));
            int probes = 0;

            while (!map[key].equals(value)) {
                searchTracker++;
                probes++;
                key = (key + offset) % size;
            }
            searchTracker++;

            // Write value and probe count to file
            // writeToFile(key, probes);
        }
        return searchTracker;
    }

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
            FileWriter writer = new FileWriter("hashes_per_index_doublehashing.txt", true);
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
            FileWriter writer = new FileWriter("probe_counts_doublehashing.txt", true);
            writer.write(key + "," + probes + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package HashingProject.HashingAlgs;

import java.io.FileWriter;
import java.io.IOException;

public class LinearProbing {
    int size;
    String[] map;
    public int[] tracker;
    public int searchTracker;
    String value;

    public LinearProbing(int size) {
        this.size = size;
        map = new String[size];
        tracker = new int[size];
    }

    public void addNode(String value) {
        int key = Math.abs(value.hashCode()) % size;
        int probes = 0;
        
        while (map[key] != null) {
            probes++;
            tracker[key]++;
            key = (key + 1) % size;
        }

        tracker[key]++;
        map[key] = value;
        
        // Write value and probe count to file
        // writeToFile(key, probes);
    }

    public int search(String[] values) {
        searchTracker = 0;
    
        for(String value : values) {
            int key = Math.abs(value.hashCode()) % size;
            int probes = 0;
            
            while (!map[key].equals(value)) {
                searchTracker++;
                probes++;
                key = (key + 1) % size;
            }
            searchTracker++;
            // Write value and probe count to file
            // writeToFile(key, probes);
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
            FileWriter writer = new FileWriter("hashes_per_index_linearprobing.txt", true);
            for (int i = 0; i < max + 1; i++) {
                int sum = 0;
                for (int j = 0; j < size; j++) {
                    if (i == tracker[j]) {
                        sum++;
                    }
                }
                writer.write(i + "->" + sum + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void writeToFile(int key, int probes) {
        try {
            FileWriter writer = new FileWriter("probe_counts_linearprobing.txt", true);
            writer.write(key + "," + probes + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

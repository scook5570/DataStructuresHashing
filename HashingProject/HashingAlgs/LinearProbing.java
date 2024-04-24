package HashingProject.HashingAlgs;

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
        
        while (map[key] != null) {
            tracker[key]++;
            key = (key+1)%size;
        }

        tracker[key]++;
        map[key] = value;
    }

    public int search(String[] values) {
        searchTracker = 0;
    
        for(String value : values) {
            int key = Math.abs(value.hashCode()) % size;
            
            while (!map[key].equals(value)) {
                searchTracker++;
                key = (key + 1) % size;
            }
            searchTracker++;
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

        for (int i = 0; i < max + 1; i++) {
            int sum = 0;
            for (int j = 0; j < size; j++) {
                if (i == tracker[j]) {
                    sum++;
                }
            }
            System.out.println(i + "->" + sum);
        }
    }
}
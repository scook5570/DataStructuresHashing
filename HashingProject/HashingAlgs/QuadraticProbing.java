package HashingProject.HashingAlgs;

public class QuadraticProbing {
    int size;
    String[] map;
    public int tracker;
    public int searchTracker;
    String value;

    public QuadraticProbing(int size) {
        this.size = size;
        map = new String[size];
        tracker = 0;
    }

    public void addNode(String value) {
        int key = Math.abs(value.hashCode()) % size;
        int i = 1;

        while (map[key] != null) {
            tracker++;
            key = (key + (i * i))%size;
            i++;
        }

        tracker++;
        map[key] = value;
    }

    public int search(String[] values) {
        searchTracker = 0;
        for (String value : values) {
            int key = Math.abs(value.hashCode()) % size;
            int i = 1;
    
            while (!map[key].equals(value)) {
                searchTracker++;
                key = (key + (i * i)) % size;
                i++;
            }
            searchTracker++;
        }
        return searchTracker;
    }
}

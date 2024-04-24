package HashingProject.HashingAlgs;

public class LinearProbing {
    int size;
    String[] map;
    public int tracker, searchTracker;
    String value;

    public LinearProbing(int size) {
        this.size = size;
        map = new String[size];
        tracker = 0;
    }

    public void addNode(String value) {
        int key = Math.abs(value.hashCode()) % size;
        
        while (map[key] != null) {
            tracker++;
            key = (key+1)%size;
        }

        tracker++;
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
}
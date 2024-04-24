package HashingProject.HashingAlgs;

public class DoubleHashing {
    int size;
    int prime;
    String[] map;
    public int tracker;
    public int searchTracker;
    String value;

    public DoubleHashing(int size) {
        this.size = size;
        map = new String[size];
        tracker = 0;
        prime = size - 1;
        while (!isPrime(prime)) {
            prime--;
        }
    }

    public void addNode(String value) {
        int key = Math.abs(value.hashCode()) % size;
        int offset = (prime - (value.hashCode() % prime));

        while (map[key] != null) {
            tracker++;
            key = (key+offset)%size;
        }

        tracker++;
        map[key] = value;
    }

    public int search(String[] values) {
        searchTracker = 0;
        for (String value : values) {
            int key = Math.abs(value.hashCode()) % size;
            int offset = (prime - (value.hashCode() % prime));
    
            while (!map[key].equals(value)) {
                searchTracker++;
                key = (key + offset) % size;
            }
            searchTracker++;
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
}
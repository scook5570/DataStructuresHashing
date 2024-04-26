package HashingProject.HashingAlgs;

public class DoubleHashing {
    int size;
    int prime;
    String[] map;
    public int tracker[];
    public int searchTracker;
    public int[] wordProbes = new int[466551];
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

    public void addNode(String value, int n) {
        int key = Math.abs(value.hashCode()) % size;
        int offset = (prime - (value.hashCode() % prime));

        while (map[key] != null) {
            wordProbes[n]++;
            tracker[key]++;
            key = (key + offset) % size;
        }

        tracker[key]++;
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
            if (sum != 0) {
                System.out.println(i + "->" + sum);
            }
        }
    }
}
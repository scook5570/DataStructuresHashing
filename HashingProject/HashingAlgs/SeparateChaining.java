package HashingProject.HashingAlgs;

import java.lang.Math;

public class SeparateChaining {
    HashNode[] map;
    public int tracker;
    public int searchTracker;
    int size;

    public SeparateChaining(int size) {
        this.size = size;
        map = new HashNode[size];
        tracker = 0;
    }

    public void addNode(String value) {
        int key = Math.abs(value.hashCode()) % size;
        HashNode node = new HashNode(value);
    
        tracker++;
    
        if (map[key] != null) {
            HashNode current = map[key];
            while (current.next != null) {
                tracker++;
                current = current.next;
            }
            tracker++;
            current.next = node;
        } else {
            map[key] = node;
        }
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
        }
        return searchTracker;
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

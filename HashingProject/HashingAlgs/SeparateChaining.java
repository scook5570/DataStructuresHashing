package HashingProject.HashingAlgs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

public class SeparateChaining {
    HashNode[] map;
    int[] tracker;
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
                current = current.next;
            }
            current.next = node;
        } else {
            map[key] = node;
        }
    }

    public void printMapToFile(File location) throws IOException {
        FileWriter writer = new FileWriter(location);
        writer.write("Seperate Chaining Results " + size + ":\n");
        for (int i = 0; i < size; i++) {
            if (map[i] == null) {
                writer.write("null\n");
            } else  {
                HashNode node = map[i];
                while (node != null) {
                    writer.write(node.value);
                    if (node.next != null) {
                        writer.write(" --> ");
                    }
                    node = node.next;
                }
                writer.write("\n");
            }
        }
        writer.write("--------------------------------------------");
    }
}

class HashNode {
    String value;
    HashNode next;
    
    HashNode(String value) {
        this.next = null;
        this.value = value;
    }
}

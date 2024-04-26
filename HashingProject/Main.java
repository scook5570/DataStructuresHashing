package HashingProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import HashingProject.HashingAlgs.*;

public class Main {
    // load factor 0.5 = 466551/933102
    // load factor 0.7 = 466551/666502 - 666511 (prime version)
    static final File words = new File("Resources/words.txt");

    public static void main(String args[]) throws IOException {
        // map sizes and timing
        int nonPrime = 933102;
        int prime = 666511;
        long start, end, probes;

        for (int loops = 10; loops < 1; loops++) {
            // create empty maps
            SeparateChaining sepMap = new SeparateChaining(nonPrime);
            SeparateChaining sepMapPrime = new SeparateChaining(prime);
            LinearProbing linProbe = new LinearProbing(nonPrime);
            LinearProbing linProbePrime = new LinearProbing(prime);
            QuadraticProbing quaProbe = new QuadraticProbing(nonPrime);
            QuadraticProbing quaProbePrime = new QuadraticProbing(prime);
            DoubleHashing douHash = new DoubleHashing(nonPrime);
            DoubleHashing douHashPrime = new DoubleHashing(prime);

            // arrays to hold search values
            String[] search10 = new String[10];
            String[] search20 = new String[20];
            String[] search30 = new String[30];
            String[] search40 = new String[40];
            String[] search50 = new String[50];
            popArr(search10);
            popArr(search20);
            popArr(search30);
            popArr(search40);
            popArr(search50);

            String[][] arrays = { search10, search20, search30, search40, search50 };

            // fill maps with hashing methods
            int i = 0;

            // separate chaining
            Scanner sepScanner = new Scanner(words);
            start = System.nanoTime();
            while (sepScanner.hasNextLine()) {
                String input = sepScanner.nextLine();
                sepMap.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            sepScanner.close();
            System.out.println("Separate Chaining: " + end + " " + sepMap.getSum());

            // separate chaining
            Scanner sepPrimeScanner = new Scanner(words);
            start = System.nanoTime();
            while (sepPrimeScanner.hasNextLine()) {
                String input = sepPrimeScanner.nextLine();
                sepMapPrime.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            sepPrimeScanner.close();
            System.out.println("Separate Chaining Prime: " + end + " " + sepMapPrime.getSum());

            // linear probing
            Scanner linScanner = new Scanner(words);
            start = System.nanoTime();
            while (linScanner.hasNextLine()) {
                String input = linScanner.nextLine();
                linProbe.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            linScanner.close();
            System.out.println("Linear Probing: " + end + " " + linProbe.getSum());

            // linear probing
            Scanner linPrimeScanner = new Scanner(words);
            start = System.nanoTime();
            while (linPrimeScanner.hasNextLine()) {
                String input = linPrimeScanner.nextLine();
                linProbePrime.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            linPrimeScanner.close();
            System.out.println("Linear Probing Prime: " + end + " " + linProbePrime.getSum());

            // quadratic probing
            Scanner quaScanner = new Scanner(words);
            start = System.nanoTime();
            while (quaScanner.hasNextLine()) {
                String input = quaScanner.nextLine();
                quaProbe.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            quaScanner.close();
            System.out.println("Quadratic Probing: " + end + " " + quaProbe.getSum());

            // quadratic probing
            Scanner quaPrimeScanner = new Scanner(words);
            start = System.nanoTime();
            while (quaPrimeScanner.hasNextLine()) {
                String input = quaPrimeScanner.nextLine();
                quaProbePrime.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            quaPrimeScanner.close();
            System.out.println("Quadratic Probing Prime: " + end + " " + quaProbePrime.getSum());

            // double hashing
            Scanner douScanner = new Scanner(words);
            start = System.nanoTime();
            while (douScanner.hasNextLine()) {
                String input = douScanner.nextLine();
                douHash.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            douScanner.close();
            System.out.println("Double Hashing: " + end + " " + douHash.getSum());

            // double hashing
            Scanner douPrimeScanner = new Scanner(words);
            start = System.nanoTime();
            while (douPrimeScanner.hasNextLine()) {
                String input = douPrimeScanner.nextLine();
                douHashPrime.addNode(input, i);
                i++;
            }
            end = System.nanoTime() - start;
            i = 0;
            douPrimeScanner.close();
            System.out.println("Double Hashing Prime: " + end + " " + douHashPrime.getSum());

            // search functions for each
            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = sepMap.search(array);
                end = System.nanoTime() - start;
                System.out.println("Separate Chaining Search: " + end + " " + probes);
            }

            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = sepMapPrime.search(array);
                end = System.nanoTime() - start;
                System.out.println("Separate Chaining Prime Search: " + end + " " + probes);
            }

            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = linProbe.search(array);
                end = System.nanoTime() - start;
                System.out.println("Linear Probing Search: " + end + " " + probes);
            }

            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = linProbePrime.search(array);
                end = System.nanoTime() - start;
                System.out.println("Linear Probing Prime Search: " + end + " " + probes);
            }

            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = quaProbe.search(array);
                end = System.nanoTime() - start;
                System.out.println("Quadratic Probing Search: " + end + " " + probes);
            }

            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = quaProbePrime.search(array);
                end = System.nanoTime() - start;
                System.out.println("Quadratic Probing Prime Search: " + end + " " + probes);
            }

            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = douHash.search(array);
                end = System.nanoTime() - start;
                System.out.println("Double Hashing Search: " + end + " " + probes);
            }

            for (String[] array : arrays) {
                start = System.nanoTime();
                probes = douHashPrime.search(array);
                end = System.nanoTime() - start;
                System.out.println("Double Hashing Prime Search: " + end + " " + probes);
            }
        }
    }

    public static void popArr(String[] arr) throws FileNotFoundException {
        Scanner searchValues = new Scanner(words);

        // skip a bunch of lines so more probing will occur
        for (int i = 0; i < 400000; i++) {
            searchValues.nextLine();
        }

        int i = 0;
        while (searchValues.hasNextLine() && i < arr.length) {
            arr[i] = searchValues.nextLine();
            i++;
        }
        searchValues.close();
    }
}

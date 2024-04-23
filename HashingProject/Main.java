package HashingProject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import HashingProject.HashingAlgs.*;

public class Main {
    // load factor 0.5 = 466551/933102
    // load factor 0.7 = 466551/666502 - 666511 (prime version)

    public static void main(String args[]) throws IOException {
        int nonPrime = 933102;
        int prime = 666511;
        long start, end;

        SeparateChaining sepMap = new SeparateChaining(nonPrime);
        SeparateChaining sepMapPrime = new SeparateChaining(prime);
        LinearProbing linProbe = new LinearProbing(nonPrime);
        LinearProbing linProbePrime = new LinearProbing(prime);
        QuadraticProbing quaProbe = new QuadraticProbing(nonPrime);
        QuadraticProbing quaProbePrime = new QuadraticProbing(prime);
        DoubleHashing douHash = new DoubleHashing(nonPrime);
        DoubleHashing douHashPrime = new DoubleHashing(prime);

        File words = new File("Resources/words.txt");

        Scanner sepScanner = new Scanner(words);
        start = System.nanoTime();
        while (sepScanner.hasNextLine()) {
            String input = sepScanner.nextLine();
            sepMap.addNode(input);
        }
        end = System.nanoTime() - start;
        sepScanner.close();
        System.out.println(end + " " + sepMap.tracker);

        Scanner sepPrimeScanner = new Scanner(words);
        start = System.nanoTime();
        while (sepPrimeScanner.hasNextLine()) {
            String input = sepPrimeScanner.nextLine();
            sepMapPrime.addNode(input);
        }
        end = System.nanoTime() - start;
        sepPrimeScanner.close();
        System.out.println(end + " " + sepMapPrime.tracker);

        Scanner linScanner = new Scanner(words);
        start = System.nanoTime();
        while (linScanner.hasNextLine()) {
            String input = linScanner.nextLine();
            linProbe.addNode(input);
        }
        end = System.nanoTime() - start;
        linScanner.close();
        System.out.println(end + " " + linProbe.tracker);

        Scanner linPrimeScanner = new Scanner(words);
        start = System.nanoTime();
        while (linPrimeScanner.hasNextLine()) {
            String input = linPrimeScanner.nextLine();
            linProbePrime.addNode(input);
        }
        end = System.nanoTime() - start;
        linPrimeScanner.close();
        System.out.println(end + " " + linProbePrime.tracker);

        Scanner quaScanner = new Scanner(words);
        start = System.nanoTime();
        while (quaScanner.hasNextLine()) {
            String input = quaScanner.nextLine();
            quaProbe.addNode(input);
        }
        end = System.nanoTime() - start;
        quaScanner.close();
        System.out.println(end + " " + quaProbe.tracker);

        Scanner quaPrimeScanner = new Scanner(words);
        start = System.nanoTime();
        while (quaPrimeScanner.hasNextLine()) {
            String input = quaPrimeScanner.nextLine();
            quaProbePrime.addNode(input);
        }
        end = System.nanoTime() - start;
        quaPrimeScanner.close();
        System.out.println(end + " " + quaProbePrime.tracker);

        Scanner douScanner = new Scanner(words);
        start = System.nanoTime();
        while (douScanner.hasNextLine()) {
            String input = douScanner.nextLine();
            douHash.addNode(input);
        }
        end = System.nanoTime() - start;
        douScanner.close();
        System.out.println(end + " " + douHash.tracker);

        Scanner douPrimeScanner = new Scanner(words);
        start = System.nanoTime();
        while (douPrimeScanner.hasNextLine()) {
            String input = douPrimeScanner.nextLine();
            douHashPrime.addNode(input);
        }
        end = System.nanoTime() - start;
        douPrimeScanner.close();
        System.out.println(end + " " + douHashPrime.tracker);
    }
}

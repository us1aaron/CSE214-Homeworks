/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 7
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the NeoViewer class
 * The user will be prompted to enter a page number to retreive information on
 * The user will be able to add onto this list, sort it, and print it out
 *
 */
import java.util.Collections;
import java.util.Scanner;
public class NeoViewer {
    public static void main(String args[]){
        NeoDataBase dataBase = new NeoDataBase();
        boolean quit = false;
        int pageNum = 0;
        Scanner input = new Scanner(System.in);
        String choice, sortChoice;
        System.out.println("Welcome to NEO Viewer!\n");
        while(!quit) {
            System.out.println("Option Menu:");
            System.out.println("\tA) Add a page to the database");
            System.out.println("\tS) Sort the database");
            System.out.println("\tP) Print the database as a table");
            System.out.println("\tQ) Quit\n");
            System.out.println("Select a menu option:");
            choice = input.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    try {
                        System.out.println("Enter a page to load:");
                        pageNum = input.nextInt();
                        input.nextLine();
                        if(pageNum < 0 || pageNum > 715)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. Number not in valid range.");break;
                    }
                    input.nextLine();
                    dataBase.addAll(dataBase.buildQueryURL(pageNum));
                    System.out.println("Page loaded successfully!");
                    break;
                case "S":
                    try {
                        System.out.println("\nR) Sort by referenceID");
                        System.out.println("D) Sort by diameter");
                        System.out.println("A) Sort by approach date");
                        System.out.println("M) Sort by miss distance");
                        System.out.println("Select a menu option:");
                        sortChoice = input.nextLine().toUpperCase();
                        if (sortChoice.equals("R")) {
                            Collections.sort(dataBase.list, new ReferenceIDComparator());
                            System.out.println("Table sorted on reference ID.");
                        } else if (sortChoice.equals("D")) {
                            Collections.sort(dataBase.list, new DiameterComparator());
                            System.out.println("Table sorted on diameter.");
                        } else if (sortChoice.equals("A")) {
                            Collections.sort(dataBase.list, new ApproachDateComparator());
                            System.out.println("Table sorted approach date.");
                        } else if (sortChoice.equals("M")) {
                            Collections.sort(dataBase.list, new MissDistanceComparator());
                            System.out.println("Table sorted on miss distance.");
                        } else
                            throw new IllegalArgumentException();
                    }catch (IllegalArgumentException e){
                        System.out.println("Error. Invalid selection.");break;
                    }
                    break;
                case "P":
                    dataBase.printTable();
                    break;
                case "Q":
                    quit = true;
            }
        }
    }
}

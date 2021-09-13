/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 6
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the StorageManager class
 * A new file will be created if one does not already exist
 * User will be prompted to make a selection from a menu
 * Program ends when user decides to either exit and save file or exit and delete
 */
import java.io.*;
import java.util.Scanner;
public class StorageManager {
    public static void main(String args[]){
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        String option, print = "", client, content;
        int id;
        StorageTable table = new StorageTable();
        StorageTable storage = new StorageTable();
        try {
            FileInputStream file = new FileInputStream("storage.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            storage = (StorageTable) inStream.readObject();
            inStream.close();
        }catch(FileNotFoundException fnfe){
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        table = storage;
        System.out.println("Hello, and welcome to Rocky Stream Storage Manager");
        while(!exit) {
            System.out.println("\nMenu:");
            System.out.println("P - Print all storage boxes");
            System.out.println("A - Insert into storage box");
            System.out.println("R - Remove contents of a storage box");
            System.out.println("C - Select all boxes owned by a particular client");
            System.out.println("F - Find a box ID and display its owner and contents");
            System.out.println("Q - Quit and save workspace");
            System.out.println("X - Quit and delete workspace");
            System.out.println("\nPlease select and option:");
            option = input.nextLine().toUpperCase();
            switch(option){
                case "P":
                    print = "";
                    System.out.println(String.format("%-13s%-30s%-20s", "Box#", "Contents", "Owner"));
                    System.out.println("----------------------------------------------------------------");
                    for(Integer i: table.table.keySet()){
                        print += String.format("%-13d%-30s%-20s", table.table.get(i).getId(), table.table.get(i).getContents(), table.table.get(i).getClient()) + "\n";
                    }
                    System.out.println(print);
                    break;
                case "A":
                    System.out.println("Please enter ID:");
                    try {
                        id = input.nextInt();
                        input.nextLine();
                        if(table.table.containsKey(id))
                            throw new IllegalArgumentException();
                    }catch (IllegalArgumentException e){
                        System.out.println("Error, id exists.");break;
                    }
                    System.out.println("Please enter client:");
                    client = input.nextLine();
                    System.out.println("Please enter contents:");
                    content = input.nextLine();
                    Storage box = new Storage(id, client,content);
                    table.putStorage(id, box);
                    break;
                case "R":
                    System.out.println("Please enter ID:");
                    id = input.nextInt();
                    input.nextLine();
                    table.table.remove(id);
                    System.out.println("Box " + id + " is now removed.");
                    break;
                case "C":
                    print = "";
                    System.out.println("Please enter name of the client:");
                    client = input.nextLine();
                    System.out.println(String.format("%-13s%-30s%-20s", "Box#", "Contents", "Owner"));
                    System.out.println("----------------------------------------------------------------");
                    for(Integer i: table.table.keySet()){
                        if(table.table.get(i).getClient().equals(client))
                            print += String.format("%-13d%-30s%-20s", table.table.get(i).getId(), table.table.get(i).getContents(), table.table.get(i).getClient()) + "\n";
                    }
                    System.out.println(print);
                    break;
                case "F":
                    print = "";
                    System.out.println("Please enter ID:");
                    id = input.nextInt();
                    input.nextLine();
                    System.out.println(String.format("%-13s%-30s%-20s", "Box#", "Contents", "Owner"));
                    System.out.println("----------------------------------------------------------------");
                    for(Integer i: table.table.keySet()){
                        if(table.table.get(i).getId() == id)
                            print += String.format("%-13d%-30s%-20s", table.table.get(i).getId(), table.table.get(i).getContents(), table.table.get(i).getClient() + "\n");
                    }
                    System.out.println(print);
                    break;
                case "Q":
                    try {
                        FileOutputStream file = new FileOutputStream("storage.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(table);
                        outStream.close();
                    }catch(FileNotFoundException fnfe){
                        System.out.println("File not found");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    break;
                case "X":
                    File f = new File("storage.obj");
                    f.delete();
                    exit = true;
                    break;
            }
        }

    }
}

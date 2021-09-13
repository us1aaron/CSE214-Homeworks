/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 5
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the Zork class
 * this will simulate the game Zork
 * the user will be prompted to enter the name of a text file
 * the file will be read and a tree will be created
 * the user will be able to either edit or play through
 * after exiting, the text file will be updated with any changes made
 */

import java.util.Scanner;
public class Zork {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        String filename = "", option;
        StoryTree tree = new StoryTree();
        boolean exit = false;
        System.out.println("Hello and Welcome to Zork!");
        while(tree.root.getLeftChild() == null) {
            System.out.println("Please enter the file name:");
            filename = input.nextLine();
            tree = new StoryTree();
            tree.readTree(filename);
        }
        System.out.println("Loading game from this file...");
        System.out.println("File Loaded!");
        while(!exit) {
            System.out.println("Would you like to edit (E), play (P), or quit (Q)?");
            option = input.nextLine().toUpperCase();
            switch (option) {
                case "E":
                    editTree(tree);break;
                case "P":
                    tree.setState(StoryTree.GameState.GAME_NOT_OVER);
                    playTree(tree);break;
                case "Q":
                    tree.saveTree(filename, tree);
                    exit = true;break;
            }
        }
    }
    public static void editTree(StoryTree tree){
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        String choice, newOption, newMessage, newPosition, s_index;
        int index;
        tree.cursor = tree.root;
        while(!exit) {
            System.out.println("Zork Editor:");
            System.out.println("\tV: View the cursor's position, option, and message.");
            System.out.println("\tS: Select a child of this cursor (optoins are 1, 2, and 3).");
            System.out.println("\tO: Set the option for this cursor.");
            System.out.println("\tM: Set the message of this cursor.");
            System.out.println("\tA: Add a child to StoryNode to the cursor.");
            System.out.println("\tD: Delete one of the cursor's children and all its descendants.");
            System.out.println("\tR: Move the cursor to the root of the tree.");
            System.out.println("\tP: Return to parent.");
            System.out.println("\tQ: Quit.\n");
            System.out.println("Please select an option:");
            choice = input.nextLine().toUpperCase();
            switch(choice){
                case "V":
                    try {
                        if (tree.cursor == null)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. Empty Tree.");break;
                    }
                    System.out.println("Position: " + tree.cursor.getPosition());
                    System.out.println("Option: " + tree.cursor.getOption());
                    System.out.println("Message: " + tree.cursor.getMessage());
                    break;
                case "S":
                    System.out.println("Please select a child: [1,2,3]");
                    index = input.nextInt();
                    input.nextLine();
                    try {
                        if (index == 1 && tree.cursor.getLeftChild() != null)
                            tree.cursor = tree.cursor.getLeftChild();
                        else if (index == 2 && tree.cursor.getMiddleChild() != null)
                            tree.cursor = tree.cursor.getMiddleChild();
                        else if (index == 3 && tree.cursor.getRightChild() != null)
                            tree.cursor = tree.cursor.getRightChild();
                        else
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. No child found.");break;
                    }
                    break;
                case "O":
                    System.out.println("Enter a new option:");
                    newOption = input.nextLine();
                    tree.cursor.setOption(newOption);
                    System.out.println("\nOption Set.");
                    break;
                case "M":
                    System.out.println("Enter a new message:");
                    newMessage = input.nextLine();
                    tree.cursor.setMessage(newMessage);
                    System.out.println("\nMessage set.");
                    break;
                case "A":
                    newPosition = tree.cursor.getPosition();
                    System.out.println("Enter an option:");
                    newOption = input.nextLine();
                    System.out.println("Enter a message:");
                    newMessage = input.nextLine();
                    try {
                        if (tree.cursor.getLeftChild() == null)
                            newPosition += "-1";
                        else if (tree.cursor.getMiddleChild() == null)
                            newPosition += "-2";
                        else if (tree.cursor.getRightChild() == null)
                            newPosition += "-3";
                        else
                            throw new TreeFullException("Error. Max children");
                    }catch(TreeFullException e){
                        System.out.println(e.getMessage());break;
                    }
                    tree.addChild(newPosition, newOption, newMessage);
                    System.out.println("Child added.");
                    break;
                case "D":
                    System.out.println("Please select a child to delete: [1,2,3]");
                    s_index = input.nextLine();
                    try {
                        if (s_index.equals("1") && tree.cursor.getLeftChild() == null)
                            throw new NodeNotPresentException("Error. Child not found.");
                        if (s_index.equals("2") && tree.cursor.getMiddleChild() == null)
                            throw new NodeNotPresentException("Error. Child not found.");
                        if (s_index.equals("3") && tree.cursor.getRightChild() == null)
                            throw new NodeNotPresentException("Error. Child not found.");
                    }catch(NodeNotPresentException e){
                        System.out.println(e.getMessage());break;
                    }
                    tree.removeChild(tree.cursor.getPosition() + s_index);
                    System.out.println("Subtree deleted.");
                    break;
                case "R":
                    try{
                        if(tree.root.getLeftChild() == null)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. Empty tree.");break;
                    }
                    tree.resetCursor();
                    System.out.println("Cursor moved to root.");
                    break;
                case "P":
                    tree.returnToParent();
                    System.out.println("Returned to previous step.");
                    break;
                case "Q":
                    exit = true;
                    break;
            }
        }
    }
    public static void playTree(StoryTree tree){
        Scanner input = new Scanner(System.in);
        tree.cursor = tree.root.getLeftChild();
        String choice;
        while(tree.getState() == StoryTree.GameState.GAME_NOT_OVER){
            System.out.println(tree.cursor.getMessage());
            if(tree.cursor.getLeftChild() != null)
                System.out.println("1. " + tree.cursor.getLeftChild().getOption());
            if(tree.cursor.getMiddleChild() != null)
                System.out.println("2. " + tree.cursor.getMiddleChild().getOption());
            if(tree.cursor.getRightChild() != null)
                System.out.println("3. " + tree.cursor.getRightChild().getOption());
            System.out.println("Choose an option:");
            choice = input.nextLine().toUpperCase();
            try {
                switch (choice) {
                    case "1":
                        tree.cursor = tree.cursor.getLeftChild();
                        break;
                    case "2":
                        tree.cursor = tree.cursor.getMiddleChild();
                        break;
                    case "3":
                        tree.cursor = tree.cursor.getRightChild();
                        break;
                    case "C":
                        System.out.println("Win probability at this point is " + tree.winProbability() + "%");break;
                    case "P":
                        tree.returnToParent();
                        System.out.println("Returned to previous step.");break;
                    case "Q":
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }catch(IllegalArgumentException e){
                System.out.println("Error. Invalid selection.");
            }
            if(tree.cursor.isWinningNode())
                tree.setState(StoryTree.GameState.GAME_OVER_WIN);
            else if(tree.cursor.isLosingNode())
                tree.setState(StoryTree.GameState.GAME_OVER_LOSE);
        }
        System.out.println(tree.cursor.getMessage());
        tree.cursor = tree.root.getLeftChild();
    }
}

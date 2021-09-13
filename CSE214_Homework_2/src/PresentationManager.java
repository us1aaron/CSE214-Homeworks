/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 2
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the PresentationManager class.
 * A menu will be presented to the user
 * The user will be prompted to select from the menu
 * This simulates a program which will create a slideshow
 * Each slide will have data such as title and bullet points
 */
import java.util.Scanner;
public class PresentationManager{
    public static void main(String[] args){
        SlideList slideShow = new SlideList();
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while(!exit){
            String option;
            System.out.println("------- Menu -------");
            System.out.println("F) Move cursor forward");
            System.out.println("B) Move cursor backward");
            System.out.println("D) Display cursor slide");
            System.out.println("E) Edit cursor slide");
            System.out.println("P) Print presentation summary");
            System.out.println("A) Append new slide to tail");
            System.out.println("I) Insert new slide before cursor");
            System.out.println("R) Remove slide at cursor");
            System.out.println("H) Reset cursor to head");
            System.out.println("Q) Quit");
            System.out.println("\nSelect a menu option:");
            option = input.nextLine();
            option = option.toUpperCase();
            String title;
            double duration;
            int bCount = 1;
            String choice = "";
            String bulletText;
            int index;
            Slide newSlide;
            switch(option){
                case("F"):
                    try{
                        if(slideShow.cursor == null)
                            throw new IllegalArgumentException();
                        else if(slideShow.cursor == slideShow.tail)
                            throw new EndOfListException("Error. Reached end of list.");
                        else{
                            slideShow.cursorForward();
                            System.out.println("Cursor moved forward to slide '" + slideShow.getCursorSlide().getTitle() + "'.");
                        }
                    }
                    catch(EndOfListException e){
                        System.out.println(e.getMessage());break;
                    }
                    catch(IllegalArgumentException d){
                        System.out.println("Error. List is empty.");
                    }
                    break;
                case("B"):
                    try{
                        if(slideShow.cursor == null)
                            throw new IllegalArgumentException();
                        else if(slideShow.cursor == slideShow.head)
                            throw new EndOfListException("Error. Reached end of list.");
                        else{
                            slideShow.cursorBackward();
                            System.out.println("Cursor moved backward to slide '" + slideShow.getCursorSlide().getTitle() + "'.");
                        }
                    }catch(EndOfListException e){
                        System.out.println(e.getMessage());break;
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error. List is empty.");
                    }
                    break;
                case("D"):
                    try{
                        if(slideShow.cursor == null)
                            throw new EndOfListException("Error. Slideshow is empty");
                        else
                            System.out.println(slideShow.getCursorSlide().toString());
                    }catch(EndOfListException e){
                        System.out.println(e.getMessage());break;
                    }
                    break;
                case("E"):
                    System.out.println("Edit title, duration, or bullets? (t/d/b)");
                    choice = input.nextLine();
                    choice = choice.toLowerCase();
                    switch(choice){
                        case("t"):
                            System.out.println("New title: ");
                            try{
                                title = input.nextLine();
                                if(title == null)
                                    throw new IllegalArgumentException();
                            }catch(IllegalArgumentException e){
                                System.out.println("Error. No title.");break;
                            }
                            slideShow.getCursorSlide().setTitle(title);
                            System.out.println("Title has been updated.");
                            break;
                        case("d"):
                            System.out.println("New duration: ");
                            try{
                                duration = input.nextDouble();
                                if(duration < 0)
                                    throw new IllegalArgumentException();
                            }catch(IllegalArgumentException e){
                                input.nextLine();
                                System.out.println("Error. Duration needs to be greater than 0.");break;
                            }
                            input.nextLine();
                            slideShow.getCursorSlide().setDuration(duration);
                            System.out.println("Duration has been updated.");
                            break;
                        case("b"):
                            System.out.println("Bullet number: ");
                            try{
                                index = input.nextInt();
                                if(index < 1 || index>slideShow.getCursorSlide().getNumBullets())
                                    throw new IllegalArgumentException();
                            }catch(IllegalArgumentException e){
                                input.nextLine();
                                System.out.println("Error. Number is not in valid range.");break;
                            }
                            input.nextLine();
                            System.out.println("Delete or edit? (d/e)");
                            try{
                                choice = input.nextLine();
                                if(!choice.equals("e") && !choice.equals("d"))
                                    throw new IllegalArgumentException();
                            }catch(IllegalArgumentException e){
                                System.out.println("Error. Invalid Choice.");break;
                            }
                            if(choice.equals("d")){
                                slideShow.bulletCount -= 1;
                                slideShow.getCursorSlide().setBullet(null, index);
                                System.out.println("Bullet " + index + " has been removed.");
                            }
                            else{
                                System.out.println("New Text: ");
                                bulletText = input.nextLine();
                                slideShow.getCursorSlide().setBullet(bulletText, index);
                                System.out.println("Bullet " + index + " has been updated.");
                            }
                            break;
                    }
                    break;
                case("P"):
                    try{
                        if(slideShow.cursor == null)
                            throw new EndOfListException("Error. Slideshow is empty");
                        else
                            System.out.println(slideShow.toString());
                    }catch(EndOfListException e){
                        System.out.println(e.getMessage());break;
                    }
                    break;
                case("A"):
                    newSlide = new Slide();
                    System.out.println("Enter the slide title: ");
                    try{
                        title = input.nextLine();
                        if(title == null)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. No title.");break;
                    }
                    newSlide.setTitle(title);
                    System.out.println("Enter the slide duration: ");
                    try{
                        duration = input.nextDouble();
                        if(duration < 0)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        input.nextLine();
                        System.out.println("Error. Duration needs to be greater than 0.");break;
                    }
                    input.nextLine();
                    newSlide.setDuration(duration);
                    System.out.println("Bullet " + bCount + ": ");
                    bulletText = input.nextLine();
                    newSlide.setBullet(bulletText, bCount);
                    bCount++;
                    while(!choice.equals("n") && bCount <= 5){
                        System.out.println("Add another bullet point? (y/n)");
                        choice = input.nextLine();
                        if(choice.equals("y")){
                            System.out.println("Bullet " + bCount + ": ");
                            bulletText = input.nextLine();
                            newSlide.setBullet(bulletText, bCount);
                        }
                        bCount += 1;
                    }
                    System.out.println("Slide '" + title + "' added to presentation.");
                    slideShow.appendToTail(newSlide);
                    break;
                case("I"):
                    newSlide = new Slide();
                    System.out.println("Enter the slide title: ");
                    try{
                        title = input.nextLine();
                        if(title == null)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. No title.");break;
                    }
                    newSlide.setTitle(title);
                    System.out.println("Enter the slide duration: ");
                    try{
                        duration = input.nextDouble();
                        if(duration < 0)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        input.nextLine();
                        System.out.println("Error. Duration needs to be greater than 0.");break;
                    }
                    newSlide.setDuration(duration);
                    input.nextLine();
                    System.out.println("Bullet " + bCount + ": ");
                    bulletText = input.nextLine();
                    newSlide.setBullet(bulletText, bCount);
                    bCount++;
                    while(!choice.equals("n") && bCount <= 5){
                        System.out.println("Add another bullet point? (y/n)");
                        choice = input.nextLine();
                        if(choice.equals("y")){
                            System.out.println("Bullet " + bCount + ": ");
                            bulletText = input.nextLine();
                            newSlide.setBullet(bulletText, bCount);
                        }
                        bCount += 1;
                    }
                    System.out.println("Slide '" + title + "' added to presentation.");
                    slideShow.insertBeforeCursor(newSlide);
                    break;
                case("R"):
                    try{
                        if(slideShow.cursor == null)
                            throw new EndOfListException("Error. List is Empty.");
                        else{
                            System.out.println(slideShow.removeCursor().getTitle() + " has been removed.");
                        }
                    }catch(EndOfListException e){
                        System.out.println(e.getMessage());break;
                    }
                    break;
                case("H"):
                    try{
                        if(slideShow.cursor == null)
                            throw new EndOfListException("Error. List is Empty.");
                        else {
                            slideShow.resetCursorToHead();
                            System.out.println("Cursor is now at on first slide.");
                        }
                    }catch(EndOfListException e){
                        System.out.println(e.getMessage());break;
                    }
                    break;
                case("Q"):
                    exit = true;
                    break;
            }
            System.out.println("\n");
        }
    }
}
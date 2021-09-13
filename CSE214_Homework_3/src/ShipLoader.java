/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 3
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the ShipLoader Class
 * The user will be prompted to declare the number of stacks, max height, and max weight of the cargo ship.
 * The user will then be presented with a menu that they will have them simulate a ship loader.
 * It will end when the user chooses Q) Quit
 */

import java.util.Scanner;
import java.util.Stack;
public class ShipLoader {
    public static void main(String[] args){
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        String option;
        int numStacks = 0, maxHeight = 0;
        double maxWeight = 0.0;
        Stack<Cargo> dock = new Stack<>();
        System.out.println("Welcome to ShipLoader!\n");
        System.out.println("Cargo Ship Parameters");
        System.out.println("----------------------------------------");
        System.out.println("Number of stacks: ");
        while(!(numStacks > 0)) {
            try {
                numStacks = input.nextInt();
                if (numStacks <= 1)
                    throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Error. Number less than one.");
            }
        }
        System.out.println("Maximum height of stacks: ");
        while(!(maxHeight > 0)) {
            try {
                maxHeight = input.nextInt();
                if (maxHeight <= 1)
                    throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Error. Number less than one.");
            }
        }
        System.out.println("Maximum total cargo weight: ");
        while(!(maxWeight > 0.0)) {
            try {
                maxWeight = input.nextDouble();
                if (maxWeight <= 0.0)
                    throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Error. Number less than one.");
            }
        }
        System.out.println("Cago ship created.\nPulling ship to dock...\nCargo ship ready to be loaded.\n");
        CargoShip ship = new CargoShip(numStacks, maxHeight, maxWeight);
        input.nextLine();
        String cargoName;
        int load = 0, unload = 0, dockSize = 0;
        double cargoWeight, totalWeight = 0;
        String cargoStrengthS;
        Cargo.CargoStrength fstrength = Cargo.CargoStrength.FRAGILE;
        Cargo.CargoStrength mstrength = Cargo.CargoStrength.MODERATE;
        Cargo.CargoStrength sstrength = Cargo.CargoStrength.STURDY;
        Cargo.CargoStrength cargoStrength = fstrength;
        Cargo newCargo, temp = null;
        while(!exit){
            System.out.println("------- Menu -------");
            System.out.println("C) Create new cargo");
            System.out.println("L) Load cargo from dock");
            System.out.println("U) Unload cargo from ship");
            System.out.println("M) Move cargo between stacks");
            System.out.println("K) Clear dock");
            System.out.println("P) Print ship stacks");
            System.out.println("S) Search for cargo");
            System.out.println("Q) Quit");
            System.out.println("\nSelect a menu option:");
            option = input.nextLine();
            option = option.toUpperCase();
            switch(option){
                case("C"):
                    System.out.println("Enter the name of the cargo: ");
                        cargoName = input.nextLine();
                    System.out.println("Enter the weight of the cargo: ");
                    try {
                        cargoWeight = input.nextInt();
                        if(cargoWeight < 0.0)
                            throw new IllegalArgumentException();
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. Number less than one.");break;
                    }
                    input.nextLine();
                    System.out.println("Enter the container strength(F/M/S): ");
                    cargoStrengthS = input.nextLine().toUpperCase();
                    try{
                        if(cargoStrengthS.equals("F"))
                            cargoStrength = fstrength;
                        else if (cargoStrengthS.equals("M"))
                            cargoStrength = mstrength;
                        else if(cargoStrengthS.equals("S"))
                            cargoStrength = sstrength;
                        else
                            throw new IllegalArgumentException();
                        if(dockSize != 0){
                            switch(cargoStrengthS){
                                case("F"):
                                    break;
                                case("M"):
                                    if(dock.peek().getStrength().equals(fstrength))
                                        throw new CargoStrengthException("Error. Existing cargo cannot support.");break;
                                case("S"):
                                    if(!(dock.peek().getStrength().equals(sstrength)))
                                        throw new CargoStrengthException("Error. Existing cargo cannot support.");break;
                            }
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println("Error. Invalid selection.");break;
                    }
                    catch(CargoStrengthException e){
                        System.out.println(e.getMessage());break;
                    }
                    newCargo = new Cargo(cargoName, cargoWeight, cargoStrength);
                    dock.push(newCargo);
                    System.out.println("Cargo '" + newCargo.getName() + "' has been pushed onto the dock.");
                    dockSize += 1;
                    break;
                case("L"):
                    try {
                        if (dockSize == 0)
                            throw new EmptyStackException("Error. Dock is empty");
                    }
                    catch(EmptyStackException e){
                        System.out.println(e.getMessage());break;
                    }
                    System.out.println("Select the load destination stack index: ");
                    try {
                        load = input.nextInt();
                        input.nextLine();
                        if(load < 1 || load > numStacks)
                            throw new IllegalArgumentException();
                        if(ship.counter[load-1] == numStacks)
                            throw new FullStackException("Error. Stack is full.");
                        if(ship.counter[load-1] > 0) {
                            temp = (Cargo) ship.stacks[load - 1].peek();
                            if (dock.peek().getStrength().equals(mstrength) && temp.getStrength().equals(fstrength))
                                throw new CargoStrengthException("Error. Existing cargo cannot support.");
                            if (dock.peek().getStrength().equals(sstrength) && !(temp.getStrength().equals(sstrength)))
                                throw new CargoStrengthException("Error. Existing cargo cannot support.");
                            if(totalWeight + temp.getWeight() > maxWeight)
                                throw new ShipOverweightException("Error. Cargo ship cannot support weight.");
                        }
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error. Invalid range.");break;
                    }
                    catch(FullStackException e){
                        System.out.println(e.getMessage());break;
                    }
                    catch(CargoStrengthException e){
                        System.out.println(e.getMessage());break;
                    }
                    catch (ShipOverweightException e){
                        System.out.println(e.getMessage());break;
                    }
                    ship.pushCargo(dock.peek(), load);
                    dock.pop();
                    dockSize -= 1;
                    Cargo first = (Cargo)ship.stacks[load-1].peek();
                    System.out.println("Cargo '" + first.getName() + "' moved from dock to stack " + load + ".");
                    totalWeight += first.getWeight();
                    break;
                case("U"):
                    System.out.println("Select the unload source stack index: ");
                    try {
                        unload = input.nextInt();
                        if(unload < 1 || unload > numStacks)
                            throw new IllegalArgumentException();
                        else if(ship.counter[load-1] == 0)
                            throw new EmptyStackException("Error. Stack is empty.");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error. Invalid range.");break;
                    }
                    catch (EmptyStackException e){
                        System.out.println(e.getMessage());break;
                    }
                    temp = (Cargo)ship.stacks[unload-1].peek();
                    try{
                        if(temp.getStrength().equals(mstrength) && dock.peek().getStrength().equals(fstrength))
                            throw new CargoStrengthException("Error. Existing cargo cannot support.");
                        else if(temp.getStrength().equals(sstrength) && !(dock.peek().getStrength().equals(sstrength)))
                            throw new CargoStrengthException("Error. Existing cargo cannot support.");
                    }catch(CargoStrengthException e){
                        System.out.println(e.getMessage());break;
                    }
                    input.nextLine();
                    dock.push(ship.popCargo(unload));
                    dockSize += 1;
                    System.out.println("Cargo '" + temp.getName() + "' moved from stack " + unload + " to dock.");
                    totalWeight -= temp.getWeight();
                    break;
                case("M"):
                    System.out.println("Source stack index: ");
                    try {
                        unload = input.nextInt();
                        if(unload < 1 || unload > numStacks)
                            throw new IllegalArgumentException();
                        if(ship.counter[unload-1] == 0)
                            throw new EmptyStackException("Error. Stack is empty.");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error. Invalid range.");break;
                    }
                    catch (EmptyStackException e){
                        System.out.println(e.getMessage());break;
                    }
                    input.nextLine();
                    System.out.println("Destination stack index: ");
                    try {
                        load = input.nextInt();
                        if(load < 0 || load > numStacks)
                            throw new IllegalArgumentException();
                        else if(ship.counter[load-1] == numStacks)
                            throw new FullStackException("Error. Stack is full.");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error. Invalid range.");break;
                    }
                    catch(FullStackException e){
                        System.out.println(e.getMessage());break;
                    }
                    input.nextLine();
                    Cargo temp1 = (Cargo)ship.stacks[unload-1].peek();
                    Cargo temp2 = (Cargo)ship.stacks[load-1].peek();
                    try{
                        if(temp1.getStrength().equals(mstrength) && temp2.getStrength().equals(fstrength))
                            throw new CargoStrengthException("Error. Existing cargo cannot support.");
                        else if(temp1.getStrength().equals(sstrength) && !(temp2.getStrength().equals(sstrength)))
                            throw new CargoStrengthException("Error. Existing cargo cannot support.");
                    }catch (CargoStrengthException e){
                        System.out.println(e.getMessage());break;
                    }
                    ship.pushCargo(ship.popCargo(unload), load);
                    System.out.println("Cargo '" + temp1.getName() + "' moved from stack " + unload + " to stack " + load);
                    break;
                case("K"):
                    for(int k = dockSize; k>0;k--)
                        dock.pop();
                    System.out.println("Dock cleared.");
                    dockSize = 0;
                    break;
                case("P"):
                    System.out.println(ship.toString());
                    String type = "";
                    System.out.println(" Dock:\n");
                    for(int i = dockSize-1; i >= 0; i--){
                        temp = ship.getCargo(dock, i);
                        if(temp.getStrength().equals(Cargo.CargoStrength.FRAGILE))
                            type = "F";
                        else if(temp.getStrength().equals(Cargo.CargoStrength.MODERATE))
                            type = "M";
                        else
                            type = "S";
                        System.out.println("   " + type + "   ");
                    }
                    System.out.println("|=====|");
                    System.out.println("Total weight on ship: " + totalWeight + "/" + maxWeight);
                    break;
                case("S"):
                    System.out.println("Enter name of cargo: ");
                    cargoName = input.nextLine();
                    ship.findAndPrint(cargoName);
                    break;
                case("Q"):
                    exit = true;
                    break;
            }
            System.out.println(ship.toString());
            String type = "";
            System.out.println(" Dock:\n");
            for(int i = dockSize-1; i >= 0; i--){
                temp = ship.getCargo(dock, i);
                if(temp.getStrength().equals(Cargo.CargoStrength.FRAGILE))
                    type = "F";
                else if(temp.getStrength().equals(Cargo.CargoStrength.MODERATE))
                    type = "M";
                else
                    type = "S";
                System.out.println("   " + type + "   ");
            }
            System.out.println("|=====|");
            System.out.println("Total weight on ship: " + totalWeight + "/" + maxWeight);
            System.out.println();
        }
    }
}
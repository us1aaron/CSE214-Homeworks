/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 3
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the CargoShip class
 * int numStacks is the number of stacks the ship will have
 * int maxHeight is the max number of Cargo objects on each stack
 * double maxWeight is the max weight the ship will allow
 * Stack[] stacks is an array of stacks with size numStacks
 * int[] counter is an array that will count the number of Cargo objects in each stack
 */
import java.util.Stack;
public class CargoShip{
    private int numStacks;
    private int maxHeight;
    private double maxWeight;
    public Stack[] stacks;
    public int[] counter;
    public Stack<Cargo> tempStack = new Stack<>();

    /**
     * Constructor for CargoShip object
     * @param numStacks
     * Number of stacks the ship will hold
     * @param initMaxHeight
     * Max number of Cargo objects each stack will hold
     * @param initMaxWeight
     * Max number of weight the whole ship will hold
     * Precondition:
     * numStacks > 0
     * maxHeight > 0
     * maxWeight > 0
     * Postcondition:
     * This object has been initialized to a CargoShip object with the indicated number of stacks, maxHeight, and maxWeight
     * @exception
     * throws IllegalArgumentException when any of the parameters are less than 0
     */
    public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight){
        stacks = new Stack[numStacks];
        counter = new int[numStacks];
        this.numStacks = numStacks;
        this.maxHeight = initMaxHeight;
        this.maxWeight = initMaxWeight;
        for(int i=0; i<numStacks; i++){
            Stack<Cargo> newStack = new Stack<>();
            stacks[i] = newStack;
            counter[i] = 0;
        }
    }

    /**
     * Method that will attempt to add a Cargo to a stack
     * @param cargo
     * Cargo objecct to be added to stack
     * @param stack
     * Index for stack that cargo will be moved to
     * Precondition:
     * This CargoShip is initialized and not null
     * Cargo is initialized and not null
     * 1 ≤ stack ≤ stacks.length
     * The size of the stack at the desired index is less than maxHeight
     * The total weight of all Cargo on the ship and cargo.getWeight()is less than maxWeight
     * Postcondition:
     * The cargo has been successfully pushed to the given stack, or the appropriate exception has been thrown,
     * in which case the state of the cargo ship should remain unchanged
     * @exception
     * throws IllegalArgumentException when Cargo is null or stack is not in valid bounds
     * throws FullStackException when the stack is being pushed to the max height
     * throws ShipOverweightException when the cargo being added would exceed the maxweight
     * throws CargoStrengthException when the cargo would be stacked ontop of a weaker Cargo object
     */
    public void pushCargo(Cargo cargo, int stack){
        stacks[stack-1].push(cargo);
        counter[stack-1] += 1;
    }

    /**
     * Method that will attempt to remove Cargo object from top of stack
     * @param stack
     * Index for stack that Cargo will be removed
     * Precondition:
     * The cargo has been successfully been popped from the stack, and returned, or the appropriate exception
     * has been thrown, in which case the state of the cargo ship should remain unchanged
     * @return
     * Cargo object that is removed from the stack
     * @exception
     * throws IllegalArgumentException when stack index is not in valid range
     * throws EmptyStackException when stacked being popped from is empty
     */
    public Cargo popCargo(int stack){
        Cargo delete = (Cargo)stacks[stack-1].peek();
        counter[stack - 1] -= 1;
        stacks[stack-1].pop();
        return delete;
    }

    /**
     * Finds and prints a formatted table of all the cargo on the ship with a given name.
     * If the item could not be found in the stacks, notify the user accordingly
     * @param name
     * Name to search the stacks on the ship for.
     * Predondition:
     * This CargoShip is initialized and not null
     * Postcondition:
     * The details of the cargo with the given name have been found and printed, or the user has been notified
     * that no such cargo has been found
     * The state of the cargo ship should remain unchanged
     */
    public void findAndPrint(String name){
        int findCount = 0;
        double findWeight = 0.0;
        String table = "";
        table += String.format("%-7s%-7s%-8s%-10s", " Stack ", "  Depth ", "  Weight ", "  Strength ");
        table += "\n=======+=======+========+==========\n";
        for(int i = 0; i < numStacks; i++){
            for(int k = 0; k < counter[i]; k++){
                Cargo temp = getCargo(stacks[i], k);
                if(temp.getName().equals(name)){
                    table += String.format("%-7d", i+1) + " ";
                    table += String.format("%-7d", k+1) + " ";
                    table += String.format("%-7f", temp.getWeight()) + " ";
                    table += String.format("%-10s", temp.getStrength());
                    table += "\n";
                    findCount++;
                    findWeight += temp.getWeight();
                }
            }
        }
        table = table + "Total Count: " + findCount + "\n";
        table = table + "Total Weight: " + findWeight + "\n";
        System.out.println(table);
    }

    /**
     * String representation of the cargoship
     * @return
     * A string representation of the Cargo objects on the cargo ship and dock
     */
    public String toString(){
        String table = "";
        String type;
        for(int i = maxHeight; i >0; i--){
            table += " ";
            for(int k = 0; k < numStacks; k++){
                if(counter[k] >= i){
                    Cargo temp = getCargo(stacks[k], i-1);
                    if(temp.getStrength().equals(Cargo.CargoStrength.FRAGILE))
                        type = "F";
                    else if(temp.getStrength().equals(Cargo.CargoStrength.MODERATE))
                        type = "M";
                    else
                        type = "S";
                }
                else
                    type = " ";
                table = table + "  " + type + "   ";
            }
            table += "\n";
        }
        table += "|";
        for(int h = 0; h < numStacks; h++){
            table += "=====|";
        }
        table += "\n";
        for(int h = 1; h < numStacks+1; h++){
            table += "|  " + h + "  ";
        }
        table += "|\n";
        return table;
    }

    /**
     * Finds cargo in a stack at a given index
     * @param stack
     * The stack being searched
     * @param index
     * Te index of the stack that user wants to retreive
     * @return
     * Cargo object at a stack at a given index
     */
    public Cargo getCargo(Stack stack, int index){
        Cargo hold;
        for(int i = 0;i<index;i++){
            tempStack.push((Cargo)stack.pop());
        }
        hold = (Cargo)stack.peek();
        for(int i = 0;i<index;i++){
            stack.push(tempStack.pop());
        }
        return hold;
    }
}

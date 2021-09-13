/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 3
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the Cargo Class
 * String name will be the name of the Cargo
 * Double weight will be the weight of ther Cargo
 * CargoStrength strength will be the strength of the Cargo
 * This Cargo objects will be used in the CargoShip class
 */
public class Cargo {
    private String name;
    private double weight;
    CargoStrength strength;

    /**
     * CargoStrength type variable
     * variables with this type can be FRAGILE, MODERATE, or STURDY
     */
    public enum CargoStrength{
        FRAGILE,
        MODERATE,
        STURDY
    }

    /**
     * Constructor for Cargo object
     * @param initname
     * name of Cargo object
     * @param initweight
     * weight of Cargo object
     * @param initstrength
     * strength of Cargo object
     * Precondition:
     * initname is not null
     * initweight > 0
     * Postcondition:
     * This object has been initialized to a Cargo object with the given name, weight, and strength
     * @exception
     * throws IllegalArgumentException when initname is null or initweight <= 0
     */
    public Cargo(String initname, double initweight, CargoStrength initstrength){
        this.name = initname;
        this.weight = initweight;
        this.strength = initstrength;
    }

    public String getName(){
        return this.name;
    } //getter method for name of cargo
    public double getWeight(){
        return this.weight;
    } //getter method for weight of cargo
    public CargoStrength getStrength(){
        return this.strength;
    } //getter method for strength of cargo
}
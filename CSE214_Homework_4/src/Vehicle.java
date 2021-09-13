/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 4
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the Vehicle Class
 * serialID is a unique number assigned to a new vehicle for identification
 * timeArrived is the time step when the vehicle is created
 */
public class Vehicle {
    private static int serialCounter = 0;
    private int serialID;
    private int timeArrived;

    /**
     * Default Constructor. You should automatically increment the serialCounter, and set the serialId to its new value
     * @param initTimeArrived
     * Time the vehicle arrived at the intersection
     * Precondition:
     * initTimeArrived > 0
     * @exception
     * throws IllegalArguemntException if initTimeArrived <= 0
     */
    public Vehicle(int initTimeArrived){
        timeArrived = initTimeArrived;
        this.serialID = Intersection.totalCarCount + 1;
    }

    public int getSerialID(){ //getter method for serialID
        return this.serialID;
    }
    public int getTimeArrived(){ //getter method for timeArrived
        return this.timeArrived;
    }
}
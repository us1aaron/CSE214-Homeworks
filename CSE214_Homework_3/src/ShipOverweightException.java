/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 3
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 *Exception class for when the weight limit of ship would be exceeded
 */
public class ShipOverweightException extends Exception{ //custom exception to check if team is full
    public ShipOverweightException(String errorMessage){
        super(errorMessage);
    }
}

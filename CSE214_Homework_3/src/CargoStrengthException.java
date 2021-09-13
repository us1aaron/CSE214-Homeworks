/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 3
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 *Exception class for when user violates CargoStrength rules
 */
public class CargoStrengthException extends Exception{ //custom exception to check if team is full
    public CargoStrengthException(String errorMessage){
        super(errorMessage);
    }
}

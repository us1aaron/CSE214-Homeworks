/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 3
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 *Exception class for when user attempts to pop a CargoStack with no Cargo
 */
public class EmptyStackException extends Exception{ //custom exception to check if team is full
    public EmptyStackException(String errorMessage){
        super(errorMessage);
    }
}

/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 2
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 *Exception class for when trying to reference after tail
 */
public class EndOfListException extends Exception{ //custom exception to check if team is full
    public EndOfListException(String errorMessage){
        super(errorMessage);
    }
}
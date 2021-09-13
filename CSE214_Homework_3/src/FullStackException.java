/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 3
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 *Exception class for when the height limit would be exceeded
 */
public class FullStackException extends Exception{ //custom exception to check if team is full
    public FullStackException(String errorMessage){
        super(errorMessage);
    }
}

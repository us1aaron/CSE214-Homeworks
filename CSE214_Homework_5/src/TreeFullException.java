/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 5
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * Exception for when all three child nodes are full
 */
public class TreeFullException extends Exception{
    public TreeFullException(String errorMessage){
        super(errorMessage);
    }
}

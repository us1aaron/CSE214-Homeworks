/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 5
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 *Exception class with node is not found
 */
public class NodeNotPresentException extends Exception{
    public NodeNotPresentException(String errorMessage){
        super(errorMessage);
    }
}

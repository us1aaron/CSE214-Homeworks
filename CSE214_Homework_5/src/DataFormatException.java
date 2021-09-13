/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 5
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * Exception class for when data format does not match
 */
public class DataFormatException extends Exception{
    public DataFormatException(String errorMessage){
        super(errorMessage);
    }
}

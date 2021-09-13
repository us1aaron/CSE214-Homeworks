/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 1
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 *Exception class for when team reaches capacity of 40 players
 */
public class FullTeamException extends Exception{ //custom exception to check if team is full
    public FullTeamException(String errorMessage){
            super(errorMessage);
    }
}
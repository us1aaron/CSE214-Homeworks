/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 1
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the Player Class.
 * Player objects will be created which will contain
 * String name for the name of the Player object.
 * int numHits for the number of hits made by Player object.
 * int numErrors for the number of errors made by Player object.
 * These Player objects will be apart of the Team class which will be an array of Player objects.
 */
public class Player{
    private String name; //name of player
    private int numHits; //number of hits by player
    private int numErrors; //number of errors by player

    /**
     * Player constructor
     * @param name
     *      name of player
     * @param numHits
     *      number of hits by player
     * @param numErrors
     *  number of errors by player
     *
     */
    public Player(String name, int numHits, int numErrors){
        this.name = name;
        this.numHits = numHits;
        this.numErrors = numErrors;
    }

    public void setName(String name){ //setter for player name
        this.name = name;
    }
    public void setNumHits(int numHits){ //setter for player hits
        this.numHits = numHits;
    }
    public void setNumErrors(int numErrors){ //setter for player errors
        this.numErrors = numErrors;
    }

    public String getName(){ //getter for player name
        return this.name;
    }
    public int getNumHits(){ //getter for player hits
        return this.numHits;
    }
    public int getNumErrors(){ //getter for player errors
        return this.numErrors;
    }

    /**
     * Compares this Player object to another for equality
     * @param a
     * Player a which this Player is being compared to
     * @return
     * Returns true if both are equal and false if otherwise
     */
    public boolean equals(Player a){
        return ((this.getName().equals(a.getName())) && (this.getNumHits() == a.getNumHits()) && (this.getNumErrors() == a.getNumErrors()));
    }

    /**
     * String representation of the Player object
     * @return
     * Returns a formatted string representation of the Player object
     */
    public String toString(){ //string representation of player class
        return name + "  -  " + numHits + " hits, " + numErrors + " errors";
    }
}



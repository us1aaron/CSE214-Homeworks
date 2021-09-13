/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 4
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the BooleanSource class
 * This class will determine if a new care will be enqueued
 * Generates a random number and checks if it is in probability range
 */

import java.util.Random;
public class BooleanSource {
    public double probability;

    /**
     * Constructor which initializes the probability to the indicated parameter
     * @param initProb
     * Probability used to construct this BooleanSource object
     * Precondition:
     * 0 < initProb <= 1
     * @exception
     * throws IllegalArguemntException if initProb <= 0 or initProb > 1
     */
    public BooleanSource(double initProb){
        this.probability = initProb;
    }

    /**
     * Method which returns true with the probability indicated by the member variable probability
     * Precondition:
     * probability is a valid probability (0 < probability <= 1)
     * @return
     * Boolean value indicating whether an event has occurred or not
     */
    public boolean occurs(){
        double temp = Math.random();
        if(temp>=0 && temp<probability)
            return true;
        return false;
    }
     public void setProb(double newProb){ //setter method for probability
        this.probability = newProb;
     }
     public double getProb(){ //getter method for probability
        return this.probability;
     }
}

/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 7
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the AppraochDateComparator class
 * it implements Comparater<NearEarthObject>
 * it sorts an arraylist of NEarEarthObjects based on its date
 */

import java.util.Comparator;
public class ApproachDateComparator implements Comparator<NearEarthObject>{
    public int compare(NearEarthObject left, NearEarthObject right){
        NearEarthObject one = (NearEarthObject) left;
        NearEarthObject two = (NearEarthObject) right;
        return one.getClosestApproachDate().compareTo(two.getClosestApproachDate());
    }
}

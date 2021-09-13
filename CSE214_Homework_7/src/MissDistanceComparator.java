/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 7
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the MissDistanceComparator class
 * it implements Comparater<NearEarthObject>
 * it sorts an arraylist of NearEarthObjects based on its miss distance
 */

import java.util.Comparator;
public class MissDistanceComparator implements Comparator<NearEarthObject>{
    public int compare(NearEarthObject left, NearEarthObject right){
        NearEarthObject one = (NearEarthObject) left;
        NearEarthObject two = (NearEarthObject) right;
        if(one.getMissDistance() == two.getMissDistance())
            return 0;
        else if(one.getMissDistance() > two.getMissDistance())
            return 1;
        else
            return -1;
    }
}

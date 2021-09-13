/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 7
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the DiameterComparator class
 * it implements Comparater<NearEarthObject>
 * it sorts an arraylist of NearEarthObjects based on its diameter
 */

import java.util.Comparator;
public class DiameterComparator implements Comparator<NearEarthObject>{
    public int compare(NearEarthObject left, NearEarthObject right){
        NearEarthObject one = (NearEarthObject) left;
        NearEarthObject two = (NearEarthObject) right;
        if(one.getAverageDiameter() == two.getAverageDiameter())
            return 0;
        else if(one.getAverageDiameter() > two.getAverageDiameter())
            return 1;
        else
            return -1;
    }
}

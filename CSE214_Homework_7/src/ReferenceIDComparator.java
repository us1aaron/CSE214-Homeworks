/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 7
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the ReferenceIDComparat0r class
 * it implements Comparater<NearEarthObject>
 * it sorts an arraylist of NearEarthObects based on their referenceID
 */

import java.util.Comparator;
public class ReferenceIDComparator implements Comparator<NearEarthObject>{
    public int compare(NearEarthObject left, NearEarthObject right){
        NearEarthObject one = (NearEarthObject) left;
        NearEarthObject two = (NearEarthObject) right;
        if(one.getReference_ID() == two.getReference_ID())
            return 0;
        else if(one.getReference_ID() > two.getReference_ID())
            return 1;
        else
            return -1;
    }
}

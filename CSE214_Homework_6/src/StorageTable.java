/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 6
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the StorageTable class
 * Here the Storage objects will be stored away
 */

import java.util.HashMap;
public class StorageTable extends HashMap {
    public static long serialVersionUID;
    public StorageTable(){}
    HashMap<Integer, Storage> table = new HashMap<Integer, Storage>();

    /**
     * Manually inserts a Storage object into the table using the specified key
     * @param storageId
     * unique key for the Storage object
     * @param storage
     * Storage object to insert into the table
     * Precondition:
     * storageId >= 0 and does not already exist in the table
     * storage != null
     * Postcondition:
     * storage has been inserted into the table with the indicated key
     * @exception
     * throws IllegalArgumentException if storageId == null or already exists in the table
     */
    public void putStorage(int storageId, Storage storage){
        table.put(storageId, storage);
    }

    /**
     * Retrieve the Storage from the table having the indicated storageID.
     * If the requested storageID does not exist in the StorageTable, return null
     * @param storageID
     * Key of the Storage to retrieve from the table
     * @return
     * storage object with the indicated key
     * null if no object found
     */
    public Storage getStorage(int storageID){
        return table.get(storageID);
    }
}

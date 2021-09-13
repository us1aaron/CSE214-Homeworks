/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 4
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the VehicleQueue Class
 * This class utilizes arraylists to simulate a queue
 * lane is an arraylist which holds vehicle obejcts
 */

import java.util.ArrayList;
public class VehicleQueue{
    ArrayList<Vehicle> lane;
    public VehicleQueue(){
        lane = new ArrayList<Vehicle>();
    }
    /**
     * Enqueues vehicle into lane
     * @param vehicle
     * vehicle object to be added into the lane
     * Postcondition:
     * vehicle has been added to lane
     */
    public void enqueue(Vehicle vehicle){
        lane.add(vehicle);
    }

    /**
     * Dequeues vehicle from lane
     * @return temp
     * temp is the vehicle that was just removed
     * Postcondition:
     * front vehicle is removed and all others move up the queue
     */
    public Vehicle dequeue(){
        Vehicle temp = lane.get(0);
        lane.remove(0);
        return temp;
    }
    public int size(){
        return lane.size();
    } //returns size of the lane
    public boolean isEmpty(){
        return lane.isEmpty();
    } //checks if the lane is emepty
}

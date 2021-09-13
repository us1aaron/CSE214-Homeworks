/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 4
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**\
 * This is the Intersection class
 * roads is an array of TwoWayRoad objects
 * lightIndex is the roads index where the light is green
 * This class manages all roads at the intersection
 */
import java.util.ArrayList;
public class Intersection {
    public TwoWayRoad[] roads;
    private int lightIndex = 0;
    private int countdownTimer;
    public static int totalCarPass = 0;
    public static int totalCarCount = 0;
    public static int totalWaitTime = 0;

    /**
     * Constructor for Intersection object
     * @param initRoads
     * Array of roads to be used by this intersection
     * Preconditions:
     * initRoads != null
     * length of initRoadsis <= MAX_ROADS
     * all indices of initRoads are not null
     * Postcondition:
     * This object has been initialized to a Intersection object managing the roads array
     * @exception
     * throws IllegalArgumentException if initRoads == null or initRoads.length>MAX_ROADS
     */
    public Intersection(TwoWayRoad[] initRoads){
        this.roads = initRoads;
        this.countdownTimer = roads[lightIndex].getGreenTime();
    }

    /**
     * performs a single iteration through the intersection
     * @return
     * array of vehciles that have passed through intersection during this time step
     * Precondition:
     * The intersection has dequeued all lanes with a green light
     */
    public Vehicle[] timeStep(){
        ArrayList<Vehicle> temp = new ArrayList<Vehicle>();
        Vehicle[] a;
        a = roads[lightIndex].proceed(countdownTimer);
        for(int k = 0; k < a.length; k++){
            temp.add(a[k]);
        }
        if(roads[lightIndex].getLightValue() == TwoWayRoad.LightValue.RED){
            roads[lightIndex].setLightValue(TwoWayRoad.LightValue.GREEN);
            if(lightIndex<roads.length-1)
                lightIndex++;
            else if(lightIndex == roads.length-1)
                lightIndex = 0;
            countdownTimer = roads[lightIndex].getGreenTime();
        }
        Vehicle[] list = new Vehicle[temp.size()];
        for(int j=0; j<temp.size(); j++){
            list[j] = temp.get(j);
        }
        countdownTimer--;
        return list;
    }

    /**
     * enqueues a vehicle onto a lane in the intersection
     * @param roadIndex
     * road index that vehicle will be enqueued
     * @param wayIndex
     * direction of road vehicle will be enqueued
     * @param laneIndex
     * lane of direction vehicle will be enquued
     * @param vehicle
     * vehcile object to be enqueued
     * Preconditions:
     * 0 <= roadIndex < roads.length.
     * 0 <= wayIndex < TwoWayRoad.NUM_WAYS.
     * 0 <= laneIndex < TwoWayRoad.NUM_LANES.
     * vehicle != null.
     * @exception
     * throws IllegalArgumentException if vehicle == null or any preconditions are violated
     *
     */
    public void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex, Vehicle vehicle){
        roads[roadIndex].enqueueVehicle(wayIndex,laneIndex,vehicle);
    }
    public void display(){}
    /**
     * toString will display the cars passing and info for current step
     * @return
     * string representation of passing vehicles
     */
    public String toString(int roadIndex){
        String print = "";
        print += roads[roadIndex].getName() + ":\n";
        print += "                       FORWARD            BACKWARD\n";
        print += "==============================            ==============================\n";
        for (int j = 0; j < 3; j++) {
            //FORWARD
            for (int k = 0; k < 6-roads[roadIndex].getLanes()[0][j].size(); k++) {
                print += "     ";
            }
            for(int m=roads[roadIndex].lanes[0][j].size()-1; m>=0;m--){
                print += "[";
                if(roads[roadIndex].lanes[0][j].lane.get(m).getSerialID()<10)
                    print += "00";
                else if(roads[roadIndex].lanes[0][j].lane.get(m).getSerialID()<100)
                    print += "0";
                print += roads[roadIndex].lanes[0][j].lane.get(m).getSerialID() + "]";
            }
            if(j == 0)
                print += "[L] ";
            else if(j==1)
                print += "[M] ";
            else if(j ==2)
                print += "[R] ";
            if(roads[roadIndex].getLightValue().equals(TwoWayRoad.LightValue.GREEN) && (j == 1 || j == 2) && (roadIndex == lightIndex))
                print += "  ";
            else if(roads[roadIndex].getLightValue().equals(TwoWayRoad.LightValue.LEFT_SIGNAL) && (j == 0) && (roadIndex == lightIndex))
                print += "  ";
            else
                print += "x ";
            //BACKWARD
            if(roads[roadIndex].getLightValue().equals(TwoWayRoad.LightValue.GREEN) && (j == 0 || j == 1)&& (roadIndex == lightIndex))
                print += "  ";
            else if(roads[roadIndex].getLightValue().equals(TwoWayRoad.LightValue.LEFT_SIGNAL) && (j == 2 )&& (roadIndex == lightIndex))
                print += "  ";
            else
                print += " x";
            if(j == 0)
                print += " [R]";
            else if(j==1)
                print += " [M]";
            else if(j ==2)
                print += " [L]";
            for(int m=0; m<roads[roadIndex].lanes[1][2-j].size();m++){
                print += "[";
                if(roads[roadIndex].lanes[1][2-j].lane.get(m).getSerialID()<10)
                    print += "00";
                else if(roads[roadIndex].lanes[1][2-j].lane.get(m).getSerialID()<100)
                    print += "0";
                print += roads[roadIndex].lanes[1][2-j].lane.get(m).getSerialID() + "]";
            }
            for (int k = 0; k < 6-roads[roadIndex].lanes[0][j].size(); k++) {
                print += "     ";
            }
            print += "\n";
        }
        return print;
    }

    public int getNumRoads(){ //getter method of number of roads
        return roads.length;
    }
    public int getLightIndex(){ //getter method for the current lightIndex
        return lightIndex;
    }
    public int getCountdownTimer(){ //getter method for CountDownTimer
        return countdownTimer;
    }
    public TwoWayRoad.LightValue getCurrentLightValue(){ //getter method for CurrentLightValue
        return roads[lightIndex].lightValue;
    }
}

/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 4
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the TwoWayRoach class
 * enum LightValue is the current light value of the road
 * A TwoWayRoacd is a 2D array that consists of a road going forward
 *   and a road going backwards. Each direction has 3 lanes. Left lane,
 *   middle lane, and right lane.
 * greenTime is the amount of steps the light on the road will be green/left_signal for
 * leftSignalGreenTime is the amount of steps the light will be yellow
 */
public class TwoWayRoad {
    public enum LightValue{
        GREEN,
        RED,
        LEFT_SIGNAL,
    }
    public final int FORWARD_WAY = 0;
    public final int BACKWARD_WAY = 1;
    public static final int NUM_WAYS = 2;
    public final int LEFT_LANE = 0;
    public final int MIDDLE_LANE = 1;
    public final int RIGHT_LANE = 2;
    public static final int NUM_LANES = 3;
    public int laneCount = 0;
    private String name;
    private int greenTime;
    private int leftSignalGreenTime;
    LightValue lightValue = LightValue.GREEN;
    public VehicleQueue[][] lanes = new VehicleQueue[NUM_WAYS][NUM_LANES];
    public VehicleQueue[][] getLanes(){
        return lanes;
    }
    /**
     * Default Constructor for TwoWayRoad object
     * @param initName
     * name of the road
     * @param initGreenTime
     * the amount of time the light on this road will be GREEN/LEFT_SIGNAL
     * Precondition:
     * initGreenTime > 0
     * Postcondition:
     * This road is initialized with all lanes initialized to empty queues, and all instance variables initialized
     * @exception
     * throws IllegalArgumentException if initGreenTime <= 0 or initName=null
     */
    public TwoWayRoad(String initName, int initGreenTime){
        this.name = initName;
        this.greenTime = initGreenTime;
        this.leftSignalGreenTime = (int)Math.floor(1.0/NUM_LANES * initGreenTime);
        if(this.leftSignalGreenTime == 0)
            this.leftSignalGreenTime = 1;
        for(int i=0; i<NUM_WAYS;i++){
            for(int k=0;k<NUM_LANES; k++){
                lanes[i][k] = new VehicleQueue();
            }
        }
    }

    /**
     * Executes the passage of time in the simulation
     * @param timerVal
     * the current timer value
     * Precondition:
     * The TwoWayRoad object should be instantiated
     * Postcondition:
     * Dequeued vehicles are placed into an array
     * @return
     * An array of vehicles dequeued during this time step
     * @exception
     * throws IllegalArgumentException if timerval <=0
     */
    public Vehicle[] proceed(int timerVal){
        int total = 0;
        boolean check1 = true, check2 = true;
        int count;
        Vehicle[] list;
        for (int i = 0; i < NUM_WAYS; i++) {
            for (int k = 0; k < NUM_LANES; k++) {
                if (!isLaneEmpty(i, k) && k!=0)
                    check1 = false;
                if (!isLaneEmpty(i, k) && k==0)
                    check2 = false;
                if(!isLaneEmpty(i, k))
                    laneCount++;
            }
        }
        list = new Vehicle[laneCount * 4];
        if(check1 || timerVal <= leftSignalGreenTime) {
            lightValue = LightValue.LEFT_SIGNAL;
            timerVal = leftSignalGreenTime;
        }
        if(lightValue == LightValue.GREEN) {
            for (int i = 0; i < NUM_WAYS; i++) {
                for (int k = 1; k < NUM_LANES; k++) {
                    count = 0;
                    while(!isLaneEmpty(i, k) && count != 4) {
                        list[total] = lanes[i][k].dequeue();
                        IntersectionSimulator.passPrint += IntersectionSimulator.passPrinter(i, k, list[total]);
                        Intersection.totalWaitTime += (IntersectionSimulator.totalTime - list[total].getTimeArrived());
                        IntersectionSimulator.passPrint += (IntersectionSimulator.totalTime - list[total].getTimeArrived()) + "\n";
                        Intersection.totalCarPass++;
                        count++;
                        total++;
                    }
                }
            }
        }
        count = 0;
        if(check2 || timerVal == 0) {
            lightValue = LightValue.RED;
            timerVal = 0;
        }
        if(lightValue == LightValue.LEFT_SIGNAL){
            for(int i = 0; i < NUM_WAYS; i++){
                count = 0;
                while(!isLaneEmpty(i,0) && count != 4){
                    list[total] = lanes[i][0].dequeue();
                    IntersectionSimulator.passPrint += IntersectionSimulator.passPrinter(i, 0, list[total]);
                    Intersection.totalWaitTime += (IntersectionSimulator.totalTime - list[total].getTimeArrived());
                    IntersectionSimulator.passPrint += (IntersectionSimulator.totalTime - list[total].getTimeArrived()) + "\n";
                    Intersection.totalCarPass++;
                    count++;
                    total++;
                }
            }
        }
        count = 0;
        return list;
    }

    /**
     * Checks if lane is empty
     * @param wayIndex
     * direction of lane
     * @param laneIndex
     * index of lane
     * Precondition:
     * The TwoWayRoad object should be instantiated
     * Postcondition:
     * The TwoWayRoad object should remain unchanged
     * @return
     * true if lane is empty and false if otherwise
     * @exception
     * throws IllegalArgumentException if wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2
     */
    public boolean isLaneEmpty(int wayIndex, int laneIndex){
        return lanes[wayIndex][laneIndex].isEmpty();
    }

    /**
     * Enqueues a vehicle into a the specified lane
     * @param wayIndex
     * the direction the car is going
     * @param laneIndex
     * the lane the car arrives in
     * @param vehicle
     * the vehicle to be enqueued
     * Precondition:
     * The TwoWayRoad object should be instantiated
     * Postcondition:
     * vehicle is enqueued
     * @exception
     * throws IllegalArgumentException if wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2 or vehicle==null
     */
    public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle){
        lanes[wayIndex][laneIndex].enqueue(vehicle);
    }

    public int getGreenTime(){ //getter method for greenTime
        return this.greenTime;
    }
    public LightValue getLightValue(){ //getter method for lightValue
        return this.lightValue;
    }
    public void setLightValue(LightValue lightValue){
        this.lightValue = lightValue;
    }
    public String getName(){ //getter method for name
        return this.name;
    }
    public int getLeftSignalGreenTime(){ //getter method for leftSignalGreenTime
        return this.leftSignalGreenTime;
    }
}

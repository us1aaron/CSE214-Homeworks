/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 4
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is  the IntersectionSimulator class
 * The user will be prompted to enter values to create an intersection of roads
 * This will simulate the arrival and departure of vehicles at the intersection
 * This will automatically run and end
 */

import java.util.Scanner;
import java.util.ArrayList;
public class IntersectionSimulator {
    public static int totalTime = 1;
    public static int simTime, numStreet = 0;
    public static double carProb;
    public static String[] names;
    public static int[] greenTimes;
    public static Intersection intersection;
    public static String passPrint = "";
    public static boolean exit = false;
    public static void main(String args[]){
        TwoWayRoad[] roadList;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Intersection Simulator 2021\n");
        System.out.println("Input the time simulation:");
        simTime = input.nextInt();
        System.out.println("Input the arrival probability:");
        try {
            carProb = input.nextDouble();
            if(carProb <= 0 || carProb>1)
                throw new IllegalArgumentException();
        }catch(IllegalArgumentException e){
            System.out.println("Error. Invalid range.");
        }
        System.out.println("Input number of streets:");
        numStreet = input.nextInt();
        input.nextLine();
        names = new String[numStreet];
        greenTimes = new int[numStreet];
        roadList = new TwoWayRoad[numStreet];
        for(int i = 1; i<=numStreet; i++){
            System.out.println("Input Street " + i + " name:");
            try {
                names[i - 1] = input.nextLine();
                if(names[i-1] == null)
                    throw new IllegalArgumentException();
            }catch(IllegalArgumentException e){
                System.out.println("Error. No name.");
            }
        }
        for(int k = 0; k<numStreet; k++){
            System.out.println("Input max green time for " + names[k] + ":");
            try {
                greenTimes[k] = input.nextInt();
                if(greenTimes[k] <= 0)
                    throw new IllegalArgumentException();
            }catch(IllegalArgumentException e){
                System.out.println("Error. Invalid range.");
            }
        }
        for(int j=0; j<numStreet; j++){
            TwoWayRoad newStreet = new TwoWayRoad(names[j], greenTimes[j]);
            roadList[j] = newStreet;
        }
        intersection = new Intersection(roadList);
        simulate(simTime, carProb, names, greenTimes);
    }
    public static void simulate(int simulationTime, double arrivalProb, String[] roadNames, int[] maxGreenTimes){
        int timer;
        Vehicle vehicle;
        BooleanSource prob;
        String arrivePrint = "";
        ArrayList<Vehicle> arrive = new ArrayList<Vehicle>();
        ArrayList<Vehicle> pass = new ArrayList<Vehicle>();
        while(totalTime<=simulationTime){
            for(int i=0; i<numStreet; i++){
                for(int k=0; k<2; k++){
                    for(int j=0; j<3; j++){
                        if(intersection.roads[i].lanes[k][j].size() < 6) {
                            prob = new BooleanSource(arrivalProb);
                            if (prob.occurs()) {
                                vehicle = new Vehicle(totalTime);
                                arrive.add(vehicle);
                                Intersection.totalCarCount++;
                                arrivePrint += "\t" + arrivePrinter(k, j, roadNames[i], vehicle);
                                intersection.enqueueVehicle(i, k, j, vehicle);
                            }
                        }
                    }
                }
            }
            for(int i=0; i<arrive.size(); i++){
                arrive.remove(0);
            }
            System.out.println("################################################################################");
            System.out.println("Time Step: " + totalTime + "\n");
            //intersection.timeStep();
            timer = intersection.getCountdownTimer();
            if(timer == 0)
                System.out.println("\tGREEN LIGHT FOR " + roadNames[intersection.getLightIndex()]);
            if(timer > (int)Math.floor(1.0/3 * greenTimes[intersection.getLightIndex()]))
                System.out.println("\tGREEN LIGHT FOR " + roadNames[intersection.getLightIndex()]);
            else if(timer > 0)
                System.out.println("\tLEFT SIGNAL FOR " + roadNames[intersection.getLightIndex()]);
            else if(timer == 0)
                timer = maxGreenTimes[intersection.getLightIndex()];
            System.out.println("\tTimer: " + timer);
            intersection.timeStep();
            System.out.println("ARRIVING CARS:");
            System.out.println(arrivePrint);
            arrivePrint = "";
            System.out.println("PASSING CARS:");
            System.out.println(passPrint);
            passPrint = "";
            for(int i = 0; i < numStreet; i++) {
                System.out.println(intersection.toString(i));
            }
            System.out.println("\nSTATISTICS");
            System.out.println("\tcars Currently waiting: " + (intersection.totalCarCount-intersection.totalCarPass) + " cars");
            System.out.println("\tTotal cars passed: " + intersection.totalCarPass + " cars");
            System.out.println("\tTotal wait time: " + intersection.totalWaitTime + " turns");
            if(intersection.totalCarPass == 0)
                System.out.println("\tAverage wait time: 0");
            else
                System.out.println("\tAverage wait time: " + ((double)intersection.totalWaitTime/intersection.totalCarPass) + " turns");
            timer--;
            totalTime++;
        }
        while(!exit){
            exit = true;
            System.out.println("################################################################################");
            System.out.println("Time Step: " + totalTime + "\n");
            timer = intersection.getCountdownTimer();
            if(timer > (int)Math.floor(1.0/3 * greenTimes[intersection.getLightIndex()]))
                System.out.println("\tGREEN LIGHT FOR " + roadNames[intersection.getLightIndex()]);
            else if(timer > 0)
                System.out.println("\tLEFT SIGNAL FOR " + roadNames[intersection.getLightIndex()]);
            else if(timer == 0)
                timer = maxGreenTimes[intersection.getLightIndex()];
            System.out.println("\tTimer: " + timer);
            intersection.timeStep();
            System.out.println("ARRIVING CARS:");
            System.out.println("\tNo more cars arriving.");
            System.out.println("PASSING CARS:");
            System.out.println(passPrint);
            passPrint = "";
            for(int i = 0; i < numStreet; i++) {
                System.out.println(intersection.toString(i));
            }
            timer--;
            totalTime++;
            for(int i =0; i <numStreet; i++){
                for(int k =0; k<2; k++){
                    for(int j=0; j<3; j++){
                        if(!intersection.roads[i].lanes[k][j].isEmpty())
                            exit = false;
                    }
                }
            }
        }
        System.out.println("\nSTATISTICS");
        System.out.println("\tcars Currently waiting: " + (intersection.totalCarCount-intersection.totalCarPass) + " cars");
        System.out.println("\tTotal cars passed: " + intersection.totalCarPass + " cars");
        System.out.println("\tTotal wait time: " + intersection.totalWaitTime + " turns");
        if(intersection.totalCarPass == 0)
            System.out.println("\tAverage wait time: 0");
        else
            System.out.println("\tAverage wait time: " + ((double)intersection.totalWaitTime/intersection.totalCarPass) + " turns");
    }

    public static String arrivePrinter(int wayIndex, int laneIndex, String name, Vehicle arriving){
        String print = "Car[";
        if(arriving.getSerialID()<10)
            print += "00";
        else if(arriving.getSerialID()<100)
            print+= "0";
        print += arriving.getSerialID() + "] entered " + name + ", going ";
        if(wayIndex == 0)
            print += "FORWARD";
        else
            print += "BACKWARD";
        print += " in ";
        if(laneIndex == 0)
            print += "LEFT";
        else if(laneIndex == 1)
            print += "MIDDLE";
        else
            print += "RIGHT";
        print += " lane.\n";
        return print;
    }

    public static String passPrinter(int wayIndex, int laneIndex, Vehicle passing){
        String print = "\tCar[";
        if(passing.getSerialID()<10)
            print += "00";
        else if(passing.getSerialID()<100)
            print+= "0";
        print += passing.getSerialID() + "] passes through. Wait time of ";
        return print;
    }

}

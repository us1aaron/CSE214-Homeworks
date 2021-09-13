/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 7
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the NearEarthObject class
 * This represents an object that with various information about it, for example,
 * int reference_ID is the id of the object
 * String name is the name of the object
 * String orbitingBody is the planet that it is orbiting
 */
import java.util.Date;
public class NearEarthObject {
    private int reference_ID;
    private String name;
    private double absoluteMagnitude;
    private double averageDiameter;
    private boolean isDangerous;
    private Date closestApproachDate;
    private double missDistance;
    private String orbitingBody;

    /**
     * Constructor for the NearEarthObject
     * @param referenceID
     * ID of the object
     * @param name
     * name of object
     * @param absoluteMagnitude
     * absolute magnitude of object
     * @param minDiameter
     * minimum diameter of object
     * @param maxDiameter
     * maximum diameter of object
     * @param isDangerous
     * tells us whether or not object poses a threat
     * @param closestDateTimestamp
     * time of when it was closest to planet
     * @param missDistance
     * distance it missed planet by
     * @param orbitingBody
     * planet that the object is orbiting
     */
    public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter,
                            double maxDiameter, boolean isDangerous, long closestDateTimestamp,
                            double missDistance, String orbitingBody){
        this.reference_ID = referenceID;
        this.name = name;
        this.absoluteMagnitude = absoluteMagnitude;
        this.averageDiameter = (minDiameter + maxDiameter)/2;
        this.isDangerous = isDangerous;
        this.closestApproachDate = new Date(closestDateTimestamp);
        this.missDistance = missDistance;
        this.orbitingBody = orbitingBody;
    }

    public int getReference_ID() { //getter method for id
        return reference_ID;
    }

    public String getName() { //getter method for name
        return name.substring(name.indexOf('('));
    }

    public double getAbsoluteMagnitude() { //getter method for absolute magnitude
        return absoluteMagnitude;
    }

    public double getAverageDiameter() { //getter method for average diameter
        return averageDiameter;
    }

    public boolean getisDangerous() { //getter method for dangerous
        return isDangerous;
    }

    public Date getClosestApproachDate() { //getter method for date
        return closestApproachDate;
    }
    public String getDate(){ //alternative getter method for date
        String date = "";
        if(closestApproachDate.getMonth() < 10)
            date += "0";
        date += closestApproachDate.getMonth() + "-";
        if(closestApproachDate.getDate() < 10)
            date += "0";
        date += closestApproachDate.getDate() + "-";
        date += closestApproachDate.getYear()+1900;
        return date;
    }
    public double getMissDistance() { //getter method for miss distance
        return Math.round(missDistance);
    }

    public String getOrbitingBody() { //getter method for orbiting body
        return orbitingBody;
    }

    public void setReference_ID(int reference_ID) { //setter method for reference id
        this.reference_ID = reference_ID;
    }

    public void setName(String name) { //setter method for name
        this.name = name;
    }

    public void setAbsoluteMagnitude(double absoluteMagnitude) { //setter method for absolute magnitude
        this.absoluteMagnitude = absoluteMagnitude;
    }

    public void setAverageDiameter(double averageDiameter) { //setter method for average diameter
        this.averageDiameter = averageDiameter;
    }

    public void setDangerous(boolean dangerous) { //setter method for dangerous
        isDangerous = dangerous;
    }

    public void setClosestApproachDate(long closestApproachDate) { //setter method for date
        this.closestApproachDate = new Date(closestApproachDate);
    }

    public void setMissDistance(double missDistance) { //setter method for miss distance
        this.missDistance = missDistance;
    }

    public void setOrbitingBody(String orbitingBody) { //setter method for orbiting body
        this.orbitingBody = orbitingBody;
    }
}

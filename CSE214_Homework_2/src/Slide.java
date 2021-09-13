/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 2
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the Slide Class.
 * Slide objects will be created which will contain
 * int MAX_BULLETS which holds the max number of bullets of a given slide
 * String title which will hold the title of the slide
 * A String array which size is set to MAX_BULLETS and will hold text for each bullet point
 * double duration which will be the amount of time the slide will be shown
 * These Slide objects will be apart of the SlideListNode class which will hold this data in a node.
 */

public class Slide{
    public final static int MAX_BULLETS = 5;
    private String title;
    private String[] bullets= new String[MAX_BULLETS];
    private double duration;

    /**
     * This is the default constructor for Slide object
     * Postcondition:
     * Slide object is initialized where title and slide are null and duration is zero
     */
    public Slide(){
    }

    public String getTitle(){ //getter method that returns title of slide
        return this.title;
    }
    /**
     * getter method for bullet point at given index
     * @param i
     * the index of array to retrieve bullet point
     * Precondition:
     * 1 <= i <= MAX_BULLETS
     * @return
     * string of bullet point at given index i
     * @exception
     * IllegalArgumentException - if i is not in valid range
     */
    public String getBullet(int i){
        return bullets[i-1];
    }
    public int getNumBullets(){ //getter method that returns number of bullets in slide
        int sum = 0;
        for(int i=0;i<MAX_BULLETS;i++){
            if(this.bullets[i] != null)
                sum++;
        }
        return sum;
    }
    public double getDuration(){ //getter method that returns the duration of the slide
        return this.duration;
    }

    /**
     * setter method for title member variable
     * @param newTitle
     * new title for title of slide
     * Precondition:
     * param should not be null
     * @exception
     * IllegalArgumentException - if param is null
     */
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    /**
     * setter method for bullets in slide
     * @param newBullet
     * new text for bullet
     * @param i
     * index of bullet
     * Precondition:
     * 1 <= i <= MAX_BULLETS
     * Postcondition:
     * bullet point at index i has been updated with newBullet
     * @exception
     * IllegalArgumentException - if i is out of range
     */
    public void setBullet(String newBullet, int i){ //setter method for bullet text of given bullet index
        if(newBullet != null)
            bullets[i-1] = newBullet;
        else{
            String[] temp = new String[MAX_BULLETS];
            if(i == 1)
                System.arraycopy(bullets, i, temp, i - 1,bullets.length - 1);
            else{
                System.arraycopy(bullets, 0, temp, 0, i - 1);
                System.arraycopy(bullets, i, temp, i - 1,bullets.length - i);
            }
            this.bullets = temp;
        }
    }
    /**
     * setter method for duration member variable
     * @param newDuration
     * new duration for slide
     * Precondition:
     * newDuration > 0
     * @exception
     * IllegalArgumentException - newDuration is less than 0
     */
    public void setDuration(double newDuration){ //setter method for duration of slide
        this.duration = newDuration;
    }
    /**
     * String representation of the Slide object
     * @return
     * Formatted string representation of the current Slide object
     * @exception
     * EndOfListException - if list/slideshow is empty
     */
    public String toString(){
        String display = "";
        display += "==============================================\n";
        display += this.title;
        display += "\n==============================================\n";
        for(int i = 1; i<=this.getNumBullets(); i++)
            display = display + " " + i + ": " + this.getBullet(i) + "\n";
        display += "==============================================\n";
        return display;
    }
}
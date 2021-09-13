/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 2
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the SlideListNode Class.
 * Slide data is the slide object or data of the node
 * SlideListNode next will be the reference to the next SlideListNode in the linked list
 * SlideListNode prev will be the reference to the previous SlideListNode in the linked list
 * The SlideListNode class will be part of the SlideList class which will wrap all the nodes into a linked list
 */
public class SlideListNode {
    private Slide data;
    private SlideListNode next;
    private SlideListNode prev;

    /**
     * Constructor for SlideListNode object
     * @param initdata
     * data to be wrapped by this SLideListNode
     * Precondition:
     * initData is not null
     * Postcondition:
     * This SlideListNode has been initialized to wrap the indicated Slide, and prev and next have been set to null.
     * @exception
     * IllegalArgumentException - if initData is null
     */
    public SlideListNode(Slide initdata){
        this.data = initdata;
    }

    /**
     * Updates data member with a new slide reference
     * @param newData
     * Referenc to a new slide
     * Precondition:
     * newData should not be null
     * @exception
     * IllegalArgumentException - if newData is null
     */
    public void setData(Slide newData){
        this.data = newData;
    }
    /**
     * Setter method that updates new node refernce
     * @param newNext
     * refernce to next node
     */
    public void setNext(SlideListNode newNext){
        this.next = newNext;
    }
    /**
     * Setter method that updates new node refernce
     * @param newPrev
     * refernce to previous node
     */
    public void setPrev(SlideListNode newPrev){
        this.prev = newPrev;
    }

    public Slide getData(){ //getter method that returns data of node
        return this.data;
    }
    public SlideListNode getNext(){ //getter method that returns refernce to next node
        return this.next;
    }
    public SlideListNode getPrev(){ //getter method that returns reference to previous node
        return this.prev;
    }
}
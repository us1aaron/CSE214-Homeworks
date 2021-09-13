/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 2
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the SlideList class.
 * SlideListNode head references the first node of the linked list
 * SlideListNode tail references the last node of the linked list
 * SlideListNode cursor references the selected node in the linked list
 * int slideCount will keep track of the total number of slides in the linked list
 * int time will keep track of the total duration of the linked list
 * int bulletCount will keep track of the total number of bullet points in the linked list
 */
public class SlideList {
    SlideListNode head;
    SlideListNode tail;
    SlideListNode cursor;
    int slideCount = 0;
    double time = 0;
    int bulletCount = 0;

    /**
     * Default constructor for SlideList object
     * Postcondition:
     * This SlideList has been initialized with head, tail, and cursor as null
     */
    public SlideList(){
    }

    public int size(){ //returns the number of slides
        return this.slideCount;
    }
    public double duration(){ //returns total duration across all slides
        return this.time;
    }
    public int numBullets(){ //returns total number of bullets in slideshow
        SlideListNode pointer;
        pointer = head;
        bulletCount = 0;
        while(pointer.getNext() != null){
            bulletCount += pointer.getData().getNumBullets();
            pointer = pointer.getNext();
        }
        return bulletCount;
    }

    public Slide getCursorSlide(){ //returns reference to slide at cursor
        return cursor.getData();
    }
    /**
     * Returns cursor to the head of the list/slideshow
     * Postcondition:
     * If head is not null, the cursor now references the first SlideListNode in list/slideshow
     * If head is null, the cursor is set to null
     */
    public void resetCursorToHead(){
        if(this.head == null)
            this.cursor = null;
        else
            this.cursor = this.head;
    }
    /**
     * Moves cursor to the next node/slide
     * @exception
     * EndOfListException - if cursor is at the tail of list
     */
    public void cursorForward(){
        this.cursor = cursor.getNext();
    }
    /**
     * Moves cursor to the previous node/slide
     * @exception
     * EndOfListException - if cursor is at the head of the list
     */
    public void cursorBackward(){
        this.cursor = cursor.getPrev();
    }

    /**
     * Inserts slide before cursor
     * @param newSlide
     * Slide object to be added
     * Precondition:
     * newSlide is not null
     * Postcondition:
     * newSlide has been wrwapped into the list/slideshow
     * If cursor is not null, the node has been inserted into the list before the cursor
     * If cursor is null, the node is now the head and tail of the list
     * The cursor now references the newly created SlideListNode
     * @exception
     * IllegalArgumentException - if newSlide is null
     */
    public void insertBeforeCursor(Slide newSlide){
        SlideListNode newSlideNode = new SlideListNode(newSlide);
        if(cursor == null){
            head = newSlideNode;
            tail = newSlideNode;
            cursor = newSlideNode;
        }
        else{
            if(cursor == head){
                cursor.setPrev(newSlideNode);
                newSlideNode.setNext(cursor);
                head = newSlideNode;
            }
            else{
                newSlideNode.setNext(cursor);
                newSlideNode.setPrev(cursor.getPrev());
                cursor.getPrev().setNext(newSlideNode);
                cursor.setPrev(newSlideNode);
            }
        }
        slideCount++;
        time += newSlide.getDuration();
        bulletCount += newSlide.getNumBullets();
        cursor = newSlideNode;
    }
    /**
     * Inserts slide to the end of the list
     * @param newSlide
     * Object to be wrapped and inserted to the end of the list
     * Precondition:
     * newSlide is not null
     * Postcondition:
     * newSlide has been wrapped in a new SlideListNode object
     * If tail was not null, the new node has been inserted after the tail
     * If tail was null, the new node has been set as the new head and tail of the list
     * The tail now references the newly created SlideListNode.
     * @exception
     * IllegalArgumentException - if newSlide is null
     */
    public void appendToTail(Slide newSlide){
        SlideListNode newSlideNode = new SlideListNode(newSlide);
        if(cursor == null){
            head = newSlideNode;
            tail = newSlideNode;
            cursor = newSlideNode;
        }
        else{
            tail.setNext(newSlideNode);
            newSlideNode.setPrev(tail);
            tail = newSlideNode;
        }
        slideCount++;
        time += newSlide.getDuration();
        bulletCount += newSlide.getNumBullets();
    }

    /**
     * Removes the current slide referenced by cursor
     * Precondition:
     * cursor is not null
     * Postcondition:
     * The slide node referenced by cursor is removed
     * All other slides exist in the same order
     * The cursor now references previous node or head if original head was removed
     * @return
     * The reference to the slide node that was just removed
     * @exception
     * EndOfListException - if cursor is null
     */
    public Slide removeCursor(){
        SlideListNode temp = cursor;
        if(slideCount == 1){
            head = null;
            tail = null;
            cursor = null;
        }
        else if(cursor == head){
            head = cursor.getNext();
            cursor.getNext().setPrev(null);
            cursor = cursor.getNext();
        }
        else if(cursor == tail){
            tail = cursor.getPrev();
            cursor.getPrev().setNext(null);
            cursor = cursor.getPrev();
        }
        else{
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getPrev();
        }
        slideCount--;
        time -= temp.getData().getDuration();
        bulletCount -= temp.getData().getNumBullets();
        return temp.getData();
    }

    /**
     * String representation of Slideshow
     * @return
     * A formatted string representation of Slideshow
     * @exception
     * EndOfListException - if list/slideshow is empty
     */
    public String toString(){
        String table = "";
        SlideListNode pointer;
        pointer = head;
        int count = 1;
        table += "Slideshow Summary:\n";
        table += "=================================================\n   ";
        table += String.format("%-8s%-20s%-10s%-6s", "Slide", "Title", "Duration", "Bullets");
        table += "\n-------------------------------------------------\n";
        while(pointer != null){
            if(pointer == cursor)
                table += "-> ";
            else
                table += "   ";
            table += String.format("%-8d%-20s%-10g%-6d", count++, pointer.getData().getTitle(), pointer.getData().getDuration(), pointer.getData().getNumBullets());
            table += "\n";
            pointer = pointer.getNext();
        }
        table += "=================================================\n";
        double timeHolder = Math.round(time*100000)/100000.0;
        table += "Total: " + slideCount + " slide(s), " + timeHolder + " minute(s), " + bulletCount + " bullet(s)\n";
        table += "=================================================\n";
        return table;
    }
}
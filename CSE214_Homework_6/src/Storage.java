/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 6
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
/**
 * This is the Storage class
 * int id is the unique number assigned to this object
 * String client is the owner of this storage object
 * String contents is the content of this storage object
 * These Storage objects will be stored
 */

import java.io.Serializable;
public class Storage implements Serializable {
    public static long serialVersionUID;
    private int id;
    private String client;
    private String contents;

    /**
     * Default constructor for Storage object
     * @param id
     * unique number key for object
     * @param client
     * owner of this object
     * @param contents
     * content of this onbject
     */
    public Storage(int id, String client, String contents){
        this.id = id;
        this.client = client;
        this.contents = contents;
    }
    public int getId() {
        return id;
    } //getter method for id

    public String getClient() {
        return client;
    } //getter method for client name

    public String getContents() {
        return contents;
    } //getter method for contents of object

    public void setId(int id) {
        this.id = id;
    } //setter method for id

    public void setClient(String client) {
        this.client = client;
    } //setter method for client name

    public void setContents(String contents) {
        this.contents = contents;
    } //setter method for object contents
}

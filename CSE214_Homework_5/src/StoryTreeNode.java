/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 5
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the StoryTreeNodeClass
 * This will hold data for each part of the story
 * String position is the position in the tree the node is
 * String option is the option user sees for this node
 * String message is the display message after user selects
 * Nodes will form a ternary tree
 * leftChild is the pointer to left child
 * middleChild is the pointer to node's middle child
 * rightChild is the pointer to the node's right child
 */
public class StoryTreeNode {
    private final String WIN_MESSAGE = "YOU WIN";
    private final String LOSE_MESSAGE = "YOU LOSE";
    private String position;
    private String option;
    private String message;
    private StoryTreeNode leftChild;
    private StoryTreeNode middleChild;
    private StoryTreeNode rightChild;

    public StoryTreeNode(){}; //Default constructor

    /**
     * Constructor for StoryTreeNode
     * @param position
     * Position of the node in the tree
     * @param option
     * Option of this node
     * @param message
     * Message of this node
     * Postcondition:
     * New node is created
     */
    public StoryTreeNode(String position, String option, String message){
        this.position = position;
        this.option = option;
        this.message = message;
    }

    /**
     * traverse will traverse through a subtree of specified node and increment the number of total leaf
     *   nodes and winning leaf nodes.
     * Postcondition:
     * count will equal the number of leaf nodes in the subtree
     * win will equal the number of winning leaf nodes in the subtree
     */
    public void traverse(){
        if(leftChild != null)
            this.getLeftChild().traverse();
        if(middleChild != null)
            this.getMiddleChild().traverse();
        if(rightChild != null)
            this.getRightChild().traverse();
        if(this.isLeaf()) {
            StoryTree.count++;
            if(this.getMessage().contains("YOU WIN"))
                StoryTree.win++;
        }
    }

    /**
     * Checks if node has any children
     * Precondition:
     * This node is initialized
     * Precondition:
     * The tree remins unchanged
     * @return
     * True if node has no children
     * False if node has children
     */
    public boolean isLeaf(){
        if(leftChild == null && middleChild == null && rightChild == null)
            return true;
        return false;
    }

    /**
     * Checks if a node is a winning node
     * Precondition:
     * This node is initialized
     * Postcondition:
     * The tree remains unchanged
     * @return
     * True if node is a lead node and contains WIN_MESSAGE
     * False otherwise
     */
    public boolean isWinningNode(){
        if(isLeaf() && getMessage().contains(WIN_MESSAGE))
            return true;
        return false;
    }
    /**
     * Checks if a node is a losing node
     * Precondition:
     * This node is initialized
     * Postcondition:
     * The tree remains unchanged
     * @return
     * True if node is a lead node and contains LOSE_MESSAGE
     * False otherwise
     */
    public boolean isLosingNode(){
        if(isLeaf() && getMessage().contains(LOSE_MESSAGE))
            return true;
        return false;
    }
    public String getPosition(){
        return this.position;
    } //getter method for position
    public String getOption(){
        return this.option;
    } //getter method for option
    public String getMessage(){
        return this.message;
    } //getter method for message
    public StoryTreeNode getLeftChild() {
        return leftChild;
    } //getter method for left child
    public StoryTreeNode getMiddleChild() {
        return middleChild;
    } //getter method for middle child
    public StoryTreeNode getRightChild() {
        return rightChild;
    } //getter method for right child
    public void setPosition(String position) {
        this.position = position;
    } //setter method for position
    public void setOption(String option) {
        this.option = option;
    } //setter method for option
    public void setMessage(String message) {
        this.message = message;
    } //setter method for message
    public void setLeftChild(StoryTreeNode leftChilds) {
        this.leftChild = leftChilds;
    } //setter method for left child
    public void setMiddleChild(StoryTreeNode middleChild) {
        this.middleChild = middleChild;
    } //setter method for middle child
    public void setRightChild(StoryTreeNode rightChild) {
        this.rightChild = rightChild;
    } //setter method for right child
}

/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 1
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */

/**
 * This is the Team class
 * Team objects will be created which will consist on an array of Player objects
 * Each team has max size of 40
 * Methods are created in order to provide user with functionality with managing team(s)
 */
public class Team {
    public static final int MAX_PLAYERS = 5;
    public int size = 0;
    private Player players[];


    public void setSize(int size){ //setter for Team size
        this.size = size;
    }

    public int getSize(){ //getter for Team size
        return this.size;
    }
    public Team(){ //default constructor for Team class
        players = new Player[MAX_PLAYERS];
    }
    /**
     * Contructs an instance of the Team class with no Player objects in it
     * @param players
     * Postcondition:
     * This Team has been initialized to an empty list of Players
     */
    public Team(Player players[]) {
        this.players = players;
    }

    public Player[] getPlayers(){ //getter function for Team list
        return players;
    }

    /**
     * Generates a clone of this Team
     * @return
     * Returns a clone of this Team
     */
    public Team clone(){ //redo for loop for index
        Player[] clonePlayers = new Player[MAX_PLAYERS];
        for(int i = 0; i < this.size(); i++){
            String cloneName = this.players[i].getName();
            int cloneHits = this.players[i].getNumHits();
            int cloneErrors = this.players[i].getNumErrors();
            Player playerClone = new Player(cloneName, cloneHits, cloneErrors);
            clonePlayers[i] = playerClone;
        }
        return new Team(clonePlayers);
    }

    /**
     * Compares this Team object to another for equality
     * @param a
     * Team a which this Team is compared to
     * @return
     * Returns true if both teams are equal and false if otherwise
     */
    public boolean equals(Team a) {
        if (this.size() == a.size()) {
            for (int i = 0; i < this.size(); i++) {
                if(!(this.getPlayers()[i].equals(a.getPlayers()[i])))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Determines number of Players in this Team
     * Precondition:
     * This Team object has been instantiated
     * @return
     * The number of Players in this Team
     */
    public int size() {
        return this.size;
    }

    /**
     * Adds Player to the team at the indicated position in the lineup
     * @param p
     * The Player to be added to this Team
     * @param position
     * The position in the lineup where the Player will be inserted
     * Precondition:
     * This Team object has been instantiated
     * 1<= position <= players_in_team + 1
     * Postcondition:
     * The new Player is now stored at the indicated position in this Team. All other players are pushed back
     * @exception
     * IllegalArgumentException - Indicates that position is not within the valid range
     * FullTeamException - Indicates that there is no more room inside of the Team to store the new Player object
     */
    public void addPlayer(Player p, int position) {
        if (position <= this.size()) {
            for (int i = this.size(); i > position - 1; i--) {
                players[i] = players[i - 1];
            }
        }
        players[position - 1] = p;
    }

    /**
     * Removes Player from this Team at the indicated position in lineup
     * @param position
     * The position in the lineup that the Player will be removed
     * Precondition:
     * This Team object has been instantiated
     * 1 ≤ position ≤ players_in_team
     * Postcondition:
     * The Player at the indicated position in this Team has been removed
     * All other players are pushed forward one position
     */
    public void removePlayer(int position) {
        Player[] temp = new Player[MAX_PLAYERS];
        if(position == 1)
            System.arraycopy(players, position, temp, position - 1,this.size() - 1);
        else{
            System.arraycopy(players, 0, temp, 0, position - 1);
            System.arraycopy(players, position, temp, position - 1,this.size() - position);
        }
        players = temp;
    }

    /**
     * Returns reference to Player at indicated position in lineup
     * @param position
     * The position in the lineup of the Player we want the reference from
     * Precondition:
     * This Team object has been instantiated
     * @return
     * Player at the indicated position lineup
     * @exception
     * IllegalArgumentException - Indicates that position is not within the valid range.
     */
    public Player getPlayer(int position) {
        return players[position - 1];
    }

    /**
     * Returns the Player with the best value in the given statistic ("hits" or "errors")
     * @param stat
     * Indicates what statistic ("hits" or "errors") to compare with
     * Precondition:
     * This Team object has been instantiated
     * @return
     * Returns Player with the best value in the indicated statistic
     * @exception
     * IllegalArgumentException - Indicates that indicated stat was neither "hits" nor "errors"
     */
    public Player getLeader(int stat) { //0 represents hits and 1 represents errors
        int k = 0;
        for(int i = 0; i< this.size(); i++){
            if(stat == 0){
                if(players[i].getNumHits() > players[k].getNumHits())
                    k = i;
            }
            else if(stat == 1){
                if(players[i].getNumErrors() < players[k].getNumErrors())
                    k = i;
            }
        }
        return players[k];
    }

    /**
     * Prints a neatly formatted table of each Player in the Team on its own line with its position number as shown in
     *   the sample output
     * Precondition:
     * This Team object has been instantiated
     * Postcondition:
     * A neatly formatted table of each Player in the Team on its own line with its position number has been displayed
     *   to the user
     */
    public void printAllPlayers(){
        System.out.println(this.toString());
    }

    /**
     *Gets the String representation of this Team object as a neatly formatted table of each Player in the Team on its
     *   own line with its position number as shown in the sample output.
     * @return
     * Returns a string representation of this Team
     */
    public String toString() {
        String table = "";
        int count = 1;
        table += String.format("%-21s%-26s%-19s%-6s", "Player #", "Name", "Hits", "Errors");
        table += "\n";
        for (Player p : players) {
            if(p != null) {
                table += String.format("%-21d%-26s%-19d%-6d", count++, p.getName(), p.getNumHits(), p.getNumErrors());
                table += "\n";
            }
        }
        return table;
    }
}
/**
 * Aaron Li
 * 113319376
 * aaron.li@stonybrook.edu
 * Assignment 1
 * CSE214
 * REC02 - Felix Rieg-Baumhauer
 */
import java.util.Scanner;
/**\
 * TeamManager class will allow the user to interact with the Team and Player classes.
 * This will call methods in the Team and Player classes to simulate a TeamManager.
 */
public class TeamManager{
    public static final int MAX_TEAMS= 5;
    private Team teams[];

    public TeamManager(){ //constructs team
        this.teams = new Team[]{new Team(), new Team(), new Team(), new Team(), new Team()};
    }

    public Team[] getTeams(){ //getter for list of teams
        return this.teams;
    }

    /**
     * main method to simulate TeamManager
     * @param args
     * The user will be prompted through a loop and will not exit unless specified by the user.
     * A menu with various options will be presented and the user will be  prompted to select one.
     */
    public static void main(String args[]){
        TeamManager teamManager = new TeamManager();
        boolean end = false;
        System.out.println("Welcome to TeamManager!");
        Scanner input = new Scanner(System.in);
        int teamSelect = 1;
        String select;
        while(!end){
            System.out.println("\n------Menu------");
            System.out.println("A) Add Player.");
            System.out.println("G) Get Player Stats.");
            System.out.println("L) Get leader in a stat.");
            System.out.println("R) Remove a Player.");
            System.out.println("P) Print all players.");
            System.out.println("S) Size of team.");
            System.out.println("T) Select team.");
            System.out.println("C) Clone team.");
            System.out.println("E) Team equals.");
            System.out.println("U) Update stat.");
            System.out.println("Q) Quit\n");
            System.out.println("Team " + teamSelect + " is selected.");
            System.out.println("Please select an option:");
            select = input.nextLine();
            select = select.toUpperCase();
            switch(select){
                case "A":
                    String uName;
                    int uHits;
                    int uErrors;
                    int uPosition;
                    System.out.println("Enter the player's name: ");
                    uName = input.nextLine();
                    System.out.println("Enter the number of hits: ");
                    try {
                        uHits = input.nextInt();
                        if(uHits < 0)
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Number entered is negative.");input.nextLine();break;
                    }
                    System.out.println("Enter the number of errors: ");
                    try {
                        uErrors = input.nextInt();
                        if(uErrors < 0)
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Number entered is negative.");input.nextLine();break;
                    }
                    System.out.println("Enter position: ");
                    try {
                        uPosition = input.nextInt();
                        if(uPosition <= 0 || uPosition > teamManager.getTeams()[teamSelect-1].size()+1)
                            throw new IllegalArgumentException();
                        if(teamManager.getTeams()[teamSelect-1].size() >= Team.MAX_PLAYERS)
                            throw new FullTeamException("Error. Team is full.");
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Error. Number is not in valid range.");input.nextLine();break;
                    }
                    catch(FullTeamException e){
                        System.out.println(e.getMessage());input.nextLine();break;
                    }
                    Player uPlayer = new Player(uName, uHits, uErrors);
                    teamManager.getTeams()[teamSelect-1].addPlayer(uPlayer, uPosition);
                    teamManager.getTeams()[teamSelect-1].setSize(teamManager.getTeams()[teamSelect-1].getSize()+1);
                    System.out.println("Player " + uName + " has been added to team " + teamSelect);
                    input.nextLine();break;
                case "G":
                    System.out.println("Enter position: ");
                    try{
                        uPosition = input.nextInt();
                        if(uPosition < 1 || uPosition > teamManager.getTeams()[teamSelect-1].size())
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Value is not in valid range.");input.nextLine();break;
                    }
                    System.out.println(teamManager.getTeams()[teamSelect-1].getPlayers()[uPosition-1].toString());
                    input.nextLine();break;
                case "L":
                    int uStat = 3;
                    System.out.println("Enter '0'/'1' for hits/errors: ");
                    try{
                        uStat = input.nextInt();
                        if(uStat != 0 && uStat != 1)
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Selection not valid.");input.nextLine();break;
                    }
                    if(uStat == 0) {
                        System.out.print("Leader in Hits:\n");
                        System.out.println(teamManager.getTeams()[teamSelect-1].getLeader(0).toString());
                    }
                    else{
                        System.out.print("Leader in Errors:\n");
                        System.out.println(teamManager.getTeams()[teamSelect-1].getLeader(1).toString());
                    }
                    input.nextLine();break;
                case "R":
                    System.out.print("Enter the position: ");
                    try{
                        uPosition = input.nextInt();
                        if(uPosition < 1 || uPosition > teamManager.getTeams()[teamSelect-1].size())
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Value is not in valid range.");input.nextLine();break;
                    }
                    teamManager.getTeams()[teamSelect-1].removePlayer(uPosition);
                    teamManager.getTeams()[teamSelect-1].setSize(teamManager.getTeams()[teamSelect-1].getSize()-1);
                    input.nextLine();break;
                case "P":
                    System.out.println("Enter team index to select(1-5)");
                    teamSelect = input.nextInt();
                    teamManager.getTeams()[teamSelect-1].printAllPlayers();
                    input.nextLine();
                    break;
                case "S":
                    System.out.println("There are " + teamManager.getTeams()[teamSelect-1].size() + " player(s) in the Current Team.");
                    break;
                case "T":
                    System.out.println("Enter team index to select(1-5)");
                    try{
                        teamSelect = input.nextInt();
                        if(teamSelect < 1 || teamSelect > 5)
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Value is not in valid range.");input.nextLine();break;
                    }
                    System.out.println("Team " + teamSelect + " has been selected.");
                    input.nextLine();break;
                case "C":
                    int uCopyTo;
                    int uCopyFrom;
                    System.out.println("Select a team to clone from(1-5): ");
                    try{
                        uCopyFrom = input.nextInt();
                        if(uCopyFrom < 1 || uCopyFrom > 5)
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Value is not in valid range.");input.nextLine();break;
                    }
                    System.out.println("Select team to clone to(1-5)");
                    try{
                        uCopyTo = input.nextInt();
                        if((uCopyTo < 1) || (uCopyTo > 5))
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Value is not in valid range.");input.nextLine();break;
                    }
                    teamManager.getTeams()[uCopyTo-1] = teamManager.getTeams()[uCopyFrom-1].clone();
                    teamManager.getTeams()[uCopyTo-1].setSize(teamManager.getTeams()[uCopyFrom-1].getSize());
                    System.out.println("Team " + uCopyFrom + " has been copied to Team " + uCopyTo);
                    input.nextLine();break;
                case "E":
                    int uTeamOne;
                    int uTeamTwo;
                    System.out.println("Select first team(1-5): ");
                    try{
                        uTeamOne = input.nextInt();
                        if(uTeamOne < 1 || uTeamOne > 5)
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Value is not in valid range.");input.nextLine();break;
                    }
                    System.out.println("Select second team(1-5): ");
                    try{
                        uTeamTwo = input.nextInt();
                        if(uTeamTwo < 1 || uTeamTwo > 5)
                            throw new IllegalArgumentException();
                    } catch(IllegalArgumentException e){
                        System.out.println("Error. Value is not in valid range.");input.nextLine();break;
                    }
                    if(teamManager.getTeams()[uTeamOne-1].equals(teamManager.getTeams()[uTeamTwo-1]))
                        System.out.println("These teams are equal.");
                    else
                        System.out.println("These teams are not equal.");
                    input.nextLine();break;
                case "U":
                    System.out.println("Enter player name to update: ");
                    uName = input.nextLine();
                    for(Player p: teamManager.getTeams()[teamSelect-1].getPlayers()){
                        try{
                            if (uName.equals(p.getName())) {
                                System.out.println("Enter '0'/'1' for hits/errors to update: ");
                                uStat = input.nextInt();
                                if (uStat == 0) {
                                    System.out.println("Enter new number of hits: ");
                                    try {
                                        uHits = input.nextInt();
                                        if(uHits < 1)
                                            throw new IllegalArgumentException();
                                    } catch(IllegalArgumentException e){
                                        System.out.println("Error. Negative number.");input.nextLine();break;
                                    }
                                    p.setNumHits(uHits);
                                    input.nextLine();break;
                                } else if (uStat == 1) {
                                    System.out.println("Enter new number of errors: ");
                                    try {
                                        uErrors = input.nextInt();
                                        if(uErrors < 1)
                                            throw new IllegalArgumentException();
                                    } catch(IllegalArgumentException e){
                                        System.out.println("Error. Negative number.");input.nextLine();break;
                                    }
                                    p.setNumErrors(uErrors);
                                    input.nextLine();break;
                                }
                            }break;
                        } catch(NullPointerException e){
                            throw e;
                        }
                    }
                    break;
                case "Q":
                    end = true;
                    break;
                default:
                    System.out.println("Error. Please select a letter from the menu.");break;
            }
        }
    }
}

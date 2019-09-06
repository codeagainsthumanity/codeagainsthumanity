package io.codeagainsthumanity.models;

import io.codeagainsthumanity.PopulateDeckGSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    boolean showRules;
    // creating a player object that will be the judge

    long currentJudge;

    // true, judge has picked winner and will not be judge anymore, false- players or judge are waiting for cards
    boolean hasBeenJudged;


    @OneToMany(fetch = FetchType.EAGER)
    List<Status> status;
    //user many statuses
    //game has many statuses
    // create status class
    // find by game and user in status repo

    double gameCode;

    @OneToOne(cascade = CascadeType.PERSIST)
    BlackCard currentBlack;

    @OneToOne(cascade = CascadeType.PERSIST)
    BlackCard previousBlack;

    @OneToOne(cascade = CascadeType.PERSIST)
    WhiteCard previousWhite;

    @OneToMany(cascade = CascadeType.PERSIST)
    List<WhiteCard> whiteDeck;
    @OneToMany(cascade = CascadeType.PERSIST)
    List<BlackCard> blackDeck;

    //the key is the users unique ID.  the list of string is the users hand.
    //pass in id, get a hand.
    HashMap<Long, List<String>> hands;

    //has this user id already submitted their white card?
    HashMap<Long, Boolean> submitted;

    //white cards to be judged.
    @OneToMany
    List<WhiteCard> toBeJudged;


    @CreationTimestamp
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Date createdAt;

    @UpdateTimestamp
    @GeneratedValue(strategy = GenerationType.AUTO)
    Time updatedAt;

    @ManyToMany (mappedBy = "myGames")
    List<ApplicationUser> players;


//    List<Boolean> submitted;


    //constructors
    public Game(){};

    public boolean isHasBeenJudged() {
        return hasBeenJudged;
    }

    public void setHasBeenJudged(boolean hasBeenJudged) {
        this.hasBeenJudged = hasBeenJudged;
    }

    public Game(ApplicationUser gameOwner, double gameCode) throws IOException {
        this.showRules = true;
        this.whiteDeck = populateWhiteDeck();
        this.blackDeck = populateBlackDeck();
        this.gameCode = gameCode;
        this.players = createPlayerList(gameOwner);
        this.currentBlack = randomBlackCard();
        this.hasBeenJudged = false;
        this.currentJudge = gameOwner.id;
        this.hands =  new HashMap<>();
        this.submitted = new HashMap<>();
    }

    //methods
    public ArrayList<ApplicationUser> createPlayerList(ApplicationUser gameOwner){
        ArrayList<ApplicationUser> newPlayerList = new ArrayList<>();
        newPlayerList.add(gameOwner);
        return newPlayerList;
    }

    public List<WhiteCard> populateWhiteDeck() throws IOException {
        PopulateDeckGSON p = new PopulateDeckGSON();
        return p.readWhiteFileGSON();
    }

    public List<BlackCard> populateBlackDeck() throws IOException {
        PopulateDeckGSON p = new PopulateDeckGSON();
        return p.readBlackFileGSON();
    }

    // method to add a new player
    public List<ApplicationUser> addPlayer(ApplicationUser playerToAdd){
        this.players.add(playerToAdd);
        return this.players;
    }

    public void setBooleanToSubmitted(Long id, Boolean bool){
        this.getSubmitted().put(id, bool);
    }

    public Boolean getBooleanToSubmitted(Long id){
        return this.getSubmitted().get(id);
    }

    //method to add a card to users hand
    public void addCardToHand(Long id){
        //random white card
        WhiteCard wc = randomWhiteCard();
        //white card text
        String text = wc.getText();
        //players hand
        List<String> hand = this.getHands().get(id);
        //add text
        hand.add(text);
        //save list back into the hashmap
        this.getHands().put(id, hand);
    }
    //method to remove a specific card from users hand
    public void removeCardFromHand(Long id, String wc){
        //players hand, add text
        this.getHands().get(id).remove(wc);
    }

    public void updateBlackCardToBeJudgedAndPreviousBlack(){
        //black random card
        BlackCard random = randomBlackCard();
        //black current card
        BlackCard oldbc = this.getCurrentBlack();

        //set current
        this.setCurrentBlack(random);
        //set previous
        this.setPreviousBlack(oldbc);

    }


    public WhiteCard randomWhiteCard(){
        //get this deck
        List<WhiteCard> w = this.getWhiteDeck();
        //get index randomly, inclusive.
        int index = getRandomNumberInRange(0, w.size()-1);
        //save that card
        WhiteCard wc = w.get(index);
        //remove that card from the deck
        w.remove(index);
        return wc;
    }

    public BlackCard randomBlackCard(){
        //get this deck
        List<BlackCard> b = this.getBlackDeck();
        //get index randomly, inclusive.
        int index = getRandomNumberInRange(0, b.size()-1);
        //save that card
        BlackCard bc = b.get(index);
        //remove that card from the deck
        b.remove(index);
        return bc;
    }

    //sauce: https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    //gets and sets

    public HashMap<Long, List<String>> getHands() {
        return hands;
    }

    public void setHands(HashMap<Long, List<String>> hands) {
        this.hands = hands;
    }

    public void swapJudge() {
        int idx = findPlayerById(this.currentJudge);
        System.out.println("we needa swap" + this.currentJudge);
        System.out.println("index is " + idx);
        if (idx == this.players.size() - 1) {
            this.currentJudge = this.players.get(0).id;
            System.out.println("end of list" + this.currentJudge);
        }
        else {
            this.currentJudge = this.players.get(idx + 1).id;
            System.out.println("somewhere over the rainbow" + this.currentJudge);
        }

    }

    public int findPlayerById(long currentJudgeId) {
        //loop through players
        for ( int i = 0; i < this.players.size(); i++) {
            if ( this.players.get(i).id == currentJudgeId) {
                return i;
            }
        }
        return 0;
    }

    public HashMap<Long, Boolean> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(HashMap<Long, Boolean> submitted) {
        this.submitted = submitted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isShowRules() {
        return showRules;
    }

    public void setShowRules(boolean showRules) {
        this.showRules = showRules;
    }


    public Double getGameCode() {
        return gameCode;
    }

    public void setGameCode(double gameCode) {
        this.gameCode = gameCode;
    }

    public BlackCard getCurrentBlack() {
        return currentBlack;
    }

    public void setCurrentBlack(BlackCard currentBlack) {
        this.currentBlack = currentBlack;
    }

    public BlackCard getPreviousBlack() {
        return previousBlack;
    }

    public void setPreviousBlack(BlackCard previousBlack) {
        this.previousBlack = previousBlack;
    }

    public WhiteCard getPreviousWhite() {
        return previousWhite;
    }

    public void setPreviousWhite(WhiteCard previousWhite) {
        this.previousWhite = previousWhite;
    }

    public List<WhiteCard> getToBeJudged() {
        return toBeJudged;
    }

    public void setToBeJudged(List<WhiteCard> toBeJudged) {
        this.toBeJudged = toBeJudged;
    }

    public List<WhiteCard> getWhiteDeck() {
        return whiteDeck;
    }

    public void setWhiteDeck(ArrayList<WhiteCard> whiteDeck) {
        this.whiteDeck = whiteDeck;
    }

    public List<BlackCard> getBlackDeck() {
        return blackDeck;
    }

    public void setBlackDeck(ArrayList<BlackCard> blackDeck) {
        this.blackDeck = blackDeck;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Time getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Time updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ApplicationUser> getPlayers() {
        return players;
    }

    public void setPlayers(List<ApplicationUser> players) {
        this.players = players;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public long getCurrentJudge() {
        return currentJudge;
    }

    public void setCurrentJudge(long currentJudge) {
        this.currentJudge = currentJudge;
    }
}
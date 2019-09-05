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

    @OneToMany
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

    //constructors
    public Game(){};

    public Game(ApplicationUser gameOwner, double gameCode) throws IOException {
        this.showRules = true;
        this.whiteDeck = populateWhiteDeck();
        this.blackDeck = populateBlackDeck();
        this.gameCode = gameCode;
        this.players = createPlayerList(gameOwner);
        this.currentBlack = randomBlackCard();
        this.hands =  new HashMap<>();
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


    public double getGameCode() {
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
}
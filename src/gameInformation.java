import java.util.List;
import java.util.ArrayList;

class gameInformation{


    // General game variables
    String[] deck = {
            
        "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "1H", "JH", "QH", "KH",
        "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "1D", "JD", "QD", "KD",
        "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "1S", "JS", "QS", "KS", 
        "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "1C", "JC", "QC", "KC"

    };

    int deckCount = 4;
    int shoeSize = deckCount*52;

    public List<String> usedDeck = new ArrayList<String>();

    String currentPlayerChoice = " ";

    int currentPlayerBet = 0;

    boolean dealerBlackjack = false;
    boolean playerBlackjack = false;


    // Player variables
    int chips = 1000;

    String name = " ";

    List<String> playerHand = new ArrayList<String>();

    List<Integer> playerHandValues = new ArrayList<Integer>(); 

    int playerHandValue = 0;

    int playerAces = 0;


    // Dealer variables

    List<String> dealerHand = new ArrayList<String>();

    List<Integer> dealerHandValues = new ArrayList<Integer>();

    int dealerHandValue = 0;

    int dealerAces = 0;

    boolean dealerFaceUp = false; // Used when displaying the dealers total hand value - to either hide or not to hide the value of one of their cards

    
    // A collection of getters and setters for the class variables.
    public int getDeckCount() {
        return deckCount;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }


    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }


    public void setCurrentPlayerBet(int currentPlayerBet) {
        this.currentPlayerBet = currentPlayerBet;
    }

    public List<String> getUsedDeck() {
        return usedDeck;
    }


    public void setUsedDeck(List<String> usedDeck) {
        this.usedDeck = usedDeck;
    }


    // void Pause(time) - pauses the program for a given duration.
    // int time: time to pause for in milliseconds
    public void pause(int time)
    {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    // void addCardsToDeck - adds cards from the "Deck" preset (Just a standard deck of cards) to the shoe, directly modifies the usedDeck variable.
    void addCardsToDeck()
    {

        for(int decks = 1; decks <= deckCount; decks++)
        {
    
            for(int cardsIndex = 0; cardsIndex < 52; cardsIndex++)
            {
    
                usedDeck.add(deck[cardsIndex]);
    
            }

        }

        setUsedDeck(usedDeck);

    }



}

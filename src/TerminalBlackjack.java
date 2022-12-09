import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


class gameActions extends gameInformation implements playerInformation, dealerInformation{

    Random ran = new Random();

    int randomSelection = 0;

    // TODO create method that determines the threshold when the deck gets reshuffled


    // TODO create shuffle function
    // A method for shuffling the shoe of the current game.
    // Modifies the list of cards within this (gameInformation) class. 
    //void shuffleShoe()

    // Random index() - returns random index (int) of a card within the borders of the game's shoe size.
    int randomIndex(){

        return ran.nextInt(shoeSize); 

    }


    // drawnCardValue(String card) - returns value (int) of a card input
    // String card: string code of card 
    public int drawnCardValue(String card)
    {

        int faceValue = 0;

        switch(card.toCharArray()[1])
        {

            case 'A': faceValue = 11;
            break;
            case '1':
            case 'J':
            case 'K':
            case 'Q': faceValue = 10;
            break;
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': faceValue = card.toCharArray()[0] - '0';
            break;

        }

        return faceValue;

    }

    // void drawnCard(String drawingFor) - draws a random card, directly modifies variables playerInformation and dealerInformation and outputs it.
    // String drawingFor: either "player" or "dealer", you choose who is hitting.  
    void drawCard(String drawingFor){

        switch (drawingFor){

            case "player":
                
                randomSelection = randomIndex();

                playerHand.add(usedDeck.get(randomSelection));

                playerHandValues.add(drawnCardValue(usedDeck.get(randomSelection)));

                usedDeck.remove(randomSelection);

                shoeSize--;

            break;
            case "dealer":

            break;

        }

    }


}


class gameInformation{


    // A standard deck of cards. Technically, can be modified for some... interesting outcomes.
    String[] deck = {
            
        "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "1H", "JH", "QH", "KH",
        "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "1D", "JD", "QD", "KD",
        "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "1S", "JS", "QS", "KS", 
        "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "1C", "JC", "QC", "KC"

    };

    int deckCount = 4;
    int shoeSize;

    List<String> usedDeck = new ArrayList<String>();

    String currentPlayerChoice = " ";

    int currentPlayerBet = 0;
    
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

    }




}

interface playerInformation{   

    int chips = 1000;

    String name = " ";

    List<String> playerHand = new ArrayList<String>();

    List<Integer> playerHandValues = new ArrayList<Integer>(); 

    int playerHandValue = 0;

    int playerAces = 0;

}

interface dealerInformation {

    List<String> dealerHand = new ArrayList<String>();

    List<Integer> dealerHandValues = new ArrayList<Integer>();

    int dealerHandValue = 0;

    int dealerAces = 0;

}

class playerInput extends gameInformation{

    String queryLine;
    String errorLine;


    // InputOfNumbers - returns int that the user provided. Checks for InputMismatchExeptions, but not everything else.
    // String queryLine: text you want to display that explains to the player what to enter
    // String errorLine: text you want to display in case of an error
    int inputOfNumbers(String queryLine, String errorLine)
    {

        int inputNumber = 0;

        Scanner input = new Scanner(System.in);

        while(true)
        {

            try
            {

                System.out.println(queryLine);
                inputNumber = input.nextInt();

            }
            catch(Exception InputMismatchException)
            {

                System.out.println();
                System.out.println(errorLine);
                input = new Scanner(System.in);
                continue;

            }
            if(inputNumber < 0)
            {

                System.out.println("You can't enter a negative number.\n");
                pause(400);
                continue;

            }

            pause(1000);

            break;

        }

        return inputNumber;

    }

    
    // InputOfText - returns string that the user provided. Checks for InputMismatchExeptions, but not everything else.
    // String queryLine: text you want to display that explains to the player what to enter
    // String errorLine: text you want to display in case of an error
    String inputOfText(String queryLine, String errorLine){

        String inputString;

        Scanner input = new Scanner(System.in);

        while(true)
        {

            try
            {

                System.out.println(queryLine); 
                inputString = input.next();

            }
            catch(Exception InputMismatchException)
            {

                System.out.println();
                System.out.println(errorLine);
                input = new Scanner(System.in);
                continue;

            }

            pause(1000);

            break;

        }

        input.close();

        return inputString;

    }

}


public class TerminalBlackjack {

	public static void main(String[] args){

        System.out.println("\nWelcome to Blackjack, but a worse terminal version and written in Java.");
        System.out.println("Created by: char0n00 and Tomukas10 \n") ;  
        System.out.println("Win payout is 1:1. Blackjack payout is 2:1. Dealer stands on 17. \n You can type \"q\" at any time to exit. \n");

        gameInformation gameInfo = new gameInformation();

        playerInput plInput = new playerInput();

        cardGeneration cardGen = new cardGeneration();

        gameActions gameAction = new gameActions();

        while(true){

            gameInfo.deckCount = plInput.inputOfNumbers("Please select the number of decks used this game. Usually, 4 are used: ", "Invalid format. \n");

            if(gameInfo.deckCount >= 100)
            {

                System.out.println("Please input a deck count between 0 and 100.\n");
                gameInfo.deckCount = 4;
                gameAction.pause(400);
                continue;

            }

            break;

        }
        gameInfo.setShoeSize(gameInfo.deckCount*52);

        System.out.println();

        do{

            while(true){

                System.out.println("You currently have " + gameActions.chips + " chips.");
                gameInfo.setCurrentPlayerBet(plInput.inputOfNumbers("Please enter your bet:", "Please enter a whole number.\n"));

                if(gameInfo.currentPlayerBet  > gameAction.chips){

                    System.out.println("You can only bet less than or the chips that you own.");
                    continue; 

                }

                break;

            }

            



        }
        while(gameInfo.currentPlayerChoice != "q");

    } 

}

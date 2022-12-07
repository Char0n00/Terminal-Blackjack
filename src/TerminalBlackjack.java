
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class gameActions extends gameInformation implements playerInformation, dealerInformation{

    Random ran = new Random();

    int randomSelection = 0;

    // A method for shuffling the shoe of the current game.
    // Modifies the list of cards within this (gameInformation) class. 
    //void shuffleShoe()


    public int randomIndex(){

        return ran.nextInt(shoeSize); 

    }

    void drawCard(String drawingFor){

        switch (drawingFor){

            case "player":
                
                randomSelection = randomIndex();

                playerHand.add(usedDeck.get(randomSelection));

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

    char currentPlayerChoice = ' ';

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


    // A method for pausing the program for a short duration.
    // Takes parameter: int time (time to pause for in milliseconds)
    public static void pause(int time)
    {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    // A method for adding a select number of cards to the game shoe. 
    // Modifies the list of cards within this (gameInformation) class.
    void addCardsToDeck()
    {

        for(int decks = 1; decks <= this.deckCount; decks++)
        {
    
            for(int cardsIndex = 0; cardsIndex < 52; cardsIndex++)
            {
    
                this.usedDeck.add(this.deck[cardsIndex]);
    
            }
    
        }

    }




}

interface playerInformation{   

    int chips = 1000;

    String name = " ";

    List<String> playerHand = new ArrayList<String>();

    int playerHandValue = 0;

    int playerAces = 0;

}

interface dealerInformation {

    List<String> dealerHand = new ArrayList<String>();

    int dealerHandValue = 0;

    int dealerAces = 0;

}

class playerInput extends gameInformation{

    String queryLine;
    String errorLine;


    //
    int inputOfNumbers(String queryLine, String errorLine)
    {

        int inputNumber;

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

            pause(1000);

            break;

        }

        input.close();

        return inputNumber;

    }

    public static String inputOfText(String queryLine, String errorLine){

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

        gameInfo.setDeckCount(plInput.inputOfNumbers("Please select the number of decks used this game. Usually, 4 are used: ", "Invalid format. \n"));
        gameInfo.setShoeSize(gameInfo.deckCount*52);

        do{

            System.out.println("You currently have " + gameAction.chips + " chips.");
            gameInfo.currentPlayerChoice 


        }
        while(gameInfo.currentPlayerChoice != "q"); 



        


    } 

}

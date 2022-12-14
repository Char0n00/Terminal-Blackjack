import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


class gameActions extends gameInformation{

    // Declaring variables

    Random ran = new Random();

    int randomSelection = 0;

    ArrayList<ArrayList<String>> playerCardDisplay = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> dealerCardDisplay = new ArrayList<ArrayList<String>>();

    ArrayList<String> plLine1 = new ArrayList<String>();
    ArrayList<String> plLine2 = new ArrayList<String>();
    ArrayList<String> plLine3 = new ArrayList<String>();
    ArrayList<String> plLine4 = new ArrayList<String>();
    ArrayList<String> plLine5 = new ArrayList<String>();
    ArrayList<String> plLine6 = new ArrayList<String>();
    ArrayList<String> plLine7 = new ArrayList<String>();
    ArrayList<String> plLine8 = new ArrayList<String>();
    ArrayList<String> plLine9 = new ArrayList<String>();

    ArrayList<String> dlLine1 = new ArrayList<String>();
    ArrayList<String> dlLine2 = new ArrayList<String>();
    ArrayList<String> dlLine3 = new ArrayList<String>();
    ArrayList<String> dlLine4 = new ArrayList<String>();
    ArrayList<String> dlLine5 = new ArrayList<String>();
    ArrayList<String> dlLine6 = new ArrayList<String>();
    ArrayList<String> dlLine7 = new ArrayList<String>();
    ArrayList<String> dlLine8 = new ArrayList<String>();
    ArrayList<String> dlLine9 = new ArrayList<String>();

    cardGeneration cardGen = new cardGeneration();


    // TODO create method that determines the threshold when the deck gets reshuffled


    // A method for shuffling the shoe of the current game.
    // Modifies the list of cards within this (gameInformation) class. 
    public void shuffleShoe()
    {

        List<String> temporaryList = new ArrayList<String>();

        List<String> temporaryUsedDeck = new ArrayList<String>();

        for(int i = 0; i < shoeSize; i++)
        {

            temporaryUsedDeck.add(super.usedDeck.get(i));

        }

        int randIndex = 0;

        for(int i = shoeSize - 1; i > 0 ; i--)
        {

            randIndex = randomIndex(i);

            temporaryList.add(temporaryUsedDeck.get(randIndex));

            temporaryUsedDeck.remove(randIndex);

        }

        temporaryList.add(usedDeck.get(0));
        temporaryUsedDeck.remove(randIndex);

        for(int i = 0; i < shoeSize; i++)
        {

            usedDeck.set(i, temporaryList.get(i));

        }



    }

    // Random index() - returns random index (int) of a card within the limit
    // int limit: the limit of the random number generated
    int randomIndex(int limit){

        return ran.nextInt(limit); 

    }


    // drawnCardValue(String card) - returns value (int) of a card input
    // String card: string code of card 
    public int drawnCardValue(String card)
    {

        int faceValue = 0;

        switch(card.toCharArray()[0])
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
    public void drawCard(String drawingFor){

        switch (drawingFor){

            case "player":
                
                randomSelection = randomIndex(shoeSize);
                playerHand.add(usedDeck.get(randomSelection));
                playerHandValues.add(drawnCardValue(usedDeck.get(randomSelection)));
                playerHandValue += drawnCardValue(usedDeck.get(randomSelection));

                if(drawnCardValue(usedDeck.get(randomSelection)) == 11)
                {

                    playerAces++;

                }

                addCardsToDisplay("player", usedDeck.get(randomSelection), "  ");

                usedDeck.remove(randomSelection);
                shoeSize--;

                if(playerAces > 0 && playerHandValue > 21)
                {

                    playerAces--;
                    playerHandValue -= 10;

                    for(int i = playerHand.size() - 1; i >= 0; i--)
                    {

                        if(playerHandValues.get(i) == 11)
                        {

                            playerHandValues.set(i, 1);

                            break;

                        }

                    }

                }

                if(playerHandValue == 21 && playerHand.size() == 2)
                {

                    playerBlackjack = true;

                }

            break;
            case "dealer":

                randomSelection = randomIndex(shoeSize);
                dealerHand.add(usedDeck.get(randomSelection));
                dealerHandValues.add(drawnCardValue(usedDeck.get(randomSelection)));
                dealerHandValue += drawnCardValue(usedDeck.get(randomSelection));

                if(drawnCardValue(usedDeck.get(randomSelection)) == 11)
                {

                    dealerAces++;

                }

                if(dealerHand.size() == 2){

                    addCardsToDisplay("dealer", "  ", "BC");

                }

                else if(dealerHand.size() > 2){

                    addCardsToDisplay("dealer", usedDeck.get(randomSelection), "ND");

                }
                else{

                    addCardsToDisplay("dealer", usedDeck.get(randomSelection), "ND");

                }


                usedDeck.remove(randomSelection);
                shoeSize--;

                if(dealerAces > 0 && dealerHandValue > 21)
                {

                    dealerAces--;
                    dealerHandValue -= 10;

                    for(int i = dealerHand.size() - 1; i >= 0; i--)
                    {

                        if(dealerHandValues.get(i) == 11)
                        {

                            dealerHandValues.set(i, 1);

                            break;

                        }

                    }

                }

                if(dealerHandValue == 21 && dealerHand.size() == 2)
                {

                    dealerBlackjack = true;

                }

            break;

        }

    }

    // TODO write a double down function 



    // TODO write a split funcion (good luck with this one)



    void addCardsToDisplay(String addingFor, String cardCode, String dealerFaceDown){

        String[] temporaryStringArr = new String[9];

        switch(addingFor){

            case "player":

                playerCardDisplay = new ArrayList<ArrayList<String>>();

                temporaryStringArr = cardGen.generation(cardCode);

                plLine1.add(temporaryStringArr[0] + " ");
                plLine2.add(temporaryStringArr[1] + " ");
                plLine3.add(temporaryStringArr[2] + " ");
                plLine4.add(temporaryStringArr[3] + " ");
                plLine5.add(temporaryStringArr[4] + " ");
                plLine6.add(temporaryStringArr[5] + " ");
                plLine7.add(temporaryStringArr[6] + " ");
                plLine8.add(temporaryStringArr[7] + " ");
                plLine9.add(temporaryStringArr[8] + " ");

                playerCardDisplay.add(plLine1);
                playerCardDisplay.add(plLine2);
                playerCardDisplay.add(plLine3);
                playerCardDisplay.add(plLine4);
                playerCardDisplay.add(plLine5);
                playerCardDisplay.add(plLine6);
                playerCardDisplay.add(plLine7);
                playerCardDisplay.add(plLine8);
                playerCardDisplay.add(plLine9);

            break;
            
            case "dealer":

                dealerCardDisplay = new ArrayList<ArrayList<String>>();

                switch(dealerFaceDown){

                    case "BC":  // First time dealing the second card for the dealer it is written as a face down card

                        temporaryStringArr = cardGen.generation(dealerFaceDown);

                        dlLine1.add(temporaryStringArr[0] + " ");
                        dlLine2.add(temporaryStringArr[1] + " ");
                        dlLine3.add(temporaryStringArr[2] + " ");
                        dlLine4.add(temporaryStringArr[3] + " ");
                        dlLine5.add(temporaryStringArr[4] + " ");
                        dlLine6.add(temporaryStringArr[5] + " ");
                        dlLine7.add(temporaryStringArr[6] + " ");
                        dlLine8.add(temporaryStringArr[7] + " ");
                        dlLine9.add(temporaryStringArr[8] + " ");

                    break;
                    case "FL":  // After the player stands or goes bust, flip over the dealers card

                        temporaryStringArr = cardGen.generation(dealerHand.get(1));

                        dlLine1.set(1, temporaryStringArr[0] + " ");
                        dlLine2.set(1, temporaryStringArr[1] + " ");
                        dlLine3.set(1, temporaryStringArr[2] + " ");
                        dlLine4.set(1, temporaryStringArr[3] + " ");
                        dlLine5.set(1, temporaryStringArr[4] + " ");
                        dlLine6.set(1, temporaryStringArr[5] + " ");
                        dlLine7.set(1, temporaryStringArr[6] + " ");
                        dlLine8.set(1, temporaryStringArr[7] + " ");
                        dlLine9.set(1, temporaryStringArr[8] + " ");

                    break;
                    case "ND":  // Normal dealing for the dealer after the face down has been flipped over

                        temporaryStringArr = cardGen.generation(cardCode);

                        dlLine1.add(temporaryStringArr[0] + " ");
                        dlLine2.add(temporaryStringArr[1] + " ");
                        dlLine3.add(temporaryStringArr[2] + " ");
                        dlLine4.add(temporaryStringArr[3] + " ");
                        dlLine5.add(temporaryStringArr[4] + " ");
                        dlLine6.add(temporaryStringArr[5] + " ");
                        dlLine7.add(temporaryStringArr[6] + " ");
                        dlLine8.add(temporaryStringArr[7] + " ");
                        dlLine9.add(temporaryStringArr[8] + " ");

                    break;

                

                }

                dealerCardDisplay.add(dlLine1);
                dealerCardDisplay.add(dlLine2);
                dealerCardDisplay.add(dlLine3);
                dealerCardDisplay.add(dlLine4);
                dealerCardDisplay.add(dlLine5);
                dealerCardDisplay.add(dlLine6);
                dealerCardDisplay.add(dlLine7);
                dealerCardDisplay.add(dlLine8);
                dealerCardDisplay.add(dlLine9);

            break;

        }

    }

    void displayCards(){

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        if(dealerFaceUp == false)
        {

            System.out.println("Dealer total is " + (dealerHandValue-dealerHandValues.get(1)) + ":" );

        }
        else{

            System.out.println("Dealer total is " + dealerHandValue + ":");

        }
        
        for(int lines = 0; lines < 9; lines++)
        {

            for(int collumns = 0; collumns < dlLine1.size(); collumns++){

                System.out.print(dealerCardDisplay.get(lines).get(collumns));

            }

            System.out.print("\n");

        }

        if(dealerFaceUp == false)
        {

            System.out.println("     " + dealerHandValues.get(0));

        }
        else{

            System.out.print("     ");

            for(int i = 0; i < dealerHandValues.size(); i++)
            {

                if(dealerHandValues.get(i) >= 10)
                {

                    System.out.print(dealerHandValues.get(i) + "          ");

                }
                else{

                    System.out.print(dealerHandValues.get(i) + "           ");

                }



            }

        }

        System.out.println();

        System.out.println("Player total is " + playerHandValue + ":");
        for(int lines = 0; lines < 9; lines++)
        {

            for(int collumns = 0; collumns < plLine1.size(); collumns++){

                System.out.print(playerCardDisplay.get(lines).get(collumns));

            }

            System.out.print("\n");

        }

        System.out.print("     ");

        for(int i = 0; i < playerHandValues.size(); i++)
        {

            if(playerHandValues.get(i) >= 10)
            {

                System.out.print(playerHandValues.get(i) + "          ");

            }
            else{

                System.out.print(playerHandValues.get(i) + "           ");

            }



        }

        System.out.println("\n");

    }

}


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

    boolean dealerFaceUp = false;

    
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
                pause(480);
                continue;

            }

            pause(500);

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

            pause(500);

            break;

        }

        return inputString.toLowerCase();

    }

}


public class TerminalBlackjack {

	public static void main(String[] args){

        // TODO save player info into a file
        // TODO pipe statistics into a file
        // TODO shorten the pauses, they do get annoying after a while

        System.out.println("\nWelcome to Blackjack, but a worse terminal version and written in Java.");
        System.out.println("Created by: char0n00 and Tomukas10 \n") ;  
        System.out.println("Win payout is 1:1. Blackjack payout is 2:1. Dealer stands on 17. \n You can type \"q\" at any time to exit. \n");

        gameActions gameAction = new gameActions();

        //gameInformation gameInfo = new gameInformation();

        playerInput plInput = new playerInput();

        while(true){

            gameAction.deckCount = plInput.inputOfNumbers("Please select the number of decks used this game. Usually, 4 are used: ", "Invalid format. \n");

            if(gameAction.deckCount >= 100)
            {

                System.out.println("Please input a deck count between 0 and 100.\n");
                gameAction.deckCount = 4;
                gameAction.pause(480);
                continue;

            }

            break;

        }

        gameAction.setShoeSize(gameAction.deckCount*52);

        gameAction.addCardsToDeck();

        gameAction.shuffleShoe();


        // Start of main game loop

        do{

            while(true){

                System.out.println("\nYou currently have " + gameAction.chips + " chips.");
                gameAction.setCurrentPlayerBet(plInput.inputOfNumbers("Please enter your bet:", "Please enter a whole number.\n"));

                if(gameAction.currentPlayerBet  > gameAction.chips){

                    System.out.println("You can only bet less than or the chips that you own.");
                    continue; 

                }

                gameAction.chips = gameAction.chips - gameAction.currentPlayerBet;

                break;

            }

            // Deal the first 4 cards

            gameAction.drawCard("player");
            gameAction.drawCard("dealer");
            gameAction.drawCard("player");
            gameAction.drawCard("dealer");

            gameAction.displayCards();

            while(!gameAction.currentPlayerChoice.equals("s") && gameAction.dealerBlackjack == false && gameAction.playerBlackjack == false && gameAction.playerHandValue <= 21){
                
                gameAction.currentPlayerChoice = new String();

                

                if(gameAction.playerHandValues.get(0) == gameAction.playerHandValues.get(1)){

                    while(true){

                        String temporaryChoice = " ";

                        temporaryChoice = plInput.inputOfText("Do you (s)tand, (h)it, (sp)lit, or (d)ouble down?", "Please input a choice.");

                        if(!temporaryChoice.equals("s") && !temporaryChoice.equals("sp") && !temporaryChoice.equals("d") && !temporaryChoice.equals("h")){

                            System.out.println("Please input a valid choice");
                            continue;

                        }

                        // TODO no inputs work when you can split

                        break;

                    }

                }
                else{

                    while(true){

                        String temporaryChoice = " ";

                        temporaryChoice = plInput.inputOfText("Do you (s)tand, (h)it or (d)ouble down?", "Please input a choice.");

                        // TODO add a check that doesn't let you double down when you don't have enough money for it


                        if(!temporaryChoice.equals("s") && !temporaryChoice.equals("d") && !temporaryChoice.equals("h")){

                            System.out.println("Please input a valid choice");
                            gameAction.pause(400);
                            continue;

                        }

                        gameAction.currentPlayerChoice = temporaryChoice;

                        break;

                    }

                }

                switch(gameAction.currentPlayerChoice){

                    case "h":

                        gameAction.drawCard("player");
                        gameAction.displayCards();

                        System.out.println("The player hit and got " + gameAction.playerHandValues.get(gameAction.playerHandValues.size()-1) + ".");

                        gameAction.pause(1000);

                    break;

                    case "d":

                        gameAction.drawCard("player");
                        gameAction.chips -= gameAction.currentPlayerBet;
                        gameAction.currentPlayerBet *= 2;
                        gameAction.currentPlayerChoice = "s";

                        gameAction.displayCards();

                        System.out.println("The player doubled down and got " + gameAction.playerHandValues.get(gameAction.playerHandValues.size()-1) + ".");

                        gameAction.pause(1000);

                    break;

                    case "sp":

                        System.out.println("Filler split");

                    break;

                }


            }

            gameAction.addCardsToDisplay("dealer", "  ", "FL");

            gameAction.dealerFaceUp = true;

            gameAction.displayCards();

            System.out.println("The dealer flips over their face down card. They have " + gameAction.dealerHandValues.get(0) + " and " + gameAction.dealerHandValues.get(1));

            gameAction.pause(1000);

            while(gameAction.dealerHandValue < 17 && gameAction.playerBlackjack == false && gameAction.playerHandValue <= 21){

                gameAction.drawCard("dealer");
                gameAction.displayCards();

                System.out.println("The dealer hit and got " + gameAction.dealerHandValues.get(gameAction.dealerHandValues.size()-1));

                gameAction.pause(1000);

            }

            if(gameAction.dealerBlackjack == true){

                System.out.println("The dealer has blackjack. You lose this one.\n");

            }
            else if(gameAction.playerBlackjack == true){

                System.out.println("You got blackjack!\n");
                gameAction.chips += gameAction.currentPlayerBet * 3;

            }
            else if(gameAction.playerHandValue > 21){

                System.out.println("You've gone and busted my good man.\n");

            }
            else if(gameAction.playerHandValue < gameAction.dealerHandValue && gameAction.dealerHandValue <= 21){

                System.out.println("You lose this one.\n");

            }
            else if(gameAction.playerHandValue > gameAction.dealerHandValue || gameAction.dealerHandValue > 21){

                System.out.println("You win!\n");
                gameAction.chips += gameAction.currentPlayerBet * 2;

            }
            else{

                System.out.println("It's a push.\n");
                gameAction.chips += gameAction.currentPlayerBet;

            }

            gameAction.playerCardDisplay = new ArrayList<ArrayList<String>>();
            gameAction.dealerCardDisplay = new ArrayList<ArrayList<String>>();

            gameAction.playerHand = new ArrayList<String>();
            gameAction.dealerHand = new ArrayList<String>();

            gameAction.dealerHandValues = new ArrayList<Integer>();
            gameAction.playerHandValues = new ArrayList<Integer>();

            gameAction.playerHandValue = 0;
            gameAction.dealerHandValue = 0;

            gameAction.playerBlackjack = false;
            gameAction.dealerBlackjack = false;

            gameAction.plLine1 = new ArrayList<String>();
            gameAction.plLine2 = new ArrayList<String>();
            gameAction.plLine3 = new ArrayList<String>();
            gameAction.plLine4 = new ArrayList<String>();
            gameAction.plLine5 = new ArrayList<String>();
            gameAction.plLine6 = new ArrayList<String>();
            gameAction.plLine7 = new ArrayList<String>();
            gameAction.plLine8 = new ArrayList<String>();
            gameAction.plLine9 = new ArrayList<String>();
        
            gameAction.dlLine1 = new ArrayList<String>();
            gameAction.dlLine2 = new ArrayList<String>();
            gameAction.dlLine3 = new ArrayList<String>();
            gameAction.dlLine4 = new ArrayList<String>();
            gameAction.dlLine5 = new ArrayList<String>();
            gameAction.dlLine6 = new ArrayList<String>();
            gameAction.dlLine7 = new ArrayList<String>();
            gameAction.dlLine8 = new ArrayList<String>();
            gameAction.dlLine9 = new ArrayList<String>();

            gameAction.playerAces = 0;
            gameAction.dealerAces = 0;

            gameAction.currentPlayerChoice = new String();

            gameAction.dealerFaceUp = false;

            gameAction.pause(600);

        }
        while(!gameAction.currentPlayerChoice.equals("q") && gameAction.chips > 0);

    } 

}

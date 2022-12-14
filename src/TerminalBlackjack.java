
import java.util.ArrayList;

public class TerminalBlackjack {

	public static void main(String[] args){

        // TODO save player info into a file
        // TODO pipe statistics into a file

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
                
                // Gives the player an option to split and normal moves, refuses any other input

                if(gameAction.playerHandValues.get(0) == gameAction.playerHandValues.get(1)){

                    while(true){

                        String temporaryChoice = " ";

                        temporaryChoice = plInput.inputOfText("Do you (s)tand, (h)it, (sp)lit, or (d)ouble down?", "Please input a choice.");

                        if(!temporaryChoice.equals("s") && !temporaryChoice.equals("sp") && !temporaryChoice.equals("d") && !temporaryChoice.equals("h")){

                            System.out.println("Please input a valid choice");
                            continue;

                        }

                        if(temporaryChoice.equals("d") && gameAction.chips < gameAction.currentPlayerBet){

                            System.out.println("You don't have enough chips to double down.");
                            continue;

                        }

                        // TODO no inputs work when you can split

                        break;

                    }

                }
                else{

                    // Gives the player a choice of standard inputs, refuses everything else

                    while(true){

                        String temporaryChoice = " ";

                        temporaryChoice = plInput.inputOfText("Do you (s)tand, (h)it or (d)ouble down?", "Please input a choice.");

                        if(!temporaryChoice.equals("s") && !temporaryChoice.equals("d") && !temporaryChoice.equals("h")){

                            System.out.println("Please input a valid choice");
                            gameAction.pause(400);
                            continue;

                        }

                        if(temporaryChoice.equals("d") && gameAction.chips < gameAction.currentPlayerBet){

                            System.out.println("You don't have enough chips to double down.");
                            continue;

                        }

                        gameAction.currentPlayerChoice = temporaryChoice;

                        break;

                    }

                }

                // Does respective things depending on the choice of the player

                switch(gameAction.currentPlayerChoice){

                    case "h":   // Calls the drawCard() method which handles everything, and displays the cards

                        gameAction.drawCard("player");
                        gameAction.displayCards();

                        System.out.println("The player hit and got " + gameAction.playerHandValues.get(gameAction.playerHandValues.size()-1) + ".");

                        gameAction.pause(1000);

                    break;

                    case "d": // Calls drawCard() once, doubles the input of the player bet (if a player has enough chips to double down is checked above, when presenting the choices)
                              // and sets the currentPlayerChoice to "s", which, after the loop reaches the end, breaks it, as if the player stood

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

            gameAction.addCardsToDisplay("dealer", "  ", "FL"); // Passes the required variables to addCardsToDisplay() to flip over the dealer face down card

            gameAction.dealerFaceUp = true;

            gameAction.displayCards(); // Displays all the cards, with the dealer's newly revealed card

            System.out.println("The dealer flips over their face down card. They have " + gameAction.dealerHandValues.get(0) + " and " + gameAction.dealerHandValues.get(1));

            gameAction.pause(1000);

            // If the player hasn't gone bust and didn't get blackjack, deals cards to the dealer and re-draws the interface each time. Deals until dealer has 17 or more.

            while(gameAction.dealerHandValue < 17 && gameAction.playerBlackjack == false && gameAction.playerHandValue <= 21){

                gameAction.drawCard("dealer");
                gameAction.displayCards();

                System.out.println("The dealer hit and got " + gameAction.dealerHandValues.get(gameAction.dealerHandValues.size()-1));

                gameAction.pause(1000);

            }

            // A collection of checks for the end of the game.

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

            // Resets the gameInformation variables that store player and dealer information for a new round.

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

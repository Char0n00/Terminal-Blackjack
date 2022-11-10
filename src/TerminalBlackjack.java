
import java.util.Random;
import java.util.Scanner;

// Main program file with the main game loop.


public class TerminalBlackjack {

	public static void main(String[] args) 
    {

        System.out.println();
        System.out.println("Welcome to Blackjack, but a worse terminal version and written in Java.");
        System.out.println("Created by: char0n00 and Tomukas10 \n") ;  
        System.out.println("You can type \"q\" at any time to exit. \n");


        Scanner input = new Scanner(System.in);

        int ownedChips = 1000; // Placeholder for betting

        int bet = 0;

        int deckCount;

        String playerChoice = new String();

        int[] drawnCardParameters;

        String[] deck = {
            
            "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "1H", "JH", "QH", "KH",
            "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "1D", "JD", "QD", "KD",
            "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "1S", "JS", "QS", "KS", 
            "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "1C", "JC", "QC", "KC",

        }; // a deck preset to be merged into usedDeck array.

        // Deck count input with error management

        while(true)
        {

            try
            {

                System.out.println("Please select the number of shoes (decks) that will be used this game (Usually, 4 decks are used):"); 
                deckCount = input.nextInt();

            }
            catch(Exception InputMismatchException)
            {

                System.out.println();
                System.out.println("You can only enter whole numbers as number of decks.");
                input = new Scanner(System.in);
                continue;

            }

            break;

        }

        int cardsInShoe = deckCount*52;

        String[] usedDeck = new String[cardsInShoe];

        // Creating array of cards with the chosen number of decks and pasting it to originalDeck, to be used when cards run out of the shoe.

        int index = 1;

        while(index < cardsInShoe)
        {

            int deckIndex = 0;

            for(int i = index - 1; i < index + 51; i++)
            {

                usedDeck[i] = deck[deckIndex];

                deckIndex++;

            }

            index += 52;

        }

        // These are saved for later when the shoe runs out of cards.

        String[] originalDeck = usedDeck.clone(); 
        int originalSize = cardsInShoe;


        //main game loop.
        
        do
        {

            Boolean gameOver = false;

            while(true)
            {

                

                try
                {

                    System.out.println("You currently have " + ownedChips + " chips." + "Please enter your bet: ");
                    bet = input.nextInt();

                }
                catch(Exception InputMismatchException)
                {

                    System.out.println("We don't give credit here. Please input a number less or equal to your owned chip amount.");
                    input = new Scanner(System.in);
                    continue;

                }

                break;

            }

            while(!gameOver)
            {

                drawnCardParameters = randomCard(usedDeck, cardsInShoe);

                System.out.println(drawnCardParameters[0] + " " + drawnCardParameters[1]);

                while(true)
                {
                    
                    try
                    {

                        System.out.println("You have " + " and the dealer has " + '\n' + "Do you (s)tand, (d)raw a card, (d)ouble (d)own or (sp)lit?");
                        playerChoice = input.next();
    
                    }
                    catch(Exception InputMismatchException)
                    {

                        System.out.println("Please input a choice in the form of a letter.");
                        input = new Scanner(System.in);

                    }

                    playerChoice = playerChoice.toLowerCase();
        
                    System.out.println(playerChoice);
    
                    if(!playerChoice.equals("s") && !playerChoice.equals("d") && !playerChoice.equals("dd") && !playerChoice.equals("sp") && !playerChoice.equals("q"))
                    {
    
                        System.out.println();
                        System.out.println("Please input one of the given choices.");
                        playerChoice = new String();
                        continue;
    
                    }

                    System.out.println();
    
                    break;
    
                }

            }

        }
        while(!playerChoice.equals("q"));

        

        input.close();
    
    }

    // Function that chooses a random card from the shoe, returns its face value and initials, and then removes the card from the deck
    
    public static int[] randomCard(String[] usedDeck, int cardsInShoe)
    {

        Random ran = new Random();

        int randomIndex = ran.nextInt(cardsInShoe);

        int faceValue = 0;

        int[] drawnCard = new int[2];

        switch(usedDeck[randomIndex].toCharArray()[0])
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
            case '9': faceValue = usedDeck[randomIndex].toCharArray()[0] - '0';
            break;

        }

        drawnCard[0] = randomIndex;
        drawnCard[1] = faceValue;

        return drawnCard;

    }


    // Function to remove card from deck

    public static String[] cardRemoval(String[] usedDeck, int cardsInShoe, int[] drawnCardParameters)
    {

        usedDeck[drawnCardParameters[0]] = usedDeck[cardsInShoe-1];

        return usedDeck; // need to add "cardsInShoe--;" after calling this function

    }


    // Function that deals the cards up until the first choice to be made to the player - to the dealer, the player, the dealer, and again the player, and returns those cards

    public static String[] cardDealing(String[] usedDeck, int cardsInShoe, int[] drawnCardParameters, int amountToDeal)
    {

        drawnCardParameters = randomCard(usedDeck, cardsInShoe);

        

        String[] plHand = new String[cards];

    }

    
    

}
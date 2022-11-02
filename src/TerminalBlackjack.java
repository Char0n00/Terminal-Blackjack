import java.util.Scanner;

// Main program file with the main game loop.

public class TerminalBlackjack {

	public static void main(String[] args) {

        System.out.println();
        System.out.println("Welcome to Blackjack, but a worse terminal version and written in Java.");
        System.out.println("Created by: char0n00 and Tomukas10" + '\n' + '\n');  
        
        Scanner input = new Scanner(System.in);

        int chips = 1000; // Placeholder for betting

        int bet;

        boolean inputSucceeded = false;

        int deckCount;

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


        String[] usedDeck = new String[deckCount*52];

        // Creating array of cards with the chosen number of decks and pasting it to originalDeck, to be used when cards run out of the shoe.

        /*for(int ii = 0; i < shoeNumber)

            for(int i = 0; i < shoeNumber; i++)
            {

                for(int j = 0; j < shoe)

            }

        //main game loop.
        
        do
        {

            System.out.println("Enter your bet");





        }
        while(!input.toLowerCase.equals("q"));

        // Function that chooses a random card from the deck, returns its face value and initials, and then removes the card from the deck

        // Function that deals the cards up until the first choice to be made to the player - to the dealer, the player, the dealer, and again the player, and returns those cards



        System.out.println("");*/

    
    
    }

}
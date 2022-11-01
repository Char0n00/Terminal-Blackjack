import java.util.Scanner;

// Main program file with the main game loop.

public class TerminalBlackjack {

	public static void main(String[] args) {

        System.out.println("Welcome to Blackjack, but a worse terminal version and written in Java.");
        System.out.println("Created by: char0n00 and Tomukas10");   
        
        Scanner input = new Scanner(System.in);

        int chips = 1000; // Placeholder for betting

        String[] deck = {
            
            "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "1H", "JH", "QH", "KH",
            "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "1D", "JD", "QD", "KD",
            "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "1S", "JS", "QS", "KS", 
            "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "1C", "JC", "QC", "KC",

        }; // a deck preset to be merged into usedDeck array.

        System.out.println("Please select the number of shoes (decks) that will be used this game:"); 
        
        int shoeNumber = input.nextInt();

        String[] usedDeck = new String[shoeNumber];

        do
        {

            System.out.println("Enter");





        }
        while(!input.toLowerCase.equals("q"));

        System.out.println("");

    
    
    }

}
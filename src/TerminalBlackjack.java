
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class gameInformation{

    public static void pause(int time)
    {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    int deckCount = 4;
    int shoeSize;

    List<String> usedDeck = new ArrayList<String>();

}

class playerInformation extends gameInformation{

    int chips = 1000;

    String name;

}

class playerInput extends gameInformation{

    String queryLine;
    String errorLine;

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

    void inputOfText(String queryLine, String errorLine, String inputString){

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

    }

}

public class TerminalBlackjack {

	public static void main(String[] args){

        System.out.println();
        System.out.println("Welcome to Blackjack, but a worse terminal version and written in Java.");
        System.out.println("Created by: char0n00 and Tomukas10 \n") ;  
        System.out.println("Win payout is 1:1. Blackjack payout is 2:1. Dealer stands on 17. \n You can type \"q\" at any time to exit. \n");

        gameInformation gameInfo = new gameInformation();

        playerInput plInput = new playerInput();

        cardGeneration cardGen = new cardGeneration();

        gameInfo.deckCount = plInput.inputOfNumbers("Please select the number of decks used this game. Usually, 4 are used: ", "Invalid format. \n");
        gameInfo.shoeSize = gameInfo.deckCount * 52;

        

    } 

}

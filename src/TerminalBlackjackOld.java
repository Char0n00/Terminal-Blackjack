
import java.util.Random;
import java.util.Scanner;

// Main program file with the main game loop.


public class TerminalBlackjackOld {

	public static void main(String[] args) 
    {

        System.out.println();
        System.out.println("Welcome to Blackjack, but a worse terminal version and written in Java.");
        System.out.println("Created by: char0n00 and Tomukas10 \n") ;  
        System.out.println("Win payout is 1:1. Blackjack payout is 2:1. Dealer stands on 17. \n You can type \"q\" at any time to exit. \n");

        Scanner input = new Scanner(System.in);

        int ownedChips = 1000; // Placeholder for betting

        int bet = 0;

        int deckCount;

        String playerChoice = new String();

        int[] drawnCardParameters = {0, 0};

        boolean gameOver = false;
    
        String[] deck = {
            
            "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "1H", "JH", "QH", "KH",
            "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "1D", "JD", "QD", "KD",
            "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "1S", "JS", "QS", "KS", 
            "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "1C", "JC", "QC", "KC"

        }; // a deck preset to be merged into usedDeck array.

        // Deck count input with error management

        while(true)
        {

            try
            {

                System.out.println("Please select the number of decks that will be used this game (Usually, 4 decks are used):"); 
                deckCount = input.nextInt();

            }
            catch(Exception InputMismatchException)
            {

                System.out.println();
                System.out.println("You can only enter whole numbers as number of decks.");
                input = new Scanner(System.in);
                continue;

            }

            pause(1000);

            break;

        }

        int cardsInShoe = deckCount*52;

        String[] usedDeck = new String[cardsInShoe];

        String[] plHand = new String[cardsInShoe];
        String[] dlHand = new String[cardsInShoe];

        int plHandValue;   
        int dlHandValue;
        int[] dlStHandValue;

        int plHandCount;
        int dlHandCount;

        int plAces;
        int dlAces;

        
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

        usedDeck = shuffle(usedDeck, cardsInShoe);

        // These are saved for later when the shoe is shuffled.

        String[] originalDeck = usedDeck.clone(); 
        int originalSize = cardsInShoe;

        Random ran = new Random();

        int shufflePercent = ran.nextInt(20) + 40; // Decides when to shuffle decks for each game. Casinos usually shuffle multi deck games when 40-60% of cards remain.
        // If one deck is used, it is shuffled every game.


        //main game loop.
        
        do
        {   
            
            gameOver = false;

            // Reset player and dealer hands

            plHandValue = 0;
            dlHandValue = 0;

            plHand = new String[cardsInShoe];
            dlHand = new String[cardsInShoe];
            dlStHandValue = new int[2];

            plHandCount = 0;
            dlHandCount = 0;

            boolean plBlackjack = false;

            while(true)
            {
        
                try
                {

                    System.out.println();
                    System.out.println("You currently have " + ownedChips + " chips." + " Please enter your bet: ");
                    bet = input.nextInt();
                    ownedChips -= bet;
                    System.out.println();

                }
                catch(Exception InputMismatchException)
                {

                    System.out.println("We don't give credit here. Please input a number less or equal to your owned chip amount.");
                    input = new Scanner(System.in);
                    continue;

                }

                pause(1000);

                break;

            }


            // Loop that deals the first cards

            for(int i = 0; i < 2; i++)
            {

                drawnCardParameters = randomCard(usedDeck, cardsInShoe);

                plHand[i] = usedDeck[drawnCardParameters[0]];

                plHandCount++;

                plHandValue += drawnCardParameters[1];

                usedDeck = cardRemoval(usedDeck, cardsInShoe, drawnCardParameters[0]);

                cardsInShoe--; // Required after calling cardRemoval

                drawnCardParameters = randomCard(usedDeck, cardsInShoe);

                dlHand[i] = usedDeck[drawnCardParameters[0]];

                dlHandCount++;

                dlStHandValue[i] = drawnCardParameters[1];

                dlHandValue += dlStHandValue[i];

                usedDeck = cardRemoval(usedDeck, cardsInShoe, drawnCardParameters[0]);

                cardsInShoe--;

                if(plHandValue == 21)
                {

                    gameOver = true;
                    ownedChips += bet*3;
                    System.out.println("Blackjack!");
                    plBlackjack = true;

                }

                if(dlHandValue == 21)
                {

                    gameOver = true;
                    System.out.println("The dealer has Blackjack.");

                }

            }

            while(!gameOver)
            {

                while(true)
                {
                    
                    try
                    {

                        System.out.println("You have: \n" + plHand[0] + " " + plHand[1] + " (" + plHandValue + ")\n" + "The dealer has:\n" + dlHand[0] + " (" + dlStHandValue[0] + ")" + "\nDo you (s)tand, (d)raw a card, (d)ouble (d)own or (sp)lit?");

                        playerChoice = input.next();
    
                    }
                    catch(Exception InputMismatchException)
                    {

                        System.out.println("Please input a choice in the form of a letter.");
                        input = new Scanner(System.in);

                    }

                    playerChoice = playerChoice.toLowerCase();
    
                    if(!playerChoice.equals("s") && !playerChoice.equals("d") && !playerChoice.equals("dd") && !playerChoice.equals("sp") && !playerChoice.equals("q"))
                    {
    
                        System.out.println();
                        System.out.println("Please input one of the given choices.");
                        playerChoice = new String();
                        continue;
    
                    }

                    pause(1000);

                    System.out.println();
    
                    break;
    
                }

                dlAces = softHand(dlHand, dlHandCount);

                plAces = softHand(plHand, plHandCount);

                switch(playerChoice)
                {

                    case "s":

                        System.out.println("Dealer flips over their face down card. They have:\n " + dlHand[0] + " " + dlHand[1] + " (" + dlHandValue + ")\n");

                        pause(2000);

                        while(dlHandValue < 17)
                        {

                            drawnCardParameters = randomCard(usedDeck, cardsInShoe);

                            if(dlHandValue + drawnCardParameters[1] > 21 && drawnCardParameters[0] == 11)
                            {

                                drawnCardParameters[1] = 1;

                            }

                            if(dlHandValue + drawnCardParameters[1] > 21 && dlAces > 0)
                            {

                                dlAces--;
                                dlHandValue -= 10;

                            }

                            dlHandValue += drawnCardParameters[1];

                            dlHand[dlHandCount-1] = usedDeck[drawnCardParameters[0]];

                            dlHandCount++;
            
                            System.out.println("The dealer hit and got " + usedDeck[drawnCardParameters[0]] + " (" + drawnCardParameters[1] + ")");

                            System.out.println("Their total now is " + dlHandValue + ".\n");

                            usedDeck = cardRemoval(usedDeck, cardsInShoe, drawnCardParameters[0]);
            
                            cardsInShoe--;

                            pause(2000);

                        }

                        System.out.println("Dealer's total is " + dlHandValue + ".");
                        System.out.println("Your total is " + plHandValue + ".\n");

                        gameOver = true;
                    
                    break;

                    case "d":

                        do{

                            drawnCardParameters = randomCard(usedDeck, cardsInShoe);

                            if(plHandValue + drawnCardParameters[1] > 21 && drawnCardParameters[1] == 11)
                            {

                                drawnCardParameters[1] = 1;

                            }

                            if(plHandValue + drawnCardParameters[1] > 21 && plAces > 0)
                            {

                                plAces--;
                                plHandValue -= 10;

                            }
    
                            plHandValue += drawnCardParameters[1];
                            
                            plHandCount++;

                            plHand[plHandCount-1] = usedDeck[drawnCardParameters[0]];
    
                            System.out.println("You hit and got " + usedDeck[drawnCardParameters[0]] + " (" + drawnCardParameters[1] + ")");
    
                            System.out.println("Your total now is " + plHandValue + ".\n");

                            pause(1000);
    
                            usedDeck = cardRemoval(usedDeck, cardsInShoe, drawnCardParameters[0]);
    
                            cardsInShoe--;

                            while(true && plHandValue < 21)
                            {
                                
                                try
                                {
            
                                    System.out.println("You have " + plHandValue + ":");
                                    
                                    for(int i = 0; i < plHandCount; i++)
                                    {

                                        System.out.println(plHand[i]);

                                    }

                                    System.out.println("The dealer has:\n" + dlHand[0] + " (" + dlStHandValue[0] + ")" + "\nDo you (s)tand or (d)raw a card?");
            
                                    playerChoice = input.next();
                
                                }
                                catch(Exception InputMismatchException)
                                {
            
                                    System.out.println("Please input a choice in the form of a letter.");
                                    input = new Scanner(System.in);
            
                                }
            
                                playerChoice = playerChoice.toLowerCase();
                
                                if(!playerChoice.equals("s") && !playerChoice.equals("d") && !playerChoice.equals("q"))
                                {
                
                                    System.out.println();
                                    System.out.println("Please input one of the given choices.");
                                    playerChoice = new String();
                                    continue;
                
                                }
            
                                pause(1000);
            
                                System.out.println();
                
                                break;
                
                            }

                        }
                        while(!playerChoice.equals("s") && plHandValue < 21);

                        if(plHandValue > 21)
                        {

                            System.out.println("You've gone and busted my good man.");
                            gameOver = true;

                        }

                        else{

                            System.out.println("Dealer flips over their face down card. They have:\n " + dlHand[0] + " " + dlHand[1] + " (" + dlHandValue + ")\n");

                            pause(2000);

                            while(dlHandValue < 17)
                            {

                                drawnCardParameters = randomCard(usedDeck, cardsInShoe);

                                if(dlHandValue + drawnCardParameters[1] > 21 && drawnCardParameters[0] == 11)
                                {

                                    drawnCardParameters[1] = 1;

                                }

                                if(dlHandValue + drawnCardParameters[1] > 21 && dlAces > 0)
                                {

                                    dlAces--;
                                    dlHandValue -= 10;

                                }

                                dlHandValue += drawnCardParameters[1];

                                dlHand[dlHandCount-1] = usedDeck[drawnCardParameters[0]];

                                dlHandCount++;
            
                                System.out.println("The dealer hit and got " + usedDeck[drawnCardParameters[0]] + " (" + drawnCardParameters[1] + ")");

                                System.out.println("Their total now is " + dlHandValue + ".\n");

                                usedDeck = cardRemoval(usedDeck, cardsInShoe, drawnCardParameters[0]);
            
                                cardsInShoe--;

                                pause(2000);

                            }

                            System.out.println("Dealer's total is " + dlHandValue + ".");
                            System.out.println("Your total is " + plHandValue + ".\n");

                            gameOver = true;

                        }
                        

                    break;

                    case "dd":

                    break;

                    case "sp":

                    break;
                }

            }

            if(plBlackjack == true)
            {

                System.out.println("You win! You get " + bet*3 + "chips.");

            }

            if((dlHandValue < plHandValue || dlHandValue > 21) && plHandValue <= 21)
            {

                System.out.println("You win! You get " + bet*2 + " chips.\n");
                ownedChips += bet*2;

            }  
            
            else if(dlHandValue == plHandValue)
            {

                System.out.println("It's a push. You get your bet back.\n");
                ownedChips += bet;

            }

            else
            {

                System.out.println("The dealer wins this one.\n");

            }

            if(shufflePercent == cardsInShoe/originalSize*100 || deckCount == 1)
            {

                cardsInShoe = originalSize;

                usedDeck = shuffle(originalDeck, cardsInShoe);

                System.out.println("The deck has been shuffled.\n");

            }

        }
        while(!playerChoice.equals("q") && ownedChips != 0);

        if(ownedChips == 0)
        {

            System.out.println("You're all out of chips. Better luck next time.");

        }

        input.close();
    
    }
    

    // Function that chooses a random card from the shoe, returns its face value and initials

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

    public static String[] cardRemoval(String[] usedDeck, int cardsInShoe, int drawnCardIndex)
    {

        usedDeck[drawnCardIndex] = usedDeck[cardsInShoe-1];

        return usedDeck; // need to add "cardsInShoe--;" after calling this function

    }


    // Function to shuffle deck

    public static String[] shuffle(String[] usedDeck, int cardsInShoe)
    {

        String[] temporaryDeck = new String[cardsInShoe];

        Random ran = new Random();

        int index = 0;

        for(int i = cardsInShoe-1; i > 0; i--)
        {

            index = ran.nextInt(i);

            temporaryDeck[i] = usedDeck[index];

            usedDeck = cardRemoval(usedDeck, i+1, index); // Shoe size "i" is +1 because otherwise the index is wrong in cardRemoval function

        }

        temporaryDeck[0] = usedDeck[0];

        return temporaryDeck;

    }

    // Pauses the game for the amount of time passed to the function

    public static void pause(int time)
    {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static int softHand(String[] passedHand, int cardCount)
    {

        int acesInHand = 0;

        for(int i = 0; i < cardCount; i++)
        {

            if(passedHand[i].toCharArray()[0] == 'A')
            {

                acesInHand++;

            }

        }

        return acesInHand;

    }
}


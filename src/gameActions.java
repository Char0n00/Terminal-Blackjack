
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class gameActions extends gameInformation{

    // Declaring variables
	
	ArrayList<ArrayList<String>> splits = new ArrayList<ArrayList<String>>();
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


    // addCardsToDisplay() - adds the dealt cards to either the players or dealers lists, that can be displayed by calling displayCards() method.
    // It is called from within the drawCard() method and doesn't need to get called seperately
    // addingFor: either "player" or "dealer", choose for who is the card being added
    // cardCode: code of the card being added to the display list
    // dealerFaceDown: "BC" - add a card for the dealer and show only it's back. "FL" - flip over the hidden card for the dealer and add no new ones. "ND" - add cards to display normally 
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


    // displayCards() - displays all the cards that the dealer and the player holds, displays their totals and the value of each card underneath.
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
    
    
    ArrayList<String> Split0 = new ArrayList<String>();
    ArrayList<String> Split1 = new ArrayList<String>();
    ArrayList<String> Split2 = new ArrayList<String>();
    
    
    ArrayList<String> Split00 = new ArrayList<String>();
    ArrayList<String> Split01 = new ArrayList<String>();
   
    
    
    
   public boolean split(List<String> playerHand)
    
    {
    	if (splits.size() < 4)
    	{
    		Split00.add(playerHand.get(0));
    		Split01.add(playerHand.get(1));
    		splits.add(Split00);
    		splits.add(Split01);
    		Split00.clear();
    		Split01.clear();
    		return true;
    	
    	} 	
    	return false;
    }
   

}


// Card value 2 - 10 automatic generation. Char0n

public class CardGen {

    public static void main(String[] args) {

        // Take the code of the card from deck array. Array size (52 - 52*x) is set by
        // user.
        // The code should be in the format ValueSuit (ex.: QH (Queen of hearts))

        String[] exampleDeck = { "2C", "3H", "4S", "5D", "6H", "7S", "8C", "9D", "1D" }; // 1 will be 10, and A will be ace

        int cardsToGenerate = exampleDeck.length;

        String[] generatedCard = new String[9];

        int[] linesGeneration = {0, 0, 0, 0, 0};

        char cardSymb = ' ';

        // Main program loop

        for (int i = 0; i <= cardsToGenerate; i++) {

            // some code....

            // Set the symbol of the card

            switch(exampleDeck[i].toCharArray()[1])
            {

                case 'C': cardSymb = '♣';
                break;
                case 'D': cardSymb = '♦';
                break;
                case 'H': cardSymb = '♥';
                break;
                case 'S': cardSymb = '♠';
                break;

            }

            // Start card symbol generation;

            int symbToDistr = exampleDeck[i].toCharArray()[0] - '0';

            if(symbToDistr == 1)
            {

                symbToDistr = 10;

            }

            while(symbToDistr > 0)
            {
                
                int firstLn = 0;
                int lastLn = 4;

                if(linesGeneration[firstLn] == 0 && linesGeneration[lastLn] == 0)
                {

                    linesGeneration[firstLn] += 1;
                    linesGeneration[lastLn] += 1;
                    
                    symbToDistr -= 2;

                }

                if(symbToDistr % 2 == 0 && symbToDistr <= 8)
                {

                    linesGeneration[2] += 2;
                    symbToDistr -= 2;

                }

                if(symbToDistr % 2 == 0 && symbToDistr >= 8)
                {

                    linesGeneration[1] += 2;
                    linesGeneration[3] += 2;
                    symbToDistr -= 2;

                }

                if(symbToDistr == 10)
                {

                    linesGeneration[2] += 2;

                }

                if(symbToDistr % 2 != 0) // finds where to place the 1 middle symbol
                {

                    for(int j = 1; j < 4; j++)
                    {

                        if(linesGeneration[j-1] == 0 && linesGeneration[j+1] == 0)
                        {

                            linesGeneration[j] += 1;
                            symbToDistr--;

                        }
                        
                        else if(linesGeneration[j-1] == 2 && linesGeneration[j+1] == 2)
                        {

                            linesGeneration[j] += 1;
                            symbToDistr--;

                        }
                        
                    }
                    
                }

            }

            // Output card in string array form

            for(int j = 0; j < 5; j++)
            {

                int alignment = 2;

                switch(linesGeneration[j])
                {

                    case 1: generatedCard[alignment] = " " + cardSymb + " ";
                    break;
                    case 2: generatedCard[alignment] = cardSymb + " " + cardSymb;
                    break;

                }

                alignment++;

            }

            // Add borders and top and bottom symbols for the generated card.

 
            generatedCard[0] = "╭─────────╮";
            generatedCard[8] = "╰─────────╯";

            int faceValue = exampleDeck[i].toCharArray()[0] - '0';

            if(faceValue == 1)
            {

                generatedCard[1] ="|" + "10" + cardSymb + "      |"; 
                generatedCard[6] ="|" + "     " + cardSymb + "10" + "|";

            }

            else
            {

                generatedCard[1] = "|" + faceValue + cardSymb + "       |";
                generatedCard[6] = "|" + "       " + cardSymb + faceValue + "|";

            }

            for(int j = 2; j < 7; j++)
            {

                generatedCard[j] = "|" + "  " + generatedCard[j] + "  " + "|";

            }

            // Code for printing cards side by side

            for(int j = 0; j < 9; j++)
            {

                System.out.println(generatedCard[j]);

            }

        }

    }

}

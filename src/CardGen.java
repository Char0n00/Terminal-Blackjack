
// Card value 2 - 10 automatic generation. Char0n

public class CardGen {

    public static void main(String[] args) {

        // Take the code of the card from deck array. Array size (52 - 52*x) is set by
        // user.
        // The code should be in the format ValueSuit (ex.: QH (Queen of hearts))

        String[] exampleDeck = { "2C", "3H", "4S", "5D", "6H", "7S", "8C", "9D", "1D" }; // 1 will be 10, and A will be ace

        int cardsToGenerate = exampleDeck.length;

        // Main program loop

        for (int i = 0; i <= cardsToGenerate; i++) {

            String[] generatedCard = {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "};

            int[] linesGeneration = {0, 0, 0, 0, 0};
    
            char cardSymb = ' ';

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

            int firstLn = 0;
            int lastLn = 4;

            int placedSymb = 0;

            int c = 1;

            while(symbToDistr > 0)
            {

                
                if(linesGeneration[firstLn] < 2 && linesGeneration[lastLn] < 2 && symbToDistr >= 2)
                {

                    linesGeneration[firstLn] += 1;
                    linesGeneration[lastLn] += 1;

                    symbToDistr -= 2;
                    placedSymb += 2;
                    
                    System.out.println("Yes top and bottom");

                    continue;

                }

                else if((placedSymb >= 4 && symbToDistr >= 5))
                {

                    linesGeneration[firstLn+c] += 2;
                    linesGeneration[lastLn-c] += 2;

                    symbToDistr -= 4;
                    placedSymb += 4;

                    System.out.println("Yes placed symbols 4");

                    c++;

                    continue;

                }

                else
                {

                    for(int j = 1; j < 4; j++)
                    {

                        if(linesGeneration[j-1] == 0 && linesGeneration[j+1] == 0 && linesGeneration[j] < 2)
                        {

                            linesGeneration[j] += 1;
                            symbToDistr--;

                            System.out.println("Yes one between empty lines");

                            break;

                        }
                        
                        else if(linesGeneration[j-1] == 2 && linesGeneration[j+1] == 2 && linesGeneration[j] < 1)
                        {

                            linesGeneration[j] += 1;

                            System.out.println("Yes one between full lines");

                            symbToDistr--;
                            break;

                        }
                        
                    }

                } 

            }


            // Output card in string array form

            int alignment = 2;

            for(int j = 0; j < 5; j++)
            {

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

            System.out.println(faceValue);

            if(faceValue == 1)
            {

                generatedCard[1] ="│" + "10" + cardSymb + "      │"; 
                generatedCard[7] ="│" + "      " + cardSymb + "10" + "│";

            }

            else
            {

                generatedCard[1] = "│" + faceValue + cardSymb + "       │";
                generatedCard[7] = "│" + "       " + cardSymb + faceValue + "│";

            }

            for(int j = 2; j <= 6; j++)
            {

                generatedCard[j] = "│" + "   " + generatedCard[j] + "   " + "│";

            }

            // Code for printing cards side by side

            for(int j = 0; j < 9; j++)
            {

                System.out.println(generatedCard[j]);

            }

            for(int j = 0; j < 5; j++)
            {

                System.out.println(linesGeneration[j]);

            }

        }

    }

}

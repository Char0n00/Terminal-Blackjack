
// Card value 2 - 10 automatic generation. Char0n

public class CardGen {

    public static void main(String[] args) {

        // Take the code of the card from deck array. Array size (52 - 52*x) is set by
        // user.
        // The code should be in the format ValueSuit (ex.: QH (Queen of hearts))

        String[] exampleDeck = { "2C", "3H", "4S", "5D", "6H", "7S", "8C", "9D", "1D" }; // 1 will be 10, and A will be ace

        int cardsToGenerate = exampleDeck.length;

        String[] generatedCard = {"", " ", " ", " ", " "};

        int[] linesGeneration = {0, 0, 0, 0, 0};

        boolean lineEmpty = true;

        char cardSymb;

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

            // I don't know what this is anymore, soryr

            // Write the code like this:
            // Add symbol to first line if empty, then last one if empty.
            //   If even number of symbols to add, add to first and last again.
            //   If odd number of symbols of add, add to middle line.
            // If first and last lines have two symbols, and there are even number (but less than 6) of symbols left, add them to middle line.
            // If first and last lines have two symbols, and there are more than six symbols left, add them to two lines right below the first and right above the last, continue from there.
            // When this ends, pass the array to a void that centers the symbols in places where there are one, and left and right aligns them where there are two.

            int symbToDistr = exampleDeck[i].toCharArray()[0] - '0';

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

                if(symbToDistr % 2 != 0) // finds where to place the 1 middle symbol
                {

                    for(int j = 1; j < 4; j++)
                    {

                        if(linesGeneration[i-1] == 0 && linesGeneration[i+1] == 0)
                        {

                            linesGeneration[i] += 1;
                            symbToDistr--;

                        }
                        
                        else if(linesGeneration[i-1] == 2 && linesGeneration[i+1] == 2)
                        {

                            linesGeneration[i] += 1;
                            symbToDistr--;

                        }
                        
                    }
                    
                }


                if(symbTo)



            }

            // Add borders and top and bottom symbols for the generated card.



            // Output card in string array form




            // Code for printing cards side by side

        }

    }

}

// Suits - Hearts, Diamonds, Spades, Clubs

┌───────── ╮
│Q♥        │
│  ╭──╮    │
│  │  │    │
│  │  │    |
|  |  │    |
|  |  |    |
│  ╰──╲    │
|        ♥Q│
└─────────┘

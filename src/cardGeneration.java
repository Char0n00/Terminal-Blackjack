// Card value 2 - 10 automatic generation. Char0n
// Card presets. Tomukas10

class cardGeneration {

    // Take the code of the card from deck array. Array size (52 - 52*x) is set by
    // user.
    // The code should be in the format ValueSuit (ex.: QH (Queen of hearts))

    // Main program loop

    char cardSymb = ' ';
    String[] generatedCard = {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "};


    
    String[] aceOfHearts = {
        "╭─────────╮",
        "│A♥       │",
        "│  _  _   │",
        "│ / \\/ \\  │",
        "│ \\    /  │",
        "│  \\  /   │",
        "│   \\/    │",
        "│       ♥A│",
        "╰─────────╯"
    };  
    
    String[] aceOfClubs = {
        "╭─────────╮",
        "│A♣  _	  │",
        "│   /│\\	│",
        "│   \\│/	│",
        "│  /\\│/\\  │",
        "│  \\/│\\/  │",
        "│   ─'─   │",
        "│       ♣A│",
        "╰─────────╯"
    };
    
    String[] aceOfSpades = {
        "╭─────────╮",
        "│A♠       │",
        "│    ^    │",
        "│   / \\   │",
        "│  /   \\  │",
        "│ /     \\ │",
        "│ \\_/│\\_/ │",
        "│       ♠A│",
        "╰─────────╯"
    };
    
    String[] aceOfDiamonds = {
        "╭─────────╮",
        "│A♦       │",
        "│   /\\    │",
        "│  /  \\   │",
        "│ /    \\  │",
        "│ \\    /  │",
        "│  \\  /   │",
        "│   \\/  ♥A│",
        "╰─────────╯"
    };
    
    String[] backOfCards = {
        "╭─────────╮",
        "│XXXXXXXXX│",
        "│/////////│",
        "│XXXXXXXXX│",
        "│\\\\\\\\\\\\\\\\\\│",
        "│XXXXXXXXX│",
        "│/////////│",
        "│XXXXXXXXX│",
        "╰─────────╯"
    };

    public String[] generation(String cardCode)
    {

        // Set the symbol of the card

        int[] linesGeneration = { 0, 0, 0, 0, 0 };
        char cardSymb = ' ';
        String[] generatedCard = {"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "};

        switch (cardCode.toCharArray()[1]) {

            case 'C':
                cardSymb = '♣';
                break;
            case 'D':
                cardSymb = '♦';
                break;
            case 'H':
                cardSymb = '♥';
                break;
            case 'S':
                cardSymb = '♠';
                break;

        }

        String[] jack = {
            "╭─────────╮",
            "│J" + cardSymb + "       │",
            "│     │   │",
            "│     │   │",
            "│     │   │",
            "│     │   │",
            "│  \\__/   │",
            "│       " + cardSymb + "J│",
            "╰─────────╯"
        };
            
        String[] queen = {
            "╭─────────╮",
            "│Q" + cardSymb + "       │",
            "│  ╭───╮  │",
            "│  │   │  │",
            "│  │   │  │",
            "│  │   │  │",
            "│  ╰───╲  │",
            "│       " + cardSymb + "Q│",
            "╰─────────╯"
        };
               
        String[] king = {
            "╭─────────╮",
            "│K" + cardSymb + "       │",
            "│  │  /   │",
            "│  │ /    │",
            "│  │ \\    │",
            "│  │  \\   │",
            "│  │   \\  │",
            "│       " + cardSymb + "K│",
            "╰─────────╯"
        };  

        
        // Start card symbol generation;

        switch (cardCode.toCharArray()[0])
        {
            


            //First gets the preset card cases out of the way

            case 'K': generatedCard = king;
            break;
            case 'Q': generatedCard = queen;
            break;
            case 'J': generatedCard = jack;
            break;

            case 'A':
                
                switch(cardCode.toCharArray()[1])
                {

                    case 'H':   generatedCard = aceOfHearts;
                    break;
                    case 'C':   generatedCard = aceOfClubs;
                    break;
                    case 'S':   generatedCard = aceOfSpades;
                    break;
                    case 'D':   generatedCard = aceOfDiamonds;
                    break;
                }

            break;

            // Card back case

            case 'B':
                
                generatedCard = backOfCards;

            break;
            
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':

            int symbToDistr = cardCode.toCharArray()[0] - '0';

            if (symbToDistr == 1) {
    
                symbToDistr = 10;
    
            }
    
            int firstLn = 0;
            int lastLn = 4;
    
            int placedSymb = 0;
    
            int c = 1;
    
            while (symbToDistr > 0) {
    
                if (linesGeneration[firstLn] < 2 && linesGeneration[lastLn] < 2 && symbToDistr >= 2) {
    
                    linesGeneration[firstLn] += 1;
                    linesGeneration[lastLn] += 1;
    
                    symbToDistr -= 2;
                    placedSymb += 2;
    
                    continue;
    
                }
    
                else if ((placedSymb >= 4 && symbToDistr >= 5)) {
    
                    linesGeneration[firstLn + c] += 2;
                    linesGeneration[lastLn - c] += 2;
    
                    symbToDistr -= 4;
                    placedSymb += 4;
    
                    c++;
    
                    continue;
    
                }
    
                else {
    
                    for (int j = 1; j < 4; j++) {
    
                        if (linesGeneration[j - 1] == 0 && linesGeneration[j + 1] == 0 && linesGeneration[j] < 2) // Case if the lone symbl needs to be in the middle
                        { // for example in 3 and 5, but it is also how 6 is generated
    
                            linesGeneration[j] += 1;
                            symbToDistr--;
                            placedSymb++;
    
                            break;
    
                        }
    
                        else if (linesGeneration[j - 1] == 2 && linesGeneration[j + 1] == 2 && linesGeneration[j] < 1) // case if symbl needs to be in between two full lines
                        { // for example in 7, 8, 9
    
                            linesGeneration[j] += 1;
    
                            symbToDistr--;
                            placedSymb++;
                            break;
    
                        }
    
                        else if (placedSymb == 9 && linesGeneration[j - 1] == 2 && linesGeneration[j + 1] == 2) // Exception for face value 10
                        {
                            linesGeneration[j] += 1;
    
                            symbToDistr--;
                            placedSymb++;
                            break;
    
                        }
    
                    }
    
                }
    
            }
    
            // Output card in string array form
    
            int alignment = 2;
    
            for (int j = 0; j < 5; j++) {
    
                switch (linesGeneration[j]) {
    
                    case 1:
                        generatedCard[alignment] = " " + cardSymb + " ";
                        break;
                    case 2:
                        generatedCard[alignment] = cardSymb + " " + cardSymb;
                        break;
    
                }
    
                alignment++;
    
            }
    
            // Add borders and top and bottom symbols for the generated card.
    
            generatedCard[0] = "╭─────────╮";
            generatedCard[8] = "╰─────────╯";
    
            int faceValue = cardCode.toCharArray()[0] - '0';
    
            // System.out.println(faceValue);
    
            if (faceValue == 1) {
    
                generatedCard[1] = "│" + "10" + cardSymb + "      │";
                generatedCard[7] = "│" + "      " + cardSymb + "10" + "│";
    
            }
    
            else {
    
                generatedCard[1] = "│" + faceValue + cardSymb + "       │";
                generatedCard[7] = "│" + "       " + cardSymb + faceValue + "│";
    
            }
    
            for (int j = 2; j <= 6; j++) {
    
                generatedCard[j] = "│" + "   " + generatedCard[j] + "   " + "│";
    
            }

            break;

        }

        return generatedCard;

    }

}

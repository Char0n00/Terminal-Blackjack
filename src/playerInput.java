import java.util.Scanner;

class playerInput extends gameInformation{

    String queryLine;
    String errorLine;

    // InputOfNumbers - returns int that the user provided. Checks for InputMismatchExeptions, but not everything else.
    // String queryLine: text you want to display that explains to the player what to enter
    // String errorLine: text you want to display in case of an error
    int inputOfNumbers(String queryLine, String errorLine)
    {

        int inputNumber = 0;

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
            if(inputNumber < 0)
            {

                System.out.println("You can't enter a negative number.\n");
                pause(480);
                continue;

            }

            pause(500);

            break;

        }

        return inputNumber;

    }

    
    // InputOfText - returns string that the user provided. Checks for InputMismatchExeptions, but not everything else.
    // String queryLine: text you want to display that explains to the player what to enter
    // String errorLine: text you want to display in case of an error
    String inputOfText(String queryLine, String errorLine){

        String inputString;

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

            pause(500);

            break;

        }

        return inputString.toLowerCase();

    }

}

package com.virtualpet.asciiart;

public class AsciiArt {
    public static void printWelcomeArt() {
        String welcomeArt = """
              Welcome to PawTopia!
                    /\\_/\\  
                   ( o.o ) 
                    > ^ <  
                  
              Get ready to build your perfect pet world.!
              """;

        System.out.println(welcomeArt);
    }

    public static void printWonArt(){
        String wonArt = """
              *   *   *    *   *
           *         |     *       *
              \\      |   /     *
          *        ----*----      *
              *    /   |   \\     *
                   /    |     \\
             *   YOU WON!  *    *
              """;

        System.out.println(wonArt);
    }
}

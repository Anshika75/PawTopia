package com.virtualpet.asciiart;

import com.virtualpet.pet.PetType;

public class PetArt {

    // Method to print ASCII art based on pet type
    public static void printPetArt(PetType petType, String petName) {
        switch (petType) {
            case CAT:
                printCatArt(petName);
                break;
            case DOG:
                printDogArt(petName);
                break;
            case BIRD:
                printBirdArt(petName);
                break;
            default:
                System.out.println("Unknown pet type.");
        }
    }

    // ASCII art for Cat using text block
    private static void printCatArt(String petName) {
        System.out.println("""
                /\\_/\\\\
               ( o.o )
                > ^ <

                Hello, my name is %s and I am your virtual cat! Meow!
                """.formatted(petName));
    }

    // ASCII art for Dog using text block
    private static void printDogArt(String petName) {
        System.out.println("""
                 / \\__
                (    @\\____
                 /         O
                /   (_____ /

                Hello, my name is %s and I am your virtual dog! Woof!
                """.formatted(petName));
    }

    // ASCII art for Bird using text block
    private static void printBirdArt(String petName) {
        System.out.println("""
                   \\ \\
                    ( o> 
                    //\\ \\ 
                   //  \\ \\

                   Hello, my name is %s and I am your virtual bird! Chirp!
                   """.formatted(petName));
    }

    public static void printPetArt(PetType petType, String petName, String activity) {
        switch (petType) {
            case CAT:
                printCatArt(petName, activity);
                break;
            case DOG:
                printDogArt(petName, activity);
                break;
            case BIRD:
                printBirdArt(petName, activity);
                break;
            default:
                System.out.println("Unknown pet type.");
        }
    }

    // ASCII art for Cat
    private static void printCatArt(String petName, String activity) {
        switch (activity) {
            case "SLEEP":
                System.out.println("""
                       |\\      _,,---,,_
                  ZZZzz /,`.-'`'    -.  ;-;;,_  
                       |,4-  ) )-,_. ,\\ (  `'-'
                      '---''(_/--'  `-'\\_)

                       Purr... %s is napping.
                """.formatted(petName));
                break;
            case "EATING":
                System.out.println("""
                       |\\__/,|   (`\\
                       |o o  |__ _) )
                     _.( T   )  `  /
                    / . .   |   _.' 
                   (   (._.|  |

                       Meow! %s is enjoying a meal.
                """.formatted(petName));
                break;
            case "BATHING":
                System.out.println("""
                        /\\_/\\
                       ( o.o )  
                       (  =  ) 
                       /   | \\
                      (____|__)

                    Splish splash! %s is taking a bath.
                """.formatted(petName));
                break;
            case "PLAYING":
                System.out.println("""
                           /\\_/\\
                          ( o.o )
                           > ^ <  
                          /     \\     
                         (       )
                          `-----'

Meow! %s is playing with its favorite toy.
                """.formatted(petName));
                break;
        }
    }

    // ASCII art for Dog
    private static void printDogArt(String petName, String activity) {
        switch (activity) {
            case "SLEEP":
                System.out.println("""
                    __      _
                   o'')}____//
                    `_/      )
                    (_(_/-(_/

                   Zzz... %s is sleeping.
                """.formatted(petName));
                break;
            case "EATING":
                System.out.println("""
                           __
                          /  \\_____
                          /       O
                          /   (_____/
                          /_____/   U
                          |   |    |

                        Woof! %s is enjoying its meal.
                        """.formatted(petName));
                break;
            case "BATHING":
                System.out.println("""
                      __
                    /  \\_
                   |     |__
                    \\____|__\\
                       || ||

                   Splish splash! %s is taking a bath.
                """.formatted(petName));
                break;
            case "PLAYING":
                System.out.println("""
                   o____
                    || || 
                   /  o  \\
                  (   --  )
                   \\____/

                   Woof! %s is playing fetch.
                """.formatted(petName));
                break;
        }
    }

    // ASCII art for Bird
    private static void printBirdArt(String petName, String activity) {
        switch (activity) {
            case "SLEEP":
                System.out.println("""
                       zZz
                     \\ 
                     (o>
                     //\\ 
                    v_/_

                   Chirp... %s is resting.
                """.formatted(petName));
                break;
            case "EATING":
                System.out.println("""
                      .----.
                      |o_o |
                      |:_/ |
                     //    \\
                    (|     | )
                  /'\\_   _/`\\
                  \\___)=(___//

Chirp! %s is enjoying its food.
                """.formatted(petName));
                break;
            case "BATHING":
                System.out.println("""
                      \\   //
                       ( o )
                     <(     )>
                       \\   //
                       Clean feathers, chirp!
        
                   %s is splashing in the water.
                """.formatted(petName));
                break;
            case "PLAYING":
                System.out.println("""
                       \\    / 
                        (o> 
                      __/\\__/\\
                     ('-o^o-') 

                   Chirp! %s is flying around happily.
                """.formatted(petName));
                break;
        }
    }
}

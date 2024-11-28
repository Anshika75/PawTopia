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
}

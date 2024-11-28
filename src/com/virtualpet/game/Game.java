package com.virtualpet.game;

import com.virtualpet.asciiart.AsciiArt;
import com.virtualpet.pet.PetType;

public class Game {
    public static void main(String[] args) {
        // Display welcome message and ASCII art
        AsciiArt.printWelcomeArt();
        
        PetType selectedPet = PetType.choosePet();
        System.out.println("You chose a " + selectedPet.name() + "!");
        // Placeholder for next steps
        System.out.println("Game starting... (next steps will be implemented soon!)");
    }
}

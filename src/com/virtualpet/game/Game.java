package com.virtualpet.game;

import com.virtualpet.asciiart.AsciiArt;
import com.virtualpet.pet.PetType;
import com.virtualpet.pet.PetBreed;
import com.virtualpet.pet.PetColor;
import com.virtualpet.pet.PetName;

public class Game {
    public static void main(String[] args) {
        // Display welcome message and ASCII art
        AsciiArt.printWelcomeArt();

        // Prompt for user to select a pet
        PetType selectedPet = PetType.choosePet();
        System.out.println("\nYou chose a " + selectedPet.name() + "!");

        // Prompt user to select a pet breed
        String selectedBreed = PetBreed.chooseBreed(selectedPet);
        System.out.println("\nYou chose the breed: " + selectedBreed + ".");

        // Prompt user to select a color
        String selectedColor = PetColor.chooseColor(selectedPet, selectedBreed);
        System.out.println("\nYou chose the color: " + selectedColor + ".");

        // Prompt user to set name
        String petName = PetName.namePet();

        // Display pet details
        System.out.println("\nYour pet details:");
        System.out.println("Type: " + selectedPet.name());
        System.out.println("Breed: " + selectedBreed);
        System.out.println("Color: " + selectedColor);
        System.out.println("Name: " + petName);

        // Placeholder for next steps
        System.out.println("\nGame starting... (next steps will be implemented soon!)");
    }
}

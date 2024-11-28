package com.virtualpet.game;

import com.virtualpet.asciiart.AsciiArt;

public class Game {
    public static void main(String[] args) {
        // Display welcome message and ASCII art
        AsciiArt.printWelcomeArt();
        
        // Prompt user to press ENTER to proceed
        System.out.println("Press ENTER to start...");
        try {
            System.in.read(); // Waits for user to press ENTER
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }

        // Placeholder for next steps
        System.out.println("Game starting... (next steps will be implemented soon!)");
    }
}

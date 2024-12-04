package com.virtualpet.story;

import com.virtualpet.pet.Pet;

import java.util.Scanner;

public class TheLostTreasureStory extends Story {

    public TheLostTreasureStory() {
        super("The Lost Treasure", 1, 50, 
              "You find an old map leading to hidden treasure. Do you follow it?",
              100, 2); // 100 bank balance and 2 game level increment
    }

    @Override
    public boolean playStory(Pet pet) {
        System.out.println(getDescription());
        System.out.println("1. Follow the map");
        System.out.println("2. Ignore it");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("You follow the map and find the treasure!");
            updatePetAfterWin(pet);
            return true;
        } else {
            System.out.println("You decide to ignore the map. Nothing happens.");
            return false;
        }
    }
}

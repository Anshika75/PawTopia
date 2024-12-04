package com.virtualpet.story;

import com.virtualpet.pet.Pet;

import java.util.Scanner;

public class TheLostTreasureStory extends Story {

    public TheLostTreasureStory() {
        super("The Lost Treasure", 0, 50,
                "You and your pet discover an old map leading to hidden treasure. Your pet seems excited! Do you follow it?",
                150, 2); // Base reward of 100 bank balance and 2 game level increment
    }

    @Override
    public boolean playStory(Pet pet) {
        System.out.println('\n' + getDescription());
        System.out.println("Your pet " + pet.getName() + " wags its tail (or reacts excitedly).");
        System.out.println("1. Follow the map");
        System.out.println("2. Ignore it and head home\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println('\n' + pet.getName() + " sniffs the map and leads the way!");
             ;
            return followMap(pet); // Call the method to handle the next steps after following the map
        } else {
            System.out.println("\nYou decide to ignore the map. " + pet.getName() + " looks disappointed.");
             ;
            return false;
        }
    }

    private boolean followMap(Pet pet) {
        System.out.println("\nYour pet " + pet.getName() + " leads you through dense forest and stops at a crossroad.");
        System.out.println("Do you trust your pet's instincts?");
        System.out.println("1. Follow " + pet.getName() + " to the mountains.");
        System.out.println("2. Choose the cave your pet seems hesitant about.");
        System.out.println("3. Turn back and leave.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return goToMountains(pet);
        } else if (choice == 2) {
             ;
            return enterCave(pet);
        } else {
            System.out.println("\nYou decide to turn back. " + pet.getName() + " gives you a puzzled look.");
             ;
            return false;
        }
    }

    private boolean goToMountains(Pet pet) {
        System.out.println('\n' + pet.getName() + " excitedly sprints ahead towards the mountains.");
        System.out.println("As you reach the peak, you see a mysterious figure offering you a choice.");
        System.out.println("1. Trust the figure and let them guide you to the treasure.");
        System.out.println("2. Decline their help and rely on " + pet.getName() + "'s instincts.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nThe guide takes you through hidden trails, leading you to the treasure!");
             ;
            updatePetReward(pet, 150, 1); // Reward: 150 bank balance and 1 level increment
        } else {
            System.out.println('\n' + pet.getName() + " sniffs the air and finds a hidden path!");
            System.out.println("The path leads you directly to a golden chest filled with treasures!");
             ;
            updatePetReward(pet, 200, 2); // Greater reward for trusting the pet
        }

        return true;
    }

    private boolean enterCave(Pet pet) {
        System.out.println('\n' + pet.getName() + " hesitates at the cave entrance but follows you loyally.");
        System.out.println("The cave is full of echoes and strange sounds. Deeper inside, you find two paths.");
        System.out.println("1. Take the path lit by glowing crystals that seems safe.");
        System.out.println("2. Take the dark path, even though " + pet.getName() + " growls uneasily.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println('\n' + pet.getName() + " leads you confidently down the crystal-lit path.");
            System.out.println("You find a small treasure chest filled with ancient coins!");
             ;
            updatePetReward(pet, 100, 1); // Smaller reward for easier path
        } else {
            System.out.println("\nYou take the dark path. Suddenly, a giant spider blocks your way!");
            System.out.println("Do you and " + pet.getName() + " face the spider together?");
            System.out.println("1. Fight the spider.");
            System.out.println("2. Retreat and try another path.\n");

            int fightChoice = scanner.nextInt();

            if (fightChoice == 1) {
                System.out.println('\n' + pet.getName() + " courageously barks and distracts the spider while you defeat it!");
             ;
            updatePetReward(pet, 250, 3); // Greater reward for overcoming a tough challenge
            } else {
                System.out.println("\nYou retreat safely, but you miss the treasure.");
             ;
            return false; // No reward for retreating
            }
        }

        return true;
    }

    // Function to update the pet's reward directly without creating Reward objects
    private void updatePetReward(Pet pet, int balance, int levelIncrement) {
        pet.setBankBalance(pet.getBankBalance() + balance);
        pet.setGameLevel(pet.getGameLevel() + levelIncrement);
        System.out.println("\nYour pet " + pet.getName() + " helped you gain " + balance + " coins!");
        System.out.println(pet.getName() + " feels accomplished as your game level increased by " + levelIncrement + "!\n");
    }
}

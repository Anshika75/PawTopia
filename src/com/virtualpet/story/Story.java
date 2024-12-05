package com.virtualpet.story;

import com.virtualpet.pet.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Story {
    private String name;
    private int levelRequirement;
    private int ticketCost;
    private String description;
    private int rewardBankBalance;
    private int rewardGameLevelIncrement;

    public Story(String name, int levelRequirement, int ticketCost, String description,
            int rewardBankBalance, int rewardGameLevelIncrement) {
        this.name = name;
        this.levelRequirement = levelRequirement;
        this.ticketCost = ticketCost;
        this.description = description;
        this.rewardBankBalance = rewardBankBalance;
        this.rewardGameLevelIncrement = rewardGameLevelIncrement;
    }

    public String getName() {
        return name;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getTicketCost() {
        return ticketCost;
    }

    public String getDescription() {
        return description;
    }

    public int getRewardBankBalance() {
        return rewardBankBalance;
    }

    public int getRewardGameLevelIncrement() {
        return rewardGameLevelIncrement;
    }

    // Abstract method to handle the story play logic
    public abstract boolean playStory(Pet pet);

    public boolean canPlay(Pet pet) {
        return pet.getGameLevel() >= levelRequirement && pet.getBankBalance() >= ticketCost;
    }

    // Static method to manage and display all available stories
    public static void displayAndPlayStories(Pet pet) {
        List<Story> stories = initializeStories();

        System.out.println("\nAvailable Stories:\n");
        for (int i = 0; i < stories.size(); i++) {
            Story story = stories.get(i);
            System.out.println((i + 1) + ". " + story.getName() +
                    " (Level " + story.getLevelRequirement() +
                    ", Ticket Cost: $" + story.getTicketCost() + ")");
            System.out.println("Description: " + story.getDescription());
            System.out.println("Reward: Bank Balance +$" + story.getRewardBankBalance() +
                    ", Game Level + " + story.getRewardGameLevelIncrement());
            System.out.println();
        }
        System.out.println("\nNote: The points and level points will only be credited if you win the story.");
        System.out.println(
                "Quick Tip: The points and levels are minimum value, if you dwell deeper, you may get more rewards. These are just the base rewards.");
        System.out.println("\nChoose a story to play (enter the number): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= stories.size()) {
            Story selectedStory = stories.get(choice - 1);

            if (selectedStory.canPlay(pet)) {
                pet.setBankBalance(pet.getBankBalance() - selectedStory.getTicketCost());
                boolean won = selectedStory.playStory(pet);
                if (won) {
                    System.out.println("\nYou won the story! Bank balance and game level updated.");
                     ;
                    // Update pet after winning
                } else {
                     ;
                    System.out.println("\nYou lost the story. Better luck next time.");
                }
            } else {
                 ;
                System.out
                        .println("\nYou cannot play this story. You need to meet the level and balance requirements.");
            }
        } else {
            System.out.println("\nInvalid choice. Please try again.");
        }
    }

    // Method to initialize and return all stories
    private static List<Story> initializeStories() {
        List<Story> stories = new ArrayList<>();
        stories.add(new TheLostTreasureStory());
        stories.add(new TheShadowedIsleStory());
        stories.add(new TheKidnappedPetStory());
        stories.add(new TheMysteriousCase());
        return stories;
    }
}

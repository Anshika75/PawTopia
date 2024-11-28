package com.virtualpet.pet;

import com.virtualpet.activity.Activity;
import com.virtualpet.shop.Shop;

public class Pet {
    private PetType petType;
    private String name;  // Store pet name from PetName
    private static int bankBalance = 5000;
    private int energy = 100, gameLevel = 1, food = 100, bath = 100, sleep = 100, play = 100, happiness = 100, age = 0;

    public Pet(PetType petType, String name, int bankBalance) {
        this.petType = petType;
        this.name = name;  // Initialize the name
        Pet.bankBalance = bankBalance;
    }

    public Pet(PetType petType, String name) {
        this.petType = petType;
        this.name = name;  // Initialize the name
    }

    public PetType getPetType() {
        return petType;
    }

    public String getName() {
        return name;  // Getter for the pet's name
    }

    public static int getBankBalance() {
        return bankBalance;
    }

    public static void setBankBalance(int bankBalance) {
        Pet.bankBalance = bankBalance;
    }

    public void displayMetrics() {
        System.out.println("\n=== Pet Metrics ===");
        System.out.println("Name: " + name);  // Display the pet's name
        System.out.println("Type: " + petType);
        System.out.println("Bank Balance: $" + bankBalance);
        System.out.println("Energy: " + energy);
        System.out.println("Game Level: " + gameLevel);
        System.out.println("Food: " + food);
        System.out.println("Bath: " + bath);
        System.out.println("Sleep: " + sleep);
        System.out.println("Play: " + play);
        System.out.println("Happiness: " + happiness);
        System.out.println("Age: " + age + " years");
    }

    public void performActivity(Activity activity) {
        System.out.println("\nActivity: " + activity.name());

        // Check if the item required for the activity is available and purchased
        boolean itemAvailable = Shop.useItemForActivity(this, activity.name(), activity);
        
        if (!itemAvailable) {
            System.out.println("You do not have the required item for this activity. Redirecting to the shop...");
            Shop.purchaseItems(this); // Pass the activity name to the shop purchase method
        } else {
            System.out.println("An item was used to enhance the activity.");
        }

        // Proceed with the activity even if no item is available
        this.food = Math.max(0, food + activity.getFoodChange());
        this.energy = Math.max(0, energy + activity.getEnergyChange());
        this.bath = Math.max(0, bath + activity.getBathChange());
        this.sleep = Math.max(0, sleep + activity.getSleepChange());
        this.play = Math.max(0, play + activity.getPlayChange());
        this.happiness = Math.max(0, happiness + activity.getHappinessChange());
        this.gameLevel = Math.max(1, gameLevel + activity.getGameLevelChange());

        System.out.println("\n" + activity.name() + " in progress.");
    }
}

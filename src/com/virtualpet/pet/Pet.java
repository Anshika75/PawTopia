package com.virtualpet.pet;

import com.virtualpet.activity.Activity;
import com.virtualpet.asciiart.PetArt;
import com.virtualpet.config.VaccinationManager;
import com.virtualpet.shop.Shop;

public class Pet {
    private PetType petType;
    private String name;
    private static int bankBalance = 1000;
    private int energy = 50, food = 50, bath = 50, sleep = 50, play = 50, happiness = 50;
    private double age = 0.0;

    public Pet(PetType petType, String name, int bankBalance) {
        this.petType = petType;
        this.name = name;
        Pet.bankBalance = bankBalance;
    }

    public Pet(PetType petType, String name) {
        this.petType = petType;
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public String getName() {
        return name;
    }

    public static int getBankBalance() {
        return bankBalance;
    }

    public double getAge() {
        return Math.round(age * 10.0) / 10.0;
    }

    public static void setBankBalance(int bankBalance) {
        Pet.bankBalance = bankBalance;
    }

    public void setAge(double age) {
        this.age = Math.round(age * 10.0) / 10.0;
    }

    public void performActivity(Activity activity) {
        System.out.println("\nActivity: " + activity.name());
        if (!Activity.canPerformActivity(food, energy, bath, sleep, play, happiness, activity)) {
            System.out.println("Activity cannot proceed. Resolve the warnings first!");
            return; // Exit without performing the activity
        }
        boolean itemAvailable = Shop.useItemForActivity(this, activity.name(), activity);

        while (!itemAvailable) {
            System.out.println("Redirecting to the shop...");
            Shop.purchaseItems(this);

            itemAvailable = Shop.useItemForActivity(this, activity.name(), activity);

            if (!itemAvailable) {
                System.out.println("Item for the activity is still unavailable. Please purchase the required item.");
                break;
            }
        }

        if (itemAvailable && Activity.canPerformActivity(food, energy, bath, sleep, play, happiness, activity)) {
            // Apply activity effects only if item is available
            this.food = Math.max(0, food + activity.getFoodChange());
            this.energy = Math.max(0, energy + activity.getEnergyChange());
            this.bath = Math.max(0, bath + activity.getBathChange());
            this.sleep = Math.max(0, sleep + activity.getSleepChange());
            this.play = Math.max(0, play + activity.getPlayChange());
            this.happiness = Math.max(0, happiness + activity.getHappinessChange());
            this.setAge(this.age + 0.2);
            PetArt.printPetArt(petType, name, activity.name());
            VaccinationManager.checkVaccination(this);
        } else {
            System.out.println("Activity cannot be performed due to missing items.");
        }
    }

    public void displayMetrics() {
        System.out.println("\nPet Stats:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Bank Balance: $" + bankBalance);
        System.out.println("Energy: " + energy);
        System.out.println("Food: " + food);
        System.out.println("Bath: " + bath);
        System.out.println("Sleep: " + sleep);
        System.out.println("Play: " + play);
        System.out.println("Happiness: " + happiness);
    }

    public void resetStats() {
        System.out.println("\nResetting all pet stats to default values...");
        this.energy = 50;
        this.food = 50;
        this.bath = 50;
        this.sleep = 50;
        this.play = 50;
        this.happiness = 50;
        this.age = 0.0;
        Pet.bankBalance = 1000; // Resetting bank balance
        System.out.println("All stats have been reset!");
    }
}

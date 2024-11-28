package com.virtualpet.shop;

import com.virtualpet.pet.Pet;
import com.virtualpet.pet.PetType;
import com.virtualpet.activity.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop {

    static class Item {
        String name;
        int price;
        boolean isBought;
        int itemLife;
        Activity[] activities;  // Link item to multiple activities

        Item(String name, int price, int itemLife, Activity[] activities) {
            this.name = name;
            this.price = price;
            this.isBought = false;
            this.itemLife = itemLife;
            this.activities = activities;
        }

        // Mark the item as bought and reset its life
        public void buyItem() {
            this.isBought = true;
            this.itemLife = 1;  // Reset item life when bought
        }

        // Reduce item life after use
        public void reduceItemLife() {
            if (itemLife > 0) {
                itemLife--;
            }
        }

        // Reset item if its life is over
        public void resetItem() {
            if (itemLife <= 0) {
                this.isBought = false;
                this.itemLife = 0;
            }
        }

        @Override
        public String toString() {
            StringBuilder activitiesList = new StringBuilder();
            for (Activity activity : activities) {
                activitiesList.append(activity).append(" ");
            }
            return name + " - $" + price + (isBought ? " (Bought)" : " (Available)") +
                    (isBought ? " - Life: " + itemLife : "") + " - Activities: " + activitiesList;
        }
    }

    private static final Map<PetType, Item[]> shopItems = new HashMap<>();

    static {
        shopItems.put(PetType.DOG, new Item[] {
            new Item("Kennel", 200, 5, new Activity[]{Activity.SLEEP}),
            new Item("Food Bowl", 50, 10, new Activity[]{Activity.EATING}),
            new Item("Bone", 25, 15, new Activity[]{Activity.EATING}),
            new Item("Ball", 20, 20, new Activity[]{Activity.PLAYING}),
            new Item("Chew Toy", 30, 10, new Activity[]{Activity.PLAYING}),
            new Item("Fur Brush", 30, 15, new Activity[]{Activity.BATHING}),
            new Item("Bath Tub", 150, 6, new Activity[]{Activity.BATHING}),
            new Item("Dog Bed", 100, 8, new Activity[]{Activity.SLEEP}),
        });

        shopItems.put(PetType.CAT, new Item[] {
            new Item("Basket", 100, 10, new Activity[]{Activity.SLEEP}),
            new Item("Food Bowl", 50, 10, new Activity[]{Activity.EATING}),
            new Item("Bath Tub", 120, 6, new Activity[]{Activity.BATHING}),
            new Item("Fur Brush", 30, 15, new Activity[]{Activity.BATHING}),
        });

        shopItems.put(PetType.BIRD, new Item[] {
            new Item("Cage", 120, 15, new Activity[]{Activity.SLEEP}),
            new Item("Food Bowl", 40, 10, new Activity[]{Activity.EATING}),
            new Item("Mirror Toy", 25, 20, new Activity[]{Activity.PLAYING}),
            new Item("Bath Tub", 100, 6, new Activity[]{Activity.BATHING}),
        });
    }

    // Method to display available items for the pet
    public static void displayShop(Pet pet) {
        System.out.println("\n=== Pet Shop ===");
        System.out.println("Your Current Bank Balance: $" + pet.getBankBalance());
        System.out.println("\nAvailable items for your " + pet.getPetType() + ":");

        Item[] items = shopItems.getOrDefault(pet.getPetType(), new Item[] {});
        for (int i = 0; i < items.length; i++) {
            items[i].resetItem();
            System.out.println((i + 1) + ". " + items[i]);
        }
    }

    // Method to purchase items for the pet
    public static void purchaseItems(Pet pet) {
        Scanner scanner = new Scanner(System.in);

        displayShop(pet);

        System.out.println("\nEnter item numbers separated by commas to purchase, or press Enter to skip:");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("You chose not to purchase any items.");
            return;
        }

        String[] itemNumbers = input.split(",");
        int totalCost = 0;

        for (String itemNumberStr : itemNumbers) {
            try {
                int itemNumber = Integer.parseInt(itemNumberStr.trim()) - 1;
                if (itemNumber < 0 || itemNumber >= shopItems.get(pet.getPetType()).length) {
                    System.out.println("Invalid item number: " + (itemNumber + 1));
                    continue;
                }

                Item selectedItem = shopItems.get(pet.getPetType())[itemNumber];
                if (selectedItem.isBought && selectedItem.itemLife > 0) {
                    System.out.println("You already own the " + selectedItem.name);
                } else if (!selectedItem.isBought && pet.getBankBalance() >= selectedItem.price) {
                    totalCost += selectedItem.price;
                    pet.setBankBalance(pet.getBankBalance() - selectedItem.price);
                    selectedItem.buyItem();
                    System.out.println("You successfully purchased: " + selectedItem.name);
                } else {
                    System.out.println("Not enough balance to purchase: " + selectedItem.name);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + itemNumberStr + ". Please enter valid numbers.");
            }
        }

        if (totalCost > 0) {
            System.out.println("\nPurchase complete! New balance: $" + pet.getBankBalance());
        } else {
            System.out.println("No items were purchased.");
        }
    }

    // Method to use an item for an activity
    public static boolean useItemForActivity(Pet pet, String itemName, Activity activity) {
        Item[] items = shopItems.getOrDefault(pet.getPetType(), new Item[] {});
        Item itemToUse = null;

        for (Item item : items) {
            if (item.name.equalsIgnoreCase(itemName) && item.isBought) {
                // Check if the item is associated with the activity
                for (Activity a : item.activities) {
                    if (a == activity) {
                        itemToUse = item;
                        break;
                    }
                }
                if (itemToUse != null) break;
            }
        }

        if (itemToUse == null || itemToUse.itemLife <= 0) {
            System.out.println("You do not have the item: " + itemName + " or it cannot be used for this activity.");
            return false;
        }

        itemToUse.reduceItemLife();
        System.out.println("Used " + itemToUse.name + " for the activity: " + activity);
        return true;
    }
}

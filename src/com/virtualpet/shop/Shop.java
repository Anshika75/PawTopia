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

        Item(String name, int price, int itemLife) {
            this.name = name;
            this.price = price;
            this.isBought = false;
            this.itemLife = itemLife;
        }

        // Mark the item as bought and reset its life
        public void buyItem() {
            this.isBought = true;
            this.itemLife = 1;  // After purchase, set the item life to 1 or the required initial value
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
                this.isBought = false; // Change item status from "bought" to "available"
                this.itemLife = 0; // Reset the item life
            }
        }

        @Override
        public String toString() {
            return name + " - $" + price + (isBought ? " (Bought)" : " (Available)") +
                    (isBought ? " - Life: " + itemLife : "");
        }
    }

    private static final Map<PetType, Item[]> shopItems = new HashMap<>();

    static {
        shopItems.put(PetType.DOG, new Item[] {
            new Item("Kennel", 200, 5),
            new Item("Food Bowl", 50, 10),
            new Item("Chain", 80, 8),
            new Item("Collar", 40, 12),
            new Item("Fur Brush", 30, 15),
            new Item("Bath Tub", 150, 6),
            new Item("Ball", 20, 20),
            new Item("Bone", 25, 15),
            new Item("Chew Toy", 30, 10),
            new Item("Dog Bed", 100, 8),
        });

        shopItems.put(PetType.CAT, new Item[] {
            new Item("Basket", 100, 10),
            new Item("Fur Brush", 30, 15),
            new Item("Wool Toy", 25, 20),
            new Item("Collar", 40, 12),
            new Item("Food Bowl", 50, 10),
            new Item("Bath Tub", 120, 6),
        });

        shopItems.put(PetType.BIRD, new Item[] {
            new Item("Cage", 120, 15),
            new Item("Feather Brush", 20, 20),
            new Item("Food Bowl", 40, 10),
            new Item("Bird Swing", 60, 5),
            new Item("Perch", 30, 15),
            new Item("Mirror Toy", 25, 20),
            new Item("Bath Tub", 100, 6),
        });
    }

    // Method to display available items for the pet
    public static void displayShop(Pet pet) {
        System.out.println("\n=== Pet Shop ===");
        System.out.println("Your Current Bank Balance: $" + pet.getBankBalance());
        System.out.println("\nAvailable items for your " + pet.getPetType() + ":");

        Item[] items = shopItems.getOrDefault(pet.getPetType(), new Item[] {});
        for (int i = 0; i < items.length; i++) {
            items[i].resetItem(); // Check if the item's life has expired and reset if necessary
            System.out.println((i + 1) + ". " + items[i]);
        }
    }

    // Method to purchase items for the pet
    public static void purchaseItems(Pet pet) {
        Scanner scanner = new Scanner(System.in);

        // Display the shop and available items for the pet type
        displayShop(pet);

        System.out.println("\nEnter item numbers separated by commas to purchase (e.g., 1, 3, 5), or press Enter to skip: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("You chose not to purchase any items.");
            return; // Exit if no input
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

                // Check if the item is already bought and has life left
                if (selectedItem.isBought && selectedItem.itemLife > 0) {
                    System.out.println("You already own the " + selectedItem.name + " with remaining life.");
                } else if (!selectedItem.isBought && pet.getBankBalance() >= selectedItem.price) {
                    totalCost += selectedItem.price;
                    pet.setBankBalance(pet.getBankBalance() - selectedItem.price);
                    selectedItem.buyItem();
                    System.out.println("You successfully purchased: " + selectedItem.name + " for $" + selectedItem.price);
                    System.out.println("Updated Bank Balance: $" + pet.getBankBalance());
                } else if (selectedItem.isBought && selectedItem.itemLife <= 0) {
                    // If the item life is over, let the user buy again
                    totalCost += selectedItem.price;
                    pet.setBankBalance(pet.getBankBalance() - selectedItem.price);
                    selectedItem.buyItem();
                    selectedItem.itemLife = 1; // Reset item life to 1 for reuse
                    System.out.println("You purchased a new " + selectedItem.name + " as the old one expired.");
                    System.out.println("Updated Bank Balance: $" + pet.getBankBalance());
                } else {
                    System.out.println("Not enough balance to purchase: " + selectedItem.name);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + itemNumberStr + ". Please enter valid numbers.");
            }
        }

        if (totalCost > 0) {
            System.out.println("\nPurchase complete! Your new balance is: $" + pet.getBankBalance());
        } else {
            System.out.println("No items were purchased.");
        }
    }

    // Method to use an item for an activity (e.g., pet care, training, etc.)
    public static boolean useItemForActivity(Pet pet, String itemName, Activity activity) {
        // Find the item for the given pet type
        Item[] items = shopItems.getOrDefault(pet.getPetType(), new Item[] {});
        Item itemToUse = null;

        // Search for the item by name
        for (Item item : items) {
            if (item.name.equalsIgnoreCase(itemName) && item.isBought) {
                itemToUse = item;
                break;
            }
        }

        // Check if the item is available and has life left
        if (itemToUse == null || itemToUse.itemLife <= 0) {
            System.out.println("You do not own the item: " + itemName + " or it is no longer usable.");
            return false; // Item cannot be used
        }

        // Use the item (e.g., reduce item life)
        itemToUse.reduceItemLife();
        System.out.println("You have used the " + itemToUse.name + " for activity: " + activity.name());
        return true; // Item use was successful
    }
}

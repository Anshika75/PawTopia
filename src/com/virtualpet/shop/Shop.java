package com.virtualpet.shop;

import com.virtualpet.pet.PetType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop {

    // Item class to store the name, price, purchased state, and item life
    static class Item {
        String name;
        int price;
        boolean isBought;
        int itemLife;  // Item life in usage count

        Item(String name, int price, int itemLife) {
            this.name = name;
            this.price = price;
            this.isBought = false;
            this.itemLife = itemLife;
        }

        public void buyItem() {
            this.isBought = true;
        }

        public void reduceItemLife() {
            if (itemLife > 0) {
                itemLife--;
            }
        }

        @Override
        public String toString() {
            return name + " - $" + price + (isBought ? " (Bought)" : " (Available)") +
                    (isBought ? " - Life: " + itemLife : "");
        }
    }

    // Define shop items for each pet type with price and item life
    private static final Map<PetType, Item[]> shopItems = new HashMap<>();

    static {
        // Define items for each pet type with associated prices and item life
        shopItems.put(PetType.DOG, new Item[]{
            new Item("Kennel", 200, 5),
            new Item("Food Bowl", 50, 10),
            new Item("Chain", 80, 8),
            new Item("Collar", 40, 12),
            new Item("Fur Brush", 30, 15),
            new Item("Bath Tub", 150, 6)
        });

        shopItems.put(PetType.CAT, new Item[]{
            new Item("Basket", 100, 10),
            new Item("Fur Brush", 30, 15),
            new Item("Wool Toy", 25, 20),
            new Item("Collar", 40, 12),
            new Item("Food Bowl", 50, 10),
            new Item("Bath Tub", 120, 6)
        });

        shopItems.put(PetType.BIRD, new Item[]{
            new Item("Cage", 120, 15),
            new Item("Feather Brush", 20, 20),
            new Item("Food Bowl", 40, 10),
            new Item("Bird Swing", 60, 5)
        });
    }

    // Display the shop with the available items and prices
    public static void displayShop(PetType petType, int bankBalance) {
        System.out.println("\n=== Pet Shop ===");
        System.out.println("Your Current Bank Balance: $" + bankBalance);
        System.out.println("\nAvailable items for your " + petType.name() + ":");

        // List items specific to the selected pet type
        Item[] items = shopItems.getOrDefault(petType, new Item[]{});
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }

        System.out.println("\n(Next steps: Purchase functionality implemented!)");
    }

    // Handle the item purchase based on the bank balance
    public static boolean purchaseItem(PetType petType, int itemIndex, int bankBalance) {
        Item[] items = shopItems.get(petType);
        if (itemIndex < 0 || itemIndex >= items.length) {
            System.out.println("Invalid item selection.");
            return false;
        }

        Item selectedItem = items[itemIndex];

        // Check if the user has enough balance to purchase the item
        if (bankBalance >= selectedItem.price && !selectedItem.isBought) {
            System.out.println("You have purchased the " + selectedItem.name + " for $" + selectedItem.price);
            selectedItem.buyItem();
            return true;
        } else {
            System.out.println("Insufficient funds or item already bought.");
            return false;
        }
    }
}

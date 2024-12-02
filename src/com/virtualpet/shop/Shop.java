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
        int initialItemLife; // Keep track of the initial item life
        Activity[] activities; // Link item to multiple activities

        Item(String name, int price, int itemLife, Activity[] activities) {
            this.name = name;
            this.price = price;
            this.isBought = false;
            this.itemLife = itemLife;
            this.initialItemLife = itemLife; // Store initial item life
            this.activities = activities;
        }

        // Mark the item as bought and reset its life to its initial value
        public void buyItem() {
            this.isBought = true;
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
                this.isBought = false; // Mark item as available again
                this.itemLife = initialItemLife; // Restore original item life
            }
        }

        @Override
        public String toString() {
            StringBuilder activitiesList = new StringBuilder();
            for (Activity activity : activities) {
                activitiesList.append(activity).append(" ");
            }
            return name + " - Price: $" + price + " | Associated Activity: " + activitiesList + " | Item Life: "
                    + itemLife + " | Status: " + (isBought ? " Bought" : " Available");
        }
    }

    private static final Map<PetType, Item[]> shopItems = new HashMap<>();

    private static void initializeShopItems()  {
        shopItems.put(PetType.DOG, new Item[] {
                new Item("Dog Bed", 100, 4, new Activity[] { Activity.SLEEP }),
                new Item("Kennel", 200, 8, new Activity[] { Activity.SLEEP }),
                new Item("Food Bowl", 50, 1, new Activity[] { Activity.EATING }),
                new Item("Bone", 150, 3, new Activity[] { Activity.EATING }),
                new Item("Ball", 100, 2, new Activity[] { Activity.PLAYING }),
                new Item("Chew Toy", 300, 5, new Activity[] { Activity.PLAYING }),
                new Item("Bath Tub", 150, 3, new Activity[] { Activity.BATHING }),
                new Item("Fur Brush", 350, 7, new Activity[] { Activity.BATHING }),
        });

        shopItems.put(PetType.CAT, new Item[] {
                new Item("Cat Bed", 100, 4, new Activity[] { Activity.SLEEP }),
                new Item("Basket", 200, 8, new Activity[] { Activity.SLEEP }),
                new Item("Food Bowl", 50, 1, new Activity[] { Activity.EATING }),
                new Item("Fish", 150, 3, new Activity[] { Activity.EATING }),
                new Item("Wool", 100, 2, new Activity[] { Activity.PLAYING }),
                new Item("Laser Toy", 300, 5, new Activity[] { Activity.PLAYING }),
                new Item("Bath Tub", 150, 3, new Activity[] { Activity.BATHING }),
                new Item("Fur Brush", 350, 7, new Activity[] { Activity.BATHING }),
        });

        shopItems.put(PetType.BIRD, new Item[] {
                new Item("Bird Cage", 100, 4, new Activity[] { Activity.SLEEP }),
                new Item("Perch", 200, 8, new Activity[] { Activity.SLEEP }),
                new Item("Food Bowl", 50, 1, new Activity[] { Activity.EATING }),
                new Item("Seeds", 150, 3, new Activity[] { Activity.EATING }),
                new Item("Swing", 100, 2, new Activity[] { Activity.PLAYING }),
                new Item("Mirror", 300, 5, new Activity[] { Activity.PLAYING }),
                new Item("Bath Tub", 150, 3, new Activity[] { Activity.BATHING }),
                new Item("Feather Brush", 350, 7, new Activity[] { Activity.BATHING }),
        });
    }

    public static void displayShop(Pet pet) {
        System.out.println("\n=== Pet Shop ===");
        System.out.println("Your Current Bank Balance: $" + pet.getBankBalance());
        System.out.println("\nAvailable items for your " + pet.getPetType() + ":");

        Item[] items = shopItems.getOrDefault(pet.getPetType(), new Item[] {});
        for (int i = 0; i < items.length; i++) {
            if (!items[i].isBought) {
                items[i].resetItem(); // Reset any items with 0 life
            }
            System.out.println((i + 1) + ". " + items[i]);
        }
    }

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

    public static boolean useItemForActivity(Pet pet, String itemName, Activity activity) {
        Item[] items = shopItems.getOrDefault(pet.getPetType(), new Item[] {});
        Item itemToUse = null;

        for (Item item : items) {
            if (item.isBought) {
                for (Activity a : item.activities) {
                    if (a == activity) {
                        itemToUse = item;
                        break;
                    }
                }
                if (itemToUse != null)
                    break;
            }
        }

        if (itemToUse == null || itemToUse.itemLife <= 0) {
            System.out.println("You do not have the item: " + itemName + " or it cannot be used for this activity.");
            return false;
        }

        itemToUse.reduceItemLife();
        if (itemToUse.itemLife <= 0) {
            itemToUse.resetItem();
            System.out.println(
                    "The item " + itemToUse.name + " has been used up and is now available for purchase again.");
        } else {
            System.out.println("Used " + itemToUse.name + " for the activity: " + activity + ". Remaining life: "
                    + itemToUse.itemLife);
        }

        return true;
    }

    public static void resetShop() {
        shopItems.clear(); // Clear existing entries
    initializeShopItems(); // Reinitialize shop items
    System.out.println("Shop has been reset with new items.");
    }
}

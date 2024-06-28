package com.example.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that represents a shopping cart.
 */

public class Cart  {
    private static Cart instance;
    private List<ModelClass> items;


    /**
     * Private constructor to prevent instantiation from outside.
     */
    private Cart() {
        // Initialize the list of items in the cart.
        items = new ArrayList<>();
    }

    /**
     * Returns the single instance of the Cart class.
     * @return The Cart instance.
     */

    public static synchronized Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    /**
     * Adds an item to the cart.
     * @param item The item to be added.
     */

    public void addItem(ModelClass item) {
        items.add(item);
    }

    /**
     * Returns the list of items in the cart.
     * @return The list of items.
     */

    public List<ModelClass> getItems() {
        return items;
    }
}
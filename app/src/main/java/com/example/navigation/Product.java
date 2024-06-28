// This is the package declaration, which specifies the namespace for this class
package com.example.navigation;

/**
 * This is a Java class that represents a Product
 */

public class Product {
    // Private field to store the name of the product
    private String name;
    // Private field to store the image of the product
    private String image;

    /**
     * This is the constructor for the Product class
     *
     * @param name   The name of the product
     * @param image  The image of the product
     */

    public Product(String name, String image) {
        // Initialize the name field with the provided name
        this.name = name;
        // Initialize the image field with the provided image
        this.image = image;
    }
}

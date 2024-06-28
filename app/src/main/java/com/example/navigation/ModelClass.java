package com.example.navigation;

/**
 * Model class that represents a product.
 */

public class ModelClass {
    // Product properties
    String id,category,name;
    int image;
    public String getIdList() {
        return id;
    }

    /**
     * Getter for the product ID.
     * @return The product ID.
     */

    public void setIdList(String id) {
        this.id = id;
    }

      /**
         * Getter for the product name.
         * @return The product name.
         */

    public String getNameList() {
        return name;
    }

    /**
     * Setter for the product name.
     * @param name The product name.
     */

    public void setNameList(String name) {
        this.name = name;
    }

    public String getCategoryList() {
        return category;
    }

    /**
     * Setter for the product category.
     * @param category The product category.
     */

    public void setCategoryList(String category) {
        this.category = category;
    }

    /**
     * Getter for the product image.
     * @return The product image.
     */

    public int getImage() {
        return image;
    }

    /**
     * Setter for the product image.
     * @param image The product image.
     */

    public void setImage(int image) {
        this.image = image;
    }
}

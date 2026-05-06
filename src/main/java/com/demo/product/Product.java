// Product.java
package com.demo.product;

public class Product {

    private String productId;
    private String productName;
    private double price;
    private int stock;
    private Category category;

    public Product(String productId,
                   String productName,
                   double price,
                   int stock,
                   Category category) {

        if (productId == null || productId.isBlank()) {

            throw new IllegalArgumentException(
                    "ProductId must not be null or blank");
        }

        if (productName == null || productName.isBlank()) {

            throw new IllegalArgumentException(
                    "Product name must not be null or blank");
        }

        if (price < 0) {

            throw new IllegalArgumentException(
                    "Price must be >= 0");
        }

        if (stock < 0) {

            throw new IllegalArgumentException(
                    "Stock must be >= 0");
        }

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setProductId(String productId) {

        if (productId == null || productId.isBlank()) {

            throw new IllegalArgumentException(
                    "ProductId must not be null or blank");
        }

        this.productId = productId;
    }

    public void setProductName(String productName) {

        if (productName == null || productName.isBlank()) {

            throw new IllegalArgumentException(
                    "Product name must not be null or blank");
        }

        this.productName = productName;
    }

    public void setPrice(double price) {

        if (price < 0) {

            throw new IllegalArgumentException(
                    "Price must be >= 0");
        }

        this.price = price;
    }

    public void setStock(int stock) {

        if (stock < 0) {

            throw new IllegalArgumentException(
                    "Stock must be >= 0");
        }

        this.stock = stock;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {

        return "Product ID: " + productId
                + ", Name: " + productName
                + ", Price: " + price
                + ", Stock: " + stock
                + ", Category: " + category;
    }
}
// ProductManager.java
package com.demo.product;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductManager {

    private final LinkedHashMap<String, Product> catalog =
            new LinkedHashMap<>();

    public void addProduct(Product p) {

        if (p == null) {

            throw new IllegalArgumentException(
                    "Product must not be null");
        }

        if (catalog.containsKey(p.getProductId())) {

            throw new IllegalArgumentException(
                    "Duplicate product ID: " + p.getProductId());
        }

        catalog.put(p.getProductId(), p);
    }

    public boolean removeProduct(String productId) {

        if (productId == null) {
            return false;
        }

        return catalog.remove(productId) != null;
    }

    public boolean updateProduct(String productId,
                                 String newName,
                                 double newPrice,
                                 Category newCategory) {

        Product product = catalog.get(productId);

        if (product == null) {
            return false;
        }

        product.setProductName(newName);
        product.setPrice(newPrice);
        product.setCategory(newCategory);

        return true;
    }

    public boolean updateStock(String productId, int qty) {

        if (productId == null) {
            return false;
        }

        Product product = catalog.get(productId);

        if (product == null) {
            return false;
        }

        int newStock = product.getStock() + qty;

        if (newStock < 0) {
            return false;
        }

        product.setStock(newStock);

        return true;
    }

    public List<Product> viewAllProducts() {

        return catalog.values()
                .stream()
                .collect(Collectors.toList());
    }

    public List<Product> getByCategory(Category c) {

        if (c == null) {
            return Collections.emptyList();
        }

        return catalog.values()
                .stream()
                .filter(p -> c.equals(p.getCategory()))
                .collect(Collectors.toList());
    }

    public Product getMostExpensive() {

        return catalog.values()
                .stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    public double getTotalInventoryValue() {

        return catalog.values()
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }

    public List<Product> getLowStock(int threshold) {

        if (threshold < 0) {
            return Collections.emptyList();
        }

        return catalog.values()
                .stream()
                .filter(p -> p.getStock() < threshold)
                .collect(Collectors.toList());
    }
}
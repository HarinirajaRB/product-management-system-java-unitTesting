// App.java
package com.demo.product;

import java.util.List;
import java.util.Scanner;

/*
 * Main method class
 */
public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProductManager pm = new ProductManager();

        while (true) {

            System.out.println("\n===== PRODUCT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Stock");
            System.out.println("4. View All Products");
            System.out.println("5. Update Product");
            System.out.println("6. Get Products By Category");
            System.out.println("7. Get Most Expensive Product");
            System.out.println("8. Get Total Inventory Value");
            System.out.println("9. Get Low Stock Products");
            System.out.println("10. Exit");

            System.out.print("Enter Choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

            case 1:

                scanner.nextLine();

                try {

                    System.out.print("Enter Product ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Stock: ");
                    int stock = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter Category (ELECTRONICS/CLOTHING/FOOD/TOYS/OTHER): ");

                    Category category =
                            Category.valueOf(scanner.nextLine().toUpperCase());

                    pm.addProduct(
                            new Product(id, name, price, stock, category));

                    System.out.println("Product Added Successfully!");

                } catch (Exception e) {

                    System.out.println("Error: " + e.getMessage());
                }

                break;

            case 2:

                scanner.nextLine();

                System.out.print("Enter Product ID to Remove: ");
                String removeId = scanner.nextLine();

                if (pm.removeProduct(removeId)) {
                    System.out.println("Product Removed Successfully!");
                } else {
                    System.out.println("Product Not Found!");
                }

                break;

            case 3:

                scanner.nextLine();

                System.out.print("Enter Product ID: ");
                String updateId = scanner.nextLine();

                System.out.print("Enter Quantity to Add/Reduce: ");
                int qty = scanner.nextInt();

                if (pm.updateStock(updateId, qty)) {
                    System.out.println("Stock Updated Successfully!");
                } else {
                    System.out.println("Stock Update Failed!");
                }

                break;

            case 4:

                List<Product> allProducts = pm.viewAllProducts();

                if (allProducts.isEmpty()) {

                    System.out.println("No Products Available!");

                } else {

                    for (Product p : allProducts) {
                        System.out.println(p);
                    }
                }

                break;

            case 5:

                scanner.nextLine();

                try {

                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();

                    System.out.print("Enter New Product Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter New Price: ");
                    double newPrice = scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print("Enter New Category: ");

                    Category newCategory =
                            Category.valueOf(scanner.nextLine().toUpperCase());

                    if (pm.updateProduct(productId,
                            newName,
                            newPrice,
                            newCategory)) {

                        System.out.println("Product Updated Successfully!");

                    } else {

                        System.out.println("Product Not Found!");
                    }

                } catch (Exception e) {

                    System.out.println("Error: " + e.getMessage());
                }

                break;

            case 6:

                scanner.nextLine();

                try {

                    System.out.print("Enter Category: ");

                    Category c =
                            Category.valueOf(scanner.nextLine().toUpperCase());

                    List<Product> products = pm.getByCategory(c);

                    if (products.isEmpty()) {

                        System.out.println("No Products Found!");

                    } else {

                        for (Product p : products) {
                            System.out.println(p);
                        }
                    }

                } catch (Exception e) {

                    System.out.println("Invalid Category!");
                }

                break;

            case 7:

                Product most = pm.getMostExpensive();

                if (most != null) {

                    System.out.println("Most Expensive Product:");
                    System.out.println(most);

                } else {

                    System.out.println("No Products Available!");
                }

                break;

            case 8:

                System.out.println("Total Inventory Value:");
                System.out.println(pm.getTotalInventoryValue());

                break;

            case 9:

                System.out.print("Enter Threshold: ");
                int threshold = scanner.nextInt();

                List<Product> lowStock = pm.getLowStock(threshold);

                if (lowStock.isEmpty()) {

                    System.out.println("No Low Stock Products!");

                } else {

                    for (Product p : lowStock) {
                        System.out.println(p);
                    }
                }

                break;

            case 10:

                System.out.println(
                        "Thank you for using Product Management System");

                scanner.close();

                System.exit(0);

            default:

                System.out.println("Invalid Choice!");
            }
        }
    }
}
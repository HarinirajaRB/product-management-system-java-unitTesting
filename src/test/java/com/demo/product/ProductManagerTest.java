// ProductManagerTest.java
package com.demo.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductManagerTest {

    private ProductManager pm;

    
    // ── setUp ────────────────────────────────────────────────────────────────
    // Initializes ProductManager object and adds sample products before each test
    
    @BeforeEach
    void setUp() {

        pm = new ProductManager();

        pm.addProduct(
                new Product("P01",
                        "Laptop",
                        75000,
                        10,
                        Category.ELECTRONICS));

        pm.addProduct(
                new Product("P02",
                        "T-Shirt",
                        599,
                        200,
                        Category.CLOTHING));

        pm.addProduct(
                new Product("P03",
                        "Phone",
                        45000,
                        3,
                        Category.ELECTRONICS));
    }

    
    // ── testAddProduct ───────────────────────────────────────────────────────
    // Tests whether a product is added successfully into catalog
    
    @Test
    void testAddProduct() {

        pm.addProduct(
                new Product("P04",
                        "Rice",
                        50,
                        500,
                        Category.FOOD));

        List<Product> food =
                pm.getByCategory(Category.FOOD);

        assertEquals(1, food.size());

        assertEquals(
                "Rice",
                food.get(0).getProductName());
    }

    
    // ── testRemoveProduct ────────────────────────────────────────────────────
    // Tests product removal functionality and validates null/not-found cases
    
    @Test
    void testRemoveProduct() {

        assertTrue(pm.removeProduct("P02"));

        assertFalse(pm.removeProduct("P99"));

        assertFalse(pm.removeProduct(null));

        assertEquals(
                0,
                pm.getByCategory(Category.CLOTHING).size());
    }

    
    // ── testUpdateStock ──────────────────────────────────────────────────────
    // Tests stock update operation including valid and invalid stock updates
    
    @Test
    void testUpdateStock() {

        assertTrue(pm.updateStock("P03", 10));

        assertEquals(
                13,
                pm.getByCategory(Category.ELECTRONICS)
                        .stream()
                        .filter(p -> p.getProductId().equals("P03"))
                        .findFirst()
                        .get()
                        .getStock());

        assertFalse(pm.updateStock("P03", -100));

        assertFalse(pm.updateStock("P99", 5));

        assertFalse(pm.updateStock(null, 5));
    }

    
    // ── testUpdateProduct ────────────────────────────────────────────────────
    // Tests updating existing product details and invalid product update case
    
    @Test
    void testUpdateProduct() {

        assertTrue(pm.updateProduct(
                "P01",
                "Gaming Laptop",
                90000,
                Category.ELECTRONICS));

        Product updated =
                pm.getByCategory(Category.ELECTRONICS)
                        .stream()
                        .filter(p -> p.getProductId().equals("P01"))
                        .findFirst()
                        .get();

        assertEquals(
                "Gaming Laptop",
                updated.getProductName());

        assertEquals(
                90000,
                updated.getPrice());

        assertFalse(pm.updateProduct(
                "P99",
                "Test",
                100,
                Category.OTHER));
    }

    
    // ── testGetByCategory ────────────────────────────────────────────────────
    // Tests filtering products based on category
    
    @Test
    void testGetByCategory() {

        List<Product> electronics =
                pm.getByCategory(Category.ELECTRONICS);

        assertEquals(2, electronics.size());

        assertTrue(electronics.stream()
                .anyMatch(p -> p.getProductName()
                        .equals("Laptop")));

        assertTrue(electronics.stream()
                .anyMatch(p -> p.getProductName()
                        .equals("Phone")));

        assertEquals(
                0,
                pm.getByCategory(Category.FOOD).size());

        assertEquals(
                0,
                pm.getByCategory(null).size());
    }

    
    // ── testGetMostExpensive ─────────────────────────────────────────────────
    // Tests finding the highest priced product from catalog
    
    @Test
    void testGetMostExpensive() {

        Product most = pm.getMostExpensive();

        assertNotNull(most);

        assertEquals(
                "Laptop",
                most.getProductName());

        ProductManager empty =
                new ProductManager();

        assertNull(empty.getMostExpensive());
    }

    
    // ── testGetTotalInventoryValue ───────────────────────────────────────────
    // Tests total inventory value calculation for all products
    
    @Test
    void testGetTotalInventoryValue() {

        double expected =
                (75000 * 10)
                        + (599 * 200)
                        + (45000 * 3);

        assertEquals(
                expected,
                pm.getTotalInventoryValue(),
                0.001);

        assertEquals(
                0.0,
                new ProductManager()
                        .getTotalInventoryValue(),
                0.001);
    }

    
    // ── testGetLowStock ──────────────────────────────────────────────────────
    // Tests retrieval of products whose stock is below threshold value
    
    @Test
    void testGetLowStock() {

        List<Product> low =
                pm.getLowStock(5);

        assertEquals(1, low.size());

        assertEquals(
                "Phone",
                low.get(0).getProductName());

        assertEquals(
                0,
                pm.getLowStock(0).size());

        assertEquals(
                3,
                pm.getLowStock(1000).size());
    }

    
    // ── testDuplicateProductId ───────────────────────────────────────────────
    // Tests duplicate product ID validation while adding products
    
    @Test
    void testDuplicateProductId() {

        assertThrows(
                IllegalArgumentException.class,
                () -> pm.addProduct(
                        new Product("P01",
                                "Duplicate",
                                100,
                                1,
                                Category.OTHER)));
    }
}
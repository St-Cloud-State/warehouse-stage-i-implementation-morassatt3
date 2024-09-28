// Morgan Rassatt

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wishlist {
    private String clientID; // Stores the client ID associated with this wishlist.
    private List<Product> products; // Stores Product objects added to the wishlist.

    // Constructor to initialize the Wishlist with a specific client ID.
    public Wishlist(String clientID) {
        this.clientID = clientID;
        this.products = new ArrayList<>(); // Initialize an empty list of products.
    }

    // Getter method to retrieve the client ID.
    public String getClientID() {
        return clientID;
    }

    // **Needed info with Products below**

    // Method to get a copy of the list of products to ensure encapsulation and data integrity.
    public List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy of the products list to prevent external modifications.
    }

    // Method to add a Product to the wishlist.
    public boolean addProduct(Product product) {
        if (product == null || products.stream().anyMatch(p -> p.getID().equals(product.getID()))) {
            return false;  // Return false if the product is null or already exists in the wishlist.
        }
        products.add(product); // Add the product to the list.
        return true; // Return true indicating successful addition.
    }

    // Method to retrieve information about a product in the wishlist using its ID.
    public String getProductInfo(String productID) {
        // Find the first product that matches the given ID.
        Optional<Product> product = products.stream()
                                            .filter(p -> p.getID().equals(productID))
                                            .findFirst();
        // If the product is found, return its details; otherwise, return a not-found message.
        return product.map(Product::getProductInfo)
                      .orElse("Product not found in the wishlist.");
    }

    // Method to update a product's details in the wishlist.
    public boolean updateProduct(String productID, double newPrice, String newDescription, String newCategory) {
        // Find the product to update based on the productID.
        Optional<Product> product = products.stream()
                                            .filter(p -> p.getID().equals(productID))
                                            .findFirst();
        if (product.isPresent()) { // Check if the product was found.
            // Update the product details directly.
            product.get().setPrice(newPrice);
            product.get().setDescription(newDescription);
            product.get().setCategory(newCategory);
            return true; // Return true indicating successful update.
        }
        return false; // Return false if the product was not found.
    }

    // Method to remove a product from the wishlist by its ID.
    public boolean removeProduct(String productID) {
        // Remove the product if its ID matches the given productID.
        return products.removeIf(p -> p.getID().equals(productID));
    }
}

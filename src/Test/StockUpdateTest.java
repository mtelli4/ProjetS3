package Test;

import Model.*;
import java.util.ArrayList;

public class StockUpdateTest {
    public static void main(String[] args) {
        CommandeDAO commandeDAO = new CommandeDAO();
        Pizza pizza = new Pizza(1, "Margherita", 8.0, 10, "Normal", false, new ArrayList<>()); // Assurez-vous que cette pizza existe dans votre base de données
        Ingredient ingredient = new Ingredient(1, "Tomate", null); // Assurez-vous que cet ingrédient existe dans votre base de données

        int initialQuantity = commandeDAO.getCurrentQuantity(ingredient.getId_Ing());
        int neededQuantity = commandeDAO.getNeededQuantity(pizza.getId(), ingredient.getId_Ing());

        System.out.println("Initial quantity in stock: " + initialQuantity);
        System.out.println("Needed quantity for the pizza: " + neededQuantity);

        // Simuler la mise à jour du stock
        pizza.setPret(true);
        int currentQuantity = commandeDAO.getCurrentQuantity(ingredient.getId_Ing());
        int newQuantity = currentQuantity - neededQuantity;
        commandeDAO.updateStock(ingredient.getId_Ing(), newQuantity);

        int finalQuantity = commandeDAO.getCurrentQuantity(ingredient.getId_Ing());

        System.out.println("Final quantity in stock: " + finalQuantity);

        if (initialQuantity - neededQuantity == finalQuantity) {
            System.out.println("The stock update test passed.");
        } else {
            System.out.println("The stock update test failed.");
        }
        commandeDAO.close();
    }
}
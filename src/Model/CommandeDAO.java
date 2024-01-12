package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

    Connection connection;
    public CommandeDAO() {
        this.connection = DatabaseConnection.getConnection();
        if (this.connection != null) {
            System.out.println("La connexion à la base de données a été établie avec succès.");
        } else {
            System.out.println("La connexion à la base de données a échoué.");
        }
    }

    public List<Pizza> getPizzasCommandees() {
        List<Pizza> pizzas = new ArrayList<>();
        String query = "SELECT P.* FROM PIZZA P " +
                "INNER JOIN contient C ON P.num_Piz = C.num_Piz " +
                "WHERE C.num_Cmd IS NOT NULL";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("La requête SQL a été exécutée avec succès.");

            while (resultSet.next()) {
                System.out.println("Traitement d'une ligne de résultats...");

                int id = resultSet.getInt("num_Piz");
                String nom = resultSet.getString("nom_Piz");
                double prix = resultSet.getDouble("prix_Piz");
                int tempsFab = resultSet.getInt("tempsFabr_Piz");
                String type = resultSet.getString("type_Piz");
                boolean pret = resultSet.getBoolean("Pret");
                ArrayList<Ingredient> ingredients = getIngredientsForPizza(id);
                Pizza pizza = new Pizza(id, nom, prix, tempsFab, type, pret, ingredients);
                pizzas.add(pizza);

                System.out.println("Pizza ajoutée à la liste : " + nom);
            }
        } catch (SQLException e) {
            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
            e.printStackTrace();
        }

        return pizzas;
    }

    private ArrayList<Ingredient> getIngredientsForPizza(int pizzaId) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String query = "SELECT I.* FROM INGREDIENT I " +
                "INNER JOIN contient_des CD ON I.id_Ing = CD.id_Ing " +
                "WHERE CD.num_Piz = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pizzaId);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("La requête SQL pour obtenir les ingrédients a été exécutée avec succès.");

            while (resultSet.next()) {
                System.out.println("Traitement d'une ligne de résultats pour les ingrédients...");

                int id = resultSet.getInt("id_Ing");
                String nom = resultSet.getString("nom_Ing");
                Ingredient ingredient = new Ingredient(id, nom, null); // Vous devez définir le constructeur approprié dans la classe Ingredient
                ingredients.add(ingredient);

                System.out.println("Ingrédient ajouté à la liste : " + nom);
            }
        } catch (SQLException e) {
            System.out.println("Une erreur SQL s'est produite lors de la récupération des ingrédients : " + e.getMessage());
            e.printStackTrace();
        }

        return ingredients;
    }
    public void deleteOrder(Commande order) {
        String query = "DELETE FROM Commande WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getId());
            statement.executeUpdate();

            System.out.println("The order has been successfully deleted from the database.");
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public int getNeededQuantity(int pizzaId, int ingredientId) {
        String query = "SELECT quantite FROM contient_des WHERE num_Piz = ? AND id_Ing = ?";
        int neededQuantity = 0;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pizzaId);
            statement.setInt(2, ingredientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                neededQuantity = resultSet.getInt("quantite");
            }
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return neededQuantity;
    }

    public int getCurrentQuantity(int ingredientId) {
        String query = "SELECT Qte_Stck FROM STOCK WHERE id_Ing = ?";
        int currentQuantity = 0;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ingredientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                currentQuantity = resultSet.getInt("Qte_Stck");
            }
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return currentQuantity;
    }

    public void updateStock(int ingredientId, int newQuantity) {
        String query = "UPDATE STOCK SET Qte_Stck = ? WHERE id_Ing = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            System.out.println("updateStock method is called"); // Etape 1

            connection.setAutoCommit(false); // Etape 4

            statement.setInt(1, newQuantity);
            statement.setInt(2, ingredientId);
            statement.executeUpdate();

            System.out.println("SQL query is executed successfully"); // Etape 2

            connection.commit(); // Etape 3

            System.out.println("The stock has been successfully updated in the database.");
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }
}
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
}
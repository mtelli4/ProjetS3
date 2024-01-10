package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import View.ListeCommandesGeneraleVue;

public class CommandeDAO {

    Connection connection;
    public CommandeDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Pizza> getPizzasCommandees() {
        List<Pizza> pizzas = new ArrayList<>();
        String query = "SELECT P.* FROM PIZZA P " +
                "INNER JOIN contient C ON P.num_Piz = C.num_Piz " +
                "INNER JOIN COMMANDE CMD ON C.num_Cmd = CMD.num_Cmd";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("num_Piz");
                String nom = resultSet.getString("nom_Piz");
                // Ajoutez ici les autres attributs de la pizza
                double prix = resultSet.getDouble("prix_Piz");
                int tempsFab = resultSet.getInt("tempsFabr_Piz");
                String type = resultSet.getString("type_Piz");
                boolean pret = resultSet.getBoolean("Pret");
                ArrayList<Ingredient> ingredients = getIngredientsForPizza(id);
                Pizza pizza = new Pizza(id, nom, prix, tempsFab, type, pret, ingredients);
                pizzas.add(pizza);
            }
        } catch (SQLException e) {
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

            while (resultSet.next()) {
                int id = resultSet.getInt("id_Ing");
                String nom = resultSet.getString("nom_Ing");
                // Ajoutez ici les autres attributs de l'ingrédient
                // int quantite = resultSet.getInt("quantite");

                // Ingredient ingredient = new Ingredient(quantite, nom, ...);
                // ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
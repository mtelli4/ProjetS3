package View;

import Model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;
import Model.Pizza;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ListeCommandesGeneraleVue extends ListeCommandesVideVue {
    // Étape 1 : Créer une méthode pour générer un élément de la liste des pizzas
    private Node createPizzaNode(Pizza pizza) {
        Label label = new Label(pizza.getNom());
        // Vous pouvez personnaliser ce label comme vous le souhaitez,
        // par exemple en définissant sa police, sa couleur, etc.
        // label.setFont(...);
        // label.setTextFill(...);
        return label;
    }

    // Étape 2 : Récupérer la liste des pizzas commandées
    private List<Pizza> getPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.70.36.54:3306/saes3-aratovo", "saes3-aratovo", "Projet_rma_s3");
            CommandeDAO commandeDAO = new CommandeDAO(connection);
            pizzas = commandeDAO.getPizzasCommandees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizzas;
    }
    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);

        Pane root = (Pane) primaryStage.getScene().getRoot();

        // Étape 2 : Récupérer la liste des pizzas commandées
        List<Pizza> pizzas = getPizzas();  // Remplacez ceci par votre méthode pour obtenir les pizzas

        // Étape 3 : Pour chaque pizza dans la liste, générer un Node
        VBox pizzaList = new VBox();
        for (Pizza pizza : pizzas) {
            Node pizzaNode = createPizzaNode(pizza);
            pizzaList.getChildren().add(pizzaNode);
        }

        // Étape 4 : Ajouter le VBox à la première colonne
        // Vous devrez ajuster ces propriétés pour positionner correctement le VBox dans votre interface
        pizzaList.setLayoutX(10);
        pizzaList.setLayoutY(10);
        root.getChildren().add(pizzaList);
    }

    public static void main(String[] args) {
        // Créer une instance de Stock
        Stock stock1 = new Stock(1, 50); // Remplacez 1 et 50 par les valeurs appropriées
        Stock stock2 = new Stock(2, 50); // Remplacez 2 et 50 par les valeurs appropriées

        // Créer quelques ingrédients
        Ingredient ingredient1 = new Ingredient(1, "Tomate", stock1);
        Ingredient ingredient2 = new Ingredient(2, "Fromage", stock2);

        // Ajouter les ingrédients à une liste
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        // Créer une nouvelle pizza
        Pizza pizza = new Pizza(1, "Margherita", 8.5, 15, "Normal", false, ingredients);

        // Afficher le nom de la pizza
        System.out.println(pizza.getNom());

        // Lancer l'application
        Application.launch(args);
    }
}
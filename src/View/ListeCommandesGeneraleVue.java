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
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import Controller.CommandeControle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class ListeCommandesGeneraleVue extends ListeCommandesVideVue {
    private Stage primaryStage; // Ajouter un champ de classe pour le Stage
    private CommandeControle controle; // Ajouter un champ de classe pour le contrôleur

    private Node createPizzaNode(Pizza pizza) {
        Label label = new Label(pizza.getNom());
        label.setStyle("-fx-text-fill: white;"); // Ajout de cette ligne pour définir la couleur du texte en blanc
        label.setOnMouseClicked(event -> {
            double destinationX = primaryStage.getScene().getWidth() * 0.55;
            double destinationY = primaryStage.getScene().getHeight() * 0.14;
            controle.movePizzaToReady(label, destinationX, destinationY); // Utiliser l'instance du contrôleur pour appeler la méthode
            pizza.setPret(true);
        });
        return label;
    }

    private List<Pizza> getPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        CommandeDAO commandeDAO = new CommandeDAO();
        pizzas = commandeDAO.getPizzasCommandees();
        return pizzas;
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Stocker le Stage dans le champ de classe
        this.controle = new CommandeControle(); // Créer une instance du contrôleur

        super.start(primaryStage);

        Pane root = (Pane) primaryStage.getScene().getRoot();

        List<Pizza> pizzas = getPizzas();

        VBox pizzaList = new VBox();
        for (Pizza pizza : pizzas) {
            Node pizzaNode = createPizzaNode(pizza);
            pizzaList.getChildren().add(pizzaNode);
        }



// ...

        FlowPane pizzaFlow = new FlowPane();

        pizzaFlow.setVgap(10); // Espacement vertical entre les pizzas
        pizzaFlow.setHgap(10); // Espacement horizontal entre les pizzas
        pizzaFlow.prefHeightProperty().bind(primaryStage.getScene().heightProperty().multiply(0.9));
        for (Pizza pizza : pizzas) {
            Node pizzaNode = createPizzaNode(pizza);
            pizzaFlow.getChildren().add(pizzaNode);
        }

// Lier la position du FlowPane à la taille de la scène
        pizzaFlow.layoutXProperty().bind(primaryStage.getScene().widthProperty().multiply(0.05));
        pizzaFlow.layoutYProperty().bind(primaryStage.getScene().heightProperty().multiply(0.14));

        root.getChildren().add(pizzaFlow);
        // Lier la position de la liste de pizzas à la taille de la scène
        pizzaList.layoutXProperty().bind(primaryStage.getScene().widthProperty().multiply(0.05));
        pizzaList.layoutYProperty().bind(primaryStage.getScene().heightProperty().multiply(0.14));
        root.getChildren().add(pizzaList);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
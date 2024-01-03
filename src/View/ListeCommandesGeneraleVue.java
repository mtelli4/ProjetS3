package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.layout.StackPane;

public class ListeCommandesGeneraleVue extends ListeCommandesVideVue {
    @Override
    public void start(Stage primaryStage) {
        // Appeler la méthode start de la classe parente
        super.start(primaryStage);

        // Votre code spécifique à ListeCommandesGeneraleVue ici
        // Créer des commandes
        CommandeVue commande1 = new CommandeVue("1", "Sur place", "Margherita");
        CommandeVue commande2 = new CommandeVue("2", "À emporter", "Pepperoni");

        // Ajouter les commandes à la scène
        ((Pane) primaryStage.getScene().getRoot()).getChildren().addAll(commande1, commande2);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
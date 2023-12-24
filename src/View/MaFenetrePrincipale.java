package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class MaFenetrePrincipale extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création d'une mise en page (layout) pour contenir les éléments de la fenêtre
        Pane root = new Pane();

        // Création de la scène avec une taille spécifique et une couleur de fond définie par un code couleur
        Scene scene = new Scene(root, 1000, 600);
        scene.setFill(Color.web("#1F1F1F")); // Définition de la couleur de fond de la scène avec le code couleur #1F1F1F

        // Définir la couleur de fond du StackPane à #1F1F1F
        root.setBackground(new Background(new BackgroundFill(Color.web("#1F1F1F"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Création d'une image à partir du fichier "monImage.png"
        Image image = new Image(getClass().getResource("/Ressources/bannière.png").toExternalForm());
        // Création d'un ImageView pour afficher l'image
        ImageView imageView = new ImageView(image);
        // Redimensionner l'image
        imageView.setFitWidth(333);  // Largeur de l'image
        imageView.setFitHeight(100); // Hauteur de l'image
        // Déplacer l'image légèrement plus haut que le centre
        imageView.setLayoutY(100);

        imageView.layoutXProperty().bind(scene.widthProperty().subtract(imageView.fitWidthProperty()).divide(2));
        // Ajouter un écouteur à la propriété width de la scène
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Centrer l'image sur l'axe des abscisses
            imageView.setTranslateX((newValue.doubleValue() - imageView.getFitWidth()) / 2);
        });
        // Création d'une boîte de texte
        TextField textField = new TextField();
        textField.setPromptText("Entrez votre prénom");

        // Définir la largeur préférée et maximale de la boîte de texte
        textField.setPrefWidth(200);
        textField.setMaxWidth(210);
        textField.setPrefHeight(50);
        textField.setMaxHeight(50);

        // Définir le style du textField
        textField.setStyle("-fx-prompt-text-fill: #FFFFFF; " +
                "-fx-background-color: #1a1a1a; " +
                "-fx-text-fill: #FFFFFF; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-font-size: 20px;");

        // Création d'un bouton
        Button button = new Button("Connexion");
        button.setPrefWidth(200);

        // Création d'un HBox pour contenir le TextField et le Button
        VBox vbox = new VBox(textField, button);
        vbox.setTranslateY(250); // Positionner la HBox en dessous de l'image
        // Ajouter un écouteur à la propriété width de la scène
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Centrer le VBox sur l'axe des abscisses
            vbox.setTranslateX((newValue.doubleValue() - vbox.getWidth()) / 2);
        });
        // Centrer le VBox sur l'axe des abscisses
        vbox.layoutXProperty().bind(scene.widthProperty().subtract(vbox.widthProperty()).divide(2));

        // Ajout de l'ImageView et du textField à la mise en page
        root.getChildren().addAll(imageView, vbox);

        // Configuration de la scène dans la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ma Fenêtre Principale"); // Titre de la fenêtre

        // Définir la taille minimale de la fenêtre
        /*
        primaryStage.setMinWidth(1240);
        primaryStage.setMinHeight(700);
*/
        primaryStage.show(); // Afficher la fenêtre
    }

    public static void main(String[] args) {
        launch(args); // Méthode pour lancer l'application JavaFX
    }
}

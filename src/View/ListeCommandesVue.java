package View;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ListeCommandesVue extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Création d'une mise en page (layout) pour contenir les éléments de la fenêtre
        Pane root = new Pane();

        // Création de la scène avec une taille spécifique et une couleur de fond définie par un code couleur
        Scene scene = new Scene(root, 1000, 600);
        scene.setFill(Color.web("#1F1F1F")); // Définition de la couleur de fond de la scène avec le code couleur #1F1F1F

        // Définir la couleur de fond du StackPane à #1F1F1F
        root.setBackground(new Background(new BackgroundFill(Color.web("#1F1F1F"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Création d'un rectangle avec des coins arrondis
        Button emporter = new Button();
        // Définir la largeur et la hauteur du bouton à 10% de la largeur et de la hauteur de la scène
        emporter.prefWidthProperty().bind(scene.widthProperty().multiply(0.175));
        emporter.prefHeightProperty().bind(scene.heightProperty().multiply(0.175));
        // Ajout d'une marge pour le contenu du bouton
        emporter.setPadding(new Insets(10, 10, 10, 10));
        // Ajouter une marge extérieure au bouton
        // Définir la marge en pourcentage de la taille de la fenêtre
        double marginPercent = 0.03; // 3%
        // Ajouter une marge au dessus à l'extérieure au bouton en pourcentage de la taille de la fenêtre 8%
        emporter.translateYProperty().bind(scene.heightProperty().multiply(marginPercent));

        // Positionner le bouton avec une marge en pourcentage de la taille de la fenêtre
        emporter.layoutXProperty().bind(scene.widthProperty().multiply(marginPercent));
        emporter.layoutYProperty().bind(scene.heightProperty().multiply(marginPercent));

        // Créer une Timeline pour l'animation
        Timeline timeline = new Timeline();

        /* Définir le style de panel détail en linear de 60ADE6 à 9AC8FF
        emporter.setStyle("-fx-background-color: linear-gradient(#60ADE6, #9AC8FF); " +
                "-fx-background-radius: 10; " +
                "-fx-border-radius: 10; ");*/
        // Définir le style du bouton en noir légèrement transparent avec une bordure noire
        emporter.setStyle("-fx-background-color: #343435; " +
                "-fx-background-radius: 10; " +
                "-fx-border-color: #EA6B66; " +
                "-fx-border-radius: 10; " +
                "-fx-border-width: 2; " +
                "-fx-opacity: 0.8; ");

        // Création des labels
        Label numcommande = new Label("N° commande");
        numcommande.setTextFill(Color.web("#EA6B66"));
        numcommande.setStyle("-fx-font-size: 20px;");
        // Ajout d'une marge par rapport au bord du rectangle
        numcommande.setPadding(new Insets(5, 0, 0, 5));

        Label prix = new Label("Prix");
        prix.setTextFill(Color.web("#FFFFFF"));
        prix.setStyle("-fx-font-size: 20px;");
        // Ajout d'une marge par rapport au bord du rectangle
        prix.setPadding(new Insets(5, 5, 0, 0));

        Label statut = new Label("Statut");
        statut.setTextFill(Color.web("#FFFFFF"));
        statut.setStyle("-fx-font-size: 20px;");
        // Ajout d'une marge par rapport au bord du rectangle
        statut.setPadding(new Insets(0, 0, 5, 5));

        // Positionner le label numcommande dans le coin en haut à gauche du rectangle
        numcommande.layoutXProperty().bind(emporter.layoutXProperty());
        numcommande.layoutYProperty().bind(emporter.layoutYProperty());

        // Positionner le label prix dans le coin en haut à droite du rectangle
        prix.layoutXProperty().bind(emporter.layoutXProperty().add(emporter.widthProperty()).subtract(prix.widthProperty()));
        prix.layoutYProperty().bind(emporter.layoutYProperty());

        // Positionner le label statut dans le coin en bas à gauche du rectangle
        statut.layoutXProperty().bind(emporter.layoutXProperty());
        statut.layoutYProperty().bind(emporter.layoutYProperty().add(emporter.heightProperty()).subtract(statut.heightProperty()));

        // Ajouter le rectangle à la mise en page
        root.getChildren().addAll(emporter, numcommande, prix, statut);

        // Afficher la fenêtre
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
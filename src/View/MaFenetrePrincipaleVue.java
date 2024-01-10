package View;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
public class MaFenetrePrincipaleVue extends Application {

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

        // Mettre une marge de sorte à ce que l'image soit centrée sur la scène seulement sur l'axe des X
        imageView.setTranslateX(scene.getWidth() * 0.5 - imageView.getFitWidth() * 0.5);
        // Faire en sorte que l'image reste centrée sur la scène même si la fenêtre est redimensionnée
        imageView.translateXProperty().bind(scene.widthProperty().multiply(0.5).subtract(imageView.getFitWidth() * 0.5));

        // Création d'une boîte de texte
        TextField textField = new TextField();
        textField.setPromptText("Nom Prénom");

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
        button.setPrefWidth(textField.getPrefWidth());
        button.setPrefHeight(50);
        // Créer une Timeline pour l'animation
        Timeline timeline = new Timeline();



// Définir le style initial du bouton
        final String[] colors = new String[2];
        colors[0] = "#FFFFFF"; // initialBgColor
        colors[1] = "#1a1a1a"; // initialTextColor

        button.setStyle("-fx-background-color: " + colors[0] + "; " +
                "-fx-text-fill: " + colors[1] + "; " +
                "-fx-border-color: #ffffff; " +
                "-fx-border-width: 3px; " +
                "-fx-border-radius: 5px; " +
                "-fx-font-size: 20px; " +
                "-fx-effect: dropshadow(three-pass-box, rgb(255,255,255), 10, 0, 0, 0);");
// Ajouter un écouteur d'événements de clic au bouton
        button.setOnAction(event -> {
            // Vérifier si le textField est vide
            if (textField.getText().isEmpty()) {
                // Afficher un message d'erreur en rouge
                textField.setStyle("-fx-prompt-text-fill: #ff5b5b; " +
                        "-fx-background-color: #1a1a1a; " +
                        "-fx-text-fill: #ff5b5b; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 1px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-font-size: 15px;");
                // Affiche un petit texte au dessus du textField pour indiquer à l'utilisateur qu'il doit entrer un nom
                textField.setPromptText("Veuillez entrer un nom");
                // Ajouter un écouteur à la propriété textProperty du textField
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    // Réinitialiser le style du textField à son style initial
                    textField.setStyle("-fx-prompt-text-fill: #FFFFFF; " +
                            "-fx-background-color: #1a1a1a; " +
                            "-fx-text-fill: #FFFFFF; " +
                            "-fx-border-color: black; " +
                            "-fx-border-width: 1px; " +
                            "-fx-border-radius: 5px; " +
                            "-fx-font-size: 20px;");
                });
            } else {
                // Inverser les couleurs
                String temp = colors[0];
                colors[0] = colors[1];
                colors[1] = temp;

                // Créer des KeyValues pour l'animation
                KeyValue kv1 = new KeyValue(button.textFillProperty(), Color.web(colors[1]));
                KeyValue kv2 = new KeyValue(button.backgroundProperty(), new Background(new BackgroundFill(Color.web(colors[0]), CornerRadii.EMPTY, Insets.EMPTY)));

                // Créer un KeyFrame avec les KeyValues et ajouter à la Timeline
                KeyFrame kf = new KeyFrame(Duration.millis(150), kv1, kv2);
                timeline.getKeyFrames().add(kf);

                // Démarrer l'animation
                timeline.play();
            }
        });

        // Création d'un HBox pour contenir le TextField et le Button
        VBox vbox = new VBox(textField, button);
        vbox.setTranslateY(250); // Positionner la HBox en dessous de l'image
        // Centrer la VBox sur la scène
        vbox.translateXProperty().bind(scene.widthProperty().subtract(vbox.widthProperty()).divide(2));
        vbox.setSpacing(10); // Définir l'espacement entre les éléments de la HBox
        // Ajout de l'ImageView et du textField à la mise en page
        root.getChildren().addAll(imageView, vbox);

        // Configuration de la scène dans la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ma Fenêtre Principale"); // Titre de la fenêtre

        // Définir la taille minimale de la fenêtre

        primaryStage.setMinWidth(1240);
        primaryStage.setMinHeight(700);

        primaryStage.show(); // Afficher la fenêtre
    }

    public static void main(String[] args) {
        launch(args); // Méthode pour lancer l'application JavaFX
    }
}

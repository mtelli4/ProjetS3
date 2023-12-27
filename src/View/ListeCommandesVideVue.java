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

public class ListeCommandesVideVue extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Création d'une mise en page (layout) pour contenir les éléments de la fenêtre
        Pane root = new Pane();

        // Création de la scène avec une taille spécifique et une couleur de fond définie par un code couleur
        Scene scene = new Scene(root, 1000, 600);
        scene.setFill(Color.web("#1F1F1F")); // Définition de la couleur de fond de la scène avec le code couleur #1F1F1F

        // Définir la couleur de fond du StackPane à #1F1F1F
        root.setBackground(new Background(new BackgroundFill(Color.web("#1F1F1F"), CornerRadii.EMPTY, Insets.EMPTY)));
/*
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
                "-fx-border-radius: 10; ");
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
*/
        // Création de 2 lignes noires verticales parallèles qui divisent l'écran en 3 colonnes


        // Création de la première ligne
        Line line1 = new Line();
        line1.startXProperty().bind(scene.widthProperty().divide(3));  // Position de départ X
        line1.startYProperty().bind(scene.heightProperty().multiply(0.12));  // Position de départ Y
        line1.endXProperty().bind(scene.widthProperty().divide(3));  // Position de fin X
        line1.endYProperty().bind(scene.heightProperty().multiply(0.95));  // Position de fin Y
        line1.setStroke(Color.BLACK);  // Couleur de la ligne

        // Création de la deuxième ligne
        Line line2 = new Line();
        line2.startXProperty().bind(scene.widthProperty().divide(3).multiply(2));  // Position de départ X
        line2.startYProperty().bind(scene.heightProperty().multiply(0.12));  // Position de départ Y
        line2.endXProperty().bind(scene.widthProperty().divide(3).multiply(2));  // Position de fin X
        line2.endYProperty().bind(scene.heightProperty().multiply(0.95));  // Position de fin Y
        line2.setStroke(Color.BLACK);  // Couleur de la ligne

        // Ecris le texte "Liste des commandes" en Arial bold en haut à gauche de ma fenêtre en laissant un peu d'espace par rapport aux bordures de la fenêtre
        Label lblListeCommandes = new Label("Liste des commandes");
        lblListeCommandes.setTextFill(Color.web("#FFFFFF"));
        lblListeCommandes.setStyle("-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Arial;");
        // Ajout d'une marge par rapport au bord du rectangle
        lblListeCommandes.setPadding(new Insets(10, 0, 0, 10));

        // Créer un rectangle avec des coins arrondis en haut de chaque colonne
        Rectangle rect1 = new Rectangle();
        // Définir la largeur et la hauteur du rectangle à 5% de la largeur et de la hauteur de la scène
        rect1.widthProperty().bind(scene.widthProperty().multiply(0.175));
        rect1.heightProperty().bind(scene.heightProperty().multiply(0.06));
        // Choisir la position de départ du rectangle sur l'axe Y légèrement au dessus de la ligne
        rect1.yProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect1.heightProperty()));
        // Positionner le rectangle à la moitié de la première colonne sur l'axe X
        rect1.xProperty().bind(scene.widthProperty().divide(6).subtract(rect1.widthProperty().divide(2)));
        rect1.setFill(Color.web("#343435"));  // Couleur de fond du rectangle
        rect1.setArcWidth(10);  // Arrondir les coins du rectangle
        rect1.setArcHeight(10);  // Arrondir les coins du rectangle
        // Ajoute un texte au rectangle "À préparer"
        Label lblAPreparer = new Label("À préparer");
        lblAPreparer.setTextFill(Color.web("#FFFFFF"));
        lblAPreparer.setStyle("-fx-font-size: 12px;" +
                "-fx-font-family: Arial;");

        // Ajout d'une image à gauche du texte
        Image image = new Image(getClass().getResource("/Ressources/icon_apreparer.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(20);
        lblAPreparer.setGraphic(imageView);
        lblAPreparer.setContentDisplay(ContentDisplay.LEFT);


        // Créer un StackPane pour le rectangle et le label
        StackPane spApreparer = new StackPane();

        // Ajouter le rectangle et le label au StackPane
        spApreparer.getChildren().addAll(rect1, lblAPreparer);

        // Positionner le StackPane à la moitié de la première colonne sur l'axe X
        spApreparer.layoutXProperty().bind(scene.widthProperty().divide(6).subtract(rect1.widthProperty().divide(2)));

        // Choisir la position de départ du StackPane sur l'axe Y légèrement au dessus de la ligne
        spApreparer.layoutYProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect1.heightProperty()));


        // Faire la même chose pour la deuxième colonne
        Rectangle rect2 = new Rectangle();
        rect2.widthProperty().bind(scene.widthProperty().multiply(0.175));
        rect2.heightProperty().bind(scene.heightProperty().multiply(0.06));
        rect2.yProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect2.heightProperty()));
        rect2.xProperty().bind(scene.widthProperty().divide(2).subtract(rect2.widthProperty().divide(2)));
        rect2.setFill(Color.web("#343435"));
        rect2.setArcWidth(10);
        rect2.setArcHeight(10);

        // Ajoute un texte au rectangle "En préparation"
        Label lblEnprepa = new Label("En préparation");
        lblEnprepa.setTextFill(Color.web("#FFFFFF"));
        lblEnprepa.setStyle("-fx-font-size: 12px;" +
                "-fx-font-family: Arial;");

        // Ajout d'une image à gauche du texte
        Image image2 = new Image(getClass().getResource("/Ressources/icon_enpreparation.png").toExternalForm());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(20);
        imageView2.setFitHeight(20);
        lblEnprepa.setGraphic(imageView2);
        lblEnprepa.setContentDisplay(ContentDisplay.LEFT);

        // Créer un StackPane pour le rectangle et le label
        StackPane spEnpreparation = new StackPane();

        // Ajouter le rectangle et le label au StackPane
        spEnpreparation.getChildren().addAll(rect2, lblEnprepa);

        // Positionner le StackPane à la moitié de la deuxième colonne sur l'axe X
        spEnpreparation.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(rect2.widthProperty().divide(2)));

        // Choisir la position de départ du StackPane sur l'axe Y légèrement au dessus de la ligne
        spEnpreparation.layoutYProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect2.heightProperty()));

        // Faire la même chose pour la troisième colonne
        Rectangle rect3 = new Rectangle();
        rect3.widthProperty().bind(scene.widthProperty().multiply(0.175));
        rect3.heightProperty().bind(scene.heightProperty().multiply(0.06));
        rect3.yProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect3.heightProperty()));
        rect3.xProperty().bind(scene.widthProperty().divide(6).multiply(5).subtract(rect3.widthProperty().divide(2)));
        rect3.setFill(Color.web("#343435"));
        rect3.setArcWidth(10);
        rect3.setArcHeight(10);

        // Ajoute un texte au rectangle "Prête(s)"
        Label lblPrete = new Label("Prête(s)");
        lblPrete.setTextFill(Color.web("#FFFFFF"));
        lblPrete.setStyle("-fx-font-size: 12px;" +
                "-fx-font-family: Arial;");

        // Ajout d'une image à gauche du texte
        Image image3 = new Image(getClass().getResource("/Ressources/icon_prête.png").toExternalForm());
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitWidth(20);
        imageView3.setFitHeight(20);
        lblPrete.setGraphic(imageView3);
        lblPrete.setContentDisplay(ContentDisplay.LEFT);

        // Créer un StackPane pour le rectangle et le label
        StackPane spPrete = new StackPane();

        // Ajouter le rectangle et le label au StackPane
        spPrete.getChildren().addAll(rect3, lblPrete);

        // Positionner le StackPane à la moitié de la troisième colonne sur l'axe X
        spPrete.layoutXProperty().bind(scene.widthProperty().divide(6).multiply(5).subtract(rect3.widthProperty().divide(2)));

        // Choisir la position de départ du StackPane sur l'axe Y légèrement au dessus de la ligne
        spPrete.layoutYProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect3.heightProperty()));

        root.getChildren().addAll(line1, line2, lblListeCommandes, spApreparer, spEnpreparation, spPrete);
        // Afficher la fenêtre
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
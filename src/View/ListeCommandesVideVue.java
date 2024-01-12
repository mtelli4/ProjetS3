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

    protected StackPane spApreparer; // Déclarez spApreparer comme un champ

    // ...

    public StackPane getSpApreparer() {
        return spApreparer;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMaximized(true);
        // Création d'une mise en page (layout) pour contenir les éléments de la fenêtre
        Pane root = new Pane();

        // Création de la scène avec une taille spécifique et une couleur de fond définie par un code couleur
        Scene scene = new Scene(root, 1000, 600);
        scene.setFill(Color.web("#1F1F1F")); // Définition de la couleur de fond de la scène avec le code couleur #1F1F1F

        // Définir la couleur de fond du StackPane à #1F1F1F
        root.setBackground(new Background(new BackgroundFill(Color.web("#1F1F1F"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Création de 2 lignes noires verticales parallèles qui divisent l'écran en 3 colonnes

        // Création de la ligne noire
        Line line1 = new Line();
        line1.startXProperty().bind(scene.widthProperty().divide(2));  // Position de départ X
        line1.startYProperty().bind(scene.heightProperty().multiply(0.12));  // Position de départ Y
        line1.endXProperty().bind(scene.widthProperty().divide(2));  // Position de fin X
        line1.endYProperty().bind(scene.heightProperty().multiply(0.95));  // Position de fin Y
        line1.setStroke(Color.BLACK);  // Couleur de la ligne

        // Ecris le texte "Liste des commandes" en Arial bold en haut à gauche de ma fenêtre en laissant un peu d'espace par rapport aux bordures de la fenêtre
        Label lblListeCommandes = new Label("Liste des commandes");
        lblListeCommandes.setTextFill(Color.web("#FFFFFF"));
        lblListeCommandes.setStyle("-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Arial;");
        // Ajout d'une marge par rapport au bord du rectangle
        lblListeCommandes.setPadding(new Insets(10, 0, 0, 10));

        //---------------------------------------------------------------\\
        //              CREATION DU RECTANGLE 1 : "A PREPARER"           \\
        //---------------------------------------------------------------\\
        // Créer un rectangle avec des coins arrondis en haut de chaque colonne
        Rectangle rect1 = new Rectangle();
        // Définir la largeur et la hauteur du rectangle à 5% de la largeur et de la hauteur de la scène
        rect1.widthProperty().bind(scene.widthProperty().multiply(0.175));
        rect1.heightProperty().bind(scene.heightProperty().multiply(0.06));
        // Choisir la position de départ du rectangle sur l'axe Y légèrement au dessus de la ligne
        rect1.yProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect1.heightProperty()));
        // Positionner le rectangle à la moitié de la première colonne sur l'axe des X
        rect1.xProperty().bind(scene.widthProperty().divide(2).subtract(rect1.widthProperty().divide(2)));
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
        spApreparer = new StackPane();


        // Ajouter le rectangle et le label au StackPane
        spApreparer.getChildren().addAll(rect1, lblAPreparer);

        // Positionner le StackPane à la moitié de la première colonne sur l'axe X
        // Positionner le StackPane au centre de la première colonne sur l'axe X
        spApreparer.layoutXProperty().bind(scene.widthProperty().divide(4).subtract(spApreparer.widthProperty().divide(2)));
        // Choisir la position de départ du StackPane sur l'axe Y légèrement au dessus de la ligne
        spApreparer.layoutYProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect1.heightProperty()));

        //---------------------------------------------------------------\\
        //               CREATION DU RECTANGLE 3 : "PRÊTE(S)"            \\
        //---------------------------------------------------------------\\
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

        // Positionner le StackPane à la moitié de la deuxième colonne sur l'axe X
// Positionner le StackPane au centre de la deuxième colonne sur l'axe X
        spPrete.layoutXProperty().bind(scene.widthProperty().divide(4).multiply(3).subtract(spPrete.widthProperty().divide(2)));
        // Choisir la position de départ du StackPane sur l'axe Y légèrement au dessus de la ligne
        spPrete.layoutYProperty().bind(scene.heightProperty().multiply(0.12).subtract(rect3.heightProperty()));

        root.getChildren().addAll(line1, lblListeCommandes, spApreparer, spPrete);
        // Afficher la fenêtre
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class MaFenetrePrincipale extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création d'une mise en page (layout) pour contenir les éléments de la fenêtre
        StackPane root = new StackPane();

        // Création de la scène avec une taille spécifique et une couleur de fond définie par un code couleur
        Scene scene = new Scene(root, 400, 300);
        scene.setFill(Color.web("#1F1F1F")); // Définition de la couleur de fond de la scène avec le code couleur #1F1F1F

        // Configuration de la scène dans la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ma Fenêtre Principale"); // Titre de la fenêtre
        primaryStage.show(); // Afficher la fenêtre
    }

    public static void main(String[] args) {
        launch(args); // Méthode pour lancer l'application JavaFX
    }
}

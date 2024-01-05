package View;

import Model.Commande;
import Model.Pizza;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class ListeCommandesGeneraleVue extends ListeCommandesVideVue {
    @Override
    public void start(Stage primaryStage) {
        // Appeler la méthode start de la classe parente
        super.start(primaryStage);

        // Récupérer le root de la scène
        Pane root = (Pane) primaryStage.getScene().getRoot();


    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
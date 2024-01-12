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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import java.util.*;
import java.util.stream.Collectors;
import Model.Pizza;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.layout.StackPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Separator;
import Controller.*;
public class ListeCommandesGeneraleVue extends ListeCommandesVideVue {
    private Stage primaryStage; // Ajouter un champ de classe pour le Stage
    private CommandeControle controle; // Ajouter un champ de classe pour le contrôleur

    private Map<String, Button> readyPizzas = new HashMap<>(); // Ajouter un champ pour stocker les pizzas prêtes

    public Node createPizzaNode (Pizza pizza, Button moveButton) {
        moveButton.setText(pizza.getNom());
        moveButton.setStyle("-fx-text-fill: white;");
        moveButton.setOnAction(event -> {
            pizza.setPret(true);
            Button newButton = new Button(pizza.getNom());
            newButton.setStyle("-fx-background-color: linear-gradient(#47BB59, #9AFF9E); " +
                    "-fx-background-radius: 5px; " +
                    "-fx-font-size: 20px; " +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;");
            newButton.prefWidthProperty().bind(primaryStage.getScene().widthProperty().multiply(0.20));
            newButton.prefHeightProperty().bind(primaryStage.getScene().heightProperty().multiply(0.10));

            Button readyPizzaButton = readyPizzas.get(pizza.getNom());
            if (readyPizzaButton == null) {
                readyPizzaButton = new Button("1x " + pizza.getNom());
                readyPizzaButton.setStyle("-fx-background-color: linear-gradient(#47BB59, #9AFF9E); " +
                        "-fx-background-radius: 5px; " +
                        "-fx-font-size: 20px; " +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;");
                readyPizzaButton.prefWidthProperty().bind(primaryStage.getScene().widthProperty().multiply(0.20));
                readyPizzaButton.prefHeightProperty().bind(primaryStage.getScene().heightProperty().multiply(0.10));
                readyPizzas.put(pizza.getNom(), readyPizzaButton);
            } else {
                String buttonText = readyPizzaButton.getText();
                int pizzaCount = Integer.parseInt(buttonText.split("x ")[0]);
                readyPizzaButton.setText((pizzaCount + 1) + "x " + pizza.getNom());
            }

            GridPane readyPizzaGrid = new GridPane();
            readyPizzaGrid.setHgap(70);
            readyPizzaGrid.setVgap(70);
            readyPizzaGrid.layoutYProperty().bind(getSpApreparer().layoutYProperty().add(primaryStage.getScene().heightProperty().multiply(0.15)));
            readyPizzaGrid.layoutXProperty().bind(primaryStage.getScene().widthProperty().multiply(0.52)); // Position à droite de la ligne noire

            int j = 0;
            // Parcours la liste des pizzas prêtes et ajoute un bouton pour chaque pizza
            for (Map.Entry<String, Button> entry : readyPizzas.entrySet()) {
                Button button = entry.getValue();
                StackPane stackPane = new StackPane(button);
                readyPizzaGrid.add(stackPane, j % 2, j / 2);
                j++;
            }

            // Ajouter la grille à la fenêtre ListeCommandesGeneraleVue
            ((Pane) primaryStage.getScene().getRoot()).getChildren().add(readyPizzaGrid);
        });
        return moveButton;
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
        this.primaryStage = primaryStage;
        this.controle = new CommandeControle();

        super.start(primaryStage);
        primaryStage.setMaximized(true);
        // Changer le titre de la fenêtre
        primaryStage.setTitle("Liste des commandes");
        Pane root = (Pane) primaryStage.getScene().getRoot();

        List<Pizza> pizzas = getPizzas();

        Map<String, Integer> pizzaCount = new HashMap<>();
        for (Pizza pizza : pizzas) {
            pizzaCount.put(pizza.getNom(), pizzaCount.getOrDefault(pizza.getNom(), 0) + 1);
        }

        GridPane pizzaGrid = new GridPane();
        pizzaGrid.setHgap(70);
        pizzaGrid.setVgap(70);
        pizzaGrid.layoutYProperty().bind(getSpApreparer().layoutYProperty().add(primaryStage.getScene().heightProperty().multiply(0.15)));
        pizzaGrid.layoutXProperty().bind(primaryStage.getScene().widthProperty().multiply(0.02));

        int i = 0;
        for (Map.Entry<String, Integer> entry : pizzaCount.entrySet()) {
            String labelText = entry.getValue() + "x " + entry.getKey();
            Label label = new Label(labelText);
            label.setStyle("-fx-text-fill: transparent;");

            Button button = new Button(labelText);
            // Mettre la couleur des boutons en linear de #E86666 à #FF9A9A
            button.setStyle("-fx-background-color: linear-gradient(#E86666, #FF9A9A); " +
                    "-fx-background-radius: 5px; " +
                    "-fx-font-size: 20px; " +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;"); // Ajout de cette ligne pour rendre le texte en gras
            button.prefWidthProperty().bind(primaryStage.getScene().widthProperty().multiply(0.20));
            button.prefHeightProperty().bind(primaryStage.getScene().heightProperty().multiply(0.10));
            button.setOnAction(event -> {
                // 1. Récupérez toutes les pizzas du même type que le bouton cliqué
                String pizzaName = button.getText().split("x ")[1];
                List<Pizza> sameTypePizzas = pizzas.stream()
                        .filter(pizza -> pizza.getNom().equals(pizzaName))
                        .collect(Collectors.toList());

                // Imprimez la taille de la liste sameTypePizzas pour vérifier si elle est vide ou non
                System.out.println(sameTypePizzas.size());
                // 2. Créez une nouvelle VBox pour contenir les informations des pizzas
                VBox vbox = new VBox();
                vbox.setSpacing(10); // Espacement entre les éléments

                // Définissez la couleur de fond de la VBox à #1F1F1F
                vbox.setBackground(new Background(new BackgroundFill(Color.web("#1F1F1F"), CornerRadii.EMPTY, Insets.EMPTY)));

                // 3. Pour chaque pizza, créez un nouveau Label avec les informations de la pizza et un bouton "Déplacer vers Prête(s)"
                for (Pizza pizza : sameTypePizzas) {


                    StringBuilder pizzaInfoBuilder = new StringBuilder();
                    pizzaInfoBuilder.append("Nom: ").append(pizza.getNom()).append("\n");
                    pizzaInfoBuilder.append("Type: ").append(pizza.getType()).append("\n");
                    pizzaInfoBuilder.append("Temps de fabrication: ").append(pizza.getTempsFab()).append("\n");
                    pizzaInfoBuilder.append("Ingrédients: ");
                    for (Ingredient ingredient : pizza.getIngredients()) {
                        pizzaInfoBuilder.append(ingredient.getNom_Ing()).append(", ");
                    }
                    // Remove the last comma and space
                    pizzaInfoBuilder.setLength(pizzaInfoBuilder.length() - 2);

                    Label pizzaInfo = new Label(pizzaInfoBuilder.toString());
                    pizzaInfo.setStyle("-fx-text-fill: white;");
                    if (sameTypePizzas.indexOf(pizza) == 0) { // If it's the first pizza
                        pizzaInfo.setPadding(new Insets(primaryStage.getScene().getHeight() * 0.02, 0, 0, primaryStage.getScene().getWidth() * 0.02));
                    }
                    else {
                        pizzaInfo.setPadding(new Insets(0, 0, 0, primaryStage.getScene().getWidth() * 0.02));
                    }
                    Button moveButton = new Button("Déplacer vers Prête(s)");
                    MoveButtonController controller = new MoveButtonController(moveButton, pizza, label, controle, primaryStage, this);                    moveButton.setMinWidth(primaryStage.getScene().getWidth() * 0.2); // Définit la largeur minimale du bouton à 6% de la largeur de la scène
                    moveButton.setMaxWidth(primaryStage.getScene().getWidth() * 0.2); // Définit la largeur maximale du bouton à 6% de la largeur de la scène
                    moveButton.setMinHeight(primaryStage.getScene().getHeight() * 0.01); // Définit la hauteur minimale du bouton à 3% de la hauteur de la scène
                    moveButton.setMaxHeight(primaryStage.getScene().getHeight() * 0.01); // Définit la hauteur maximale du bouton à 3% de la hauteur de la scène
                    // Mettre la couleur des boutons en linear de #47BB59 à #9AFF9E
                    moveButton.setStyle("-fx-background-color: linear-gradient(#47BB59, #9AFF9E); " +
                            "-fx-background-radius: 5px; " +
                            "-fx-font-size: 13px; " +
                            "-fx-text-fill: white;" +
                            "-fx-font-weight: bold;"); // Ajout de cette ligne pour rendre le texte en gras


                    AnchorPane anchorPane = new AnchorPane();

                    if (sameTypePizzas.indexOf(pizza) == 0) { // If it's the first button
                        AnchorPane.setTopAnchor(moveButton, primaryStage.getScene().getHeight() * 0.02);
                        AnchorPane.setRightAnchor(moveButton, primaryStage.getScene().getWidth() * 0.02);
                    } else {
                        AnchorPane.setRightAnchor(moveButton, primaryStage.getScene().getWidth() * 0.02);
                    }

                    anchorPane.getChildren().addAll(pizzaInfo, moveButton);

                    // Centrer le bouton dans l'AnchorPane sur l'axe des Y
                    AnchorPane.setTopAnchor(moveButton, (anchorPane.getHeight() - moveButton.getHeight()) / 2);
                    AnchorPane.setBottomAnchor(moveButton, (anchorPane.getHeight() - moveButton.getHeight()) / 2);

                    vbox.getChildren().add(anchorPane);

                    // Ajouter une ligne personnalisée après chaque VBox pour séparer les pizzas par des traits horizontaux noirs
                    Line separator = new Line();
                    separator.setStartX(0);
                    separator.endXProperty().bind(vbox.widthProperty());
                    separator.setStroke(Color.BLACK);
                    vbox.getChildren().add(separator);
                }

                // 5. Créez une nouvelle Stage et définissez son contenu sur la VBox
                Stage popupStage = new Stage();
                popupStage.setTitle("Informations détaillées sur les pizzas " + pizzaName + " commandées");

// Définir la taille minimale et maximale du popup
                popupStage.setMinWidth(1024); // Remplacez 500 par la largeur minimale que vous souhaitez
                popupStage.setMaxWidth(1024); // Remplacez 500 par la largeur maximale que vous souhaitez
                popupStage.setMinHeight(576); // Remplacez 300 par la hauteur minimale que vous souhaitez
                popupStage.setMaxHeight(576); // Remplacez 300 par la hauteur maximale que vous souhaitez

// Créez une nouvelle Scene avec la VBox et définissez sa couleur de fond à #1F1F1F
                Scene scene = new Scene(vbox, 500, 300); // Remplacez 500 et 300 par la largeur et la hauteur que vous souhaitez pour la scène

// Définissez la Scene du Stage sur la nouvelle Scene
                popupStage.setScene(scene);

// 6. Affichez le popup
                popupStage.show();
            });

            StackPane stackPane = new StackPane(button, label);

            pizzaGrid.add(stackPane, i % 2, i / 2);
            i++;
        }

        root.getChildren().add(pizzaGrid);
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
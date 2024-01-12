package Controller;

import Model.Commande;
import Model.Pizza; // Assurez-vous que cette importation est correcte
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import java.util.List;
import java.util.stream.Collectors;
import View.*;
import Model.Pizza;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Model.*;
public class CommandeControle {
    public void setMoveButtonAction(Button moveButton, Pizza pizza, Label label, Stage primaryStage) {
        moveButton.setOnAction(event -> {
            pizza.setPret(true);
            movePizzaToReady(label, primaryStage.getScene().getWidth() * 0.55, primaryStage.getScene().getHeight() * 0.14);

            CommandeDAO commandeDAO = new CommandeDAO();

            // Mettre à jour le stock pour chaque ingrédient de la pizza
            for (Ingredient ingredient : pizza.getIngredients()) {
                int neededQuantity = commandeDAO.getNeededQuantity(pizza.getId(), ingredient.getId_Ing());
                int currentQuantity = commandeDAO.getCurrentQuantity(ingredient.getId_Ing());
                int newQuantity = currentQuantity - neededQuantity;
                commandeDAO.updateStock(ingredient.getId_Ing(), newQuantity);
                commandeDAO.close();
            }
        });
    }

    public void movePizzaToReady(Node pizzaNode, double destinationX, double destinationY) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(pizzaNode);
        transition.setToX(destinationX);
        transition.setToY(destinationY);
        transition.setDuration(Duration.seconds(1));
        transition.play();
    }
}
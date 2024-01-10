package Controller;

import Model.Commande;
import Model.Pizza; // Assurez-vous que cette importation est correcte
import javafx.geometry.Insets;
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

public class CommandeControle {
    public Pane creerCommandeVue(Commande commande) {
        String numeroCommande = String.valueOf(commande.getId());
        String typeCommande = commande.getMode_paiement();
        List<String> nomsPizza = commande.getCommander().stream().map(Pizza::getNom).collect(Collectors.toList());

        Label labelNumeroCommande = new Label("Numéro de commande : " + numeroCommande);
        Label labelTypeCommande = new Label("Type de commande : " + typeCommande);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(labelNumeroCommande, labelTypeCommande);

        ScrollPane scrollPane = new ScrollPane();
        VBox pizzaBox = new VBox();
        for (int i = 0; i < nomsPizza.size(); i++) {
            if (i < 2) {
                pizzaBox.getChildren().add(new Text(nomsPizza.get(i)));
            } else {
                pizzaBox.getChildren().add(new Text("..."));
                break;
            }
        }
        scrollPane.setContent(pizzaBox);
        vbox.getChildren().add(scrollPane);

        // Style the VBox
        vbox.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(15), Insets.EMPTY)));
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(15), BorderWidths.DEFAULT)));

        GridPane gridPane = new GridPane();
        gridPane.add(vbox, 0, 0);

        return gridPane;
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
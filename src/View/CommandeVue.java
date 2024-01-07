package View;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class CommandeVue extends GridPane {
    public CommandeVue(String numeroCommande, String typeCommande, List<String> nomsPizza, int column) {
        Label labelNumeroCommande = new Label("Numéro de commande : " + numeroCommande);
        Label labelTypeCommande = new Label("Type de commande : " + typeCommande);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(labelNumeroCommande, labelTypeCommande);

        ScrollPane scrollPane = new ScrollPane();
        VBox pizzaBox = new VBox();
        for (String nomPizza : nomsPizza) {
            pizzaBox.getChildren().add(new Text(nomPizza));
        }
        scrollPane.setContent(pizzaBox);

        vbox.getChildren().add(scrollPane);
        this.add(vbox, column, 0);
    }
}
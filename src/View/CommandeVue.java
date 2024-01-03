package View;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CommandeVue extends Pane {
    public CommandeVue(String numeroCommande, String typeCommande, String nomPizza) {
        Label labelNumeroCommande = new Label("Numéro de commande : " + numeroCommande);
        Label labelTypeCommande = new Label("Type de commande : " + typeCommande);
        Label labelNomPizza = new Label("Nom de la pizza : " + nomPizza);

        this.getChildren().addAll(labelNumeroCommande, labelTypeCommande, labelNomPizza);
    }
}
package Controller;

import Model.Pizza;
import View.ListeCommandesGeneraleVue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.function.Consumer;


public class MoveButtonController {
    private Button moveButton;
    private Pizza pizza;
    private Label label;
    private CommandeControle controle;
    private Stage primaryStage;
    private ListeCommandesGeneraleVue listeCommandesGeneraleVue;

    public MoveButtonController(Button moveButton, Pizza pizza, Label label, CommandeControle controle, Stage primaryStage, ListeCommandesGeneraleVue listeCommandesGeneraleVue) {
        this.moveButton = moveButton;
        this.pizza = pizza;
        this.label = label;
        this.controle = controle;
        this.primaryStage = primaryStage;
        this.listeCommandesGeneraleVue = listeCommandesGeneraleVue;
        setMoveButtonActionWithPizza(moveButton, pizza);
    }

    private void setMoveButtonAction() {
        controle.setMoveButtonAction(moveButton, pizza, label, primaryStage);
    }
    private Consumer<Pizza> createPizzaNodeCallback;

    public void setMoveButtonActionWithPizza(Button moveButton, Pizza pizza) {
            moveButton.setOnAction(event -> {
        listeCommandesGeneraleVue.createPizzaNode(pizza, moveButton);            });
    }
}
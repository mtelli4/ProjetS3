package Model;

import java.util.ArrayList;

public class Ingredient {
    private String nom;
    private int quantite_use;
    private Stock STK_ig;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite_use() {
        return quantite_use;
    }

    public void setQuantite_use(int quantite_use) {
        this.quantite_use = quantite_use;
    }

    public Stock getSTK_ig() {
        return STK_ig;
    }

    public void setSTK_ig(Stock sTK_ig) {
        STK_ig = sTK_ig;
    }

    public Ingredient(int qt,String nm,Stock ig) {
        quantite_use=qt;
        nom=nm;
        STK_ig=ig;

    }



}

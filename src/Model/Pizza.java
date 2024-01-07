package Model;

import java.util.ArrayList;

public class Pizza {
    private int id;
    private String nom;
    private ArrayList<Ingredient> ingredients;
    private double prix;
    private int tempsFab;
    private String type;
    private boolean pret;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNom() {
        return nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }



    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }



    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }



    public double getPrix() {
        return prix;
    }



    public void setPrix(double prix) {
        this.prix = prix;
    }



    public int getTempsFab() {
        return tempsFab;
    }



    public void setTempsFab(int tempsFab) {
        this.tempsFab = tempsFab;
    }



    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
    }



    public boolean isPret() {
        return pret;
    }



    public void setPret(boolean pret) {
        this.pret = pret;
    }



    public Pizza(int ids,String nm,double px, int tmpFb,String tp,boolean pr,ArrayList<Ingredient> ig) {
        id=ids;
        nom=nm;
        prix=px;
        tempsFab=tmpFb;
        type=tp;
        pret=pr;
        ingredients=ig;

    }



}

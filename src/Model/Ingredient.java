package Model;

public class Ingredient {
    private int id_Ing;
    private String nom_Ing;
    private int quantite_use;
    private Stock STK_ig;

    public int getId_Ing() {
        return id_Ing;
    }
    public String getNom_Ing() {
        return nom_Ing;
    }

    public void setNom_Ing(String nom_Ing) {
        this.nom_Ing = nom_Ing;
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
        nom_Ing =nm;
        STK_ig=ig;

    }



}
